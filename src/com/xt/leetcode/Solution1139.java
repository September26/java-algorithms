package com.xt.leetcode;


/**
 * 1139. 最大的以 1 为边界的正方形
 * 每日一题：2023.02.17
 * 完成日期：2023.02.17
 * 链接：https://leetcode.cn/problems/largest-1-bordered-square/
 * 描述：
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：9
 * 示例 2：
 * <p>
 * 输入：grid = [[1,1,0,0]]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= grid.length <= 100
 * 1 <= grid[0].length <= 100
 * grid[i][j] 为 0 或 1
 * <p>
 * 解题思路：
 * 这个题的解题核心就是构建bottom和right，分别记录每个位置上，横向或者纵向连续1的个数。
 * 然后开始遍历，先找bottom和right边连续1的个数，比如shortSide，如果符合目标。
 * 在去查找top和left边，这时候虽然有可能长度不如shortSide，但是仍然有可能比之前的maxSideLength要大，所以要检查maxSideLength和shortSide之间的所有范围。
 * state:done
 */
public class Solution1139 {

    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        //横向连续的1的数量
        int[][] bottom = new int[n][m];
        //纵向连续的1的数量
        int[][] right = new int[n][m];

        int maxSideLength = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (j == 0) {
                    bottom[i][j] = 1;
                } else {
                    bottom[i][j] = bottom[i][j - 1] + 1;
                }
                if (i == 0) {
                    right[i][j] = 1;
                } else {
                    right[i][j] = right[i - 1][j] + 1;
                }

                int shortSide = Math.min(bottom[i][j], right[i][j]);
                if (shortSide <= maxSideLength) {
                    continue;
                }
                while (shortSide > maxSideLength) {
                    if (bottom[i - shortSide + 1][j] >= shortSide && right[i][j - shortSide + 1] >= shortSide) {
                        maxSideLength = Math.max(maxSideLength, shortSide);
                    }
                    shortSide--;
                }

            }
        }
        return maxSideLength * maxSideLength;
    }

}