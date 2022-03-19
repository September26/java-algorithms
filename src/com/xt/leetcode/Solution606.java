package com.xt.leetcode;

import com.xt.model.TreeNode;

/**
 * 606. 根据二叉树创建字符串
 * 每日一题：2022.03.19
 * 完成日期：2022.03.19
 * 链接：https://leetcode-cn.com/problems/construct-string-from-binary-tree/
 * 描述：
 * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 * <p>
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 二叉树: [1,2,3,4]
 * 1
 * /   \
 * 2     3
 * /
 * 4
 * <p>
 * 输出: "1(2(4))(3)"
 * <p>
 * 解释: 原本将是“1(2(4)())(3())”，
 * 在你省略所有不必要的空括号对之后，
 * 它将是“1(2(4))(3)”。
 * 示例 2:
 * <p>
 * 输入: 二叉树: [1,2,3,null,4]
 * 1
 * /   \
 * 2     3
 * \
 * 4
 * <p>
 * 输出: "1(2()(4))(3)"
 * <p>
 * 解释: 和第一个示例相似，
 * 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-string-from-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 今天这题没有描述清楚。规则少了一条，应该是左节点为空，右节点不为空时，左节点要记录成()。
 * 首先递归的方式进行前序遍历，使用stringbuilder记录
 * <p>
 * <p>
 * state:done
 */
public class Solution606 {
    public String tree2str(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        search(root, builder);
        return builder.toString();
    }

    private void search(TreeNode root, StringBuilder builder) {
        if (root == null) {
            return;
        }
        builder.append(root.val);

        //左节点为空，右节点不为空时，左节点要记录成()
        if (root.left != null || root.right != null) {
            builder.append("(");
            search(root.left, builder);
            builder.append(")");
        }
        //右节点为空就不记录
        if (root.right != null) {
            builder.append("(");
            search(root.right, builder);
            builder.append(")");
        }
    }

}