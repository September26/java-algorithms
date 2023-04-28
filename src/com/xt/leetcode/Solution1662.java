package com.xt.leetcode;

import java.util.Vector;

/**
 * 1662. 检查两个字符串数组是否相等
 * 每日一题：2022.11.1
 * 完成日期：2022.11.1
 * 链接：
 * 描述：https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent/description/
 * <p>
 * 解题思路：
 * 直接拼成字符串比较就好了
 * <p>
 * <p>
 * state:
 */
public class Solution1662 {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder builder = new StringBuilder();
        String str1;
        for (String str : word1) {
            builder.append(str);
        }
        str1 = builder.toString();
        builder.setLength(0);
        for (String str : word2) {
            builder.append(str);
        }
        return str1.equals(builder.toString());
    }
}