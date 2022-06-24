package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 515. 在每个树行中找最大值
 * 每日一题：2022.06.24
 * 完成日期：2022.06.24
 * 链接：https://leetcode.cn/problems/find-largest-value-in-each-tree-row/
 * 描述：
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * <p>
 *  
 * <p>
 * 示例1：
 * <p>
 * <p>
 * <p>
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 示例2：
 * <p>
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 *  
 * <p>
 * 提示：
 * <p>
 * 二叉树的节点个数的范围是 [0,104]
 * -2^31 <= Node.val <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-largest-value-in-each-tree-row
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 用list记录每一次层级最大的值。对TreeNode进行递归遍历，传入值为层级/节点和list。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution515 {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        search(0, root, list);
        return list;
    }

    private void search(int level, TreeNode node, List<Integer> list) {
        if (list.size() <= level) {
            list.add(node.val);
        } else {
            list.set(level, Math.max(list.get(level), node.val));
        }

        if (node.left != null) {
            search(level + 1, node.left, list);
        }
        if (node.right != null) {
            search(level + 1, node.right, list);
        }
    }
}