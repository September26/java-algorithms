package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.Vector;

/**
 * 687. 最长同值路径
 * 每日一题：2022.09.02
 * 完成日期：2022.09.02
 * 链接：https://leetcode.cn/problems/longest-univalue-path/
 * 描述：
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 * <p>
 * 两个节点之间的路径长度 由它们之间的边数表示。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 *  
 * <p>
 * 提示:
 * <p>
 * 树的节点数的范围是 [0, 104] 
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过 1000 
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-univalue-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 求最长节点长度，可以分为三条线，当前节点之前的路径长度N1，左叶子节点的路径长度left，右叶子节点的路径长度right。
 * 如果当前节点和左叶子节点的值是相同的，那么就可以求出N1+left的长度。同理可以求出N1+right的长度。最后和left+right的长度做比较求最大值。
 * 如果不相同，则作为跟节点重新进行计算。
 * <p>
 * <p>
 * state:done
 */
public class Solution687 {

    int maxLength = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return maxLength;
        }
        longestUnivaluePath2(root, 0);
        return maxLength;
    }

    public int longestUnivaluePath2(TreeNode root, int parentTimes) {
        int left = 0;
        int right = 0;
        if (root.left != null) {
            if (root.left.val == root.val) {
                left = longestUnivaluePath2(root.left, parentTimes + 1) + 1;
            } else {
                longestUnivaluePath2(root.left, 0);
            }
        }
        if (root.right != null) {
            if (root.right.val == root.val) {
                right = longestUnivaluePath2(root.right, parentTimes + 1) + 1;
            } else {
                longestUnivaluePath2(root.right, 0);
            }
        }

        int childMax = Math.max(left, right);
        int max = Math.max(parentTimes + childMax, left + right);
        maxLength = Math.max(max, maxLength);
        return childMax;
    }


}