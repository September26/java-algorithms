package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 1638. 统计只差一个字符的子串数目
 * 每日一题：2023.03.27
 * 完成日期：2023.03.27
 * 链接：https://leetcode.cn/problems/count-substrings-that-differ-by-one-character/
 * 描述：
 * 给你两个字符串 s 和 t ，请你找出 s 中的非空子串的数目，这些子串满足替换 一个不同字符 以后，是 t 串的子串。换言之，请你找到 s 和 t 串中 恰好 只有一个字符不同的子字符串对的数目。
 * <p>
 * 比方说， "computer" and "computation" 只有一个字符不同： 'e'/'a' ，所以这一对子字符串会给答案加 1 。
 * <p>
 * 请你返回满足上述条件的不同子字符串对数目。
 * <p>
 * 一个 子字符串 是一个字符串中连续的字符。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aba", t = "baba"
 * 输出：6
 * 解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * 加粗部分分别表示 s 和 t 串选出来的子字符串。
 * 示例 2：
 * 输入：s = "ab", t = "bb"
 * 输出：3
 * 解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
 * ("ab", "bb")
 * ("ab", "bb")
 * ("ab", "bb")
 * 加粗部分分别表示 s 和 t 串选出来的子字符串。
 * 示例 3：
 * 输入：s = "a", t = "a"
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：s = "abe", t = "bbc"
 * 输出：10
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length, t.length <= 100
 * s 和 t 都只包含小写英文字母。
 * <p>
 * 解题思路：
 * 穷尽s中所有可能的子串，寻找其在t中是否存在只差一个的情况。
 * 因为构造的子串有可能会重复，所以构建一个map缓存，如果出现重复的子串，直接返回缓存值即可。
 * 时间原因没有详细实现，用的是官方题解的答案。
 * <p>
 * <p>
 * state:done
 */
public class Solution1638 {

    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length();
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diff = 0;
                //k为长度。比较两个字符串，
                for (int k = 0; i + k < m && j + k < n; k++) {
                    diff += s.charAt(i + k) == t.charAt(j + k) ? 0 : 1;
                    if (diff > 1) {
                        break;
                    } else if (diff == 1) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

}