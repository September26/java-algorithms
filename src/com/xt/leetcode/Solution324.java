package com.xt.leetcode;

import java.util.Arrays;

/**
 * 324.摆动排序 II
 * 每日一题：2022.06.28
 * 完成日期：2022.06.28
 * 链接：https://leetcode.cn/problems/wiggle-sort-ii/
 * 描述：
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * <p>
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 * 示例 2：
 * <p>
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 *  
 * <p>
 * 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/wiggle-sort-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这里最简单的方式，排序，然后分为两部分，小的部分和大的部分。
 * 然后依次小的取一个，大的取一个，直到取完。
 * <p>
 * <p>
 * state:done
 */
public class Solution324 {

    public void wiggleSort(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int n = nums.length;
        int j = (n + 1) / 2 - 1;
        int k = n - 1;
        int i = 0;
        while (i < nums.length) {
            nums[i++] = arr[j--];
            if (i < n) {
                nums[i++] = arr[k--];
            }
        }
    }

}