package com.xt.leetcode;

import java.util.*;

/**
 * 1763. 最长的美好子字符串
 * 日期：2022.2.1
 * 描述
 * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
 * <p>
 * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "YazaAay"
 * 输出："aAa"
 * 解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
 * "aAa" 是最长的美好子字符串。
 * 示例 2：
 * <p>
 * 输入：s = "Bb"
 * 输出："Bb"
 * 解释："Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。
 * 示例 3：
 * <p>
 * 输入：s = "c"
 * 输出：""
 * 解释：没有美好子字符串。
 * 示例 4：
 * <p>
 * 输入：s = "dDzeE"
 * 输出："dD"
 * 解释："dD" 和 "eE" 都是最长美好子字符串。
 * 由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 100
 * s 只包含大写和小写英文字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-nice-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 这题的核心是找到那些导致不美好的字符。
 * 所以我的思路是这样的，从头开始遍历找，如果找到导致不美好字符，比如位置是7，则分两部分，0-6和8-end这两部分递归继续求最长美好字符串。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1763 {

    public String longestNiceSubstring(String s) {
        String search = search(s);
        return search;
    }

    private String search(String s) {
        if (s.length() <= 1) {
            return "";
        }
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char aChar = s.charAt(i);
            char otherChar = '.';
            if (aChar < 'a') {
                otherChar = (char) (aChar + 32);
            } else {
                otherChar = (char) (aChar - 32);
            }

            int index = s.indexOf(String.valueOf(otherChar));
            if (index == -1) {
                String search1 = search(s.substring(start, i));
                String search2 = search(s.substring(i + 1));
                return search1.length() >= search2.length() ? search1 : search2;
            }
        }
        return s;
    }
}