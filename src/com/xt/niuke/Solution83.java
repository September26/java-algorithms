package com.xt.niuke;


/**
 * 剪绳子
 * 描述
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长的 m 段（ m 、 n 都是整数， n > 1 并且 m > 1 ， m <= n ），每段绳子的长度记为 k[1],...,k[m] 。请问 k[1]*k[2]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到的最大乘积是 18 。
 * <p>
 * 由于答案过大，请对 998244353 取模。
 * <p>
 * 数据范围：2≤n≤10的14次方
 * <p>
 * 进阶：空间复杂度 O(1)， 时间复杂度 O(logn)
 */
public class Solution83 {

    /**
     * 9696969696968
     */
    public long cutRope(long number) {
        if (number == 2) {
            return 1;
        }
        if (number == 3) {
            return 2;
        }
        long sum = 1;
        long yushu = number % 3;
        long times;
        int base = 1;
        if (yushu == 0) {
            times = number / 3;
        } else if (yushu == 1) {
            times = number / 3 - 1;
            base = 4;
        } else {
            times = number / 3;
            base = 2;
        }
        sum = pow(times, 998244353);
        sum = sum * base;
        return sum % 998244353;
    }

    public long pow(long times, int moshu) {
        if (times == 0) {
            return 1;
        }
        if (times == 1) {
            return 3;
        }
        long pow = pow(times / 2, moshu);
        long l = pow * pow % moshu;
        if (times % 2 == 0) {
            return l;
        }
        return 3 * l;
    }

}
