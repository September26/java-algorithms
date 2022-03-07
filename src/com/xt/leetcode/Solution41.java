package com.xt.leetcode;

/**
 * 41.缺失的第一个正数
 * 日期：
 * 完成日期：2022.03.01
 * 链接：https://leetcode-cn.com/problems/first-missing-positive/
 * 描述
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * <p>
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题的核心就是利用现有的nums数组的空间，在不改变其原来值的情况下，用来标记存在的数字。
 * 然后分三步
 * 第一步是把所有小于等于0的数，挪到数组的尾端。并且记录尾端的位置。
 * 第二步是遍历所有大于0的区间，如果value值没有超过数组的范围，则把对应位置的index的值改为负数。
 * 第三步就是找出第一个不为负数的数，那就是对应的位置。如果遍历完都不存在，那就是length+1
 * <p>
 * state:done
 */
public class Solution41 {

    //    7,2,  8, 1, 9, 11, 12
    public int firstMissingPositive(int[] nums) {
        //1.处理数组中所有<=0的数，挪到队尾
        int lastIndex = nums.length;
        for (int i = 0; i < nums.length; ) {
            if (i >= lastIndex) {
                break;
            }
            if (nums[i] <= 0) {
                lastIndex--;
                int local = nums[i];
                nums[i] = nums[lastIndex];
                nums[lastIndex] = local;
                continue;
            }
            i++;
        }

        //2.把对应的数字的值挪到对应的index位置
        for (int i = 0; i < lastIndex; i++) {
            int value = Math.abs(nums[i]);
            if (value > nums.length) {
                continue;
            }
            if (nums[value - 1] < 0) {
                continue;
            }
            nums[value - 1] = -nums[value - 1];
        }

        //3.遍历数组，找出第一个不为负数的数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                return i + 1;
            }
            if (i >= lastIndex) {
                return lastIndex + 1;
            }
        }
        return lastIndex + 1;
    }
}