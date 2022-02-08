package com.xt.leetcode;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [5,8,8,8,8,8,8,8,8,8,8,8,8,8,10], target = 8
 * 输出：[-1,-1]
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums 是一个非递减数组
 * -10^9 <= target <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解体思路：
 * 写两个方法binarySearch和binarySearch2，分别查找target在数组中的第一个和最后一个位置。
 * 最终只要比较一下两个位置的值是否等于target即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution34 {

    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 查找target在数组中最后一个
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int result = end;
        do {
            int middle = (start + end) / 2;
            if (nums[middle] >= target) {
                end = middle - 1;
            } else {
                start = middle + 1;
                result = start;
            }
        } while (start <= end);

        return result;
    }

    /**
     * 查找target在数组中第一个
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch2(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int result = end;
        do {
            int middle = (start + end) / 2;
            if (nums[middle] >= target) {
                end = middle - 1;
                result = end;
            } else {
                start = middle + 1;
            }
        } while (start <= end);

        return result;
    }
}