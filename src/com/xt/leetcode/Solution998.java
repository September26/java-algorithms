package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * 998. 最大二叉树 II
 * 每日一题：2022.08.30
 * 完成日期：2022.08.30
 * 链接：https://leetcode.cn/problems/maximum-binary-tree-ii/
 * 描述：
 * 最大树 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。
 * <p>
 * 给你最大树的根节点 root 和一个整数 val 。
 * <p>
 * 就像 之前的问题 那样，给定的树是利用 Construct(a) 例程从列表 a（root = Construct(a)）递归地构建的：
 * <p>
 * 如果 a 为空，返回 null 。
 * 否则，令 a[i] 作为 a 的最大元素。创建一个值为 a[i] 的根节点 root 。
 * root 的左子树将被构建为 Construct([a[0], a[1], ..., a[i - 1]]) 。
 * root 的右子树将被构建为 Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]) 。
 * 返回 root 。
 * 请注意，题目没有直接给出 a ，只是给出一个根节点 root = Construct(a) 。
 * <p>
 * 假设 b 是 a 的副本，并在末尾附加值 val。题目数据保证 b 中的值互不相同。
 * <p>
 * 返回 Construct(b) 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [4,1,3,null,null,2], val = 5
 * 输出：[5,4,null,1,3,null,null,2]
 * 解释：a = [1,4,2,3], b = [1,4,2,3,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [5,2,4,null,1], val = 3
 * 输出：[5,2,4,null,1,null,3]
 * 解释：a = [2,1,5,4], b = [2,1,5,4,3]
 * 示例 3：
 * <p>
 * <p>
 * 输入：root = [5,2,3,null,1], val = 4
 * 输出：[5,2,4,null,1,3]
 * 解释：a = [2,1,5,3], b = [2,1,5,3,4]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-binary-tree-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 我的思路是先把root转换回a，然后尾部加上val,再去生成新的root节点。
 * <p>
 * <p>
 * state:done
 */
public class Solution998 {

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        List<Integer> list = getListFromNode(root);
        list.add(val);
        TreeNode treeNode = constructMaximumBinaryTree(list, 0, list.size());
        return treeNode;
    }

    private List<Integer> getListFromNode(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node.left != null) {
            list.addAll(getListFromNode(node.left));
        }
        list.add(node.val);
        if (node.right != null) {
            list.addAll(getListFromNode(node.right));
        }
        return list;
    }

    private TreeNode constructMaximumBinaryTree(List<Integer> nums, int start, int end) {
        int i = searchMaxIndex(nums, start, end);
        TreeNode treeNode = new TreeNode(nums.get(i));

        if (start < i) {
            treeNode.left = constructMaximumBinaryTree(nums, start, i);
        }
        if (i + 1 < end) {
            treeNode.right = constructMaximumBinaryTree(nums, i + 1, end);
        }
        return treeNode;
    }

    private int searchMaxIndex(List<Integer> nums, int start, int end) {
        int maxIndex = start;
        for (int i = start + 1; i < end; i++) {
            if (nums.get(i) > nums.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}