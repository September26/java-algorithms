package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.Vector;

/**
 * 450. 删除二叉搜索树中的节点
 * 每日一题：2022.06.02
 * 完成日期：2022.06.02
 * 链接：https://leetcode.cn/problems/delete-node-in-a-bst/
 * 描述：
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 *  
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * 示例 3:
 * <p>
 * 输入: root = [], key = 0
 * 输出: []
 *  
 * <p>
 * 提示:
 * <p>
 * 节点数的范围 [0, 104].
 * -105 <= Node.val <= 105
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -105 <= key <= 105
 *  
 * <p>
 * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/delete-node-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 解题思路：
 * 首先最简单的方式肯定是把二叉树转换成队列，然后重新排序就好了。这样肯定符合，但是时间复杂度较高，也不是本题考察的点。
 * 先根据二叉树的性质，找到key对应的节点，然后找到这个节点下，右侧的节点中子节点里面最左侧的那一个替换当前结点。然后找到这个目标节点后，我们还要把其parent对其的引用干掉。
 * <p>
 * <p>
 * state:
 */
public class Solution450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode recursion = recursion(root, key);
        return recursion;
    }


    private TreeNode recursion(TreeNode current, int key) {
        if (current == null) {
            return null;
        }
        if (current.val == key) {
            if (current.right != null) {
                TreeNode left = current.left;
                TreeNode right = current.right;
                TreeNode node = findNode(current, current.right);
                node.left = left;
                if (node != right) {
                    node.right = right;
                }
                return node;
            }
            return current.left;
        }
        if (current.val > key) {
            current.left = recursion(current.left, key);
        } else {
            current.right = recursion(current.right, key);
        }
        return current;
    }

    private TreeNode findNode(TreeNode parent, TreeNode treeNode) {
        if (treeNode.left != null) {
            return findNode(treeNode, treeNode.left);
        }
        if (treeNode.right != null) {
            parent.left = treeNode.right;
        } else {
            parent.left = null;
        }
        return treeNode;
    }

}