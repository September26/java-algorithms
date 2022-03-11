package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 2049. 统计最高分的节点数目
 * 每日一题：2022.03.11
 * 完成日期：2022.03.11
 * 链接：https://leetcode-cn.com/problems/count-nodes-with-the-highest-score/
 * 描述：
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
 * <p>
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 * <p>
 * 请你返回有 最高得分 节点的 数目 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入：parents = [-1,2,0,2,0]
 * 输出：3
 * 解释：
 * - 节点 0 的分数为：3 * 1 = 3
 * - 节点 1 的分数为：4 = 4
 * - 节点 2 的分数为：1 * 1 * 2 = 2
 * - 节点 3 的分数为：4 = 4
 * - 节点 4 的分数为：4 = 4
 * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：parents = [-1,2,0]
 * 输出：2
 * 解释：
 * - 节点 0 的分数为：2 = 2
 * - 节点 1 的分数为：2 = 2
 * - 节点 2 的分数为：1 * 1 = 1
 * 最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
 *  
 * <p>
 * 提示：
 * <p>
 * n == parents.length
 * 2 <= n <= 10^5
 * parents[0] == -1
 * 对于 i != 0 ，有 0 <= parents[i] <= n - 1
 * parents 表示一棵二叉树。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-nodes-with-the-highest-score
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:
 */
public class Solution2049 {

    public int countHighestScoreNodes(int[] parents) {
        Map<Integer, Node> map = new HashMap<>();//1,2
        for (int i = 0; i < parents.length; i++) {
            Node node = new Node();
            node.value = i;
            map.put(i, node);
        }
        for (int i = 0; i < parents.length; i++) {
            int parent = parents[i];
            Node parentNode = map.get(parent);
            if (parentNode == null) {
                continue;
            }
            if (parentNode.left == -1) {
                parentNode.left = i;
                continue;
            }
            parentNode.right = i;

            Node node = map.get(i);
            node.parent = parent;
        }
        int num = searchList(map.get(0), map);
        long maxValue = 0;
        int maxValueNum = 0;
        for (int i = 0; i < parents.length; i++) {
            Node node = map.get(i);
            long leftNum = node.leftNum;
            long rightNum = node.rightNum;
            long topNum = num - leftNum - rightNum - 1;
            topNum = topNum == 0 ? 1 : topNum;
            leftNum = leftNum == 0 ? 1 : leftNum;
            rightNum = rightNum == 0 ? 1 : rightNum;
            long value = leftNum * rightNum * topNum;
            if (value > maxValue) {
                maxValueNum = 1;
                maxValue = value;
            } else if (value == maxValue) {
                maxValueNum++;
            }
        }
        return maxValueNum;
    }

    private int searchList(Node node, Map<Integer, Node> map) {
        int leftNum = 0;
        int rightNum = 0;
        if (node.left >= 0) {
            if (node.leftNum > 0) {
                leftNum = node.leftNum;
            } else {
                leftNum = searchList(map.get(node.left), map);
            }
        }
        if (node.right >= 0) {
            if (node.rightNum > 0) {
                rightNum = node.rightNum;
            } else {
                rightNum = searchList(map.get(node.right), map);
            }
        }
        node.leftNum = leftNum;
        node.rightNum = rightNum;
        return leftNum + rightNum + 1;
    }


    static class Node {
        int value = -1;
        int leftNum = 0;
        int rightNum = 0;
        int parent = -1;
        int left = -1;
        int right = -1;

    }

}