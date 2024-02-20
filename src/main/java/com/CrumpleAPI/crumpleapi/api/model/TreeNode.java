package com.CrumpleAPI.crumpleapi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TreeNode {

    public int level = 0;
    public int key;
    public TreeNode left = null;
    public TreeNode right = null;

    @JsonIgnore
    public TreeNode parent = null;

    public TreeNode(int key) {
        this.key = key;
    }

}
