package com.xt.leetcode;

import com.xt.model.Node;

import java.util.*;

/**
 * 589. N 叉树的前序遍历
 * 每日一题：2022.03.10
 * 完成日期：2022.03.10
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 * 描述：
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 *  
 * <p>
 * 提示：
 * <p>
 * 节点总数在范围 [0, 104]内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 *  
 * <p>
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 递归法：
 * 比较简单，先记录root节点的值，然后遍历每个子节点执行递归操作即可。
 * <p>
 * 迭代法的话，我的想法是构建两个集合，分别存放每一层遍历到的节点index和list（因为子节点对parent持有引用）
 * 遍历每一层的时候，如果当前节点有子集，则层级+1（level++），并且index从0开始。
 * 如果遍历完当前所有节点或者子集为空，则层级-1（level--），并且上一级的index值要+1；
 * <p>
 * state:done
 */
public class Solution589 {

    //递归法
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        preorder3(root, list);
        return list;
    }

    //递归法
    public void preorder2(Node root, List<Integer> list) {
        list.add(root.val);
        for (Node node : root.children) {
            preorder2(node, list);
        }
    }

    //迭代法
    public void preorder3(Node root, List<Integer> list) {
        Map<Integer, Node> levelList = new HashMap<>();
        Map<Integer, Integer> levelIndexList = new HashMap<>();
        int level = 0;
        levelList.put(0, root);
        list.add(root.val);
        levelIndexList.put(0, 0);

        while (true) {
            Node node = levelList.get(level);//遍历的永远是最后一级节点
            List<Node> children = node.children;

            int index = levelIndexList.get(level);
            if (children.size() == 0 || index == children.size()) {
                levelList.remove(level);
                level--;
                //遍历层级为负数则代表结束了
                if (level < 0) {
                    break;
                }
                levelIndexList.put(level, levelIndexList.get(level) + 1);//上一级的index+1
            } else {
                Node node1 = children.get(index);
                if (node1 == null) {
                    levelIndexList.put(level, index + 1);
                    continue;
                }
                //加入节点值
                list.add(node1.val);
                level++;
                levelList.put(level, node1);
                levelIndexList.put(level, 0);
            }
        }
    }
}