package com.xt.niuke;


/**
 * 跳台阶
 * 描述
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * <p>
 * 数据范围：0 \leq n \leq 400≤n≤40
 * 要求：时间复杂度：O(n)O(n) ，空间复杂度： O(1)O(1)
 * 示例1
 * 输入：
 * 2
 * 返回值：
 * 2
 * 说明：
 * 青蛙要跳上两级台阶有两种跳法，分别是：先跳一级，再跳一级或者直接跳两级。因此答案为2
 * 示例2
 * 输入：
 * 7
 * 返回值：
 * 21
 * 示例3
 * 输入：
 * 0
 * 返回值：
 * 0
 * 10月31
 */
public class Solution69 {

    public int jumpFloor(int target) {
        if (target == 1) {
            return 1;
        }
        int[] ints = new int[target + 1];
        ints[0] = 0;
        ints[1] = 1;
        ints[2] = 2;
        return digui(ints, target);
    }

    public int digui(int[] ints, int n) {
        if (ints[n] != 0) {
            return ints[n];
        }
        int i = digui(ints, n - 1) + digui(ints, n - 2);
        ints[n] = i;
        return i;
    }

}
