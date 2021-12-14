package com.xt.leetcode;


/**
 * 29. 两数相除
 * <p>
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * done
 */
public class Solution29 {

    /**
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    public int divide(int dividend, int divisor) {
        boolean isMin = false;
        if (dividend == Integer.MIN_VALUE) {
            isMin = true;
            dividend = dividend >> 1;
        }

        //特殊处理把，如果dividen是MIN的话就先除以2，结果然后*2
        //判断两者符号是否一致
        boolean flag = (dividend ^ divisor) >= 0;
        dividend = abs(dividend);//abd
        divisor = abs(divisor);
        int result = 0;
        int currentLevel = divisor;
        int level = 1;

        while (currentLevel <= dividend) {
            currentLevel = currentLevel << 1;
            level = level << 1;
            if (currentLevel == (Integer.MAX_VALUE / 2 + 1)) {
                break;
            }
        }
        if (currentLevel != (Integer.MAX_VALUE / 2 + 1)) {
            currentLevel = currentLevel >> 1;
            level = level >> 1;
        }
        do {
            int i = dividend - currentLevel;
            if (i >= 0) {
                result += level;
                dividend = i;
                if (i == 0) {
                    break;
                }
            }
            level = level >> 1;
            currentLevel = currentLevel >> 1;
        } while (level > 0);
        int i = flag ? result : (~result) + 1;
        if (isMin) {
            return i << 1;
        }
        return i;//取反
    }

    public int abs(int i) {
        if (i > 0) {
            return i;
        }
        return (~i) + 1;
    }
}