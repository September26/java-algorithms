package com.xt.leetcode;

import com.xt.model.TreeNode;

/**
 * 814. 二叉树剪枝
 * 每日一题：2022.07.21
 * 完成日期：2022.07.21
 * 链接：https://leetcode.cn/problems/binary-tree-pruning/
 * 描述：
 * 给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
 * <p>
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * <p>
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,null,0,0,1]
 * 输出：[1,null,0,null,1]
 * 解释：
 * 只有红色节点满足条件“所有不包含 1 的子树”。 右图为返回的答案。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,0,1,0,0,0,1]
 * 输出：[1,null,1,null,1]
 * 示例 3：
 * <p>
 * <p>
 * 输入：root = [1,1,0,1,1,0,1,0]
 * 输出：[1,1,0,1,1,null,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 200] 内
 * Node.val 为 0 或 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-pruning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 递归的思路，
 * 每次递归的时候，分别去判断left,right,自身都不包含1，如果都不包含，则返回false。反之返回true。
 * 上一层接收到false时，则删除此节点。
 * <p>
 * <p>
 * state:done
 */
public class Solution814 {

    public TreeNode pruneTree(TreeNode root) {
        boolean b = have1Node(root);
        return !b ? null : root;
    }

    /**
     * root中包含1则返回true，否则为false
     *
     * @param root
     * @return
     */
    public boolean have1Node(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        boolean result = false;
        if (left != null) {
            if (have1Node(left)) {
                result = true;
            } else {
                root.left = null;
            }
        }
        if (right != null) {
            if (have1Node(right)) {
                result = true;
            } else {
                root.right = null;
            }
        }
        return result || root.val == 1;
    }
}