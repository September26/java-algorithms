package com.xt.leetcode;

import java.util.*;

/**
 * 30. 串联所有单词的子串
 * 每日一题：2022.06.23
 * 完成日期：2022.06.23
 * 链接：https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
 * 描述：
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题我的解题思路是首先遍历一遍s，找出所有word的起始位置。
 * 做法就是先遍历words集合，找出每个word在s中的index位置，添加到indexWordMap中，同时汇总到一个wordIndexList中，然后排序一下。
 * 我们有了这样的一个组合，就可以知道一个单词之后的 那个单词是否对应的上了。
 * 我们遍历indexMap，这个map记录了每个单词对应的位置集合。我们创建一个新的集合包含words中的所有单词，集合中remove掉当前位置的，继续往后寻找，判断是否符合。
 * List<String> set = createList(words);
 * set.remove(word);
 * flag = forwardSearch(word, set, inWordIndex);
 * 这样下来，我们就能知道所有的结果了，但是这样算下来时间复杂度比较高，所以我们构建一层缓存。比如ababababa这样的字符串，word总长度为5时，index=0和index=2，对应的字符串都是ababa，所以我们就可以以ababa为key了。
 * String cacheKey = s.substring(inWordIndex, inWordIndex + wordLength);
 * //这里创建一层缓存，如果输入的字符串长度相同，则直接返回结果
 * Boolean flag = resultMap.get(cacheKey);
 * <p>
 * state:done
 */
public class Solution30 {
    /**
     * key:字符串
     * value:对应的位置的集合
     */
    Map<String, List<Integer>> indexMap = new HashMap<>();

    /**
     * key:index位置
     * value:对应的字符串
     */
    Map<Integer, String> indexWordMap = new HashMap<>();

    /**
     * 每个符合的字段在字符串中的位置
     */
    List<Integer> wordIndexList = new ArrayList<>();

    Map<String, Boolean> resultMap = new HashMap<>();

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> resultList = new ArrayList<>();
        int wordLength = 0;
        for (int i = 0; i < words.length; i++) {
            wordLength += words[i].length();
            List<Integer> integers = indexMap.computeIfAbsent(words[i], f -> new ArrayList<Integer>());
            if (integers.size() > 0) {
                continue;
            }
            int find = 0;
            while (find < s.length()) {
                find = s.indexOf(words[i], find);
                if (find != -1) {
                    integers.add(find);
                    find++;
                } else {
                    break;
                }
            }
        }
        for (String key : indexMap.keySet()) {
            List<Integer> integers = indexMap.get(key);
            for (Integer index : integers) {
                indexWordMap.put(index, key);
                wordIndexList.add(index);
            }
        }
        Collections.sort(wordIndexList);


        for (String word : indexMap.keySet()) {
            List<Integer> integers = indexMap.get(word);
            for (Integer inWordIndex : integers) {
                if (inWordIndex + wordLength > s.length()) {
                    continue;
                }
                String cacheKey = s.substring(inWordIndex, inWordIndex + wordLength);
                //这里创建一层缓存，如果输入的字符串长度相同，则直接返回结果
                Boolean flag = resultMap.get(cacheKey);
                if (flag == null) {
                    List<String> set = createList(words);
                    set.remove(word);
                    flag = forwardSearch(word, set, inWordIndex);
                    resultMap.put(cacheKey, flag);
                }
                if (flag) {
                    resultList.add(inWordIndex);
                }
            }
        }

        return resultList;
    }

    /**
     * @param currentWord
     * @param set
     * @param inWordIndex
     * @return
     */
    public boolean forwardSearch(String currentWord, List<String> set, int inWordIndex) {
        if (set.size() == 0) {
            return true;
        }
        while (set.size() > 0) {
            int nextWordIndex = inWordIndex + currentWord.length();
            int nextIndex = wordIndexList.indexOf(nextWordIndex);
            if (nextIndex == -1) {
                return false;
            }
            //改成非递归的模式
            String s = indexWordMap.get(nextWordIndex);
            if (set.contains(s)) {
                set.remove(s);
                currentWord = s;
                inWordIndex = nextWordIndex;
            } else {
                return false;
            }
        }
        return true;
    }

    public List<String> createList(String[] words) {
        return new ArrayList<>(Arrays.asList(words));
    }


}