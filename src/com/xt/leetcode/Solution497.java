package com.xt.leetcode;

import java.util.*;

/**
 * 497.非重叠矩形中的随机点
 * 每日一题：2022.06.09
 * 完成日期：2022.06.09
 * 链接：https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles/
 * 描述：
 * 给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角点。设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。
 *
 * 在给定的矩形覆盖的空间内的任何整数点都有可能被返回。
 *
 * 请注意 ，整数点是具有整数坐标的点。
 *
 * 实现 Solution 类:
 *
 * Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
 * int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入:
 * ["Solution", "pick", "pick", "pick", "pick", "pick"]
 * [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
 * 输出:
 * [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
 *
 * 解释：
 * Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
 * solution.pick(); // 返回 [1, -2]
 * solution.pick(); // 返回 [1, -1]
 * solution.pick(); // 返回 [-1, -2]
 * solution.pick(); // 返回 [-2, -2]
 * solution.pick(); // 返回 [0, 0]
 *  
 *
 * 提示：
 *
 * 1 <= rects.length <= 100
 * rects[i].length == 4
 * -109 <= ai < xi <= 109
 * -109 <= bi < yi <= 109
 * xi - ai <= 2000
 * yi - bi <= 2000
 * 所有的矩形不重叠。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 求出所有区域的面积之和，因为是按照点来算的，所以宽和高都要+1。
 * 然后以面积和为底数秋随机数，确定随机数属于哪个区域(search方法)，然后在求出属于这个区域的第几个，则对应的位置就出来了
 * <p>
 * <p>
 * state:done
 */
public class Solution497 {

    public static class Solution {
        int allArea = 0;
        List<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        int[][] rects;

        public Solution(int[][] rects) {
            this.rects = rects;
            list.add(0);
            for (int[] rect : rects) {
                int width = rect[2] - rect[0] + 1;
                int height = rect[3] - rect[1] + 1;
                int area = width * height;
                allArea += area;
                list.add(allArea);
            }
        }

        public int[] pick() {
            int randomInt = random.nextInt(allArea);
            int i = search(list, randomInt);
            int index = randomInt - list.get(i);
            int[] rect = rects[i];
            int y = index / (rect[2] - rect[0] + 1);
            int x = index % (rect[2] - rect[0] + 1);
            int[] ints = {rect[0] + x, rect[1] + y};
            return ints;
        }

        private int search(List<Integer> list, int input) {
            int left = 0;
            int right = list.size();
            int result = 0;
            while (left <= right) {
                int middle = (right + left) / 2;
                int value = list.get(middle);
                if (input == value) {
                    result = middle;
                    break;
                }
                if (input < value) {
                    right = middle - 1;
                    continue;
                }
                result = middle;
                left = middle + 1;
            }
            return result;
        }
    }
}