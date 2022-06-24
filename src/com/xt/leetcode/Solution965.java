package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.Vector;

/**
 * 965. 单值二叉树
 * 每日一题：2022.05.24
 * 完成日期：2022.05.24
 * 链接：https://leetcode.cn/problems/univalued-binary-tree/
 * 描述：
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * <p>
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：[2,2,2,5,2]
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99] 。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/univalued-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 递归遍历，如果不等于root的值，则返回false。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution965 {

    public boolean isUnivalTree(TreeNode root) {
        return isUnivalTree(root, root.val);
    }

    public boolean isUnivalTree(TreeNode root, Integer value) {
        if (root.val != value) {
            return false;
        }
        if (root.left != null && !isUnivalTree(root.left, value)) {
            return false;
        }
        if (root.right != null && !isUnivalTree(root.right, value)) {
            return false;
        }
        return true;
    }
}