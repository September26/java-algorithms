package com.xt.leetcode;

import java.util.Vector;

/**
 * 2014.子数组范围和
 * 每日一题：2022.03.04
 * 完成日期：2022.03.04
 * 链接：https://leetcode-cn.com/problems/sum-of-subarray-ranges/
 * 描述：
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 * <p>
 * 返回 nums 中 所有 子数组范围的 和 。
 * <p>
 * 子数组是数组中一个连续 非空 的元素序列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [2]，范围 = 2 - 2 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,2]，范围 = 2 - 1 = 1
 * [2,3]，范围 = 3 - 2 = 1
 * [1,2,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
 * 示例 2：
 * <p>
 * 输入：nums = [1,3,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [3]，范围 = 3 - 3 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,3]，范围 = 3 - 1 = 2
 * [3,3]，范围 = 3 - 3 = 0
 * [1,3,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
 * 示例 3：
 * <p>
 * 输入：nums = [4,-2,-3,4,1]
 * 输出：59
 * 解释：nums 中所有子数组范围的和是 59
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * -109 <= nums[i] <= 109
 *  
 * <p>
 * 进阶：你可以设计一种时间复杂度为 O(n) 的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-subarray-ranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先子数组为自身的不用管，因为其差值为0。
 * 然后双重for循环遍历，每一轮都求出最大最小值。
 * 官方的题解O(n)的感觉像一道数据题了，确实做不出来。
 * <p>
 * <p>
 * state:
 */
public class Solution2014 {

    public long subArrayRanges(int[] nums) {
        long sum = 0;
        int minIndex;
        int maxIndex;
        for (int i1 = 0; i1 < nums.length; i1++) {
            minIndex = i1;
            maxIndex = i1;
            for (int i2 = i1 + 1; i2 < nums.length; i2++) {
                minIndex = nums[i2] < nums[minIndex] ? i2 : minIndex;
                maxIndex = nums[i2] > nums[maxIndex] ? i2 : maxIndex;
                int value = nums[maxIndex] - nums[minIndex];
                sum += value;
            }
        }
        return sum;
    }
}