package com.xt.leetcode;

import java.util.*;

/**
 * 1615. 最大网络秩
 * 每日一题：2023.03.15
 * 完成日期：2023.03.15
 * 链接：https://leetcode.cn/problems/maximal-network-rank/
 * 描述：
 * n 座城市和一些连接这些城市的道路 roads 共同组成一个基础设施网络。每个 roads[i] = [ai, bi] 都表示在城市 ai 和 bi 之间有一条双向道路。
 * <p>
 * 两座不同城市构成的 城市对 的 网络秩 定义为：与这两座城市 直接 相连的道路总数。如果存在一条道路直接连接这两座城市，则这条道路只计算 一次 。
 * <p>
 * 整个基础设施网络的 最大网络秩 是所有不同城市对中的 最大网络秩 。
 * <p>
 * 给你整数 n 和数组 roads，返回整个基础设施网络的 最大网络秩 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
 * 输出：4
 * 解释：城市 0 和 1 的网络秩是 4，因为共有 4 条道路与城市 0 或 1 相连。位于 0 和 1 之间的道路只计算一次。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
 * 输出：5
 * 解释：共有 5 条道路与城市 1 或 2 相连。
 * 示例 3：
 * <p>
 * 输入：n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
 * 输出：5
 * 解释：2 和 5 的网络秩为 5，注意并非所有的城市都需要连接起来。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 100
 * 0 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 2
 * 0 <= ai, bi <= n-1
 * ai != bi
 * 每对城市之间 最多只有一条 道路相连
 * <p>
 * 解题思路：
 * 构建一个map，key为位置，value为这个位置所有的道路的set，然后按照set的数量从大到小排序。
 * 然后进行双重遍历，两两组合，求出两者之和的最大值。
 * 这里需要注意一点，因为两个最大值组合，哪怕这两者相连，也只会减少1，所以我们求出的value1小于maxValue - 1时，跳出循环即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution1615 {

    public int maximalNetworkRank(int n, int[][] roads) {
        if (roads.length == 0) {
            return 0;
        }

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] road : roads) {
            getList(map, road[0]).add(road[1]);
            getList(map, road[1]).add(road[0]);
        }

        List<Integer> integers = new ArrayList<>(map.keySet());
        integers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o2).size() - map.get(o1).size();
            }
        });

        int result = 0;
        int size = integers.size();
        int maxValue = map.get(integers.get(size - 1)).size();
        for (int i = 0; i < size; i++) {
            Set<Integer> integers1 = map.get(integers.get(i));
            if (integers.size() < maxValue - 1) {
                break;
            }
            int value1 = integers1.size();
            for (int j = i + 1; j < size; j++) {
                int currentValue = value1 + map.get(integers.get(j)).size();
                if (integers1.contains(integers.get(j))) {
                    currentValue--;
                }
                result = Math.max(result, currentValue);
            }
        }
        return result;
    }

    public Set<Integer> getList(Map<Integer, Set<Integer>> map, Integer key) {
        Set<Integer> integers = map.get(key);
        if (integers == null) {
            integers = new HashSet<>();
            map.put(key, integers);
        }
        return integers;
    }
}