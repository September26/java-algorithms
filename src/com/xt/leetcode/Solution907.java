package com.xt.leetcode;

import java.util.*;

/**
 * 907. 子数组的最小值之和
 * 每日一题：2022.10.28
 * 完成日期：2022.10.28
 * 链接：https://leetcode.cn/problems/sum-of-subarray-minimums/
 * 描述：
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * <p>
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 * 示例 2：
 * <p>
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 3 * 104
 * 1 <= arr[i] <= 3 * 104
 * 解题思路：
 * 这题我一开始没想出来答案，是看了官方题解之后在想明白的。
 * 首先我们举一个例子，比如官方中的[1,7,5,2,4,3,9]，
 * 我们首先用数组dp来记录，dp[i]表示以第i位结尾的所有子数组的最小值之和。
 * 我们以第3位的2来举例子，2结尾共有4种情况
 * [1,2]:1
 * [7,2]:2
 * [5,2]:2
 * [2,2]:2
 * 这些情况，我们分为两部分，存在比2小的部分，也就是[1,4]的部分，这时候最小值位1，
 * 剩余的部分最小值为2，其长度为当前位置i减去1的位置0=3。所以dp[3]=7。
 * 同理，我们继续，如果以第4为的4来举例子，那么共有5中情况
 * [1,4]:1
 * [7,4]:2
 * [5,4]:2
 * [2,4]:2
 * [4,4]:4
 * 我们仍然分为两部分，比4小的部分，以及大于等于4的部分。
 * 比4小的部分，其实就是2所在位置的所有情况，也就是dp[3]的值。
 * 剩余的部分最小值为4，其长度为当前位置i减去2所在的位置3，4-3=1。所以dp[4]=dp[3]+1*4=11。
 * <p>
 * state:done
 */
public class Solution907 {

    //    1, 7, 5, 2, 4, 3, 9
    public int sumSubarrayMins(int[] arr) {
        int[] dp = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        final int MOD = 1000000007;
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];

            while (stack.size() > 0 && arr[stack.peek()] > value) {
                stack.pop();
            }
            if (stack.size() > 0) {
                int k = i - stack.peek();
                dp[i] = dp[i - k] + k * value;
            } else {
                dp[i] = (i + 1) * value;
            }
            ans = (ans + dp[i]) % MOD;
            stack.push(i);
        }
        return ans;
    }
}