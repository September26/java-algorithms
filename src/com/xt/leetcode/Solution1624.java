package com.xt.leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * 1624. 两个相同字符之间的最长子字符串
 * 每日一题：2022.09.18
 * 完成日期：2022.09.18
 * 链接：https://leetcode.cn/problems/largest-substring-between-two-equal-characters/
 * 描述：
 * 给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。如果不存在这样的子字符串，返回 -1 。
 * <p>
 * 子字符串 是字符串中的一个连续字符序列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aa"
 * 输出：0
 * 解释：最优的子字符串是两个 'a' 之间的空子字符串。
 * 示例 2：
 * <p>
 * 输入：s = "abca"
 * 输出：2
 * 解释：最优的子字符串是 "bc" 。
 * 示例 3：
 * <p>
 * 输入：s = "cbzxy"
 * 输出：-1
 * 解释：s 中不存在出现出现两次的字符，所以返回 -1 。
 * 示例 4：
 * <p>
 * 输入：s = "cabbac"
 * 输出：4
 * 解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 300
 * s 只含小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-substring-between-two-equal-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 使用一个长度为26的数组来保存，key为对应的字符，value为一个长度为2的数组，
 * ints[0]为对应字符首次出现的的位置，ints[1]为对应字符最后一次出现的位置。
 * 最后对这个数组进行遍历，求出最大值。
 *
 * <p>
 * state:
 */
public class Solution1624 {

    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, Integer[]> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            Integer[] integers = map.get(aChar);
            if (integers == null) {
                integers = new Integer[2];
                integers[0] = i;
                map.put(aChar, integers);
                continue;
            }
            integers[1] = i;
        }
        int max = -1;
        for (Integer[] integers : map.values()) {
            max = Math.max(max, integers[1] == null ? 0 : integers[1] - integers[0]);
        }
        return max - 1;
    }
}