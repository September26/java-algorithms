package com.xt.leetcode;

import com.xt.model.Node;

import java.util.*;

/**
 * 590. N 叉树的后序遍历
 * 每日一题：2022.03.12
 * 完成日期：2022.03.12
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 * 描述：
 * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[5,6,3,2,4,1]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 节点总数在范围 [0, 10^4] 内
 * 0 <= Node.val <= 10^4
 * n 叉树的高度小于或等于 1000
 *  
 * <p>
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 参考589，只修改list.add()的位置即可
 * <p>
 * <p>
 * state:done
 */
public class Solution590 {

    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        preorder2(root, list);
        return list;
    }

    //递归法
    public void preorder2(Node root, List<Integer> list) {
        for (Node node : root.children) {
            preorder2(node, list);
        }
        list.add(root.val);
    }

    //迭代法
    public void preorder3(Node root, List<Integer> list) {
        Map<Integer, Node> levelList = new HashMap<>();
        Map<Integer, Integer> levelIndexList = new HashMap<>();
        int level = 0;
        levelList.put(0, root);
        levelIndexList.put(0, 0);

        while (true) {
            Node node = levelList.get(level);//遍历的永远是最后一级节点
            List<Node> children = node.children;

            int index = levelIndexList.get(level);
            if (children.size() == 0 || index == children.size()) {
                levelList.remove(level);
                level--;
                //加入节点值
                //遍历层级为负数则代表结束了
                if (level < 0) {
                    break;
                }
                Node node1 = levelList.get(level);
                list.add(node1.val);
                levelIndexList.put(level, levelIndexList.get(level) + 1);//上一级的index+1
            } else {
                Node node1 = children.get(index);
                if (node1 == null) {
                    levelIndexList.put(level, index + 1);
                    continue;
                }

                level++;
                levelList.put(level, node1);
                levelIndexList.put(level, 0);
            }
        }
        list.add(root.val);
    }
}