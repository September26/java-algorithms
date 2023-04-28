package com.xt.leetcode;

import java.util.Vector;

/**
 * 2373. 矩阵中的局部最大值
 * 每日一题：2023.03.01
 * 完成日期：2023.03.01
 * 链接：https://leetcode.cn/problems/largest-local-values-in-a-matrix/
 * 描述：
 * 给你一个大小为 n x n 的整数矩阵 grid 。
 * <p>
 * 生成一个大小为 (n - 2) x (n - 2) 的整数矩阵  maxLocal ，并满足：
 * <p>
 * maxLocal[i][j] 等于 grid 中以 i + 1 行和 j + 1 列为中心的 3 x 3 矩阵中的 最大值 。
 * 换句话说，我们希望找出 grid 中每个 3 x 3 矩阵中的最大值。
 * <p>
 * 返回生成的矩阵。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
 * 输出：[[9,9],[8,6]]
 * 解释：原矩阵和生成的矩阵如上图所示。
 * 注意，生成的矩阵中，每个值都对应 grid 中一个相接的 3 x 3 矩阵的最大值。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
 * 输出：[[2,2,2],[2,2,2],[2,2,2]]
 * 解释：注意，2 包含在 grid 中每个 3 x 3 的矩阵中。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == grid.length == grid[i].length
 * 3 <= n <= 100
 * 1 <= grid[i][j] <= 100
 * <p>
 * 解题思路：
 * 最简单的思路，就是每个y,x位置的周围9个值求最大值。
 *
 * <p>
 * state:done
 */
public class Solution2373 {

    public int[][] largestLocal(int[][] grid) {
        int[][] newGrid = new int[grid.length - 2][grid[0].length - 2];
        for (int y = 0; y < newGrid.length; y++) {
            for (int x = 0; x < newGrid[0].length; x++) {
                newGrid[y][x] = getMax(grid, y, x);
            }
        }
        return newGrid;
    }

    private int getMax(int[][] grid, int y, int x) {
        int max = 0;
        for (int i = y; i <= y + 2; i++) {
            System.out.println("");
            for (int j = x; j <= x + 2; j++) {
                int i1 = grid[i][j];
                max = Math.max(max, i1);
            }
        }
        return max;
    }

}