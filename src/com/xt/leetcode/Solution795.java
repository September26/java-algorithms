package com.xt.leetcode;

/**
 * 795. 区间子数组个数
 * 每日一题：2022.11.24
 * 完成日期：2022.11.24
 * 链接：https://leetcode.cn/problems/number-of-subarrays-with-bounded-maximum/
 * 描述：
 * 给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。
 * <p>
 * 生成的测试用例保证结果符合 32-bit 整数范围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,1,4,3], left = 2, right = 3
 * 输出：3
 * 解释：满足条件的三个子数组：[2], [2, 1], [3]
 * 示例 2：
 * <p>
 * 输入：nums = [2,9,2,5,6], left = 2, right = 8
 * 输出：7
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= left <= right <= 109
 * 解题思路：
 * 我们首先可以把nums分为若干小的数组，分割的依据就是以>right的来分割。
 * 找到这样的小数组a中，再把数分为两种，在[left,right]范围内和不再范围内的。
 * 首先这个小数组a长度为4的情况下，一共有1+2+3+4种子数组可能，
 * 我们在继续分割，找出这个小数组a中不包含任何[left,right]范围内数值的更小数组b。
 * 数组a所有的可能性，减去数组b所有的可能性，就是包含[left,right]的可能性。
 * 所有符合的可能性累加，就是我们想要的结果
 *
 * <p>
 * state:done
 */
public class Solution795 {
    int[] times;

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        times = new int[nums.length + 1];
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum += i;
            times[i] = sum;
        }
        int start = 0;
        int index = 0;
        int result = 0;
        while (index < nums.length) {
            int value = nums[index++];
            if (value <= right) {
                continue;
            }
            result += search(nums, start, index - 1, left);
            start = index;
        }
        result += search(nums, start, nums.length, left);
        return result;
    }

    private int search(int[] nums, int start, int end, int left) {
        if (start == end) {
            return 0;
        }
        int result = 0;
        int index = start;
        int num = 0;
        while (index < end) {
            if (nums[index] >= left) {
                result += times[num];
                num = 0;
                index++;
                continue;
            }
            index++;
            num++;
        }
        result += times[num];
        return times[end - start] - result;
    }
}