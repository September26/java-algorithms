package com.xt.leetcode;

import java.util.*;

/**
 * 720. 词典中最长的单词
 * 每日一题：2022.03.17
 * 完成日期：2022.03.17
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary/
 * 描述：
 * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
 * <p>
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
 * 示例 2：
 * <p>
 * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * 所有输入的字符串 words[i] 都只包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先按照字符串长度排序，长的字符串一定由短的组成的。
 * 每遍历一个字符串如果符合就加入到set当中。判断条件为则新加入的字符串取substring(0, length - 1)，如果set中存在就加入，不存在则说明不符合。
 * 最终对list种的字符串计算字序，返回结果
 * <p>
 * <p>
 * state:done
 */
public class Solution720 {

    public String longestWord(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        int maxLength = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int length = word.length();
            if (length == 1) {
                set.add(word);
                maxLength = 1;
                list.add(word);
                continue;
            }
            if (!set.contains(word.substring(0, length - 1))) {
                continue;
            }
            set.add(word);
            if (length > maxLength) {
                list.clear();
                list.add(word);
                maxLength = length;
            } else if (length == maxLength) {
                list.add(word);
            }
        }
        if (list.size() == 0) {
            return "";
        }
        String result = list.get(0);
        for (int k = 1; k < list.size(); k++) {
            String str = list.get(k);
            char[] chars = str.toCharArray();
            char[] chars1 = result.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == chars1[i]) {
                    continue;
                }
                result = chars[i] < chars1[i] ? str : result;
                break;
            }
        }
        return result;
    }
}