package com.xt.model;

public class TreeNode {
    public String name = "";
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, String name) {
        this.name = name;
        this.val = val;
    }

}