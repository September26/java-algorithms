package com.xt.niuke;


import com.xt.model.TreeNode;

/**
 * 平衡二叉树
 * 描述
 * 输入一棵节点数为 n 二叉树，判断该二叉树是否是平衡二叉树。
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 * 平衡二叉树（Balanced Binary Tree），具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 * 样例解释：
 */
public class Solution79 {

    boolean IsBalanced = true;

    public boolean IsBalanced_Solution(TreeNode root) {
        getNodeLevel(root);
        return IsBalanced;
    }


    private int getNodeLevel(TreeNode node) {
        System.out.println("1");
        if (node == null) {
            return 0;
        }
        int leftNodeLevel = getNodeLevel(node.left);
        int rightNodeLevel = getNodeLevel(node.right);
        if (Math.abs(leftNodeLevel - rightNodeLevel) > 1) {
            IsBalanced = false;
        }
        return Math.max(leftNodeLevel + 1, rightNodeLevel + 1);
    }

}
