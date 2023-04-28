package com.xt.leetcode;

import com.xt.model.TreeNode;

/**
 * 1026. 节点与其祖先之间的最大差值
 * 每日一题：2023.04.18
 * 完成日期：2023.04.18
 * 链接：https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor/
 * 描述：
 * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 * <p>
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * 输出：7
 * 解释：
 * 我们有大量的节点与其祖先的差值，其中一些如下：
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * 在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,null,2,null,0,3]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数在 2 到 5000 之间。
 * 0 <= Node.val <= 105
 * <p>
 * 解题思路：
 * 动态规划的思路，每次计算时，传入之前的最大最小值，和当前值计算差值。
 * 然后更新最大最小值，继续遍历其左右节点。
 * <p>
 * <p>
 * state:done
 */
public class Solution1026 {
    int maxAbs = 0;

    public int maxAncestorDiff(TreeNode root) {
        search(root.left, root.val, root.val);
        search(root.right, root.val, root.val);
        return maxAbs;
    }


    private void search(TreeNode root, int max, int min) {
        if (root == null) {
            return;
        }
        int abs = Math.max(Math.abs(max - root.val), Math.abs(min - root.val));
        maxAbs = Math.max(abs, maxAbs);
        max = Math.max(root.val, max);
        min = Math.min(root.val, min);
        search(root.left, max, min);
        search(root.right, max, min);
    }
}