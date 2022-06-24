package com.xt.leetcode;

import java.util.Vector;

/**
 * 1688.比赛中的配对次数
 * 每日一题：2022.02.07
 * 完成日期：
 * 链接：
 * 描述：
 * <p>
 * 解题思路：
 * 转为string,然后判断距离即可
 * <p>
 * <p>
 * state:
 */
public class Solution868 {

    public int binaryGap(int n) {
        char[] chars = Integer.toString(n, 2).toCharArray();
        int result = 0;
        int currentIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '0') {
                continue;
            }
            result = Math.max(i - currentIndex, result);
            currentIndex = i;
        }

        return result;
    }
}