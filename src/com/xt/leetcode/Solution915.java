package com.xt.leetcode;

import java.util.Vector;

/**
 * 915. 分割数组
 * 每日一题：2022.10.24
 * 完成日期：2022.10.24
 * 链接：https://leetcode.cn/problems/partition-array-into-disjoint-intervals/
 * 描述：
 * 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
 * <p>
 * left 中的每个元素都小于或等于 right 中的每个元素。
 * left 和 right 都是非空的。
 * left 的长度要尽可能小。
 * 在完成这样的分组后返回 left 的 长度 。
 * <p>
 * 用例可以保证存在这样的划分方法。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,0,3,8,6]
 * 输出：3
 * 解释：left = [5,0,3]，right = [8,6]
 * 示例 2：
 * <p>
 * 输入：nums = [1,1,1,0,6,12]
 * 输出：4
 * 解释：left = [1,1,1,0]，right = [6,12]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 105
 * 0 <= nums[i] <= 106
 * 可以保证至少有一种方法能够按题目所描述的那样对 nums 进行划分。
 * <p>
 * 解题思路：
 * 用三个变量来记录，分别记录左侧最大值，右侧的最大值，左右侧分割的氛围。
 * 一次遍历nums数组，如果value < nums[leftMaxIndex]，则说明右侧有数字小于左侧，则我们把分割线置为空-1，此时右侧的最大值就是下一次左侧最大值。
 * 如果value >= nums[leftMaxIndex]，如果分割线为空，则设置为新的分割线。
 * 并且此时如果value > nums[rightMaxIndex]，则更新右侧最大值
 * <p>
 * state:done
 */
public class Solution915 {

    public int partitionDisjoint(int[] nums) {
        int leftMaxIndex = 0;
        int dividerIndex = -1;
        int rightMaxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            if (value < nums[leftMaxIndex]) {
                leftMaxIndex = rightMaxIndex;
                dividerIndex = -1;
                continue;
            }
            if (dividerIndex == -1) {
                dividerIndex = i;
            }
            if (value > nums[rightMaxIndex]) {
                rightMaxIndex = i;
                continue;
            }
        }
        return dividerIndex;
    }
}