package com.xt.leetcode;

import java.util.Arrays;
import java.util.Vector;

/**
 * 1051. 高度检查器
 * 每日一题：2022.06.13
 * 完成日期：2022.06.13
 * 链接：https://leetcode.cn/problems/height-checker/
 * 描述：
 * 学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 非递减 的高度顺序排成一行。
 * <p>
 * 排序后的高度情况用整数数组 expected 表示，其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。
 * <p>
 * 给你一个整数数组 heights ，表示 当前学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）。
 * <p>
 * 返回满足 heights[i] != expected[i] 的 下标数量 。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：heights = [1,1,4,2,1,3]
 * 输出：3
 * 解释：
 * 高度：[1,1,4,2,1,3]
 * 预期：[1,1,1,2,3,4]
 * 下标 2 、4 、5 处的学生高度不匹配。
 * 示例 2：
 * <p>
 * 输入：heights = [5,1,2,3,4]
 * 输出：5
 * 解释：
 * 高度：[5,1,2,3,4]
 * 预期：[1,2,3,4,5]
 * 所有下标的对应学生高度都不匹配。
 * 示例 3：
 * <p>
 * 输入：heights = [1,2,3,4,5]
 * 输出：0
 * 解释：
 * 高度：[1,2,3,4,5]
 * 预期：[1,2,3,4,5]
 * 所有下标的对应学生高度都匹配。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/height-checker
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 对数组进行拷贝后排序，然后和原数组对比不相同的数量即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution1051 {

    public int heightChecker(int[] heights) {
        int[] copy = new int[heights.length];
        System.arraycopy(heights, 0, copy, 0, heights.length);
        Arrays.sort(copy);
        int sum = 0;
        for (int i = 0; i < heights.length; i++) {
            int height1 = heights[i];
            int height2 = copy[i];
            if (height1 != height2) {
                sum++;
            }
        }
        return sum;
    }
}