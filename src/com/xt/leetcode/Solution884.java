package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * 884. 两句话中的不常见单词
 * 日期：2021.1.29
 * 描述
 * 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
 * <p>
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
 * <p>
 * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s1 = "this apple is sweet", s2 = "this apple is sour"
 * 输出：["sweet","sour"]
 * 示例 2：
 * <p>
 * 输入：s1 = "apple apple", s2 = "banana"
 * 输出：["banana"]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s1.length, s2.length <= 200
 * s1 和 s2 由小写英文字母和空格组成
 * s1 和 s2 都不含前导或尾随空格
 * s1 和 s2 中的所有单词间均由单个空格分隔
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/uncommon-words-from-two-sentences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 比较简单，根据题目描述，只要求出所有单词出现次数为1的单词即可。所有构建一个map，key为单词，value为出现的次数，遍历两个字符串。
 * <p>
 * <p>
 * state:
 */
public class Solution884 {

    public String[] uncommonFromSentences(String s1, String s2) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] split1 = s1.split(" ");
        String[] split2 = s2.split(" ");
        for (String str : split1) {
            Integer num = map.getOrDefault(str, 0);
            map.put(str, num + 1);
        }
        for (String str : split2) {
            Integer num = map.getOrDefault(str, 0);
            map.put(str, num + 1);
        }
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                list.add(key);
            }
        }
        String[] strings = new String[list.size()];
        list.toArray(strings);
        return strings;
    }
}