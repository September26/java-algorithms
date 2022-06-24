package com.xt.leetcode;

import com.xt.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 449. 序列化和反序列化二叉搜索树
 * 每日一题：2022.05.11
 * 完成日期：2022.05.11
 * 链接：https://leetcode.cn/problems/serialize-and-deserialize-bst/
 * 描述：
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 *
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 *
 * 编码的字符串应尽可能紧凑。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [2,1,3]
 * 输出：[2,1,3]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 树中节点数范围是 [0, 104]
 * 0 <= Node.val <= 104
 * 题目数据 保证 输入的树是一棵二叉搜索树。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/serialize-and-deserialize-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 我们可以按照层序遍历的概念来解题。
 * 序列化的时候，我们每次遍历当前层级，都记录下一层级，同时生成下一层级的字符串。如果下一层级不全为空，则加入到最后的字符串当中。
 * 反序列化的时候，我们同样设置两个list来记录，分别记录当前和下一层级。以","分割字符串，每次遍历的数量，是上一层级的两倍（不包含为null的情况）。
 * <p>
 * <p>
 * state:done
 */
public class Solution449 {

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            List<TreeNode> treeNodes = new ArrayList<>();
            List<TreeNode> nextNodes = new ArrayList<>();
            treeNodes.add(root);
            StringBuilder builder = new StringBuilder();
            builder.append(root.val);
            builder.append(",");
            StringBuilder lineBuilder = new StringBuilder();
            while (treeNodes.size() > 0) {
                builder.append(lineBuilder);
                lineBuilder.setLength(0);
                nextNodes.clear();
                for (TreeNode node : treeNodes) {
                    if (node.left != null) {
                        lineBuilder.append(node.left.val);
                        lineBuilder.append(",");
                        nextNodes.add(node.left);
                    } else {
                        lineBuilder.append("#,");
                    }

                    if (node.right != null) {
                        lineBuilder.append(node.right.val);
                        lineBuilder.append(",");
                        nextNodes.add(node.right);
                    } else {
                        lineBuilder.append("#,");
                    }
                }
                treeNodes.clear();
                treeNodes.addAll(nextNodes);
            }
            return builder.toString();
        }


        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() == 0) {
                return null;
            }
            String[] split = data.split(",");
            TreeNode root = null;
            List<TreeNode> treeNodes = new ArrayList<>();
            List<TreeNode> nextNodes = new ArrayList<>();
            int base = 1;
            int start = 0;
            while (start < split.length) {
                nextNodes.clear();
                for (int i = 0; i < base; i++) {
                    if (start + i >= split.length) {
                        break;
                    }
                    String value = split[start + i];
                    if (root == null) {
                        root = new TreeNode(Integer.parseInt(value));
                        nextNodes.add(root);
                        continue;
                    }
                    if (value.equals("#")) {
                        continue;
                    }
                    TreeNode currentNode = new TreeNode(Integer.parseInt(value));
                    TreeNode parent = treeNodes.get(i / 2);
                    if (i % 2 == 0) {
                        parent.left = currentNode;
                    } else {
                        parent.right = currentNode;
                    }
                    nextNodes.add(currentNode);
                }
                start = start + base;
                base = nextNodes.size() * 2;
                treeNodes.clear();
                treeNodes.addAll(nextNodes);
            }
            return root;
        }
    }
}