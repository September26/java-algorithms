package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * 1609. 奇偶树
 * 12.25
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
 * <p>
 * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 * <p>
 * 示例1：
 * 输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 * 输出：true
 * 解释：每一层的节点值分别是：
 * 0 层：[1]
 * 1 层：[10,4]
 * 2 层：[3,7,9]
 * 3 层：[12,8,6,2]
 * 由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
 * <p>
 * <p>
 * 示例2：
 * 输入：root = [5,4,2,3,3,7]
 * 输出：false
 * 解释：每一层的节点值分别是：
 * 0 层：[5]
 * 1 层：[4,2]
 * 2 层：[3,3,7]
 * 2 层上的节点值不满足严格递增的条件，所以这不是一棵奇偶树。
 * <p>
 * 示例3：
 * 输入：root = [5,9,1,3,5,7]
 * 输出：false
 * 解释：1 层上的节点值应为偶数。
 * <p>
 * 示例4：
 * 输入：root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
 * 输出：true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/even-odd-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 使用动态规划的思路去解决，
 * 动态规划的方法中，首先判断这一层节点是否符合递增或者递减。不符合返回false，
 * 如果符合，则返回下一层层级节点的结果。
 * state:done
 */
public class Solution1609 {

    public boolean isEvenOddTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        return isEvenOddTree(list, true);
    }

    private boolean isEvenOddTree(List<TreeNode> list, boolean isEven) {
        if (list.size() == 0) {
            return true;
        }
        Integer lastValue = null;
        List<TreeNode> nextList = new ArrayList<>();
        for (TreeNode node : list) {
            if (node.left != null) {
                nextList.add(node.left);
            }
            if (node.right != null) {
                nextList.add(node.right);
            }
            int val = node.val;
            //判断奇偶数是否符合
            if (isEven) {
                if (val % 2 == 0) {
                    return false;
                }
                if (lastValue == null) {
                    lastValue = val;
                    continue;
                }
                if (val <= lastValue) {
                    return false;
                }
                lastValue = val;
                continue;
            }
            if (val % 2 != 0) {
                return false;
            }
            if (lastValue == null) {
                lastValue = val;
                continue;
            }
            if (val >= lastValue) {
                return false;
            }
            lastValue = val;
        }
        return isEvenOddTree(nextList, !isEven);
    }
}