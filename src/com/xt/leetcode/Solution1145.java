package com.xt.leetcode;

import com.xt.model.TreeNode;

/**
 * 1145. 二叉树着色游戏
 * 每日一题：2023.02.03
 * 完成日期：2023.02.03
 * 链接：https://leetcode.cn/problems/binary-tree-coloring-game/
 * 描述：
 * 有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点 root，树上总共有 n 个节点，且 n 为奇数，其中每个节点上的值从 1 到 n 各不相同。
 * <p>
 * 最开始时：
 * <p>
 * 「一号」玩家从 [1, n] 中取一个值 x（1 <= x <= n）；
 * 「二号」玩家也从 [1, n] 中取一个值 y（1 <= y <= n）且 y != x。
 * 「一号」玩家给值为 x 的节点染上红色，而「二号」玩家给值为 y 的节点染上蓝色。
 * <p>
 * 之后两位玩家轮流进行操作，「一号」玩家先手。每一回合，玩家选择一个被他染过色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色（「一号」玩家染红色，「二号」玩家染蓝色）。
 * <p>
 * 如果（且仅在此种情况下）当前玩家无法找到这样的节点来染色时，其回合就会被跳过。
 * <p>
 * 若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。
 * <p>
 * 现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个 y 值可以确保你赢得这场游戏，则返回 true ；若无法获胜，就请返回 false 。
 * <p>
 * <p>
 * 示例 1 ：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
 * 输出：true
 * 解释：第二个玩家可以选择值为 2 的节点。
 * 示例 2 ：
 * <p>
 * 输入：root = [1,2,3], n = 3, x = 1
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目为 n
 * 1 <= x <= n <= 100
 * n 是奇数
 * 1 <= Node.val <= n
 * 树中所有值 互不相同
 * <p>
 * 解题思路：
 * 方法一：深度优先搜索
 * 基于X节点，分为三个部分，左侧节点，右侧节点，其他节点。
 * 因为玩家二想赢，所以尽可能的多占用，所以肯定是占用头节点。
 * 所以基于X节点统计三块区域中是否存在一方大于其余(两方之和+1)即可
 *
 * state:done
 */
public class Solution1145 {

    int mLeftWeight = 0;
    int mRightWeight = 0;

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        countWeight(root, x);
        int rootWeight = n - mLeftWeight - mRightWeight - 1;
        int max = Math.max(Math.max(mRightWeight, mLeftWeight), rootWeight);
        return max * 2 > (mLeftWeight + mRightWeight + rootWeight + 1);
    }

    public int countWeight(TreeNode root, int x) {
        if (root == null) {
            return 0;
        }
//        System.out.println(root.val);
        int leftWeight = countWeight(root.left, x);
        int rightWeight = countWeight(root.right, x);

        if (x == root.val) {
            mLeftWeight = leftWeight;
            mRightWeight = rightWeight;
        }
        int weight = leftWeight + rightWeight;
        if (root.val == x) {
            return 0;
        }
        return weight + 1;
    }
}
