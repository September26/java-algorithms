package com.xt.leetcode;

/**
 * 1802. 有界数组中指定下标处的最大值
 * 每日一题：2022.01.04
 * 完成日期：2022.01.04
 * 链接：https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/
 * 描述：
 * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 *
 * nums.length == n
 * nums[i] 是 正整数 ，其中 0 <= i < n
 * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
 * nums 中所有元素之和不超过 maxSum
 * nums[index] 的值被 最大化
 * 返回你所构造的数组中的 nums[index] 。
 *
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4, index = 2,  maxSum = 6
 * 输出：2
 * 解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
 * 示例 2：
 *
 * 输入：n = 6, index = 1,  maxSum = 10
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= n <= maxSum <= 109
 * 0 <= index < n
 * <p>
 * 解题思路：
 * 这题其实就是一道数学题，肯定是有O(1)的算法的。这里为了图省时，就不尝试了。
 * 首先n个位置，每个位置都放1。然后从index开始增加，
 * 先index位置+1，如果sum不超出限制，
 * 则index-1位置+1，index位置+2，index+1位置+1。
 * 持续继续下去，成金字塔状增加，一直到sum超过maxSum。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1802 {

    public int maxValue(int n, int index, int maxSum) {
        int sum = 1;
        int max = 0;
        int addSum = 1;
        while (sum <= (maxSum - n)) {
            max++;
            if (max <= index && max < (n - index)) {
                addSum += 2;
            } else if (max <= index || max < (n - index)) {
                addSum += 1;
            } else {
                max += (maxSum - sum) / n -1;
                break;
            }
            sum += addSum;
        }
        return max + 1;
    }
}