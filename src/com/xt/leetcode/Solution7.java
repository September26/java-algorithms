package com.xt.leetcode;

/**
 * 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：x = -123
 * 输出：-321
 * done
 */
public class Solution7 {

    public int reverse(int x) {
        int result = 0;
        int abs = Math.abs(x);
        while (abs > 0) {
            int i = abs % 10;
            if (result > Integer.MAX_VALUE / 10) {
                return 0;
            }
            result = result * 10;
            if (result > Integer.MAX_VALUE - i) {
                return 0;
            }
            result = result + i;
            abs = abs / 10;
        }
        return x > 0 ? result : (result * -1);
    }
}