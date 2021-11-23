package com.xt.niuke;


import com.xt.model.TreeNode;

/**
 * 二叉树中和为某一值的路径(三)
 * 描述
 * 给定一个二叉树root和一个整数值 sum ，求该树有多少路径的的节点值之和等于 sum 。
 * 1.该题路径定义不需要从根节点开始，也不需要在叶子节点结束，但是一定是从父亲节点往下到孩子节点
 * 2.总节点数目为n
 * 3.保证最后返回的路径个数在整形范围内(即路径个数小于231-1)
 * <p>
 * 数据范围:
 * 0<=n<=10000<=n<=1000
 * -10^9<=节点值<=10^9−10
 * 9
 * <=节点值<=10
 * 9
 * <p>
 * <p>
 * 假如二叉树root为{1,2,3,4,5,4,3,#,#,-1,-1,-1,-1}，sum=6，那么总共如下所示，有3条路径符合要求
 * done
 */
public class Solution84 {

    public int FindPath(TreeNode root, int sum) {
        return FindPathByRoot(root, sum, false);
    }

    /**
     * 每一个root都返回
     *
     * @param root
     * @param sum
     * @param isHaveRootValue 是否包含上一节点的值，如果包含，则只有一种情况sum=sum-root.val
     * @return
     */
    private int FindPathByRoot(TreeNode root, int sum, boolean isHaveRootValue) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val == sum) {
            count++;
        }
        if (root.left != null) {
            if (!isHaveRootValue) {
                count = count + FindPathByRoot(root.left, sum, false);//包含根节点等于sum
            }
            count = count + FindPathByRoot(root.left, sum - root.val, true);//包含根节点等于sum
        }
        if (root.right != null) {
            if (!isHaveRootValue) {
                count = count + FindPathByRoot(root.right, sum, false);//包含根节点等于sum
            }
            count = count + FindPathByRoot(root.right, sum - root.val, true);//包含根节点等于sum
        }
        return count;
    }
}
