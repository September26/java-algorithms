package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.Vector;

/**
 * 669. 修剪二叉搜索树
 * 每日一题：2022.09.10
 * 完成日期：2022.09.21
 * 链接：https://leetcode.cn/problems/trim-a-binary-search-tree/
 * 描述：
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
 *
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,0,2], low = 1, high = 2
 * 输出：[1,null,2]
 * 示例 2：
 *
 *
 * 输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 * 输出：[3,2,null,1]
 *  
 *
 * 提示：
 *
 * 树中节点数在范围 [1, 104] 内
 * 0 <= Node.val <= 104
 * 树中每个节点的值都是 唯一 的
 * 题目数据保证输入是一棵有效的二叉搜索树
 * 0 <= low <= high <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/trim-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 构建一个递归方法，返回值为修剪过的根节点。
 * 因为是二叉搜索树，所以就会简单很多，
 * node.val >= low && node.val <= high时，则在范围内，则分别对其子节点进行递归操作。
 * node.val < low时，左边肯定不符合，则对其右子节点进行递归操作
 * node.val > high时，右边肯定不符合，则对其左子节点进行递归操作
 * <p>
 * <p>
 * state:done
 */
public class Solution669 {

    public TreeNode trimBST(TreeNode node, int low, int high) {
        return searchSuitTreeNode(node, low, high);
    }


    public TreeNode searchSuitTreeNode(TreeNode node, int low, int high) {
        if (node == null) {
            return null;
        }
        if (node.val >= low && node.val <= high) {
            node.left = searchSuitTreeNode(node.left, low, high);
            node.right = searchSuitTreeNode(node.right, low, high);
            return node;
        }
        if (node.val < low) {
            return searchSuitTreeNode(node.right, low, high);
        }
        return searchSuitTreeNode(node.left, low, high);
    }
}