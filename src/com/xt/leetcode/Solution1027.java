package com.xt.leetcode;


import java.util.*;

/**
 * 1027. 最长等差数列
 * 每日一题：2023.04.24
 * 完成日期：2023.04.24
 * 链接：https://leetcode.cn/problems/longest-arithmetic-subsequence/
 * 描述：
 * 给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。
 * <p>
 * 回想一下，nums 的子序列是一个列表 nums[i1], nums[i2], ..., nums[ik] ，且 0 <= i1 < i2 < ... < ik <= nums.length - 1。并且如果 seq[i+1] - seq[i]( 0 <= i < seq.length - 1) 的值都相同，那么序列 seq 是等差的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,6,9,12]
 * 输出：4
 * 解释：
 * 整个数组是公差为 3 的等差数列。
 * 示例 2：
 * <p>
 * 输入：nums = [9,4,7,2,10]
 * 输出：3
 * 解释：
 * 最长的等差子序列是 [4,7,10]。
 * 示例 3：
 * <p>
 * 输入：nums = [20,1,15,3,10,5,8]
 * 输出：4
 * 解释：
 * 最长的等差子序列是 [20,15,10,5]。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 1000
 * 0 <= nums[i] <= 500
 * <p>
 * 解题思路：
 * 这题的核心就是如何设计一个动态规划的数据源。
 * 我们设计一个二维数组dp，dp[i][diff]代表以第i位结束，并且差值为diff的等差数列的最大长度。
 * 所以如果我们求dp[i+1][diff]，则需要遍历从1到i位置上，所有的可能。
 * 比如nums[i+1]-nums[i]=5，则dp[i+1][5]=dp[i][5]+1;
 * 比如nums[i+1]-nums[i-1]=3，则dp[i+1][5]=dp[i-1][3]+1;
 * 这样，就出i+1位置所有的等差数列的最大长度。
 * 就这样遍历，直到结束，求出最大值。
 * <p>
 * state:done
 */
public class Solution1027 {

    public int longestArithSeqLength(int[] nums) {
        int[][] dp = new int[nums.length][1001];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            for (int j = 0; j < i; j++) {
                int diff = value - nums[j] + 500;
                int maxLength = dp[j][diff];
                dp[i][diff] = maxLength + 1;
                max = Math.max(max, dp[i][diff]);
            }
        }
        return max + 1;
    }
}