package com.xt.leetcode;

import java.util.*;

/**
 * 剑指 Offer II 114. 外星文字典
 * 每日一题：2022.05.31
 * 完成日期：2022.05.31
 * 链接：https://leetcode.cn/problems/Jf1JuT/
 * 描述：
 * 现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
 * <p>
 * 给定一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
 * <p>
 * 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
 * <p>
 * 字符串 s 字典顺序小于 字符串 t 有两种情况：
 * <p>
 * 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
 * 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["wrt","wrf","er","ett","rftt"]
 * 输出："wertf"
 * 示例 2：
 * <p>
 * 输入：words = ["z","x"]
 * 输出："zx"
 * 示例 3：
 * <p>
 * 输入：words = ["z","x","z"]
 * 输出：""
 * 解释：不存在合法字母顺序，因此返回 "" 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 仅由小写英文字母组成
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/Jf1JuT
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题困难题，时间问题没有完成，只是想了个大体思路写了一下。
 * 先通过递归的方式，找出其所有的依赖关系。比如a在b前面，则a的HashSet中添加b。
 * 这样最终得出的所有的依赖。
 * 然后通过拓扑排序 + 深度优先搜索进行搜索，从而推导出正确的字符串。
 * <p>
 * <p>
 * state:done
 */
public class Solution114 {

    public String alienOrder(String[] words) {
        HashSet<Character>[] sets = new HashSet[26];
        for (int i = 0; i < 26; i++) {
            sets[i] = new HashSet<>();
        }
        searchRely(0, Arrays.asList(words), sets);
        return "";
    }

    private void searchRely(int index, List<String> words, HashSet<Character>[] sets) {
        if (words.size() == 0) {
            return;
        }
        char lastChar = 0;
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i <= words.size(); i++) {
            if (i == words.size()) {
                searchRely(index + 1, newList, sets);
                break;
            }
            String str = words.get(i);
            char c = str.charAt(index);
            if (lastChar == 0) {
                lastChar = c;
                newList.add(str);
                continue;
            }
            if (c != lastChar) {
                sets[c - 'a'].add(lastChar);
                lastChar = c;
                searchRely(index + 1, newList, sets);
                newList.clear();
            } else {
                newList.add(str);
            }
        }
    }
}