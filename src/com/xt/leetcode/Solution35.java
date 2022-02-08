package com.xt.leetcode;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 示例 4:
 * <p>
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 * 示例 5:
 * <p>
 * 输入: nums = [1], target = 0
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解体思路：
 * 设置insert进行记录插入位置。
 * 二分查找，设置start和end，逐渐的缩小范围集合，start和end设置后为下一轮的。
 * <p>
 * state:done
 */
public class Solution35 {

    /**
     * 搜索插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (target <= nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        //二叉搜索，找到小于target的那个数
        int start = 0;
        int end = nums.length - 1;
        int insert = nums.length;
        do {
            int middle = (start + end) / 2;
            if (nums[middle] >= target) {
                insert = middle;
                end = middle - 1;
                continue;
            }
            if (nums[middle] < target) {
                start = middle + 1;
                continue;
            }
        } while (start <= end);
        return insert;
    }
}