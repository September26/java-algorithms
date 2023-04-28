package com.xt.leetcode;

import java.util.*;

/**
 * 792. 匹配子序列的单词数
 * 每日一题：2022.11.17
 * 完成日期：2022.11.17
 * 链接：https://leetcode.cn/problems/number-of-matching-subsequences/
 * 描述：
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 * <p>
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 * <p>
 * 例如， “ace” 是 “abcde” 的子序列。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcde", words = ["a","bb","acd","ace"]
 * 输出: 3
 * 解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
 * Example 2:
 * <p>
 * 输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * 输出: 2
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length <= 5 * 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * words[i]和 s 都只由小写字母组成。
 * 解题思路：
 * 首先我们这里看复杂度，首先5W一定要遍历一遍的，其次50的长度也一定要遍历一遍的，所以我们优化的重点应该放在如何把words的5000长度优化为1。
 * 可以把s转换为一个Map<Character,List<Integer>>的结构，但是因为是a到z，所以使用数组更为合适。
 * 构建一个List<Integer>[]类型的数组，存放的s中每个字符出现的位置集合，另外使用一个数组int[] indexs记录遍历是每个字符在List集合中使用到的位置。
 * 遍历words，依次读取每个字符，根据字符找到List<Integer>集合，从集合中读取最靠前并且大于当前位置currentCharIndex的index值，
 * 找到，则继续，找不到说明不满足则退出此次循环。
 *
 * <p>
 * state:
 */
public class Solution792 {

    public int numMatchingSubseq(String s, String[] words) {
        int result = 0;
        int[] indexs = new int[26];
        List<Integer>[] lists = new ArrayList[26];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            lists[aChar - 'a'].add(i);
        }

        for (String word : words) {
            Arrays.fill(indexs, 0);
            char[] wordChar = word.toCharArray();
            int currentCharIndex = -1;
            for (int i = 0; i < wordChar.length; i++) {
                char c = wordChar[i];
                int charInt = c - 'a';
                List<Integer> list = lists[charInt];
                int index = indexs[charInt];
                if (index >= list.size()) {
                    break;
                }
                int oldcurrentCharIndex = currentCharIndex;
                while (index < list.size()) {
                    Integer charIndex = list.get(index);
                    index++;
                    indexs[charInt] = index;
                    if (charIndex >= currentCharIndex) {
                        currentCharIndex = charIndex;
                        break;
                    }
                }
                //找不时，此时oldcurrentCharIndex == currentCharIndex
                if (currentCharIndex == oldcurrentCharIndex) {
                    break;
                }
                if (i == wordChar.length - 1) {
                    result++;
                }
            }
        }
        return result;
    }
}