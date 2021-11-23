package com.xt.niuke;


import com.xt.model.TreeNode;

/**
 * 描述
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * 示例1
 * 输入：
 * {1,2,3,4,5,#,6,#,#,7}
 * 复制
 * 返回值：
 * 4
 */
public class Solution55 {
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
