package com.xt.leetcode;


/**
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解体思路：
 * 这题我们仍然可以通过二叉搜索去查找，但是判断条件不能只是单纯的是否相等了，要结合start，end值来判断区间。
 * 比如{2,3,4,5,6,7,8,9,10,1}，查找4的话。
 * 这里我们以m代表nums[middle]的值，s代表num[start]的值，t代表targe的值。
 * m>t时，我么可以用start的值来判断。
 * m>s时，我们可以推出有两种可能性{3,4,5,6,7}和{2,3,4,5,1,2}
 * 这时候，我们在判断一下t和s即可。t>s，说明在数组的前半段。否则就在数组的后半段。
 * state:done
 */
public class Solution33 {

    public int search(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;
        int middle = 0;
        do {
            middle = (start + end) / 2;
            if (target == nums[middle]) {
                return middle;
            }
            if (target == nums[end]) {
                return end;
            } else if (target == nums[start]) {
                return start;
            }
            if (middle == start) {
                break;
            }
            if (target > nums[middle]) {
                if (nums[middle] < nums[end]) {
                    if (target < nums[end]) {
                        start = middle;
                    } else {
                        end = middle;
                    }
                } else {
                    if (target > nums[end]) {
                        start = middle;
                    } else {
                        end = middle;
                    }
                }
                continue;
            }
            if (target < nums[middle]) {
                if (nums[middle] > nums[start]) {
                    if (target > nums[start]) {
                        end = middle;
                    } else {
                        start = middle;
                    }
                } else {
                    if (target > nums[start]) {
                        start = middle;
                    } else {
                        end = middle;
                    }
                }
            }
        } while (start < end);
        return -1;
    }
}