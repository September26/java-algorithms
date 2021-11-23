package com.xt.niuke;


import com.xt.model.TreeNode;

/**
 * 描述
 * 给定一个二叉树root和一个值 sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 * <p>
 * 数据范围：
 * 1.树上的节点数满足 0 \le n \le 100000≤n≤10000
 * 2.每 个节点的值都满足 |val| \le 1000∣val∣≤1000
 * 要求：空间复杂度 O(n)O，时间复杂度 O(n)
 * 进阶：空间复杂度 O(树的高度)，时间复杂度 O(n)
 * <p>
 * 输入：
 * {5,4,8,1,11,#,9,#,#,2,7},22
 * 复制
 * 返回值：
 * true
 * <p>
 * done
 */
public class Solution82 {

    /**
     * @param root TreeNode类
     * @param sum  int整型
     * @return bool布尔型
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        boolean isLeft = false;
        boolean isRight = false;
        if (root.left != null) {
            isLeft = hasPathSum(root.left, sum - root.val);
        }
        if (root.right != null) {
            isRight = hasPathSum(root.right, sum - root.val);
        }
        return isLeft || isRight;
    }

}
