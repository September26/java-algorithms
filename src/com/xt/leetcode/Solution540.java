package com.xt.leetcode;


/**
 * 540. 有序数组中的单一元素
 * 日期：2022.2.14
 * 链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
 * 描述
 * <p>
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * <p>
 * 请你找出并返回只出现一次的那个数。
 * <p>
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * logN的复杂度肯定是二分查找，判断在middle前还是后的话。如果偶数位和奇数位相等，则之后，否则就是之前。
 * <p>
 * <p>
 * state:done
 */
public class Solution540 {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        int middle;
        int target = 0;
        while (left <= right) {
            middle = (left + right) / 2;
            if (middle % 2 == 0) {
                if (middle < nums.length - 1 && nums[middle] == nums[middle + 1]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                    target = middle;
                }
            } else {
                if (nums[middle] == nums[middle + 1]) {
                    right = middle - 1;
                    target = middle;
                } else {
                    left = middle + 1;

                }
            }
        }
        return nums[target];
    }
}