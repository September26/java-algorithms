package com.xt.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 剑指 Offer 47. 礼物的最大价值
 * 每日一题：2022.03.08
 * 完成日期：2022.08.08
 * 链接：https://leetcode.cn/problems/Jf1JuT/
 * 描述：
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 * <p>
 * 解题思路：
 * DP值题，首先求出走一步时可能拿到的最大值，然后据此推断出走2步是的最大值，一步步的往下推断。
 * 这种中间肯定会有很多重复计算的位置，所以我们设置一个二维数组valueGrid，来记录每个位置可能的最大值。
 * <p>
 * <p>
 * state:done
 */
public class Offer47 {

    public int maxValue(int[][] grid) {
        int[][] valueGrid = new int[grid.length][grid[0].length];
        valueGrid[0][0] = grid[0][0];
        for (int i = 1; i < grid.length + grid[0].length; i++) {
            searchMaxValue(grid, valueGrid, i);
        }
        return valueGrid[grid.length - 1][grid[0].length - 1];
    }

    private void searchMaxValue(int[][] grid, int[][] valueGrid, int step) {
        for (int m = 0; m <= step; m++) {
            int n = step - m;
            if (n >= valueGrid.length) {
                continue;
            }
            if (m >= valueGrid[0].length) {
                break;
            }
            int value = grid[n][m];
            int newValue = 0;
            if (n > 0) {
                newValue = valueGrid[n - 1][m] + value;
            }
            if (m > 0) {
                newValue = Math.max(newValue, valueGrid[n][m - 1] + value);
            }
            valueGrid[n][m] = newValue;
        }
    }
}