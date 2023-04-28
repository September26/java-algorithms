package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 2341. 数组能形成多少数对
 * 每日一题：2023.02.16
 * 完成日期：2023.02.16
 * 链接：https://leetcode.cn/problems/maximum-number-of-pairs-in-array/
 * 描述：
 * 给你一个下标从 0 开始的整数数组 nums 。在一步操作中，你可以执行以下步骤：
 * <p>
 * 从 nums 选出 两个 相等的 整数
 * 从 nums 中移除这两个整数，形成一个 数对
 * 请你在 nums 上多次执行此操作直到无法继续执行。
 * <p>
 * 返回一个下标从 0 开始、长度为 2 的整数数组 answer 作为答案，其中 answer[0] 是形成的数对数目，answer[1] 是对 nums 尽可能执行上述操作后剩下的整数数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,2,1,3,2,2]
 * 输出：[3,1]
 * 解释：
 * nums[0] 和 nums[3] 形成一个数对，并从 nums 中移除，nums = [3,2,3,2,2] 。
 * nums[0] 和 nums[2] 形成一个数对，并从 nums 中移除，nums = [2,2,2] 。
 * nums[0] 和 nums[1] 形成一个数对，并从 nums 中移除，nums = [2] 。
 * 无法形成更多数对。总共形成 3 个数对，nums 中剩下 1 个数字。
 * 示例 2：
 * <p>
 * 输入：nums = [1,1]
 * 输出：[1,0]
 * 解释：nums[0] 和 nums[1] 形成一个数对，并从 nums 中移除，nums = [] 。
 * 无法形成更多数对。总共形成 1 个数对，nums 中剩下 0 个数字。
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[0,1]
 * 解释：无法形成数对，nums 中剩下 1 个数字。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * 解题思路：
 * 求每个数字的出现数量，最后遍历数量，
 * num/2代表出现对的数量，
 * num%2代表有余数的情况。
 * <p>
 * <p>
 * state:done
 */
public class Solution2341 {

    public int[] numberOfPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            Integer num = map.getOrDefault(i, 0);
            map.put(i, num + 1);
        }
        int i1 = 0;
        int i2 = 0;
        for (int num : map.values()) {
            i1 += num / 2;
            i2 += num % 2;
        }
        return new int[]{i1, i2};
    }
}