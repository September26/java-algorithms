package com.xt.leetcode;

import java.util.*;

/**
 * 2045. 到达目的地的第二短时间
 * 描述
 * 城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。图中的边用一个二维整数数组 edges 表示，其中每个 edges[i] = [ui, vi] 表示一条节点 ui 和节点 vi 之间的双向连通边。每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time 分钟。
 * <p>
 * 每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都 同时 改变。你可以在 任何时候 进入某个节点，但是 只能 在节点 信号灯是绿色时 才能离开。如果信号灯是  绿色 ，你 不能 在节点等待，必须离开。
 * <p>
 * 第二小的值 是 严格大于 最小值的所有值中最小的值。
 * <p>
 * 例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
 * 给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
 * <p>
 * 注意：
 * <p>
 * 你可以 任意次 穿过任意顶点，包括 1 和 n 。
 * 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
 * 输出：13
 * 解释：
 * 上面的左图展现了给出的城市交通图。
 * 右图中的蓝色路径是最短时间路径。
 * 花费的时间是：
 * - 从节点 1 开始，总花费时间=0
 * - 1 -> 4：3 分钟，总花费时间=3
 * - 4 -> 5：3 分钟，总花费时间=6
 * 因此需要的最小时间是 6 分钟。
 * <p>
 * 右图中的红色路径是第二短时间路径。
 * - 从节点 1 开始，总花费时间=0
 * - 1 -> 3：3 分钟，总花费时间=3
 * - 3 -> 4：3 分钟，总花费时间=6
 * - 在节点 4 等待 4 分钟，总花费时间=10
 * - 4 -> 5：3 分钟，总花费时间=13
 * 因此第二短时间是 13 分钟。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 2, edges = [[1,2]], time = 3, change = 2
 * 输出：11
 * 解释：
 * 最短时间路径是 1 -> 2 ，总花费时间 = 3 分钟
 * 最短时间路径是 1 -> 2 -> 1 -> 2 ，总花费时间 = 11 分钟
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 10^4
 * n - 1 <= edges.length <= min(2 * 10^4, n * (n - 1) / 2)
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * ui != vi
 * 不含重复边
 * 每个节点都可以从其他节点直接或者间接到达
 * 1 <= time, change <= 10^3
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *  
 * 解题思路：
 * 广度搜索的题目，
 * 每一步，都有一个用时最短的方案。那么每一步，也都存在一种用时次短的方案。
 * 所以记录每一个点最短和用时次短的方案，不断的递归找下去。
 * <p>
 * <p>
 * state:
 */
public class Solution2045 {


    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // path[i][0] 表示从 1 到 i 的最短路长度，path[i][1] 表示从 1 到 i 的严格次短路长度
        int[][] path = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(path[i], Integer.MAX_VALUE);
        }
        path[1][0] = 0;
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{1, 0});
        while (path[n][1] == Integer.MAX_VALUE) {
            int[] arr = queue.poll();
            int cur = arr[0], len = arr[1];
            List<Integer> integers = graph[cur];
            for (int next : integers) {
                num++;
                if (len + 1 < path[next][0]) {
                    path[next][0] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                } else if (len + 1 > path[next][0] && len + 1 < path[next][1]) {
                    path[next][1] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < path[n][1]; i++) {
            if (ret % (2 * change) >= change) {
                ret = ret + (2 * change - ret % (2 * change));
            }
            ret = ret + time;
        }
        System.out.println("num:" + num);
        return ret;
    }

    Integer minStep = null;
    Integer min2Step = Integer.MAX_VALUE;
    int[] cache = new int[10001];
    Map<Integer, Set<Integer>> canSelect = new HashMap<>();

    public int secondMinimum2(int n, int[][] edges, int time, int change) {
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int i0 = edge[0];
            int i1 = edge[1];
            Set<Integer> integers = canSelect.get(i0);
            if (integers == null) {
                integers = new HashSet<>();
                canSelect.put(i0, integers);
            }
            integers.add(i1);

            integers = canSelect.get(i1);
            if (integers == null) {
                integers = new HashSet<>();
                canSelect.put(i1, integers);
            }
            integers.add(i0);

        }

        HashSet<Integer> firstLevel = new HashSet<>();
        firstLevel.add(n);
        searchChange(n, firstLevel);
        //计算一下时间就好
        min2Step = Math.min(min2Step, minStep + 2);
        //计算时间
        int currentTime = 0;
        int currentIndex = 0;

        while (true) {
            currentTime += time;
            int i = currentTime / change;
            if (++currentIndex >= min2Step) {
                break;
            }
            if (i % 2 == 1) {
                currentTime = (i + 1) * change;
            }

        }
        System.out.println("num:" + num);
        return currentTime;
    }

    int num = 0;

    //改为非递归
    private void searchChange(int n, Set<Integer> levelSelectSet) {
        int stepLevel = 0;
        cache[n] = 2;
        while (true) {
            if (stepLevel >= n) {
                break;
            }
            if (levelSelectSet.contains(1)) {
                if (minStep == null) {
                    minStep = stepLevel;
                } else if (stepLevel > minStep) {
                    min2Step = stepLevel;
                    break;
                }
            }
            levelSelectSet.remove(1);
            Set<Integer> nextSelectSet = new HashSet<>();
            int[] cacheLocal = new int[n + 1];
            System.arraycopy(cache, 0, cacheLocal, 0, n);
            for (Integer select : levelSelectSet) {
                Set<Integer> canSelectSet = canSelect.get(select);
                for (int i : canSelectSet) {
                    num++;
                    //最优解，一个数字超过2次则不需要添加
                    if (cache[i] == 2) {
                        continue;
                    }
                    if (cache[i] == 1) {
                        cacheLocal[i] = 2;
                    } else {
                        cacheLocal[i] = 1;
                    }
                    nextSelectSet.add(i);
                }
            }
            cache = cacheLocal;
            levelSelectSet = nextSelectSet;
            stepLevel++;
        }
    }
}