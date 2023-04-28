package com.xt.leetcode;

import java.util.Arrays;
import java.util.Vector;
import java.util.function.IntConsumer;

/**
 * 1017. 负二进制转换
 * 每日一题：2023.04.06
 * 完成日期：2023.04.06
 * 链接：https://leetcode.cn/problems/convert-to-base-2/
 * 描述：
 * 给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。
 * <p>
 * 注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出："110"
 * 解释：(-2)2 + (-2)1 = 2
 * 示例 2：
 * <p>
 * 输入：n = 3
 * 输出："111"
 * 解释：(-2)2 + (-2)1 + (-2)0 = 3
 * 示例 3：
 * <p>
 * 输入：n = 4
 * 输出："100"
 * 解释：(-2)2 = 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 109
 * <p>
 * 解题思路：
 * 思路分成两部分：
 * 第一部分：每一位上的数都可以做拆分，比如2=4-2，16=16；
 * 第二部分，取每一位上的数拆分，如果遇到4-2这样的，就进位。如果第i位上的数有0个，则添加0，有1个，则添加1，有2个，则进位并且添加0。
 * <p>
 * <p>
 * state:done
 */
public class Solution1017 {

    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        int flagBit = 0;
        StringBuilder builder = new StringBuilder();
        boolean jinwei = false;
        while (n > 0 || jinwei) {
            int num = (n & 1 << flagBit) >> flagBit;
            if (jinwei) {
                jinwei = false;
                num++;
            }
            if (num == 2) {
                jinwei = true;
                num = 0;
            }
            if (num == 0) {
                builder.insert(0, 0);
            } else {
                builder.insert(0, 1);
                if (flagBit % 2 != 0) {
                    jinwei = true;
                }
            }
            n = n - (n & 1 << flagBit);
            flagBit++;
        }
        return builder.toString();
    }
}