package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 1417. 重新格式化字符串
 * 每日一题：2022.08.11
 * 完成日期：2022.08.11
 * 链接：https://leetcode.cn/problems/reformat-the-string/
 * 描述：
 * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
 * <p>
 * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
 * <p>
 * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "a0b1c2"
 * 输出："0a1b2c"
 * 解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b" 也是满足题目要求的答案。
 * 示例 2：
 * <p>
 * 输入：s = "leetcode"
 * 输出：""
 * 解释："leetcode" 中只有字母，所以无法满足重新格式化的条件。
 * 示例 3：
 * <p>
 * 输入：s = "1229857369"
 * 输出：""
 * 解释："1229857369" 中只有数字，所以无法满足重新格式化的条件。
 * 示例 4：
 * <p>
 * 输入：s = "covid2019"
 * 输出："c2o0v1i9d"
 * 示例 5：
 * <p>
 * 输入：s = "ab123"
 * 输出："1a2b3"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 仅由小写英文字母和/或数字组成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reformat-the-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 把s分割成字符，然后按照数字，字母分为两个集合。
 * 如果两个集合的长度之差大于等于2，则就没有符合的。
 * 否则依次一个集合取一个
 * <p>
 * <p>
 * state:done
 */
public class Solution1417 {

    public String reformat(String s) {
        char[] chars = s.toCharArray();
        List<Character> numList = new ArrayList<>();
        List<Character> letterList = new ArrayList<>();
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                numList.add(c);
            } else {
                letterList.add(c);
            }
        }
        List<Character> longerList;
        List<Character> shortList;
        if (numList.size() >= letterList.size()) {
            longerList = numList;
            shortList = letterList;
        } else {
            longerList = letterList;
            shortList = numList;
        }

        if (longerList.size() - shortList.size() >= 2) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < longerList.size(); i++) {
            builder.append(longerList.get(i));
            if (i >= shortList.size()) {
                break;
            }
            builder.append(shortList.get(i));
        }
        return builder.toString();
    }
}