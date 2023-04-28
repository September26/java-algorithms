package com.xt.leetcode;

import java.util.Arrays;
import java.util.Vector;

/**
 * 2389. 和有限的最长子序列
 * 每日一题：2023.03.17
 * 完成日期：2023.03.17
 * 链接：
 * 描述：
 * 给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
 * <p>
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
 * <p>
 * 子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,2,1], queries = [3,10,21]
 * 输出：[2,3,4]
 * 解释：queries 对应的 answer 如下：
 * - 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
 * - 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
 * - 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,3,4,5], queries = [1]
 * 输出：[0]
 * 解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 1000
 * 1 <= nums[i], queries[i] <= 106
 * <p>
 * 解题思路：
 * 先对nums排序，这样长度为3的子序列最小和一定时前三位，其它也同理。所以长度为N的子序列最小和一定时前N位。
 * 然后我们通过二分查找，找到queries[i]在nums的位置。
 * <p>
 * <p>
 * state:done
 */
public class Solution2389 {

    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] sums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = binarySearch(sums, queries[i]) - 1;
        }
        return result;
    }


    public int binarySearch(int[] sums, int target) {
        int start = 1;
        int end = sums.length;
        int middle;
        while (start < end) {
            middle = (end + start) / 2;
            if (target < sums[middle]) {
                end = middle;
                continue;
            }
            start = middle + 1;
        }
        return start;
    }

}