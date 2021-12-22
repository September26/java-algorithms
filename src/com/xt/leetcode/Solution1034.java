package com.xt.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 1034. 边界着色
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 * <p>
 * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
 * <p>
 * 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
 * <p>
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * 输出：[[3,3],[3,2]]
 * 示例 2：
 * <p>
 * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
 * 输出：[[1,3,3],[2,3,3]]
 * 示例 3：
 * <p>
 * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
 * 输出：[[2,2,2],[2,1,2],[2,2,2]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coloring-a-border
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先拷贝一份grid，这样对copy进行操作，就不会影响正常的判断。
 * 然后从row，col开始分别向上，下，左，右四个方向递归寻找，如果四个方向任意一个方向是表格的边界或者是不同颜色值时，则表示是边界。
 *
 * state:done
 * 日期:2021.12.07
 */
public class Solution1034 {
    Set<String> set = new HashSet<>();

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int[][] copy = copyGrid(grid);
        colorBorderBy(grid, copy, row, col, color);
        return copy;

    }

    private int[][] copyGrid(int[][] grid) {
        int[][] copy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, grid[i].length);
        }
        return copy;
    }

    /**
     * @param grid
     * @param row
     * @param col
     * @param color
     */
    public void colorBorderBy(int[][] grid, int[][] copy, int row, int col, int color) {
        if (set.contains(row + "_" + col)) {
            return;
        }
        set.add(row + "_" + col);
        //四个都有值则表明不是边界，否则就是边界
        int yLength = grid.length;
        int xLength = grid[0].length;
        int currentColor = grid[row][col];
        boolean isBianjie = false;
        //x-是否边界
        if (col > 0 && currentColor == grid[row][col - 1]) {
            colorBorderBy(grid, copy, row, col - 1, color);
        } else {
            isBianjie = true;
        }

        //x+
        if (col < xLength - 1 && currentColor == grid[row][col + 1]) {
            colorBorderBy(grid, copy, row, col + 1, color);
        } else {
            isBianjie = true;
        }

        //y-
        if (row > 0 && currentColor == grid[row - 1][col]) {
            colorBorderBy(grid, copy, row - 1, col, color);
        } else {
            isBianjie = true;
        }

        //y+
        if (row < yLength - 1 && currentColor == grid[row + 1][col]) {
            colorBorderBy(grid, copy, row + 1, col, color);
        } else {
            isBianjie = true;
        }

        if (isBianjie) {
            copy[row][col] = color;
        }
    }
}