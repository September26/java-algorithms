package com.xt.leetcode;

import java.util.Vector;

/**
 * 1031. 两个非重叠子数组的最大和
 * 每日一题：2023.04.26
 * 完成日期：2023.04.26
 * 链接：https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays/
 * 描述：
 * 给你一个整数数组 nums 和两个整数 firstLen 和 secondLen，请你找出并返回两个非重叠 子数组 中元素的最大和，长度分别为 firstLen 和 secondLen 。
 * <p>
 * 长度为 firstLen 的子数组可以出现在长为 secondLen 的子数组之前或之后，但二者必须是不重叠的。
 * <p>
 * 子数组是数组的一个 连续 部分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
 * 输出：20
 * 解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
 * 示例 2：
 * <p>
 * 输入：nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
 * 输出：29
 * 解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
 * 示例 3：
 * <p>
 * 输入：nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
 * 输出：31
 * 解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= firstLen, secondLen <= 1000
 * 2 <= firstLen + secondLen <= 1000
 * firstLen + secondLen <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * <p>
 * 解题思路：
 * 这题看长度，应该是一个n2时间复杂度的题目。
 * 首先求出前缀和，这样就方便求出某个区间的和。
 * 然后就可以遍历nums数组，分别枚举firstLen长度的子数组求和，然后确定firstLen的位置后，在在剩余的空间内枚举所有secondLen长度子数组求和。
 * 求出两者之和最大的那个就是本题的目标值。
 * <p>
 * <p>
 * state:done
 */
public class Solution1031 {

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int maxSum = 0;
        for (int i = firstLen; i < prefixSum.length; i++) {
            for (int j = secondLen; j < prefixSum.length; j++) {
                //如果有重叠，则continue
                boolean flag = j - secondLen >= i;
                flag |= j <= i - firstLen;
                if (!flag) {
                    continue;
                }
                int sum1 = prefixSum[i] - prefixSum[i - firstLen];
                int sum2 = prefixSum[j] - prefixSum[j - secondLen];
                maxSum = Math.max(sum1 + sum2, maxSum);
            }
        }
        return maxSum;
    }
}