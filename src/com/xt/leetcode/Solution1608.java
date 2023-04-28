package com.xt.leetcode;

import java.util.Arrays;

/**
 * 1608. 特殊数组的特征值
 * 每日一题：2022.09.13
 * 完成日期：2022.09.13
 * 链接：https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/
 * 描述：
 * <p>
 * 解题思路：
 * 如果数组长度位8，则就尝试1..8的所有可能。
 * 首先对数组排序，每个值所对应的位置index，而(nums.length - index)就是大于等于其的数量。
 * 使用二分法去查找所对应的位置index
 * <p>
 * state:done
 */
public class Solution1608 {

    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i <= nums.length; i++) {
            int index = binarySearch(nums, i);
            if (i == nums.length - index) {
                return i;
            }
        }
        return -1;
    }

    public int binarySearch(int[] nums, int target) {
        if (target < nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        int start = 0;
        int end = nums.length - 1;
        int result = end;
        do {
            int middle = (start + end) / 2;
            if (nums[middle] >= target) {
                result = middle;
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        } while (start <= end);

        return result;
    }

}