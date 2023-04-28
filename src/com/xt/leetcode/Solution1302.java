package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 1302. 层数最深叶子节点的和
 * 每日一题：2022.08.17
 * 完成日期：2022.08.17
 * 链接：https://leetcode.cn/problems/deepest-leaves-sum/
 * 描述：
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 * 示例 2：
 * <p>
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：19
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 104] 之间。
 * 1 <= Node.val <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/deepest-leaves-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题有很多种解法，这里我们使用层序遍历的方式进行。
 * 如果下一层级的list为空，则计算当前层级的值并返回。
 * <p>
 * <p>
 * state:done
 */
public class Solution1302 {

    public int deepestLeavesSum(TreeNode root) {
        List<TreeNode> objects = new ArrayList<>();
        objects.add(root);
        return searchAndGetSum(objects);
    }


    public int searchAndGetSum(List<TreeNode> list) {
        List<TreeNode> childList = new ArrayList<>();
        for (TreeNode node : list) {
            if (node.right != null) {
                childList.add(node.right);
            }
            if (node.left != null) {
                childList.add(node.left);
            }
        }
        if (childList.size() > 0) {
            return searchAndGetSum(childList);
        }
        int sum = 0;
        for (TreeNode node : list) {
            sum += node.val;
        }
        return sum;

    }
}