package com.CrumpleAPI.scratch;

import com.CrumpleAPI.crumpleapi.api.model.CrumpleTree;
import com.CrumpleAPI.crumpleapi.api.model.TreeNode;

public class Main {

    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);

        System.out.println("----------");
        System.out.println(root.key + " " + root.level);

        inOrder(root.right);
    }

    public static void main(String[] args) {
        CrumpleTree tree = new CrumpleTree();

        tree.insert(18);
        tree.insert(19);
        tree.insert(20);
        tree.insert(21);

        inOrder(tree.root);
    }
}
