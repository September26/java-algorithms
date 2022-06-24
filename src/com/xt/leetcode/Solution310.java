package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 310. 最小高度树
 * 每日一题：2022.04.06
 * 完成日期：2022.04.06
 * 链接：https://leetcode-cn.com/problems/minimum-height-trees/
 * 描述：
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * <p>
 * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 * <p>
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 * <p>
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 * <p>
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 4, edges = [[1,0],[1,2],[1,3]]
 * 输出：[1]
 * 解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * 输出：[3,4]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2 * 104
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * 所有 (ai, bi) 互不相同
 * 给定的输入 保证 是一棵树，并且 不会有重复的边
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-height-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:
 */
public class Solution310 {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List[] list = new ArrayList[n];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList();
        }
        for (int[] ints : edges) {
            int i1 = ints[0];
            int i2 = ints[1];
            list[i1].add(i2);
            list[i2].add(i1);
        }
        ArrayList<Integer> result = new ArrayList<>();
        int minLevelNum = Integer.MAX_VALUE;
        for (int i = 0; i < list.length; i++) {
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(i);
            int[] use = new int[n];
            int currentMinLevel = getMinLevel(list, use, integers, 0);
            if (currentMinLevel > minLevelNum) {
                continue;
            }
            if (currentMinLevel < minLevelNum) {
                result.clear();
                minLevelNum = currentMinLevel;
            }
            result.add(i);
        }
        return result;
    }

    private int getMinLevel(List[] list, int[] use, List<Integer> levelSelect, int level) {
        List<Integer> nextList = new ArrayList<>();
        for (Integer i : levelSelect) {
            if (use[i] == 1) {
                //使用
                continue;
            }
            use[i] = 1;
            nextList.addAll(list[i]);
        }
        if (nextList.size() > 0) {
            return getMinLevel(list, use, nextList, level + 1);
        }
        return level;
    }

}