package com.xt.niuke;


/**
 * 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 * <p>
 * 数据范围：两个数都满足 0 \le n \le 10000≤n≤1000
 * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(1)O(1)
 * 10月27
 * Done
 */
public class Solution65 {

    public int Add(int num1, int num2) {
        int k0 = num1 & num2;//二进制如果对应的位相同，则需要进位，先求需要进位的数
        int k1 = num1 ^ num2;//不相同，则对应的位结果为1，不需要进位
        int i = (k0 << 1) & k1;
        int result = (k0 << 1) | k1;
        if (i == 0) {
            return result;
        }
        return Add(result, i);
    }
}
