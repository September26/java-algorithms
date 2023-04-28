package com.xt.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 1637. 两点之间不包含任何点的最宽垂直区域
 * 每日一题：2023.3.30
 * 完成日期：2023.3.30
 * 链接：https://leetcode.cn/problems/widest-vertical-area-between-two-points-containing-no-points/
 * 描述：
 * 给你 n 个二维平面上的点 points ，其中 points[i] = [xi, yi] ，请你返回两点之间内部不包含任何点的 最宽垂直区域 的宽度。
 * 垂直区域 的定义是固定宽度，而 y 轴上无限延伸的一块区域（也就是高度为无穷大）。 最宽垂直区域 为宽度最大的一个垂直区域。
 * 请注意，垂直区域 边上 的点 不在 区域内。
 * 解题思路：
 * 这题有一点莫名其妙，直接给x轴排序，求两两之间的最大值即可。
 * state:done
 */
public class Solution1637 {

    public int maxWidthOfVerticalArea(int[][] points) {
        List<Integer> collect = Arrays.stream(points).map(ints -> ints[0]).sorted(Comparator.comparingInt(o -> o)).collect(Collectors.toList());
        int abs = 0;
        for (int i = 1; i < collect.size(); i++) {
            abs = Math.max(abs, collect.get(i) - collect.get(i - 1));
        }
        return abs;
    }
}