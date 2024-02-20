package com.CrumpleAPI.crumpleapi.api.model;

import com.CrumpleAPI.helpers.Pair;

public class CrumpleTree {

    public TreeNode root = null;
    private TreeNode deleteNode = null;
    private int sze = 0;

    public CrumpleTree() {

    }

    private Pair getShape(TreeNode root) {
        int leftLevel = root.level;
        if (root.left != null) {
            leftLevel -= root.left.level;
        }
        int rightLevel = root.level;
        if (root.right != null) {
            rightLevel -= root.right.level;
        }

        return new Pair(leftLevel, rightLevel);

    }

    public int size() {
        return sze;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean contains(int key) {
        TreeNode curr = root;
        if (empty()) {
            return false;
        }
        while (curr != null) {
            if (key == curr.key) {
                return true;
            }else if (key < curr.key) {
                curr = curr.left;
            } else if (curr.key < key) {
                curr = curr.right;
            }
        }
        return false;
    }

    public int level(int key) {
        TreeNode curr = root;
        if (empty()) {
            throw new RuntimeException("level(): Element Not Found");
        }
        while (curr != null) {
            if (key == curr.key) {
                return curr.level;
            }else if (key < curr.key) {
                curr = curr.left;
            } else if (curr.key < key) {
                curr = curr.right;
            }
        }
        throw new RuntimeException("level(): Element Not Found");
    }

    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            root.level = 1;
            sze++;
            return;
        }

        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null) {
            //std::cout << curr.key << ' ' << key << ' ' << curr.isNull << std::endl;
            if (key == curr.key) {
                return;
            } else if (key < curr.key) {
                prev = curr;
                curr = curr.left;
            } else if (curr.key < key) {
                prev = curr;
                curr = curr.right;
            }
        }

        if (key < prev.key) {
            prev.left = new TreeNode(key);
            curr = prev.left;
            curr.level = 1;
            curr.parent = prev;
        } else if (prev.key < key) {
            prev.right = new TreeNode(key);
            curr = prev.right;
            curr.level = 1;
            curr.parent = prev;
        }


        sze++;

        curr = curr.parent;

        while (curr != null) {
            Pair shape = getShape(curr);
            // CASE 1 (Trivial)
            if (shape.first == 1 && shape.second == 1) {

            }
            // CASE 2
            else if (shape.first == 0 && shape.second == 1) {
                curr.level++;
            } else if (shape.first == 1 && shape.second == 0) {
                curr.level++;
            }
            // CASE 3, 4, 5
            else if (shape.first == 0 && shape.second == 2) {

                //if (curr.key < curr.parent.key) {

                //}

                Pair leftShape = getShape(curr.left);
                // Case 3
                if (leftShape.first == 1 && leftShape.second == 2) {
                    TreeNode f = curr;
                    TreeNode b = f.left;
                    TreeNode d = b.right;
                    f.level--;
                    b.parent = f.parent;
                    f.parent = b;
                    f.left = d;
                    if (d != null) {
                        d.parent = f;
                    }
                    b.right = f;
                    curr = b;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 4
                else if (leftShape.first == 1 && leftShape.second == 1) {
                    TreeNode f = curr;
                    TreeNode b = f.left;
                    TreeNode d = b.right;
                    b.level++;
                    b.parent = f.parent;
                    f.parent = b;
                    f.left = d;
                    if (d != null) {
                        d.parent = f;
                    }
                    b.right = f;
                    curr = b;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 5
                else if (leftShape.first == 2 && leftShape.second == 1) {
                    TreeNode f = curr;
                    TreeNode b = f.left;
                    TreeNode d = b.right;
                    TreeNode x = d.left;
                    TreeNode y = d.right;

                    d.level++;
                    b.level--;
                    f.level--;

                    d.parent = f.parent;
                    b.right = x;
                    f.left = y;
                    if (x != null) {
                        x.parent = b;
                    }
                    if (y != null) {
                        y.parent = f;
                    }
                    d.left = b;
                    d.right = f;
                    b.parent = d;
                    f.parent = d;
                    curr = d;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }

                }
            } else if (shape.first == 2 && shape.second == 0) {
                Pair rightShape = getShape(curr.right);
                // Case 3
                if (rightShape.first == 2 && rightShape.second == 1) {
                    TreeNode b = curr;
                    TreeNode f = b.right;
                    TreeNode d = f.left;
                    b.level--;
                    f.parent = b.parent;
                    b.parent = f;
                    b.right = d;
                    if (d != null) {
                        d.parent = b;
                    }
                    f.left = b;
                    curr = f;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 4
                else if (rightShape.first == 1 && rightShape.second == 1) {
                    TreeNode b = curr;
                    TreeNode f = b.right;
                    TreeNode d = f.left;
                    f.level++;
                    f.parent = b.parent;
                    b.parent = f;
                    b.right = d;
                    if (d != null) {
                        d.parent = b;
                    }
                    f.left = b;
                    curr = f;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 5
                else if (rightShape.first == 1 && rightShape.second == 2) {
                    TreeNode b = curr;
                    TreeNode f = b.right;
                    TreeNode d = f.left;
                    TreeNode x = d.left;
                    TreeNode y = d.right;

                    d.level++;
                    b.level--;
                    f.level--;

                    d.parent = b.parent;
                    b.right = x;
                    f.left = y;
                    if (x != null) {
                        x.parent = b;
                    }
                    if (y != null) {
                        y.parent = f;
                    }
                    d.left = b;
                    d.right = f;
                    b.parent = d;
                    f.parent = d;
                    curr = d;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
            }
            if (curr.parent == null) {
                break;
            }
            curr = curr.parent;
        }
        root = curr;

    }

    private TreeNode removeHelper(TreeNode rootNode, int key) {
        if (rootNode == null) {
            return rootNode;
        }

        if (rootNode.key < key) {
            rootNode.right = removeHelper(rootNode.right, key);
            //if (tempptr1 != null && root.right != tempptr) {
            //delete tempptr1;
            //tempptr1 = null;
            //}
        } else if (key < rootNode.key) {
            rootNode.left = removeHelper(rootNode.left, key);
            //if (tempptr2 != null && root.left != tempptr) {
            //delete tempptr2;
            //tempptr2 = null;
            //}
        } else {
            if (rootNode.left == null && rootNode.right == null) {
                if (rootNode.parent != null) {
                    //rootNode.right.parent = rootNode.parent;
                    //rootNode.parent.right = rootNode.right;
                    deleteNode = rootNode.parent;
                    //if (rootNode.key < rootNode.parent.key) {
                    //rootNode.parent.left = null;
                    //} else if (rootNode.parent.key < rootNode.key) {
                    //rootNode.parent.right = null;
                    //}
                }
                return null;
            }
            else if (rootNode.right != null) {
                TreeNode curr = rootNode.right;
                while (curr.left != null) {
                    curr = curr.left;
                }
                rootNode.key = curr.key;
                rootNode.right = removeHelper(rootNode.right, rootNode.key);

            } else if (rootNode.left != null) {
                TreeNode curr = rootNode.left;
                while (curr.right != null) {
                    curr = curr.right;
                }
                rootNode.key = curr.key;
                rootNode.left = removeHelper(rootNode.left, rootNode.key);

            }

            //if (tempptr != null && rootNode.right != tempptr) {
            //delete tempptr;
            //tempptr = null;
            //}
        }
        return rootNode;
    }

    public void remove(int key) {
        // TODO: Implement this
        if (!contains(key)) {
            throw new RuntimeException("remove(): Element Not Found");
        }
        if (size() == 1) {
            root = null;
            sze--;
            return;
        }
        root = removeHelper(root, key);
        sze--;

        TreeNode curr = deleteNode;


        while (curr != null) {
            Pair shape = getShape(curr);

            // Case 1B Left & Right
            if (shape.first == 2 && shape.second == 2 && curr.left == null && curr.right == null) {
                curr.level--;
            }
            // Case 2 Left
            else if (shape.first == 3 && shape.second == 2) {
                curr.level--;
            }
            // Case 2 Right
            else if (shape.first == 2 && shape.second == 3) {
                curr.level--;
            }

            // Case 3,4a,4b,5,6 Left
            else if (shape.first == 3 && shape.second == 1) {
                Pair rightShape = getShape(curr.right);
                // Case 4b
                if (rightShape.first == 2 && rightShape.second == 1 && curr.left == null) {
                    TreeNode f = curr;
                    TreeNode j = f.right;
                    f.level -= 2;
                    f.right = j.left;
                    j.left = f;
                    j.parent = f.parent;
                    f.parent = j;
                    curr = j;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 3
                else if (rightShape.first == 1 && rightShape.second == 1) {
                    TreeNode f = curr;
                    TreeNode j = f.right;
                    TreeNode h = j.left;
                    f.level--;
                    j.level++;
                    f.right = j.left;
                    j.left = f;
                    j.parent = f.parent;
                    h.parent = f;
                    f.parent = j;
                    curr = j;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 4a
                else if (rightShape.first == 2 && rightShape.second == 1) {
                    TreeNode f = curr;
                    TreeNode j = f.right;
                    TreeNode h = j.left;
                    f.level--;
                    j.level++;
                    f.right = j.left;
                    j.left = f;
                    j.parent = f.parent;
                    h.parent = f;
                    f.parent = j;
                    curr = j;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 5
                else if (rightShape.first == 1 && rightShape.second == 2) {
                    TreeNode f = curr;
                    TreeNode j = f.right;
                    TreeNode h = j.left;
                    f.level -= 2;
                    h.level += 2;
                    j.level--;
                    h.parent = f.parent;
                    f.right = h.left;
                    j.left = h.right;

                    if (h.left != null) {
                        h.left.parent = f;
                    }
                    if (h.right != null) {
                        h.right.parent = j;
                    }

                    h.left = f;
                    f.parent = h;
                    h.right = j;
                    j.parent = h;
                    curr = h;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 6
                else if (rightShape.first == 2 && rightShape.second == 2) {
                    TreeNode f = curr;
                    TreeNode j = f.right;
                    f.level--;
                    j.level--;
                }
            }
            //Case 3,4a,4b,5,6 Right
            else if (shape.first == 1 && shape.second == 3) {
                Pair leftShape = getShape(curr.left);
                // Case 4b
                if (leftShape.first == 1 && leftShape.second == 2 && curr.right == null) {
                    TreeNode f = curr;
                    TreeNode b = f.left;
                    f.level -= 2;
                    f.left = b.right;
                    b.right = f;
                    b.parent = f.parent;
                    f.parent = b;
                    curr = b;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }

                }
                // Case 3
                else if (leftShape.first == 1 && leftShape.second == 1) {
                    TreeNode f = curr;
                    TreeNode b = f.left;
                    TreeNode c = b.right;
                    f.level--;
                    b.level++;
                    f.left = b.right;
                    b.right = f;
                    b.parent = f.parent;
                    c.parent = f;
                    f.parent = b;
                    curr = b;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 4a
                else if (leftShape.first == 1 && leftShape.second == 2) {
                    TreeNode f = curr;
                    TreeNode b = f.left;
                    TreeNode c = b.right;
                    f.level--;
                    b.level++;
                    f.left = b.right;
                    b.right = f;
                    b.parent = f.parent;
                    c.parent = f;
                    f.parent = b;
                    curr = b;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 5
                else if (leftShape.first == 2 && leftShape.second == 1) {
                    TreeNode f = curr;
                    TreeNode b = f.left;
                    TreeNode c = b.right;
                    f.level -= 2;
                    c.level += 2;
                    b.level--;
                    c.parent = f.parent;
                    f.left = c.right;
                    b.right = c.left;

                    if (c.left != null) {
                        c.left.parent = b;
                    }
                    if (c.right != null) {
                        c.right.parent = f;
                    }

                    c.right = f;
                    f.parent = c;
                    c.left = b;
                    b.parent = c;
                    curr = c;
                    if (curr.parent != null) {
                        if (curr.key < curr.parent.key) {
                            curr.parent.left = curr;
                        } else {
                            curr.parent.right = curr;
                        }
                    }
                }
                // Case 6
                else if (leftShape.first == 2 && leftShape.second == 2) {
                    TreeNode f = curr;
                    TreeNode b = f.left;
                    f.level--;
                    b.level--;
                }
            }

            if (curr.parent == null) {
                break;
            }
            curr = curr.parent;
        }

        root = curr;
    }

}
