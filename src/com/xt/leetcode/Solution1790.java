package com.xt.leetcode;

import javafx.util.Pair;

import java.util.Vector;

/**
 * 1790. 仅执行一次字符串交换能否使两个字符串相等
 * 每日一题：2022.10.11
 * 完成日期：2022.10.11
 * 链接：https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/
 * 描述：
 * 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 * <p>
 * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s1 = "bank", s2 = "kanb"
 * 输出：true
 * 解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
 * 示例 2：
 * <p>
 * 输入：s1 = "attack", s2 = "defend"
 * 输出：false
 * 解释：一次字符串交换无法使两个字符串相等
 * 示例 3：
 * <p>
 * 输入：s1 = "kelb", s2 = "kelb"
 * 输出：true
 * 解释：两个字符串已经相等，所以不需要进行字符串交换
 * 示例 4：
 * <p>
 * 输入：s1 = "abcd", s2 = "dcba"
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s1.length, s2.length <= 100
 * s1.length == s2.length
 * s1 和 s2 仅由小写英文字母组成
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 用两个Pair记录对应位置不相同的字符，第一次不相同用pair1记录，第二次用pair2记录。
 * 如果有第三次不相同，则直接返回false。
 * 第二次不相同时，比较一下pari1.key==pari2.value 和pari2.key==pari1.value，不相同则返回false。
 * 最终，如果pair1==null,说明都相等，返回true，或者pair2!=null，说明恰好有两个不相同的且满足条件。
 *
 * <p>
 * state:done
 */
public class Solution1790 {

    public boolean areAlmostEqual(String s1, String s2) {
        Pair<Character, Character> pair1 = null;
        Pair<Character, Character> pair2 = null;
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            char char1 = chars1[i];
            char char2 = chars2[i];
            if (char1 == char2) {
                continue;
            }
            if (pair1 == null) {
                pair1 = new Pair<>(char1, char2);
                continue;
            }
            if (pair2 != null) {
                return false;
            }
            pair2 = new Pair<>(char1, char2);
            if (pair1.getKey() != char2 || pair1.getValue() != char1) {
                return false;
            }
        }
        return pair1 == null || pair2 != null;
    }
}