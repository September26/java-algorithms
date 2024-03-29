package com.xt.leetcode;

import java.util.Vector;

/**
 * 1800. 最大升序子数组和
 * 每日一题：2022.10.7
 * 完成日期：2022.10.10
 * 链接：https://leetcode.cn/problems/maximum-ascending-subarray-sum/
 * 描述：
 * 给你一个正整数组成的数组 nums ，返回 nums 中一个 升序 子数组的最大可能元素和。
 *
 * 子数组是数组中的一个连续数字序列。
 *
 * 已知子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，若对所有 i（l <= i < r），numsi < numsi+1 都成立，则称这一子数组为 升序 子数组。注意，大小为 1 的子数组也视作 升序 子数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [10,20,30,5,10,50]
 * 输出：65
 * 解释：[5,10,50] 是元素和最大的升序子数组，最大元素和为 65 。
 * 示例 2：
 *
 * 输入：nums = [10,20,30,40,50]
 * 输出：150
 * 解释：[10,20,30,40,50] 是元素和最大的升序子数组，最大元素和为 150 。
 * 示例 3：
 *
 * 输入：nums = [12,17,15,13,10,11,12]
 * 输出：33
 * 解释：[10,11,12] 是元素和最大的升序子数组，最大元素和为 33 。
 * 示例 4：
 *
 * 输入：nums = [100,10,1]
 * 输出：100
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-ascending-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 该题比较简单，设置三个变量，分别存储最大升序子数组和，当前数组和，以及上一个数的值。
 * 遍历数组，如果lastValue >= value，则说明不是连续升序子数组，则重置currentSum的值。
 * 每次循环时，都利用Math.max(maxSum, currentSum);计算最大升序子数组和
 * <p>
 * <p>
 * state:
 */
public class Solution1800 {

    public int maxAscendingSum(int[] nums) {
        int maxSum = 0;
        int currentSum = 0;
        int lastValue = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (lastValue >= value) {
                currentSum = value;
            } else {
                currentSum += value;
            }
            lastValue = value;
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}