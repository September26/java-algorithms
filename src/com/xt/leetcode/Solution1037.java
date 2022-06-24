package com.xt.leetcode;

import java.util.Vector;

/**
 * 1037. 有效的回旋镖
 * 每日一题：2022.06.08
 * 完成日期：2022.06.08
 * 链接：https://leetcode.cn/problems/valid-boomerang/
 * 描述：
 * 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。
 *
 * 回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：points = [[1,1],[2,3],[3,2]]
 * 输出：true
 * 示例 2：
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：false
 *  
 *
 * 提示：
 *
 * points.length == 3
 * points[i].length == 2
 * 0 <= xi, yi <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-boomerang
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先对比看一下三个点是否有重合的。
 * 然后判断三个点是否都在X轴方向上相同。
 * 最后判断三个点两两相减，Y轴差值除以X轴差值，看是否相同。
 * <p>
 * <p>
 * state:done
 */
public class Solution1037 {

    public boolean isBoomerang(int[][] points) {
        int[] point0 = points[0];
        int[] point1 = points[1];
        int[] point2 = points[2];
        if (point0[0] == point1[0] && point0[1] == point1[1]) {
            return false;
        }
        if (point0[0] == point2[0] && point0[1] == point2[1]) {
            return false;
        }
        if (point1[0] == point2[0] && point1[1] == point2[1]) {
            return false;
        }
        if (point0[0] == point1[0] || point0[0] == point2[0]) {
            return point0[0] != point1[0] || point0[0] != point2[0];
        }
        return (double)(point0[1] - point1[1]) / (double)(point0[0] - point1[0]) != (double)(point0[1] - point2[1]) / (double)(point0[0] - point2[0]);
    }
}