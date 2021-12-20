package com.xt.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * state:done
 */
public class Solution31 {

    public void nextPermutation(int[] nums) {
        Integer changeIndex = null;

        for (int i = nums.length - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                changeIndex = i - 1;
                break;
            }
        }
        if (changeIndex != null) {
            //找到大于i-1的最小值
            Integer minIndex = null;
            for (int i = nums.length - 1; i > changeIndex; i--) {
                if (nums[i] > nums[changeIndex]) {
                    if (minIndex == null) {
                        minIndex = i;
                        continue;
                    }
                    minIndex = nums[minIndex] > nums[i] ? i : minIndex;
                }
            }
            //交换minIndex和changeIndex
            int local = nums[changeIndex];
            nums[changeIndex] = nums[minIndex];
            nums[minIndex] = local;
        } else {
            changeIndex = -1;
        }
        Arrays.sort(nums, changeIndex + 1, nums.length);
    }
}