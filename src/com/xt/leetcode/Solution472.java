package com.xt.leetcode;

import java.util.*;

/**
 * 472. 连接词
 * 12月28日
 * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * <p>
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 解释："catsdogcats" 由 "cats", "dog" 和 "cats" 组成;
 * "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成;
 * "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
 * <p>
 * 示例 2：
 * <p>
 * 输入：words = ["cat","dog","catdog"]
 * 输出：["catdog"]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 10^4
 * 0 <= words[i].length <= 1000
 * words[i] 仅由小写字母组成
 * 0 <= sum(words[i].length) <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/concatenated-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * <p>
 * state:done
 */
public class Solution472 {

    Trie trie = new Trie();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<String>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() == 0) {
                continue;
            }
            if (dfs(word, 0)) {
                ans.add(word);
            } else {
                insert(word);
            }
        }
        return ans;
    }

    public boolean dfs(String word, int start) {
        if (word.length() == start) {
            return true;
        }
        Trie node = trie;
        for (int i = start; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            node = node.children[index];
            if (node == null) {
                return false;
            }
            if (node.isEnd) {
                if (dfs(word, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void insert(String word) {
        Trie node = trie;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }
}

class Trie {
    Trie[] children;
    boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }
}

//    Map<String, Integer> map = new HashMap<>();
//    Map<String, Boolean> resultMap = new HashMap<>();
//
//    public List<String> findAllConcatenatedWordsInADict(String[] words) {
//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < words.length; i++) {
//            String word = words[i];
//            map.put(word, i);
//        }
//        for (int i = 0; i < words.length; i++) {
//            String word = words[i];
//            boolean match = isMatch(word, 0, 1, i);
//            if (match) {
//                list.add(word);
//            }
//            System.out.println("i:" + i + ",num:" + num);
//        }
//        return list;
//    }
//
//    int num = 0;
//
//    private boolean isMatch(String word, int startIndex, int length, int index) {
//        if (startIndex == word.length()) {
//            return false;
//        }
//        String substring = word.substring(startIndex);
//        Integer integer1 = map.get(substring);
//        if (integer1 != null && integer1 != index) {
//            return true;
//        }
//        if (substring.length() == 1) {
//            return false;
//        }
//        Boolean aBoolean = resultMap.get(substring);
//        if (aBoolean != null) {
//            return aBoolean;
//        }
//        num++;
//        int end = startIndex + length;
//        while (end <= word.length()) {
//            String str = word.substring(startIndex, end);
//            Integer integer = map.get(str);
//            if (integer == null) {
//                end++;
//                continue;
//            }
//            //分两种情况继续
//            boolean match = isMatch(word, startIndex, str.length() + 1, index);
//            if (match) {
//                resultMap.put(substring, true);
//                return true;
//            }
//            match = isMatch(word, end, 1, index);
//            resultMap.put(substring, match);
//            return match;
//        }
//        return false;
//    }
//
//    static class Trie {
//        Trie[] children;
//        boolean isEnd;
//
//        public Trie() {
//            children = new Trie[26];
//            isEnd = false;
//        }
//    }
//
//}