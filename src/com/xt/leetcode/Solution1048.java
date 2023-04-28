package com.xt.leetcode;

import java.util.*;

/**
 * 1048. 最长字符串链
 * 每日一题：2023.04.27
 * 完成日期：2023.04.27
 * 链接：https://leetcode.cn/problems/longest-string-chain/
 * 描述：
 * 给出一个单词数组 words ，其中每个单词都由小写英文字母组成。
 * <p>
 * 如果我们可以 不改变其他字符的顺序 ，在 wordA 的任何地方添加 恰好一个 字母使其变成 wordB ，那么我们认为 wordA 是 wordB 的 前身 。
 * <p>
 * 例如，"abc" 是 "abac" 的 前身 ，而 "cba" 不是 "bcad" 的 前身
 * 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word1 是 word2 的前身，word2 是 word3 的前身，依此类推。一个单词通常是 k == 1 的 单词链 。
 * <p>
 * 从给定单词列表 words 中选择单词组成词链，返回 词链的 最长可能长度 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["a","b","ba","bca","bda","bdca"]
 * 输出：4
 * 解释：最长单词链之一为 ["a","ba","bda","bdca"]
 * 示例 2:
 * <p>
 * 输入：words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * 输出：5
 * 解释：所有的单词都可以放入单词链 ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
 * 示例 3:
 * <p>
 * 输入：words = ["abcd","dbqca"]
 * 输出：1
 * 解释：字链["abcd"]是最长的字链之一。
 * ["abcd"，"dbqca"]不是一个有效的单词链，因为字母的顺序被改变了。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] 仅由小写英文字母组成。
 * 解题思路：
 * 构建一个Map<Integer, Map<String, Integer>>类型的map，key代表字符串长度，value的Map代表每个字符串的单词链最长长度。
 * 然后按照单词的长度分组。
 * 分别遍历单词的长度length分别遍历，每次遍历时，去查找length-1长度的map中，是否有满足要求的，如果有，则在其长度上+1，如果没有，则长度设置为1。
 *
 *
 * state:done
 */
public class Solution1048 {
    int mMax = 1;

    public int longestStrChain(String[] words) {
        TreeMap<Integer, List<String>> tree = new TreeMap<>();
        for (String str : words) {
            int length = str.length();
            List<String> strings = tree.get(length);
            if (strings == null) {
                strings = new ArrayList<>();
                tree.put(length, strings);
            }
            strings.add(str);
        }
        Map<Integer, Map<String, Integer>> map = new HashMap<>();
        for (int i = 0; i <= 16; i++) {
            map.put(i, new HashMap<>());
        }

        for (int length : tree.keySet()) {
            List<String> strings = tree.get(length);
            search(length, strings, map);
        }
        return mMax;
    }

    public void search(int length, List<String> list, Map<Integer, Map<String, Integer>> map) {
        Map<String, Integer> shortMap = map.get(length - 1);
        Map<String, Integer> longMap = map.get(length);
        for (String str : list) {
            for (String key : shortMap.keySet()) {
                if (isMatch(key, str)) {
                    int newLength = shortMap.get(key) + 1;
                    longMap.put(str, Math.max(newLength, longMap.getOrDefault(str, 0)));
                    mMax = Math.max(mMax, newLength);
                }
            }
            if (longMap.get(str) == null) {
                longMap.put(str, 1);
            }
        }
    }

    public boolean isMatch(String shortStr, String longStr) {
        if ((longStr.length() - shortStr.length()) != 1) {
            return false;
        }
        char[] chars1 = shortStr.toCharArray();
        char[] chars2 = longStr.toCharArray();
        int num = 0;
        for (int i = 0; i < chars2.length; i++) {
            if (i - num == chars1.length) {
                return true;
            }
            if (chars1[i - num] == chars2[i]) {
                continue;
            }
            num++;
            if (num > 1) {
                return false;
            }
        }
        return true;
    }
}