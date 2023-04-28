package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1814. 统计一个数组中好对子的数目
 * 每日一题：2023.01.17
 * 完成日期：2023.01.17
 * 链接：https://leetcode.cn/problems/count-nice-pairs-in-an-array/
 * 描述：
 * 给你一个数组 nums ，数组中只包含非负整数。定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。比方说 rev(123) = 321 ， rev(120) = 21 。我们称满足下面条件的下标对 (i, j) 是 好的 ：
 * <p>
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * 请你返回好下标对的数目。由于结果可能会很大，请将结果对 109 + 7 取余 后返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [42,11,1,97]
 * 输出：2
 * 解释：两个坐标对为：
 * - (0,3)：42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121 。
 * - (1,2)：11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12 。
 * 示例 2：
 * <p>
 * 输入：nums = [13,10,35,24,76]
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * <p>
 * 解题思路：
 * 假设读取到的数字是23，那么其反转数字位32，差值为9，则一定会有另外一个也为差值9的来和其匹配。
 * 所以我们用一个map来存储，key为差值，value为符合的数量。最终求的时候，还要刨除自身那一个。
 * 因为每个数都被计算了两次，所以最终还要除以2。
 * <p>
 * state:done
 */
public class Solution1814 {
    final int MOD = 10_0000_0000 + 7;

    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int reversal = reversal(num);
            map.put(num - reversal, map.getOrDefault(num - reversal, 0) + 1);
        }
        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int reversal = reversal(num);
            int expect = num - reversal;
            int times = map.getOrDefault(expect, 0) - 1;
            result += times;
        }
        return (int) ((result / 2) % MOD);
    }

    public int reversal(int i) {
        int result = 0;
        while (i > 0) {
            int i1 = i % 10;
            i = i / 10;
            result = result * 10 + i1;
        }
        return result;
    }

}