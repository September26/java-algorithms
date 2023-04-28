package com.xt.leetcode;

import java.util.Vector;

/**
 * 1785. 构成特定和需要添加的最少元素
 * 每日一题：2022.12.16
 * 完成日期：2022.12.16
 * 链接：https://leetcode.cn/problems/minimum-elements-to-add-to-form-a-given-sum/
 * 描述：
 * 给你一个整数数组 nums ，和两个整数 limit 与 goal 。数组 nums 有一条重要属性：abs(nums[i]) <= limit 。
 * <p>
 * 返回使数组元素总和等于 goal 所需要向数组中添加的 最少元素数量 ，添加元素 不应改变 数组中 abs(nums[i]) <= limit 这一属性。
 * <p>
 * 注意，如果 x >= 0 ，那么 abs(x) 等于 x ；否则，等于 -x 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,-1,1], limit = 3, goal = -4
 * 输出：2
 * 解释：可以将 -2 和 -3 添加到数组中，数组的元素总和变为 1 - 1 + 1 - 2 - 3 = -4 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,-10,9,1], limit = 100, goal = 0
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= limit <= 106
 * -limit <= nums[i] <= limit
 * -109 <= goal <= 109
 * <p>
 * 解题思路：
 * 求出nums的和，然后求和goal的差值，除以limit即可
 * <p>
 * <p>
 * state:done
 */
public class Solution1785 {

    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        long diff = goal - sum;
        if (diff == 0) {
            return 0;
        }
        long result = Math.abs(diff) / limit;
        return (int) (diff % limit == 0 ? result : result + 1);
    }
}