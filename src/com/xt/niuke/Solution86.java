package com.xt.niuke;


import com.xt.model.TreeNode;

/**
 * 在二叉树中找到两个节点的最近公共祖先
 * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
 * <p>
 * 数据范围：1≤n≤1000，树上每个节点的val满足 0<val≤100
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 * <p>
 * 注：本题保证二叉树中每个节点的val值均不相同。
 * <p>
 * 如当输入[3,5,1,6,2,0,8,#,#,7,4,#,#,#,10],2,10时，二叉树{3,5,1,6,2,0,8,#,#,7,4}如下图所示：
 * <p>
 * 所以节点值为5和节点值为1的节点的最近公共祖先节点的节点值为3，所以对应的输出为3。
 * done
 */
public class Solution86 {

    /**
     * @param root TreeNode类
     * @param o1   int整型
     * @param o2   int整型
     * @return int整型
     */
    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        // write code here
        TreeNode node = findNode(root, o1, o2);

        return node.val;
    }

    public TreeNode findNode(TreeNode root, int o1, int o2) {
        if (root.val == o1 || root.val == o2) {
            return root;
        }
        TreeNode node1 = null;
        TreeNode node2 = null;
        if (root.left != null) {
            node1 = findNode(root.left, o1, o2);
        }
        if (root.right != null) {
            node2 = findNode(root.right, o1, o2);
        }
        if (node1 != null && node2 != null) {
            return root;
        }
        if (node1 != null) {
            return node1;
        }
        if (node2 != null) {
            return node2;
        }
        return null;
    }


}
