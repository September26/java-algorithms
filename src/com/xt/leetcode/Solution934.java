package com.xt.leetcode;


import java.util.*;

/**
 * 934. 最短的桥
 * 每日一题：2022.10.25
 * 完成日期：2022.10.25
 * 链接：https://leetcode.cn/problems/shortest-bridge/
 * 描述：
 * 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
 * <p>
 * 岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
 * <p>
 * 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
 * <p>
 * 返回必须翻转的 0 的最小数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [[0,1],[1,0]]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] 为 0 或 1
 * grid 中恰有两个岛
 * <p>
 * 解题思路：
 * 由题目描述可知，grid中只存在三种类型的点，归属陆地A的点，归属陆地B的点，归属水域的点。
 * 那么针对于归属水域的这些点，我们就可以依次的求出每个点距离陆地A最近的点。
 * 分别用setA和setB代表归属陆地A和陆地B的点，针对水域，我们直接修改其值为距离setA陆地最近距离即可。
 * 我们先求出setA和setB的集合，然后针对setA，分别求出1步，2步，3步..的范围，直到没有下一级的范围。
 * 最后，我们只要遍历B中所有的点，找到一步可达的水域中最小值即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution934 {
    int[][] intss = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int shortestBridge(int[][] grid) {
        Set<String> setA = new HashSet<>();
        Set<String> setB = new HashSet<>();
        outer:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (setA.size() == 0) {
                    createSet(grid, setA, i, j);
                    continue;
                }
                if (setA.contains(i + "_" + j)) {
                    continue;
                }
                createSet(grid, setB, i, j);
                break outer;
            }
        }
        //
        searchStep(grid, setA, 0);

        int minValue = Integer.MAX_VALUE;
        int length = grid.length;
        for (String location : setB) {
            String[] s = location.split("_");
            int y = Integer.parseInt(s[0]);
            int x = Integer.parseInt(s[1]);
            int current = Integer.MAX_VALUE;
            for (int[] ints : intss) {
                int diffY = ints[0];
                int diffX = ints[1];
                int newY = y + diffY;
                int newX = x + diffX;
                if (newX < 0 || newY < 0 || newX > (length - 1) || newY > length - 1) {
                    continue;
                }
                if (setB.contains(newY + "_" + newX)) {
                    continue;
                }
                int step = grid[newY][newX];
                if (step == 0) {
                    continue;
                }
                current = Math.min(current, step);
            }
            minValue = Math.min(current, minValue);
        }
        return minValue;
    }

    private void createSet(int[][] grid, Set<String> set, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length) {
            return;
        }
        int value = grid[i][j];
        String location = i + "_" + j;
        if (value == 0 || set.contains(location)) {
            return;
        }
        set.add(location);
        for (int[] ints : intss) {
            createSet(grid, set, i + ints[0], j + ints[1]);
        }
    }

    private void searchStep(int[][] grid, Set<String> set, int step) {
        step++;
        int length = grid.length;
        Set<String> newSet = new HashSet<>();
        for (String location : set) {
            String[] s = location.split("_");
            int y = Integer.parseInt(s[0]);
            int x = Integer.parseInt(s[1]);
            for (int[] ints : intss) {
                int newY = y + ints[0];
                int newX = x + ints[1];
                if (newY < 0 || newY > length - 1 || newX < 0 || newX > length - 1) {
                    continue;
                }
                if (grid[newY][newX] != 0) {
                    continue;
                }
                grid[newY][newX] = step;
                newSet.add(newY + "_" + newX);
            }
        }
        if (newSet.size() == 0) {
            return;
        }
        searchStep(grid, newSet, step);
    }
}