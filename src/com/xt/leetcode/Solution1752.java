package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 1752. 检查数组是否经排序和轮转得到
 * 每日一题：2022.11.26
 * 完成日期：2022.11.30
 * 链接：https://leetcode.cn/problems/check-if-array-is-sorted-and-rotated/description/
 * 描述：
 * 给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
 * <p>
 * 如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
 * <p>
 * 源数组中可能存在 重复项 。
 * <p>
 * 注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余运算。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,5,1,2]
 * 输出：true
 * 解释：[1,2,3,4,5] 为有序的源数组。
 * 可以轮转 x = 3 个位置，使新数组从值为 3 的元素开始：[3,4,5,1,2] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,1,3,4]
 * 输出：false
 * 解释：源数组无法经轮转得到 nums 。
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：true
 * 解释：[1,2,3] 为有序的源数组。
 * 可以轮转 x = 0 个位置（即不轮转）得到 nums 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * <p>
 * 解题思路：
 * 找到最小值，然后从最小值开始拼接成一个完整数组，然后判断这个数组是否是非递减顺序排列。
 * 当然这个最小值可能存在多个，那就挨个尝试。
 * 其实，只要尝试最后一组的连续最小值的第一个即可
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1752 {

    public Solution1752() {
        System.out.println("Solution1752");
    }

    public boolean check(int[] nums) {
        int minIndex = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
                list.clear();
                list.add(i);
            } else if (nums[i] == nums[minIndex]) {
                minIndex = i;
                list.add(i);
            }
        }
        for (Integer i : list) {
            if (tryCheck(nums, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean tryCheck(int[] nums, int minIndex) {
        int[] ints = new int[nums.length];
        System.arraycopy(nums, minIndex, ints, 0, nums.length - minIndex);
        System.arraycopy(nums, 0, ints, nums.length - minIndex, minIndex);

        int lastNum = ints[0];
        for (int i = 1; i < ints.length; i++) {
            int value = ints[i];
            if (value < lastNum) {
                return false;
            }
            lastNum = value;
        }
        return true;
    }

}