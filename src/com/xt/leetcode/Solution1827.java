package com.xt.leetcode;

import java.util.Vector;

/**
 * 1827. 最少操作使数组递增
 * 每日一题：2022.12.14
 * 完成日期：2022.12.14
 * 链接：https://leetcode.cn/problems/minimum-operations-to-make-the-array-increasing/
 * 描述：
 * 给你一个整数数组 nums （下标从 0 开始）。每一次操作中，你可以选择数组中一个元素，并将它增加 1 。
 * <p>
 * 比方说，如果 nums = [1,2,3] ，你可以选择增加 nums[1] 得到 nums = [1,3,3] 。
 * 请你返回使 nums 严格递增 的 最少 操作次数。
 * <p>
 * 我们称数组 nums 是 严格递增的 ，当它满足对于所有的 0 <= i < nums.length - 1 都有 nums[i] < nums[i+1] 。一个长度为 1 的数组是严格递增的一种特殊情况。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1]
 * 输出：3
 * 解释：你可以进行如下操作：
 * 1) 增加 nums[2] ，数组变为 [1,1,2] 。
 * 2) 增加 nums[1] ，数组变为 [1,2,2] 。
 * 3) 增加 nums[2] ，数组变为 [1,2,3] 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,5,2,4,1]
 * 输出：14
 * 示例 3：
 * <p>
 * 输入：nums = [8]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * 1 <= nums[i] <= 104
 * 解题思路：
 * 使用sum来记录最小操作次数，使用current记录前一个数。
 * current的规则是如果nums[i] > current则current = nums[i]
 * 否则current = current+1；
 * state:done
 */
public class Solution1827 {

    public int minOperations(int[] nums) {
        int sum = 0;
        int current = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > current) {
                current = nums[i];
            } else {
                current++;
                sum += (current - nums[i]);
            }
        }
        return sum;
    }

}