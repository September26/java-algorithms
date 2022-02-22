package com.xt.leetcode;

import java.util.Vector;

/**
 * 2000. 反转单词前缀
 * 日期：2022.2.2
 * 描述
 * 给你一个下标从 0 开始的字符串 word 和一个字符 ch 。找出 ch 第一次出现的下标 i ，反转 word 中从下标 0 开始、直到下标 i 结束（含下标 i ）的那段字符。如果 word 中不存在字符 ch ，则无需进行任何操作。
 * <p>
 * 例如，如果 word = "abcdefd" 且 ch = "d" ，那么你应该 反转 从下标 0 开始、直到下标 3 结束（含下标 3 ）。结果字符串将会是 "dcbaefd" 。
 * 返回 结果字符串 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：word = "abcdefd", ch = "d"
 * 输出："dcbaefd"
 * 解释："d" 第一次出现在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "dcbaefd" 。
 * 示例 2：
 * <p>
 * 输入：word = "xyxzxe", ch = "z"
 * 输出："zxyxxe"
 * 解释："z" 第一次也是唯一一次出现是在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "zxyxxe" 。
 * 示例 3：
 * <p>
 * 输入：word = "abcd", ch = "z"
 * 输出："abcd"
 * 解释："z" 不存在于 word 中。
 * 无需执行反转操作，结果字符串是 "abcd" 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length <= 250
 * word 由小写英文字母组成
 * ch 是一个小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-prefix-of-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 找到ch的位置，然后拼接两段字符串即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution2000 {

    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(String.valueOf(ch));
        if (index < 0) {
            return word;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = index; i >= 0; i--) {
            builder.append(word.charAt(i));
        }
        builder.append(word.substring(index + 1));
        return builder.toString();
    }
}