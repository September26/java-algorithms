package com.xt.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 862. 和至少为 K 的最短子数组
 * 每日一题：2022.10.26
 * 完成日期：2022.10.26
 * 链接：https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/
 * 描述：
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 * <p>
 * 子数组 是数组中 连续 的一部分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1], k = 1
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：nums = [1,2], k = 4
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：nums = [2,-1,2], k = 3
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= 10^9
 * <p>
 * 解题思路：
 * 因为本题的的要求是子数组和大于K，所以我们可以依次尝试，先尝试长度为1的，然后尝试长度为2的，依次下去。
 * 如果存在，则返回。
 *
 * <p>
 * state:done
 */
public class Solution862 {

//    public int shortestSubarray(int[] nums, int k) {
//        int start = 0;
//        int end = 0;
//        int sum = 0;
//        int minLength = Integer.MAX_VALUE;
//        int length = nums.length;
//        while (start < length || end < length) {
//            if (end == length) {
//                if (sum >= k) {
//                    minLength = Math.min(minLength, end - start);
//                }
//                sum -= nums[start];
//                start++;
//                continue;
//            }
//            if (nums[start] <= 0) {
//                sum -= nums[start];
//                start++;
//            } else if (nums[end] <= 0) {
//                sum += nums[end];
//                end++;
//                continue;
//            } else {
//                sum += nums[end];
//                end++;
//                if (sum >= k) {
//                    minLength = Math.min(minLength, end - start);
//                    end--;
//                    sum -= nums[end];
//                    sum -= nums[start];
//                    start++;
//                }
//            }
//            if (start > end) {
//                sum += nums[end];
//                end++;
//            }
//        }
//        return minLength == Integer.MAX_VALUE ? -1 : minLength;
//    }

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSumArr = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSumArr[i + 1] = preSumArr[i] + nums[i];
        }
        int res = n + 1;
        Deque<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i <= n; i++) {
            long curSum = preSumArr[i];
            while (!queue.isEmpty() && curSum - preSumArr[queue.peekFirst()] >= k) {
                res = Math.min(res, i - queue.pollFirst());
            }
            while (!queue.isEmpty() && preSumArr[queue.peekLast()] >= curSum) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        return res < n + 1 ? res : -1;
    }

}