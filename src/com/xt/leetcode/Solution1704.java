package com.xt.leetcode;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1704. 判断字符串的两半是否相似
 * 每日一题：2022.11.11
 * 完成日期：2022.11.11
 * 链接：https://leetcode.cn/problems/determine-if-string-halves-are-alike/
 * 描述：
 * 给你一个偶数长度的字符串 s 。将其拆分成长度相同的两半,前一半为 a ,后一半为 b 。
 * <p>
 * 两个字符串 相似 的前提是它们都含有相同数目的元音（'a','e','i','o','u','A','E','I','O','U'）。注意,s 可能同时含有大写和小写字母。
 * <p>
 * 如果 a 和 b 相似,返回 true ；否则,返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "book"
 * 输出：true
 * 解释：a = "bo" 且 b = "ok" 。a 中有 1 个元音,b 也有 1 个元音。所以,a 和 b 相似。
 * 示例 2：
 * <p>
 * 输入：s = "textbook"
 * 输出：false
 * 解释：a = "text" 且 b = "book" 。a 中有 1 个元音,b 中有 2 个元音。因此,a 和 b 不相似。
 * 注意,元音 o 在 b 中出现两次,记为 2 个。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= s.length <= 1000
 * s.length 是偶数
 * s 由 大写和小写 字母组成
 * <p>
 * 解题思路：
 * 通过set记录所有元音，然后分别此案例前半部分和后半部分，计算出各自的元音数量
 * <p>
 * state:done
 */
public class Solution1704 {

    public boolean halvesAreAlike(String s) {
        Character[] chars = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        Set<Character> set = new HashSet(Arrays.asList(chars));
        char[] achars = s.substring(0, s.length() / 2).toCharArray();
        char[] bchars = s.substring(s.length() / 2).toCharArray();
        int aNum = 0;
        int bNum = 0;
        for (int i = 0; i < achars.length; i++) {
            if (set.contains(achars[i])) {
                aNum++;
            }
            if (set.contains(bchars[i])) {
                bNum++;
            }
        }
        return aNum == bNum;
    }
}