package com.xt.leetcode;

import java.util.Vector;

/**
 * 1688.比赛中的配对次数
 * 每日一题：2022.04.16
 * 完成日期：2022.04.16
 * 链接：https://leetcode-cn.com/problems/largest-palindrome-product/
 * 描述：
 * 给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入：n = 2
 * 输出：987
 * 解释：99 x 91 = 9009, 9009 % 1337 = 987
 * 示例 2:
 * <p>
 * 输入： n = 1
 * 输出： 9
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-palindrome-product
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:
 */
public class Solution479 {
    static int time = 0;

    public int largestPalindrome2(int n) {
        if (n == 1) {
            return 9;
        }
        int upper = (int) Math.pow(10, n) - 1;
        int ans = 0;
        for (int left = upper; ans == 0; --left) { // 枚举回文数的左半部分
            long p = left;
            for (int x = left; x > 0; x /= 10) {
                p = p * 10 + x % 10; // 翻转左半部分到其自身末尾，构造回文数 p
            }
            for (long x = upper; x * x >= p; --x) {
                time++;
                if (p % x == 0) { // x 是 p 的因子
                    ans = (int) (p % 1337);
                    break;
                }
            }
        }
        return ans;
    }

    public int largestPalindrome(int n) {
        int lefeNum = (int) Math.pow(10, n) - 1;
        for (; lefeNum > 0; lefeNum--) {
            //求currentLeft的回文数
            long num = getNum(lefeNum);
            if (!isFit(num, n)) {
                continue;
            }
            return (int) (num % 1337);
        }
        return 9;
    }

    private long getNum(int leftNum) {
        int rightNum = 0;
        int base = 1;
        long oldLeftNum = leftNum;
        while (leftNum > 0) {
            int i = leftNum % 10;
            rightNum = rightNum * 10 + i;
            leftNum /= 10;
            base *= 10;
        }
        return oldLeftNum * base + rightNum;
    }

    private boolean isFit(long num, int n) {
        int max = (int) Math.pow(10, n) - 1;
        long i = max;
        for (; i * i >= num; i--) {
            time++;
            if (num % i == 0 && num / i < max) {
                return true;
            }
        }
        return false;
    }
}