package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * 919. 完全二叉树插入器
 * 每日一题：2022.07.25
 * 完成日期：2022.07.25
 * 链接：https://leetcode.cn/problems/complete-binary-tree-inserter/
 * 描述：
 * 完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 * <p>
 * 设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。
 * <p>
 * 实现 CBTInserter 类:
 * <p>
 * CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态，并返回插入节点 TreeNode 的父节点的值；
 * CBTInserter.get_root() 将返回树的头节点。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * 输出
 * [null, 1, 2, [1, 2, 3, 4]]
 * <p>
 * 解释
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // 返回 1
 * cBTInserter.insert(4);  // 返回 2
 * cBTInserter.get_root(); // 返回 [1, 2, 3, 4]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数量范围为 [1, 1000] 
 * 0 <= Node.val <= 5000
 * root 是完全二叉树
 * 0 <= val <= 5000 
 * 每个测试用例最多调用 insert 和 get_root 操作 10^4 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/complete-binary-tree-inserter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 层序遍历的思想。记录最下面两个层级。
 * 每次加入节点的时候，判断上面的层级是否达到下面层级集合的两倍，
 * 如果没有达到，则直接加入下面的层级。
 * 如果达到，则使用下面的层级替换上面层级，然后把下面的层级集合置为空。
 * <p>
 * <p>
 * state:done
 */
public class Solution919 {

    public static class CBTInserter {
        TreeNode root;
        List<TreeNode> parentList = new ArrayList<>();
        List<TreeNode> currentList = new ArrayList<>();

        public CBTInserter(TreeNode root) {
            this.root = root;
            parentList.add(root);
            for (int i = 0; i < parentList.size(); i++) {
                TreeNode treeNode = parentList.get(i);
                TreeNode left = treeNode.left;
                TreeNode right = treeNode.right;
                if (left != null) {
                    currentList.add(left);
                } else {
                    break;
                }
                if (right != null) {
                    currentList.add(right);
                } else {
                    break;
                }
                if (i == parentList.size() - 1) {
                    parentList.clear();
                    parentList.addAll(currentList);
                    currentList.clear();
                    i = -1;
                }
            }
        }

        public int insert(int val) {
            TreeNode parentNode;
            TreeNode childNode = new TreeNode(val);
            if (currentList.size() < parentList.size() * 2) {
                parentNode = parentList.get(currentList.size() / 2);
                if (currentList.size() % 2 == 0) {
                    parentNode.left = childNode;
                } else {
                    parentNode.right = childNode;
                }
            } else {
                parentList.clear();
                parentList.addAll(currentList);
                currentList.clear();
                parentNode = parentList.get(0);
                parentNode.left = childNode;
            }
            currentList.add(childNode);
            return parentNode.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }
}