package com.xt.leetcode;


/**
 * 1043. 分隔数组以得到最大和
 * 每日一题：2023.04.19
 * 完成日期：2023.04.19
 * 链接：https://leetcode.cn/problems/partition-array-for-maximum-sum/
 * 描述：
 * 给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 *
 * 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：数组变为 [15,15,15,9,10,10,10]
 * 示例 2：
 *
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 * 示例 3：
 *
 * 输入：arr = [1], k = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 109
 * 1 <= k <= arr.length
 * 解题思路：
 * 这题的arr长度是500，说明这不是一道时间复杂度要超过O(n)的题。
 * 我们使用dp，来记录前i个数的最大和。
 * 首先，求前k个数的最大和，这个容易，只要找到前i个值中最大的那个，乘以i即可。
 * 然后，我们就要求k到arr.length之间的最大和了。
 * 比如我们求第n位的最大和，其中n>=k。
 * 那么有如下几种可能：
 * 1.dp[n-1]+arr[n];
 * 2.dp[n-2]+math(arr[n],arr[n-1])*2;
 * ...
 * 3.dp[n-k]+math(arr[n],arr[n-1]...)*k;
 * 所以，我们通过循环，找到这个最大和，就是dp[n]。
 * 然后继续循环，dp[arr.length-1]就是我们要求出的那个值。
 *
 * state:done
 */
public class Solution1043 {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        //dp前i个的最大值
        int[] dp = new int[arr.length];
        for (int i = 0; i < k; i++) {
            if (i == 0) {
                dp[0] = arr[0];
                continue;
            }
            int value = arr[i];
            if (value > dp[i - 1] / i) {
                dp[i] = value * (i + 1);
            } else {
                dp[i] = dp[i - 1] / i * (i + 1);
            }
        }
        for (int i = k; i < arr.length; i++) {
            int value = arr[i];
            int sum = 0;
            int max = value;
            for (int j = i - 1; j >= i - k; j--) {
                int length = i - j;
                int currentSum = dp[j] + max * length;
                sum = Math.max(sum, currentSum);
                max = Math.max(max, arr[j]);
            }
            dp[i] = sum;
        }
        return dp[arr.length - 1];
    }
}