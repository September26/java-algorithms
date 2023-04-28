package com.xt.leetcode;

import java.util.Vector;

/**
 * 1780. 判断一个数字是否可以表示成三的幂的和
 * 每日一题：2022.12.09
 * 完成日期：2022.12.09
 * 链接：https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three/
 * 描述：
 * 给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，请你返回 true ，否则请返回 false 。
 * <p>
 * 对于一个整数 y ，如果存在整数 x 满足 y == 3x ，我们称这个整数 y 是三的幂。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：true
 * 解释：12 = 31 + 32
 * 示例 2：
 * <p>
 * 输入：n = 91
 * 输出：true
 * 解释：91 = 30 + 32 + 34
 * 示例 3：
 * <p>
 * 输入：n = 21
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 107
 * <p>
 * 解题思路：
 * 对于b不断的除以3,看余数是否是1即可。
 * <p>
 * state:
 */
public class Solution1780 {

    public boolean checkPowersOfThree(int n) {
        while (n >= 3) {
            int i = n % 3;
            if (i == 0) {
                n = n / 3;
                continue;
            }
            if (i == 1) {
                n = (n - i) / 3;
                continue;
            }
            return false;
        }
        return n == 1;
    }
}