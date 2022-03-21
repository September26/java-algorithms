package com.xt.leetcode;

import java.util.*;

/**
 * 2039. 网络空闲的时刻
 * 每日一题：2022.03.20
 * 完成日期：2022.03.20
 * 链接：https://leetcode-cn.com/problems/the-time-when-the-network-becomes-idle/
 * 描述：
 * 给你一个有 n 个服务器的计算机网络，服务器编号为 0 到 n - 1 。同时给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示服务器 ui 和 vi 之间有一条信息线路，在 一秒 内它们之间可以传输 任意 数目的信息。再给你一个长度为 n 且下标从 0 开始的整数数组 patience 。
 * <p>
 * 题目保证所有服务器都是 相通 的，也就是说一个信息从任意服务器出发，都可以通过这些信息线路直接或间接地到达任何其他服务器。
 * <p>
 * 编号为 0 的服务器是 主 服务器，其他服务器为 数据 服务器。每个数据服务器都要向主服务器发送信息，并等待回复。信息在服务器之间按 最优 线路传输，也就是说每个信息都会以 最少时间 到达主服务器。主服务器会处理 所有 新到达的信息并 立即 按照每条信息来时的路线 反方向 发送回复信息。
 * <p>
 * 在 0 秒的开始，所有数据服务器都会发送各自需要处理的信息。从第 1 秒开始，每 一秒最 开始 时，每个数据服务器都会检查它是否收到了主服务器的回复信息（包括新发出信息的回复信息）：
 * <p>
 * 如果还没收到任何回复信息，那么该服务器会周期性 重发 信息。数据服务器 i 每 patience[i] 秒都会重发一条信息，也就是说，数据服务器 i 在上一次发送信息给主服务器后的 patience[i] 秒 后 会重发一条信息给主服务器。
 * 否则，该数据服务器 不会重发 信息。
 * 当没有任何信息在线路上传输或者到达某服务器时，该计算机网络变为 空闲 状态。
 * <p>
 * 请返回计算机网络变为 空闲 状态的 最早秒数 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：edges = [[0,1],[1,2]], patience = [0,2,1]
 * 输出：8
 * 解释：
 * 0 秒最开始时，
 * - 数据服务器 1 给主服务器发出信息（用 1A 表示）。
 * - 数据服务器 2 给主服务器发出信息（用 2A 表示）。
 * <p>
 * 1 秒时，
 * - 信息 1A 到达主服务器，主服务器立刻处理信息 1A 并发出 1A 的回复信息。
 * - 数据服务器 1 还没收到任何回复。距离上次发出信息过去了 1 秒（1 < patience[1] = 2），所以不会重发信息。
 * - 数据服务器 2 还没收到任何回复。距离上次发出信息过去了 1 秒（1 == patience[2] = 1），所以它重发一条信息（用 2B 表示）。
 * <p>
 * 2 秒时，
 * - 回复信息 1A 到达服务器 1 ，服务器 1 不会再重发信息。
 * - 信息 2A 到达主服务器，主服务器立刻处理信息 2A 并发出 2A 的回复信息。
 * - 服务器 2 重发一条信息（用 2C 表示）。
 * ...
 * 4 秒时，
 * - 回复信息 2A 到达服务器 2 ，服务器 2 不会再重发信息。
 * ...
 * 7 秒时，回复信息 2D 到达服务器 2 。
 * <p>
 * 从第 8 秒开始，不再有任何信息在服务器之间传输，也不再有信息到达服务器。
 * 所以第 8 秒是网络变空闲的最早时刻。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：edges = [[0,1],[0,2],[1,2]], patience = [0,10,10]
 * 输出：3
 * 解释：数据服务器 1 和 2 第 2 秒初收到回复信息。
 * 从第 3 秒开始，网络变空闲。
 *  
 * <p>
 * 提示：
 * <p>
 * n == patience.length
 * 2 <= n <= 105
 * patience[0] == 0
 * 对于 1 <= i < n ，满足 1 <= patience[i] <= 105
 * 1 <= edges.length <= min(105, n * (n - 1) / 2)
 * edges[i].length == 2
 * 0 <= ui, vi < n
 * ui != vi
 * 不会有重边。
 * 每个服务器都直接或间接与别的服务器相连。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-time-when-the-network-becomes-idle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先先找0能达到的节点集合，那么这个集合就是1步可以到达的。
 * 然后通过这个集合在找这个集合中的数字可以到达的，那就是2步可以到达的，递归下去，求出所有可到达的。
 * 然后分别求1步，2步，3步...中的集合，每个数花费的时间。
 * 所花时间分为三种情况，
 * 1：patience中的时间大于等于来回时间，则此时使用来回时间即可，2*step。
 * 2：来回时间恰好等于patience中的时间的倍数，此时花费时间为：2 * step + (2 * step / time - 1) * time
 * 3：来回时间不等于patience中的时间的倍数，此时花费时间为：2 * step + (2 * step / time) * time
 * 其中，2*step/time代表等待多少个周期。
 * <p>
 * <p>
 * state:done
 */
public class Solution2039 {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<Integer>();
        }
        boolean[] visit = new boolean[n];
        for (int[] v : edges) {
            adj[v[0]].add(v[1]);
            adj[v[1]].add(v[0]);
        }

        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.offer(0);
        visit[0] = true;
        int dist = 1;
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int v : adj[curr]) {
                    if (visit[v]) {
                        continue;
                    }
                    queue.offer(v);
                    int time = patience[v] * ((2 * dist - 1) / patience[v]) + 2 * dist + 1;
                    ans = Math.max(ans, time);
                    visit[v] = true;
                }
            }
            dist++;
        }
        return ans;
    }


    public int networkBecomesIdle2(int[][] edges, int[] patience) {
        Set<Integer> allSet = new HashSet<>();
        Set<Integer>[] sets = new Set[patience.length];
        for (int i = 0; i < patience.length; i++) {
            if (i != 0) {
                allSet.add(i);
            }
            sets[0] = new HashSet<>();
        }
        for (int[] ints : edges) {
            int i1 = ints[0];
            int i2 = ints[1];

            Set<Integer> integers1 = sets[i1];
            if (i2 > 0) {
                integers1.add(i2);
            }

            Set<Integer> integers2 = sets[i2];
            if (i1 > 0) {
                integers2.add(i1);
            }
        }
        Map<Integer, Set<Integer>> levelMap = new HashMap<>();
        search(1, levelMap, allSet, sets[0], sets);
        System.out.println(levelMap.size());
        int max = 0;
        for (int step : levelMap.keySet()) {
            Set<Integer> integers = levelMap.get(step);
            for (int index : integers) {
                int time = patience[index];
                int minTime = step * 2;
                if (minTime <= time) {
                    //使用stepTime
                    max = Math.max(minTime, max);
                    continue;
                }
                if ((step * 2) % time == 0) {
                    max = Math.max(2 * step + (2 * step / time - 1) * time, max);
                    continue;
                }
                max = Math.max(2 * step + (2 * step / time) * time, max);
            }
        }
        return max + 1;
    }

    private void search(int level, Map<Integer, Set<Integer>> levelMap, Set<Integer> allSet, Set<Integer> set, Set<Integer>[] sets) {
        levelMap.put(level, set);
        allSet.removeAll(set);
        Set<Integer> newSet = new HashSet<>();
        for (Integer i : set) {
            Set<Integer> integers = sets[i];
            if (integers == null) {
                continue;
            }
            for (Integer value : integers) {
                if (allSet.contains(value)) {
                    newSet.add(value);
                    allSet.remove(value);
                }
            }
        }
        if (newSet.size() == 0) {
            return;
        }
        search(level + 1, levelMap, allSet, newSet, sets);
    }

}