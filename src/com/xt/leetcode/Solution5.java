package com.xt.leetcode;

import java.util.*;

/**
 * 5.最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * done
 * 时间复杂度O(n*log(n))
 */
public class Solution5 {

    HashMap<Integer, List<Integer>> indexMap = new HashMap<>();
    String reverseStr = "";
    String str = "";

    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        str = s;
        //循环一遍，得出每个字符的位置
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            int aChar = chars[i];
            List<Integer> integers = indexMap.computeIfAbsent(aChar, k -> new ArrayList<>());
            integers.add(i);
            builder.insert(0, (char) aChar);
        }
        reverseStr = builder.toString();

        int maxLength = 0;
        String maxStr = "";
        String currentStr = "";
        for (int i = 0; i < chars.length; i++) {
            int aChar = chars[i];
            List<Integer> integers = indexMap.get(aChar);
            if (integers.size() == 1) {
                currentStr = s.substring(integers.get(0), integers.get(0) + 1);
                if (currentStr.length() > maxLength) {
                    maxLength = currentStr.length();
                    maxStr = currentStr;
                }
                continue;
            }
            for (int k = integers.size() - 1; k >= 0; k--) {
                Integer end = integers.get(k);
                if (i >= end) {
                    break;
                }
                boolean paindrome = isPaindrome(str, reverseStr, i, end);
                if (paindrome) {
                    currentStr = str.substring(i, end + 1);
                    if (currentStr.length() > maxLength) {
                        maxLength = currentStr.length();
                        maxStr = currentStr;
                    }
                    break;
                }
            }
        }
        return maxStr;
    }

    /**
     * 判断是否回文子串
     *
     * @param str        当前字符串
     * @param startIndex
     * @param endIndex
     * @return
     */
    public boolean isPaindrome(String str, String reverseStr, int startIndex, int endIndex) {
        String substring1 = str.substring(startIndex, endIndex + 1);
        String substring2 = reverseStr.substring(str.length() - endIndex - 1, str.length() - startIndex - 1 + 1);
        return substring1.equals(substring2);
    }
}