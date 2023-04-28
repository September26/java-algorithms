package com.xt.leetcode;

import java.util.*;

/**
 * 791. 自定义字符串排序
 * 每日一题：2022.11.14
 * 完成日期：2022.11.14
 * 链接：https://leetcode.cn/problems/custom-sort-string/
 * 描述：
 * 给定两个字符串 order 和 s 。order 的所有单词都是 唯一 的，并且以前按照一些自定义的顺序排序。
 * <p>
 * 对 s 的字符进行置换，使其与排序的 order 相匹配。更具体地说，如果在 order 中的字符 x 出现字符 y 之前，那么在排列后的字符串中， x 也应该出现在 y 之前。
 * <p>
 * 返回 满足这个性质的 s 的任意排列 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: order = "cba", s = "abcd"
 * 输出: "cbad"
 * 解释:
 * “a”、“b”、“c”是按顺序出现的，所以“a”、“b”、“c”的顺序应该是“c”、“b”、“a”。
 * 因为“d”不是按顺序出现的，所以它可以在返回的字符串中的任何位置。“dcba”、“cdba”、“cbda”也是有效的输出。
 * 示例 2:
 * <p>
 * 输入: order = "cbafg", s = "abcd"
 * 输出: "cbad"
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= order.length <= 26
 * 1 <= s.length <= 200
 * order 和 s 由小写英文字母组成
 * order 中的所有字符都 不同
 * 解题思路：
 * 我的思路是这样的，首先把order转成map，然后遍历s，如果map中存在，则次数+1，并且把对应位置的字符改成'0'。
 * 然后再次遍历s，如果对应的字符为'0'，则说明该字符是需要排序的，从map中按照顺序依次找出次数>0的进行替换。
 *
 * <p>
 * state:done
 */
public class Solution791 {

    public String customSortString(String order, String s) {
        Map<Character, Integer> timesMap = new HashMap<>();
        char[] chars1 = order.toCharArray();
        for (Character c : chars1) {
            timesMap.put(c, 0);
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            Integer integer = timesMap.get(aChar);
            if (integer == null) {
                continue;
            }
            timesMap.put(aChar, integer + 1);
            chars[i] = '0';
        }
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar != '0') {
                continue;
            }
            char c = 0;
            Integer integer = 0;
            while (true) {
                c = chars1[index];
                integer = timesMap.get(c);
                if (integer == 0) {
                    index++;
                    continue;
                }
                break;
            }
            chars[i] = c;
            timesMap.put(c, integer - 1);
        }
        return new String(chars);
    }
}