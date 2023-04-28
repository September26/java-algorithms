package com.xt.leetcode;

import java.util.*;

/**
 * 676. 实现一个魔法字典
 * 每日一题：2022.07.11
 * 完成日期：2022.07.11
 * 链接：https://leetcode.cn/problems/implement-magic-dictionary/
 * 描述：
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 * <p>
 * 实现 MagicDictionary 类：
 * <p>
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * 输出
 * [null, null, false, true, false, false]
 * <p>
 * 解释
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False
 * magicDictionary.search("leetcoded"); // 返回 False
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写英文字母组成
 * dictionary 中的所有字符串 互不相同
 * 1 <= searchWord.length <= 100
 * searchWord 仅由小写英文字母组成
 * buildDict 仅在 search 之前调用一次
 * 最多调用 100 次 search
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-magic-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 因为总长度不多，可以可以适当使用暴力的手段。
 * 把dictionary按照字符长度来分组，然后输入字符串后，按照长度找到对应的集合，挨个遍历。
 * <p>
 * <p>
 * state:
 */
public class Solution676 {

    public static class MagicDictionary {
        Map<Integer, List<String>> map = new HashMap<>();

        public MagicDictionary() {
        }

        public void buildDict(String[] dictionary) {
            for (String str : dictionary) {
                List<String> strings = map.get(str.length());
                if (strings == null) {
                    strings = new ArrayList<>();
                    map.put(str.length(), strings);
                }
                strings.add(str);
            }
        }

        public boolean search(String searchWord) {
            List<String> strings = map.get(searchWord.length());
            if (strings == null) {
                return false;
            }
            for (String str : strings) {
                //比对str和searchWord，看是否只差一个
                char[] oldChars = str.toCharArray();
                char[] searchChars = searchWord.toCharArray();
                int diffNum = 0;
                for (int i = 0; i < oldChars.length; i++) {
                    if (oldChars[i] == searchChars[i]) {
                        continue;
                    }
                    diffNum++;
                }
                if (diffNum == 1) {
                    return true;
                }
            }
            return false;
        }
    }
}