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
 * 首先根据word字符的长短进行一个排序，较长的word如果是连接词，那么一定是由几个较短的连接词组成的。所以检查一个word是否是连接词的时候，只需要检查顺序之前的那些word就好了。
 * 所以我们这样实现，首先创建一个map，key=word,value则对应单词在数组中的位置。
 * 遍历word，去判断word是不是连接词，如果是，则加入到list中，不是，则加入到map当中。
 * 接下来就是如何判断word是不是连接词了，我的想法是拼接检查。
 * 比如一个word=abcdefg，则先检查a是否存在，
 * 不存在就检查ab，abc等等。
 * 如果a存在，则继续检查b是否存在。
 * 这时候如果b存在，则检查c，
 * 如果b不存在，还要检查下ab。
 * 这样执行下来，效率比较低耗时1200ms，所以我们要优化。
 * 所以对每次判断的结果做一个缓存，如果命中缓存，直接使用缓存的结果，这样就优化到154ms了。
 * <p>
 * state:done
 */
public class Solution472 {

    Map<String, Integer> map = new HashMap<>();//key为word，value对应单词在数组中的位置

    Map<String, Boolean> resultMap = new HashMap<>();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        ArrayList<String> list = new ArrayList<>();
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return 0;
                }
                return o1.length() > o2.length() ? 1 : -1;
            }
        });
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean match = isMatch(word, 0, 1, i);
            if (match) {
                list.add(word);
            } else {
                map.put(word, i);
            }
        }
        return list;
    }

    private boolean isMatch(String word, int startIndex, int length, int index) {
        if (startIndex == word.length()) {
            return false;
        }
        String substring = word.substring(startIndex);
        Integer integer1 = map.get(substring);
        if (integer1 != null && integer1 != index) {
            return true;
        }
        if (substring.length() == 1) {
            return false;
        }
        Boolean aBoolean = resultMap.get(word);
        if (aBoolean != null) {
            return aBoolean;
        }

        int end = startIndex + length;
        while (end <= word.length()) {
            String str = word.substring(startIndex, end);
            Integer integer = map.get(str);
            if (integer == null) {
                end++;
                continue;
            }
            //分两种情况继续
            boolean match = isMatch(word, startIndex, str.length() + 1, index);
            if (match) {
                resultMap.put(word, true);
                return true;
            }
            match = isMatch(word, end, 1, index);
            resultMap.put(word, match);
            return match;
        }
        return false;
    }
}