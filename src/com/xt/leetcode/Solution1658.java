package com.xt.leetcode;


import java.util.Arrays;

/**
 * 1658. 将 x 减到 0 的最小操作数
 * 每日一题：2022.01.07
 * 完成日期：2022.01.07
 * 链接：https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
 * 描述：
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
 * <p>
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,4,2,3], x = 5
 * 输出：2
 * 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 * 示例 2：
 * <p>
 * 输入：nums = [5,6,7,8,9], x = 4
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [3,2,20,1,1,3], x = 10
 * 输出：5
 * 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 1 <= x <= 10^9
 * <p>
 * 解题思路：
 * 滑动区间方案来解题。
 * 使用left和right分别记录区间的左右边界，使用sum来计算区间之和。
 * 但是sum求的是(0,left)和(right,length)范围的数值之和。
 * 我们不断的right++,来使的sum值降低，
 * 如果sum<x，则说明sum小了，此时需要从(0,left)范围进行补充，则left++。
 * 如果sum=x，则说明符合条件，记录此时的长度，长度为length - right + left + 1；
 * 如果sum>=x，则继续right++,使sum降低。
 * <p>
 * <p>
 * state:done
 */
public class Solution1658 {

    public int minOperations(int[] nums, int x) {
        int left = -1;
        int right = 0;
        int length = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum < x) {
            return -1;
        }
        int ans = Integer.MAX_VALUE;
        while (true) {
            while (sum < x) {
                left++;
                sum += nums[left];
            }
            if (sum == x) {
                ans = Math.min(ans, length - right + left + 1);
            }
            if (right >= length) {
                break;
            }
            sum -= nums[right];
            right++;

        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}