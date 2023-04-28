package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 775. 全局倒置与局部倒置
 * 每日一题：2022.11.16
 * 完成日期：2022.11.16
 * 链接：https://leetcode.cn/problems/global-and-local-inversions/
 * 描述：
 * 给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
 * <p>
 * 全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
 * <p>
 * 0 <= i < j < n
 * nums[i] > nums[j]
 * 局部倒置 的数目等于满足下述条件的下标 i 的数目：
 * <p>
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 * 当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,2]
 * 输出：true
 * 解释：有 1 个全局倒置，和 1 个局部倒置。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：false
 * 解释：有 2 个全局倒置，和 1 个局部倒置。
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 105
 * 0 <= nums[i] < n
 * nums 中的所有整数 互不相同
 * nums 是范围 [0, n - 1] 内所有数字组成的一个排列
 * <p>
 * 解题思路：
 * 我们可以发现这样一件事，局部倒置，一定是全局倒置。
 * 所以，我们只要判断是否存在属于全局倒置而不属于局部倒置的情况，就可以区分true还是false了？
 * 所以我们可以转换一下，就是寻找是否存在i和j不相邻，i<j并且num[i]>num[j]的情况。
 * 所以，我们首先从右向左遍历一遍，找到每个位置所出现的最小值没记录到mins数组中。
 * 然后从左向右遍历，判断nums[i] > mins[i + 2], mins[i + 2]代表i+2位以及以后的最小值。
 * 如果存在，则返回false.
 * <p>
 * <p>
 * state:done
 */
public class Solution775 {

    public boolean isIdealPermutation(int[] nums) {
        int[] mins = new int[nums.length];
        int min = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            min = Math.min(nums[i], min);
            mins[i] = min;
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > mins[i + 2]) {
                return false;
            }
        }
        return true;
    }
}