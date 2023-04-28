package com.xt.leetcode;

import java.util.*;

/**
 * 784. 字母大小写全排列
 * 每日一题：2022.10.29
 * 完成日期：2022.10.30
 * 链接：https://leetcode.cn/problems/letter-case-permutation/
 * 描述：
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 * <p>
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 示例 2:
 * <p>
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length <= 12
 * s 由小写英文字母、大写英文字母和数字组成
 * <p>
 * 解题思路：
 * 这题本身不难，考点应该是我们如何用最简单的代码去实现这个功能。
 * 这里我考虑的是使用是递归的方案，如果第i位是字母，则生成两个递归
 * <p>
 * state:done
 */
public class Solution784 {

    public List<String> letterCasePermutation(String s) {
        ArrayList<String> list = new ArrayList<>();
        searchAChar(list, s, s.toCharArray(), 0);
        return list;
    }

    public void searchAChar(List<String> list, String s, char[] chars, int i) {
        if (i == s.length()) {
            list.add(s);
            return;
        }
        int i1 = i + 1;
        if ((chars[i] <= 'z' && chars[i] >= 'a') || (chars[i] <= 'Z' && chars[i] >= 'A')) {
            searchAChar(list, s.substring(0, i) + s.substring(i, i + 1).toUpperCase() + s.substring(i + 1), chars, i1);
            searchAChar(list, s.substring(0, i) + s.substring(i, i + 1).toLowerCase() + s.substring(i + 1), chars, i1);
            return;
        }
        searchAChar(list, s, chars, i1);
    }
}