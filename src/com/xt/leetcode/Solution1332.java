package com.xt.leetcode;

import java.util.*;

/**
 * 1332. 删除回文子序列
 * 描述
 * 给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
 * <p>
 * 返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
 * <p>
 * 「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
 * <p>
 * 「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ababa"
 * 输出：1
 * 解释：字符串本身就是回文序列，只需要删除一次。
 * 示例 2：
 * <p>
 * 输入：s = "abb"
 * 输出：2
 * 解释："abb" -> "bb" -> "".
 * 先删除回文子序列 "a"，然后再删除 "bb"。
 * 示例 3：
 * <p>
 * 输入：s = "baabb"
 * 输出：2
 * 解释："baabb" -> "b" -> "".
 * 先删除回文子序列 "baab"，然后再删除 "b"。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅包含字母 'a'  和 'b'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-palindromic-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题的核心是子序列，并不是字串。
 * 所以无论字符串如何复杂，最多就两次，第一次把a全拆出来，第二次把b全拆出来。
 * 所以我们只要判断一下这个字符串本身是不是回文，如果是就返回1，不是返回2就好了。
 * <p>
 * state:done
 */
public class Solution1332 {

    public int removePalindromeSub(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String value = s.substring(i, i + 1);
            builder.insert(0, value);
        }
        if (builder.toString().equals(s)) {
            return 1;
        }
        return 2;
    }

}