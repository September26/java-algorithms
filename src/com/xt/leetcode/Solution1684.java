package com.xt.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * 1684. 统计一致字符串的数目
 * 每日一题：2022.11.08
 * 完成日期：2022.11.08
 * 链接：https://leetcode.cn/problems/count-the-number-of-consistent-strings/description/
 * 描述：
 * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
 *
 * 请你返回 words 数组中 一致字符串 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
 * 输出：2
 * 解释：字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
 * 示例 2：
 *
 * 输入：allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
 * 输出：7
 * 解释：所有字符串都是一致的。
 * 示例 3：
 *
 * 输入：allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
 * 输出：4
 * 解释：字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 104
 * 1 <= allowed.length <= 26
 * 1 <= words[i].length <= 10
 * allowed 中的字符 互不相同 。
 * words[i] 和 allowed 只包含小写英文字母。
 * <p>
 * 解题思路：
 * 针对allowed构建set，存放所有的字符。
 * 然后依次遍历words中的字符串，如果都在set中，则为true，反之false。
 * <p>
 * <p>
 * state:
 */
public class Solution1684 {

    public int countConsistentStrings(String allowed, String[] words) {
        char[] chars = allowed.toCharArray();
        Set<Integer> set = new HashSet<>();
        for (char c : chars) {
            set.add((int) c);
        }
        int result = 0;
        for (String word : words) {
            if (isFit(word, set)) {
                result++;
            }
        }
        return result;

    }

    private boolean isFit(String word, Set<Integer> set) {
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (set.contains((int) c)) {
                continue;
            }
            return false;
        }
        return true;

    }

}