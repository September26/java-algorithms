package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 1765. 地图中的最高点
 * <p>
 * 日期：2021.1.29
 * 描述
 * 给你一个大小为 m x n 的整数矩阵 isWater ，它代表了一个由 陆地 和 水域 单元格组成的地图。
 * <p>
 * 如果 isWater[i][j] == 0 ，格子 (i, j) 是一个 陆地 格子。
 * 如果 isWater[i][j] == 1 ，格子 (i, j) 是一个 水域 格子。
 * 你需要按照如下规则给每个单元格安排高度：
 * <p>
 * 每个格子的高度都必须是非负的。
 * 如果一个格子是 水域 ，那么它的高度必须为 0 。
 * 任意相邻的格子高度差 至多 为 1 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）
 * 找到一种安排高度的方案，使得矩阵中的最高高度值 最大 。
 * <p>
 * 请你返回一个大小为 m x n 的整数矩阵 height ，其中 height[i][j] 是格子 (i, j) 的高度。如果有多种解法，请返回 任意一个 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/map-of-highest-peak
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 我的思路是依次填充。先根据isWater生成一个新的二维数组，数组中的值全部为-1，代表还未填充，计算出所有为0的集合。
 * 遍历集合，这时候填充的是结果为1的，然后所有填充的位置记录下来加到新集合中。
 * 继续遍历新的集合，直到新集合长度为0。
 * <p>
 * <p>
 * state:
 */
public class Solution1765 {
    public int[][] highestPeak(int[][] isWater) {

        List<int[]> list = new ArrayList<>();
        int[][] integers = new int[isWater.length][isWater[0].length];
        for (int i = 0; i < isWater.length; i++) {
            for (int j = 0; j < isWater[0].length; j++) {
                if (isWater[i][j] == 1) {
                    integers[i][j] = 0;
                    addList(list, i, j);
                } else {
                    integers[i][j] = -1;
                }
            }
        }

        int current = 0;

        while (list.size() > 0) {
            List<int[]> newList = new ArrayList<>();
            for (int[] ints : list) {
                int i = ints[0];
                int j = ints[1];
                //填充上下左右
                if (fill(integers, i + 1, j, current + 1)) {
                    addList(newList, i + 1, j);
                }
                if (fill(integers, i - 1, j, current + 1)) {
                    addList(newList, i - 1, j);
                }
                if (fill(integers, i, j + 1, current + 1)) {
                    addList(newList, i, j + 1);
                }
                if (fill(integers, i, j - 1, current + 1)) {
                    addList(newList, i, j - 1);
                }
            }
            current++;
            list = newList;
        }
        return integers;
    }

    private void addList(List<int[]> list, int x, int y) {
        int[] ints = {x, y};
        list.add(ints);
    }

    private boolean fill(int[][] intss, int i, int j, int value) {
        if (i >= intss.length || i < 0) {
            return false;
        }
        if (j >= intss[0].length || j < 0) {
            return false;
        }
        if (intss[i][j] == -1) {
            intss[i][j] = value;
            return true;
        }
        return false;

    }

}