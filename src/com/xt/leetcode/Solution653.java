package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * 653. 两数之和 IV - 输入 BST - 力扣（LeetCode）
 * 每日一题：2022.03.21
 * 完成日期：2022.03.21
 * 链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
 * 描述：
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入: root = [5,3,6,2,4,null,7], k = 9
 * 输出: true
 * 示例 2：
 * <p>
 * <p>
 * 输入: root = [5,3,6,2,4,null,7], k = 28
 * 输出: false
 *  
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是  [1, 104].
 * -104 <= Node.val <= 10^4
 * root 为二叉搜索树
 * -105 <= k <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 为了效率，所以第一想法要把搜索二叉树转为map，然后再去判断。这样执行效率为4ms。
 * 后来一想，其实可以在搜索过程中就把判断给做了，一旦有符合条件的，后面就无需继续判断了。
 * <p>
 * <p>
 * state:
 */
public class Solution653 {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return searchNode(root, set, k);
    }

    private boolean searchNode(TreeNode root, Set<Integer> set, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return searchNode(root.left, set, k) || searchNode(root.right, set, k);

    }
}