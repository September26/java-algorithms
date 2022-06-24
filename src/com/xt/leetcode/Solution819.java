package com.xt.leetcode;

import java.util.*;

/**
 * 1688.比赛中的配对次数
 * 每日一题：2022.02.07
 * 完成日期：
 * 链接：
 * 描述：
 * 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
 * <p>
 * 题目保证至少有一个词不在禁用列表中，而且答案唯一。
 * <p>
 * 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * 输出: "ball"
 * 解释:
 * "hit" 出现了3次，但它是一个禁用的单词。
 * "ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。
 * 注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"），
 * "hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= 段落长度 <= 1000
 * 0 <= 禁用单词个数 <= 100
 * 1 <= 禁用单词长度 <= 10
 * 答案是唯一的, 且都是小写字母 (即使在 paragraph 里是大写的，即使是一些特定的名词，答案都是小写的。)
 * paragraph 只包含字母、空格和下列标点符号!?',;.
 * 不存在没有连字符或者带有连字符的单词。
 * 单词里只包含字母，不会出现省略号或者其他标点符号。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/most-common-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 遍历paragraph字符串，如果遇到符合条件的字符，则截取之前的字符串。添加到map当中，并统计次数。
 * 最后遍历这个map,如果在banned中则跳过，如果不在，则记录最高的那个字符串。
 * <p>
 * state:done
 */
public class Solution819 {
    //    Bob hit a ball, the hit BALL flew far after it was hit
    public String mostCommonWord(String paragraph, String[] banned) {
        HashSet<Character> splitSet = new HashSet<>();
        splitSet.add('!');
        splitSet.add(' ');
        splitSet.add('?');
        splitSet.add('\'');
        splitSet.add(',');
        splitSet.add(';');
        splitSet.add('.');

        Map<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>(Arrays.asList(banned));
        int start = 0;
        char[] chars = paragraph.toCharArray();
        for (int i = 0; i <= paragraph.length(); i++) {
            if (i == paragraph.length() || splitSet.contains(chars[i])) {
                String str = paragraph.substring(start, i).toLowerCase();
                if (str.length() == 0) {
                    start = i + 1;
                    continue;
                }
                Integer num = map.getOrDefault(str, 0);
                map.put(str, num + 1);
                start = i + 1;
            }
        }
        int max = 0;
        String result = "";
        for (String key : map.keySet()) {
            if (set.contains(key)) {
                continue;
            }
            Integer integer = map.get(key);
            if (integer > max) {
                result = key;
                max = integer;
            }
        }
        return result;
    }
}