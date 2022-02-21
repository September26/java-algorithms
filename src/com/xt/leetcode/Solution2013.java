package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2013.检测正方形
 * 描述
 * 给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：
 * <p>
 * 添加 一个在数据流中的新点到某个数据结构中。可以添加 重复 的点，并会视作不同的点进行处理。
 * 给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 面积为正 的 轴对齐正方形 ，统计 满足该要求的方案数目。
 * 轴对齐正方形 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。
 * <p>
 * 实现 DetectSquares 类：
 * <p>
 * DetectSquares() 使用空数据结构初始化对象
 * void add(int[] point) 向数据结构添加一个新的点 point = [x, y]
 * int count(int[] point) 统计按上述方式与点 point = [x, y] 共同构造 轴对齐正方形 的方案数。
 * 提示：
 * <p>
 * point.length == 2
 * 0 <= x, y <= 1000
 * 调用 add 和 count 的 总次数 最多为 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/detect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 我的思路是这样的，首先构造一个二维数组，每个坐标都记录到这个二维数组上，如果重复，则对应坐标的位置的值+1。
 * 并且构造一个数组类型的Set，记录Y轴相同的所有点。比如1,2和2，2，其y轴相同，则都记录到一个set上。
 * add的时候，同时给上面两个数据结构赋值。
 * count的时候，首先根据y值获取其一行有多少点，然后依次遍历，计算每个点和原点之间的距离。
 * 距离计算好之后，则一条边已经定了，那么只存在两种可能，即在这条边的上面构成正方形或者下方构成正方形这两种可能。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution2013 {

    public static class DetectSquares {

        int[][] data = new int[1001][1001];
        Set<Integer>[] list = new HashSet[1001];

        public DetectSquares() {
        }

        public void add(int[] point) {
            int x = point[0];
            int y = point[1];
            Set<Integer> integers = list[y];
            if (integers == null) {
                list[y] = new HashSet<>();
                integers = list[y];
            }
            integers.add(point[0]);
            data[y][x]++;
        }

        public int count(int[] point) {
            int x = point[0];
            int y = point[1];
            if (list[y] == null) {
                return 0;
            }
            int sum = 0;
            for (Integer i : list[y]) {
                int length = x - i;
                if (length == 0) {
                    continue;
                }
                sum += haveSum(x, y, length);
            }
            return sum;
        }

        private int haveSum(int x, int y, int length) {
            int y1 = y + length;
            int y2 = y - length;
            int sum1 = 0;
            int sum2 = 0;
            if (y1 >= 0 && y1 <= 1000) {
                sum1 = data[y1][x] * data[y1][x - length] * data[y][x - length];
            }
            if (y2 >= 0 && y2 <= 1000) {
                sum2 = data[y2][x] * data[y2][x - length] * data[y][x - length];
            }
            return sum1 + sum2;
        }
    }
}