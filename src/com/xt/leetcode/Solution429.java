package com.xt.leetcode;

import com.xt.model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 429. N 叉树的层序遍历
 * 每日一题：2022.04.08
 * 完成日期：2022.04.08
 * 链接：
 * 描述：
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * <p>
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 *  
 * <p>
 * 提示：
 * <p>
 * 树的高度不会超过 1000
 * 树的节点总数在 [0, 10^4] 之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 层序遍历，采取递归的方案。每次取某一层级所有的节点组合成List，遍历这个list，取val加入到结果当中。
 * 然后传入下一循环进行遍历。
 * <p>
 * <p>
 * state:
 */
public class Solution429 {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayList<Node> nodeList = new ArrayList<>();
        nodeList.add(root);
        search(nodeList, result);
        return result;
    }


    private void search(List<Node> list, List<List<Integer>> result) {
        List<Node> nextList = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        for (Node node : list) {
            if (node == null) {
                continue;
            }
            levelList.add(node.val);
            nextList.addAll(node.children);
        }
        result.add(levelList);
        if (nextList.size() == 0) {
            return;
        }
        search(nextList, result);
    }
}