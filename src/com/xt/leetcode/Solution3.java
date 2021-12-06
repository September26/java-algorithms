package com.xt.leetcode;

import com.xt.model.ListNode;

import java.util.HashMap;

/**
 * 3.无重复自负的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * done
 */
public class Solution3 {

    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int end = 0;
        int maxLength = 0;
        char[] chars = s.toCharArray();
        HashMap<Integer, Integer> timeMap = new HashMap<>();

        while (end < chars.length) {
            int aChar = chars[end];
            Integer index = timeMap.get(aChar);
            if (index == null) {
                timeMap.put(aChar, end);
                end++;
                if ((end - start) > maxLength) {
                    maxLength = end - start;
                }
                continue;
            }
            for (int i = start; i < index; i++) {
                timeMap.remove((int) chars[i]);
            }
            start = index + 1;
            timeMap.put(aChar, end);
            end++;
        }

        return maxLength;
    }
}