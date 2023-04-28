package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.*;

/**
 * 652. 寻找重复的子树
 * 每日一题：2022.09.05
 * 完成日期：2022.09.05
 * 链接：https://leetcode.cn/problems/find-duplicate-subtrees/
 * 描述：
 * 给定一棵二叉树 root，返回所有重复的子树。
 * <p>
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * <p>
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,null,2,4,null,null,4]
 * 输出：[[2,4],[4]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [2,1,1]
 * 输出：[[1]]
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：root = [2,2,2,3,null,3,null]
 * 输出：[[2,3],[3]]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中的结点数在[1,10^4]范围内。
 * -200 <= Node.val <= 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-duplicate-subtrees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 遍历所有节点，构建map映射，key为节点的拼接值，value为子节点。
 * 每次添加节点时，判断是否存在即可。现在的问题就是如何用key来识别唯一一种结构的子树了。
 * 比如[2,2,2,3,4,3,null]结构的子树，我可以用字符串[[[3]2[4]]2[[3]2]]来表示。
 * <p>
 * state:done
 */
public class Solution652 {

    Map<String, TreeNode> cacheMap = new HashMap<>();
    Map<String, TreeNode> resuleMap = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        search(root);
        return new ArrayList<>(resuleMap.values());
    }

    private String search(TreeNode root) {
        StringBuilder builder = new StringBuilder("[");
        if (root.left != null) {
            String left = search(root.left);
            builder.append(left);
        }
        builder.append(root.val);
        if (root.right != null) {
            String right = search(root.right);
            builder.append(right);
        }
        builder.append("]");
        String key = builder.toString();
        TreeNode treeNode = cacheMap.get(key);
        if (treeNode == null) {
            cacheMap.put(key, root);
            return key;
        }
        if (resuleMap.get(key) == null) {
            resuleMap.put(key, root);
        }
        return key;
    }
}