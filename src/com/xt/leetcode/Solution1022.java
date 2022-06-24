package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.Vector;


/**
 * 1022. 从根到叶的二进制数之和
 * 每日一题：2022.05.30
 * 完成日期：2022.05.30
 * 链接：https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers/
 * 描述：
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 *
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 *
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 * 示例 2：
 *
 * 输入：root = [0]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 树中的节点数在 [1, 1000] 范围内
 * Node.val 仅为 0 或 1 
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 递归遍历所有节点。每次遍历节点时，接受根结点传递过来的累加值，然后加上当前值求出当前节点的值。
 * 如果节点是叶子节点，则计算SUM值。
 * 如果不是，则传递当前节点的值并递归。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1022 {
    int sum = 0;
    public int sumRootToLeaf(TreeNode root) {
        ergodic(root, 0);
        return sum;
    }

    private void ergodic(TreeNode root, int i) {
        int current = (i << 1) + root.val;
        if (root.right == null && root.left == null) {
            sum += current;
            return;
        }
        if (root.left != null) {
            ergodic(root.left, current);
        }
        if (root.right != null) {
            ergodic(root.right, current);
        }
    }
}