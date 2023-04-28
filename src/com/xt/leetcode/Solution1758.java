package com.xt.leetcode;

import java.util.Vector;

/**
 * 1758. 生成交替二进制字符串的最少操作数
 * 每日一题：2022.11.29
 * 完成日期：2022.11.29
 * 链接：https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string/
 * 描述：
 * 给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。
 * <p>
 * 交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
 * <p>
 * 返回使 s 变成 交替字符串 所需的 最少 操作数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "0100"
 * 输出：1
 * 解释：如果将最后一个字符变为 '1' ，s 就变成 "0101" ，即符合交替字符串定义。
 * 示例 2：
 * <p>
 * 输入：s = "10"
 * 输出：0
 * 解释：s 已经是交替字符串。
 * 示例 3：
 * <p>
 * 输入：s = "1111"
 * 输出：2
 * 解释：需要 2 步操作得到 "0101" 或 "1010" 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s[i] 是 '0' 或 '1'
 * 解题思路：
 * 简单来说其实就两种情况，要么奇数位为1，要么偶数位为1。
 * 把这两种情况都计算一下，取更小值即可
 * <p>
 * <p>
 * state:done
 */
public class Solution1758 {

    public int minOperations(String s) {
        char[] chars = s.toCharArray();

        int num0 = 0;
        int num1 = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            //偶数位为1的情况
            if (i % 2 == 0) {
                if (c == '0') {
                    num1++;
                } else {
                    num0++;
                }
                continue;
            }
            if (c == '1') {
                num1++;
            } else {
                num0++;
            }
        }
        return Math.min(num1, num0);
    }
}