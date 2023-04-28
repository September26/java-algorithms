package com.xt.leetcode;

import java.util.Vector;

/**
 * 1796. 字符串中第二大的数字
 * 每日一题：2022.12.5
 * 完成日期：2022.12.5
 * 链接：https://leetcode.cn/problems/second-largest-digit-in-a-string/
 * 描述：
 * 给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
 * <p>
 * 混合字符串 由小写英文字母和数字组成。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "dfa12321afd"
 * 输出：2
 * 解释：出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
 * 示例 2：
 * <p>
 * 输入：s = "abc1111"
 * 输出：-1
 * 解释：出现在 s 中的数字只包含 [1] 。没有第二大的数字。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 只包含小写英文字母和（或）数字。
 * <p>
 * 解题思路：
 * 用maxNum和max2Num记录最大和第二大的数字，
 * 依次遍历，如果value大于maxNum，则更新maxNum和max2Num，
 * 如果value等于maxNum，则跳过，
 * 如果value > max2Num，则只更新max2Num即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution1796 {

    public int secondHighest(String s) {
        int maxNum = -1;
        int max2Num = -1;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c < '0' || c > '9') {
                continue;
            }
            int value = c - '0';
            if (value > maxNum) {
                max2Num = maxNum;
                maxNum = value;
                continue;
            }
            if (value == maxNum) {
                continue;
            }
            if (value > max2Num) {
                max2Num = value;
            }
        }
        return max2Num;
    }
}