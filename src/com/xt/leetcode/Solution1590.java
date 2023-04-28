package com.xt.leetcode;

import java.util.Arrays;
import java.util.Vector;
import java.util.function.IntConsumer;

/**
 * 1590. 使数组和能被 P 整除
 * 每日一题：2023.03.10
 * 完成日期：2023.03.10
 * 链接：https://leetcode.cn/problems/make-sum-divisible-by-p/
 * 描述：
 * 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
 * <p>
 * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
 * <p>
 * 子数组 定义为原数组中连续的一组元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,1,4,2], p = 6
 * 输出：1
 * 解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [6,3,5,2], p = 9
 * 输出：2
 * 解释：我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3], p = 3
 * 输出：0
 * 解释：和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
 * 示例  4：
 * <p>
 * 输入：nums = [1,2,3], p = 7
 * 输出：-1
 * 解释：没有任何方案使得移除子数组后剩余元素的和被 7 整除。
 * 示例 5：
 * <p>
 * 输入：nums = [1000000000,1000000000,1000000000], p = 3
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= p <= 109
 * <p>
 * 解题思路：
 * 我的解法还是比较差的一种，时间复杂度达到了O(n2)。
 * 首先求出diff值，为总和和p的余数，
 * 然后使用前缀和，分别求长度为1，2，3，4的子数组，
 * 求子数组的值，看是否有满足diff == (i1 % p)的
 * <p>
 * <p>
 * state:done
 */
public class Solution1590 {

    public int minSubarray(int[] nums, int p) {
        int length = nums.length;
        long[] prifixSum = new long[length + 1];
        prifixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            prifixSum[i + 1] = prifixSum[i] + value;
        }
        if (p > prifixSum[length]) {
            return -1;
        }
        if (prifixSum[length] % p == 0) {
            return 0;
        }
        long diff = prifixSum[length] % p;

        for (int i = 1; i < length; i++) {
            for (int index = i; index <= length; index++) {
                long i1 = prifixSum[index] - prifixSum[index - i];
                if (diff == (i1 % p)) {
                    return i;
                }
            }
        }
        return -1;
    }
}