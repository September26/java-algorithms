package com.xt.leetcode;

import com.xt.model.TreeNode;

/**
 * 2331. 计算布尔二叉树的值
 * 每日一题：2023.02.06
 * 完成日期：2023.02.06
 * 链接：https://leetcode.cn/problems/evaluate-boolean-binary-tree/
 * 描述：
 * 给你一棵 完整二叉树 的根，这棵树有以下特征：
 * <p>
 * 叶子节点 要么值为 0 要么值为 1 ，其中 0 表示 False ，1 表示 True 。
 * 非叶子节点 要么值为 2 要么值为 3 ，其中 2 表示逻辑或 OR ，3 表示逻辑与 AND 。
 * 计算 一个节点的值方式如下：
 * <p>
 * 如果节点是个叶子节点，那么节点的 值 为它本身，即 True 或者 False 。
 * 否则，计算 两个孩子的节点值，然后将该节点的运算符对两个孩子值进行 运算 。
 * 返回根节点 root 的布尔运算值。
 * <p>
 * 完整二叉树 是每个节点有 0 个或者 2 个孩子的二叉树。
 * <p>
 * 叶子节点 是没有孩子的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [2,1,3,null,null,0,1]
 * 输出：true
 * 解释：上图展示了计算过程。
 * AND 与运算节点的值为 False AND True = False 。
 * OR 运算节点的值为 True OR False = True 。
 * 根节点的值为 True ，所以我们返回 true 。
 * 示例 2：
 * <p>
 * 输入：root = [0]
 * 输出：false
 * 解释：根节点是叶子节点，且值为 false，所以我们返回 false 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在 [1, 1000] 之间。
 * 0 <= Node.val <= 3
 * 每个节点的孩子数为 0 或 2 。
 * 叶子节点的值为 0 或 1 。
 * 非叶子节点的值为 2 或 3 。
 * <p>
 * 解题思路：
 * 如果val小于2，则根据val==1判断。
 * 如果val等于2，则递归求left和right的结果，并且进行OR运算。
 * 如果val等于3，则进行AND的运算
 * <p>
 * state:done
 */
public class Solution2331 {

    public boolean evaluateTree(TreeNode root) {
        if (root.val < 2) {
            return root.val == 1;
        }
        if (root.val == 2) {
            return evaluateTree(root.left) | evaluateTree(root.right);
        }
        return evaluateTree(root.left) & evaluateTree(root.right);
    }
}