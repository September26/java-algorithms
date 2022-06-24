package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 513. 找树左下角的值
 * 每日一题：2022.06.22
 * 完成日期：2022.06.22
 * 链接：https://leetcode.cn/problems/find-bottom-left-tree-value/
 * 描述：
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * <p>
 * 假设二叉树中至少有一个节点。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: root = [2,1,3]
 * 输出: 1
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 *  
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [1,104]
 * -231 <= Node.val <= 231 - 1 
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-bottom-left-tree-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 用层序便利的方式可以解决，取最底层的list的第一个节点即可
 * <p>
 * <p>
 * state:done
 */
public class Solution513 {

    public int findBottomLeftValue(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        TreeNode treeNode = levelSearch(list);
        return treeNode.val;
    }

    private TreeNode levelSearch(List<TreeNode> list) {
        List<TreeNode> nextList = new ArrayList<>();
        for (TreeNode node : list) {
            if (node.left != null) {
                nextList.add(node.left);
            }
            if (node.right != null) {
                nextList.add(node.right);
            }
        }
        if (nextList.size() == 0) {
            return list.get(0);
        }
        return levelSearch(nextList);
    }

}