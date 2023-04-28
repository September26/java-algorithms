package com.xt.leetcode;

/**
 * 1822. 数组元素积的符号
 * 每日一题：2022.10.27
 * 完成日期：2022.10.27
 * 链接：https://leetcode.cn/problems/sign-of-the-product-of-an-array/
 * 描述：
 * 已知函数 signFunc(x) 将会根据 x 的正负返回特定值：
 * <p>
 * 如果 x 是正数，返回 1 。
 * 如果 x 是负数，返回 -1 。
 * 如果 x 是等于 0 ，返回 0 。
 * 给你一个整数数组 nums 。令 product 为数组 nums 中所有元素值的乘积。
 * <p>
 * 返回 signFunc(product) 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,-2,-3,-4,3,2,1]
 * 输出：1
 * 解释：数组中所有值的乘积是 144 ，且 signFunc(144) = 1
 * 示例 2：
 * <p>
 * 输入：nums = [1,5,0,2,-3]
 * 输出：0
 * 解释：数组中所有值的乘积是 0 ，且 signFunc(0) = 0
 * 示例 3：
 * <p>
 * 输入：nums = [-1,1,-1,1,-1]
 * 输出：-1
 * 解释：数组中所有值的乘积是 -1 ，且 signFunc(-1) = -1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * -100 <= nums[i] <= 100
 * 解题思路：
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1822 {

    public int arraySign(int[] nums) {
        int sum = 1;
        for (int i : nums) {
            if (i == 0) {
                return 0;
            }
            sum = (i > 0) ? sum : -1 * sum;
        }
        return sum;
    }
}