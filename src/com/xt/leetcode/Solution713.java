package com.xt.leetcode;


/**
 * 713. 乘积小于 K 的子数组
 * 每日一题：2022.05.05
 * 完成日期：2022.05.05
 * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k/
 * 描述：
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3], k = 0
 * 输出：0
 *  
 * <p>
 * 提示: 
 * <p>
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解题思路：
 * 这题如果每次都是双层for循环，那很简单，但是O(n2)的复杂度，会超出时间限制。
 * 我的想法是每次循环，从第0位开始，累乘。如果大于k，则继续累乘肯定也大于K，则把当前的结果重置，再从第1位开始
 *
 * <p>
 * state:done
 *
 */
public class Solution713 {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int result = 0;
        int currentValue;
        for (int i = 0; i < nums.length; i++) {
            currentValue = 1;
            for (int j = i; j < nums.length; j++) {
                currentValue *= nums[j];
                if (currentValue >= k) {
                    break;
                }
                result++;
            }
        }
        return result;
    }
}