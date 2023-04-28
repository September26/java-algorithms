package com.xt.leetcode;

import java.util.HashSet;
import java.util.Vector;

/**
 * 1805. 字符串中不同整数的数目
 * 每日一题：2022.12.06
 * 完成日期：2022.12.06
 * 链接：https://leetcode.cn/problems/number-of-different-integers-in-a-string/
 * 描述：
 * 给你一个字符串 word ，该字符串由数字和小写英文字母组成。
 * <p>
 * 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123  34 8  34" 。注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）："123"、"34"、"8" 和 "34" 。
 * <p>
 * 返回对 word 完成替换后形成的 不同 整数的数目。
 * <p>
 * 只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：word = "a123bc34d8ef34"
 * 输出：3
 * 解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
 * 示例 2：
 * <p>
 * 输入：word = "leet1234code234"
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：word = "a1b01c001"
 * 输出：1
 * 解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length <= 1000
 * word 由数字和小写英文字母组成
 * <p>
 * 解题思路：
 * 我的思路比较简单，直接替代，使用正则用空格替换a-z。
 * 最后对结果用空格分割，取消str中0的前缀。保存到set中。
 * 最后求set的长度即可。
 *
 * state:done
 */
public class Solution1805 {

    public int numDifferentIntegers(String word) {
        String s = word.replaceAll("[a-z]+", " ");

        String[] s1 = s.split(" ");
        HashSet<String> set = new HashSet<>();
        for (String str : s1) {
            if (str.length() == 0) {
                continue;
            }
            while (str.startsWith("0")){
                str = str.substring(1);
            }
            set.add(str);
        }
        return set.size();
    }
}