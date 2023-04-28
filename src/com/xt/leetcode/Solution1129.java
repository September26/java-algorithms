package com.xt.leetcode;

import java.util.*;

/**
 * 1129. 颜色交替的最短路径
 * 每日一题：2023.02.02
 * 完成日期：2023.02.02
 * 链接：https://leetcode.cn/problems/shortest-path-with-alternating-colors/
 * 描述：
 * 在一个有向图中，节点分别标记为 0, 1, ..., n-1。图中每条边为红色或者蓝色，且存在自环或平行边。
 * <p>
 * red_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的蓝色有向边。
 * <p>
 * 返回长度为 n 的数组 answer，其中 answer[X] 是从节点 0 到节点 X 的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 answer[x] = -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * 输出：[0,1,-1]
 * 示例 2：
 * <p>
 * 输入：n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
 * 输出：[0,1,-1]
 * 示例 3：
 * <p>
 * 输入：n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
 * 输出：[0,-1,-1]
 * 示例 4：
 * <p>
 * 输入：n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
 * 输出：[0,1,2]
 * 示例 5：
 * <p>
 * 输入：n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
 * 输出：[0,1,1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * red_edges.length <= 400
 * blue_edges.length <= 400
 * red_edges[i].length == blue_edges[i].length == 2
 * 0 <= red_edges[i][j], blue_edges[i][j] < n
 * <p>
 * 解题思路：
 * 从0点开始，一层一层的往上找。每层区分是从红边开始还是从蓝边开始，
 * 如果是红边开始则寻找蓝边可到达的边，如果存在则加入Set，继续进行下一轮。反之蓝边开始也是一样的
 * 最后得到两个数组，分别记录的是从蓝边和红边出发最短的路径，求两者更低的即可。
 *
 * state:done
 */
public class Solution1129 {
    Map<Integer, HashSet<Integer>> redMap = new HashMap<>();
    Map<Integer, HashSet<Integer>> blueMap = new HashMap<>();

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        for (int[] ints : redEdges) {
            HashSet<Integer> set = redMap.get(ints[0]);
            if (set == null) {
                set = new HashSet<Integer>();
                redMap.put(ints[0], set);
            }
            set.add(ints[1]);
        }
        for (int[] ints : blueEdges) {
            HashSet<Integer> set = blueMap.get(ints[0]);
            if (set == null) {
                set = new HashSet<Integer>();
                blueMap.put(ints[0], set);
            }
            set.add(ints[1]);
        }
        int[] redLength = new int[n];
        int[] blueLength = new int[n];
        Arrays.fill(redLength, Integer.MAX_VALUE);
        Arrays.fill(blueLength, Integer.MAX_VALUE);
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        search(0, redLength, blueLength, set, set);
        for (int i = 0; i < redLength.length; i++) {
            redLength[i] = Math.min(redLength[i], blueLength[i]);
            redLength[i] = redLength[i] == Integer.MAX_VALUE ? -1 : redLength[i];
        }
        redLength[0] = 0;
        return redLength;
    }

    private void search(int length, int[] redLength, int[] blueLength, Set<Integer> redSet, Set<Integer> blueSet) {
        length++;
        Set<Integer> nextRedSet = new HashSet<>();
        Set<Integer> nextBlueSet = new HashSet<>();
        for (Integer current : redSet) {
            HashSet<Integer> integers = redMap.getOrDefault(current, new HashSet<>());
            for (int index : integers) {
                if (redLength[index] == Integer.MAX_VALUE) {
                    redLength[index] = length;
                    nextBlueSet.add(index);
                }
            }
        }

        for (Integer current : blueSet) {
            HashSet<Integer> integers = blueMap.getOrDefault(current, new HashSet<>());
            for (int index : integers) {
                if (blueLength[index] == Integer.MAX_VALUE) {
                    blueLength[index] = length;
                    nextRedSet.add(index);
                }
            }
        }
        if (nextRedSet.size() == 0 && nextBlueSet.size() == 0) {
            return;
        }
        search(length, redLength, blueLength, nextRedSet, nextBlueSet);
    }
}