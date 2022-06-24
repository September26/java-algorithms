package com.xt.mst;

import com.xt.model.TreeNode;

/**
 * 面试题 04.06. 后继者
 * 每日一题：2022.05.13
 * 完成日期：2022.05.13
 * 链接：https://leetcode.cn/problems/one-away-lcci/
 * 描述：
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 *
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 *
 * 输入: root = [2,1,3], p = 1
 *
 *   2
 *  / \
 * 1   3
 *
 * 输出: 2
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 *
 * 输出: null
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/successor-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 进行中序遍历，如果读到了目标节点，则把标记位isStart改为true。
 * 如果isStart为true，则记录当前节点为targetNode。
 * 如果targetNode不为空，则证明找到了所需要的节点，则返回。
 *
 * <p>
 * <p>
 * state:done
 */
public class SolutionMST0406 {

    boolean isStart = false;
    TreeNode targetNode = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //中序遍历
        search(root, p);
        return targetNode;
    }

    public void search(TreeNode node, TreeNode p) {
        if (node.left != null) {
            search(node.left, p);
        }
        if (targetNode != null) {
            return;
        }
        if (isStart) {
            targetNode = node;
            return;
        }
        if (node == p) {
            isStart = true;
        }
        if (node.right != null) {
            search(node.right, p);
        }
    }
}