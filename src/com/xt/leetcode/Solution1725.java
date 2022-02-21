package com.xt.leetcode;

import java.util.Vector;

/**
 * 1725. 可以形成最大正方形的矩形数目
 * 日期：2022.2.4
 * 描述
 * 给你一个数组 rectangles ，其中 rectangles[i] = [li, wi] 表示第 i 个矩形的长度为 li 、宽度为 wi 。
 * <p>
 * 如果存在 k 同时满足 k <= li 和 k <= wi ，就可以将第 i 个矩形切成边长为 k 的正方形。例如，矩形 [4,6] 可以切成边长最大为 4 的正方形。
 * <p>
 * 设 maxLen 为可以从矩形数组 rectangles 切分得到的 最大正方形 的边长。
 * <p>
 * 请你统计有多少个矩形能够切出边长为 maxLen 的正方形，并返回矩形 数目 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：rectangles = [[5,8],[3,9],[5,12],[16,5]]
 * 输出：3
 * 解释：能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
 * 最大正方形的边长为 5 ，可以由 3 个矩形切分得到。
 * 示例 2：
 * <p>
 * 输入：rectangles = [[2,3],[3,7],[4,3],[3,7]]
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= rectangles.length <= 1000
 * rectangles[i].length == 2
 * 1 <= li, wi <= 109
 * li != wi
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-rectangles-that-can-form-the-largest-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题解题思路其实就是先求数组中两边边的短边，然后记一下短边的长度。如果等于最小长度则num++
 * <p>
 * <p>
 * state:done
 */
public class Solution1725 {

    public int countGoodRectangles(int[][] rectangles) {
        int max = Integer.MIN_VALUE;
        int num = 0;
        for (int i = 0; i < rectangles.length; i++) {
            int[] rectangle = rectangles[i];
            int min = Math.min(rectangle[0], rectangle[1]);
            if (min > max) {
                max = min;
                num = 1;
                continue;
            }
            if (min == max) {
                num++;
            }
        }

        return num;
    }
}