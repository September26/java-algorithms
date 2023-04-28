package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.Vector;

/**
 * 623.在二叉树中增加一行
 * 每日一题：2022.08.05
 * 完成日期：2022.08.05
 * 链接：https://leetcode.cn/problems/add-one-row-to-tree/
 * 描述：
 * 给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。
 * <p>
 * 注意，根节点 root 位于深度 1 。
 * <p>
 * 加法规则如下:
 * <p>
 * 给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
 * cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。
 * 如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 *  
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: root = [4,2,6,3,1,5], val = 1, depth = 2
 * 输出: [4,1,1,2,null,null,6,3,1,5]
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入: root = [4,2,null,3,1], val = 1, depth = 3
 * 输出:  [4,2,null,1,1,3,null,null,1]
 *  
 * <p>
 * 提示:
 * <p>
 * 节点数在 [1, 104] 范围内
 * 树的深度在 [1, 104]范围内
 * -100 <= Node.val <= 100
 * -105 <= val <= 105
 * 1 <= depth <= the depth of tree + 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-one-row-to-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 递归判断，分三种场景：
 * depath<level时，则直接返回即可。
 * depth > level时，则需要递归判断。这里需要注意一点，如果root本身只有三级，但是depth=4时，也要添加节点的。所以处理root.left == null并且depth == level + 1的场景
 * depth == level时，需要判断ifLeft，如果为true则parentNode.left = newNode;并且 newNode.left = root;，为false时操作类似。
 *
 * <p>
 * <p>
 * state:
 */
public class Solution623 {

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        return addOneRowByLevel(root, val, depth, 1, true, null);
    }

    private TreeNode addOneRowByLevel(TreeNode root, int val, int depth, int level, boolean isLeft, TreeNode parentNode) {
        if (depth < level) {
            return root;
        }
        if (depth > level) {
            if (root.left != null) {
                addOneRowByLevel(root.left, val, depth, level + 1, true, root);
            } else if (depth == level + 1) {
                root.left = new TreeNode(val);
            }
            if (root.right != null) {
                addOneRowByLevel(root.right, val, depth, level + 1, false, root);
            } else if (depth == level + 1) {
                root.right = new TreeNode(val);
            }
            return root;
        }

        TreeNode newNode = new TreeNode(val);
        if (isLeft) {
            newNode.left = root;
            if (parentNode != null) {
                parentNode.left = newNode;
            }
        } else {
            newNode.right = root;
            parentNode.right = newNode;
        }
        return newNode;
    }
}