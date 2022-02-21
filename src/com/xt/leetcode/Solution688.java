package com.xt.leetcode;

import java.util.*;

/**
 * 688.骑士在棋盘上的概率
 * 日期：2022.2.17
 * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard/
 * <p>
 * 描述
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * <p>
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * <p>
 * <p>
 * <p>
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * <p>
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 * <p>
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 * 示例 2：
 * <p>
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:
 */
public class Solution688 {

    static int[][] dirs = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};

    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        for (int step = 0; step <= k; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (step == 0) {
                        dp[step][i][j] = 1;
                    } else {
                        for (int[] dir : dirs) {
                            int ni = i + dir[0], nj = j + dir[1];
                            if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                                dp[step][i][j] += dp[step - 1][ni][nj] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }


    /**
     * @param n      宽度
     * @param k      步数
     * @param row    起点x
     * @param column 起点y
     * @return
     */
    public double knightProbability2(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        if (n <= 2) {
            return 0;
        }
        Map<String, Set<String>> map = new HashMap<>();

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                String key = x + "_" + y;
                //8中可能
                Set<String> strings = haveInNum(x, y, n);
                map.put(key, strings);
            }
        }
        float fenzi = 0;
        float fenmu = 1;
        Map<String, Float> step = new HashMap<>();
        Map<String, Float> nextStep = new HashMap<>();
        step.put(getKey(row, column), 1f);
        for (int i = 0; i < k; i++) {
            nextStep.clear();
            fenzi = 0;
            for (String key : step.keySet()) {
                Set<String> strings = map.get(key);
                Float value = step.get(key);
                for (String key2 : strings) {
                    nextStep.put(key2, nextStep.getOrDefault(key2, 0f) + value);
                }
                fenzi += value * strings.size();
            }
            fenmu *= 8;
            System.out.println(fenzi + "/" + fenmu);
            step.clear();
            step.putAll(nextStep);
        }
        return (double) fenzi / (double) fenmu;
    }

    private Set<String> haveInNum(int x, int y, int n) {
        Set<String> set = new HashSet<>();
        addSet(set, x + 2, y + 1, n);
        addSet(set, x + 2, y - 1, n);
        addSet(set, x + 1, y + 2, n);
        addSet(set, x + 1, y - 2, n);
        addSet(set, x - 2, y + 1, n);
        addSet(set, x - 2, y - 1, n);
        addSet(set, x - 1, y + 2, n);
        addSet(set, x - 1, y - 2, n);
        return set;
    }

    private void addSet(Set<String> set, int x, int y, int n) {
        if (x < 0 || x >= n) {
            return;
        }
        if (y < 0 || y >= n) {
            return;
        }
        set.add(getKey(x, y));
    }

    private String getKey(int x, int y) {
        return x + "_" + y;
    }

}