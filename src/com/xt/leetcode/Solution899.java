package com.xt.leetcode;

import java.util.Vector;

/**
 * 899. 有序队列
 * 每日一题：2022.08.03
 * 完成日期：2022.08.03
 * 链接：https://leetcode.cn/problems/orderly-queue/
 * 描述：
 * 给定一个字符串 s 和一个整数 k 。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 * <p>
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "cba", k = 1
 * 输出："acb"
 * 解释：
 * 在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
 * 在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。
 * 示例 2：
 * <p>
 * 输入：s = "baaca", k = 3
 * 输出："aaabc"
 * 解释：
 * 在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
 * 在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= S.length <= 1000
 * s 只由小写字母组成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/orderly-queue
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 看k的大小，如果k大于1，那么其实就等于插入排序，所以经过若干轮的排序后，一定能形成最小字典字符串。
 * 如果k=1，则找出字典上最小字符串即可
 * <p>
 * state:done
 */
public class Solution899 {

    public String orderlyQueue(String s, int k) {
        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();
        if (k > 1) {
            int[] charNums = new int[26];
            for (int i = 0; i < chars.length; i++) {
                char aChar = chars[i];
                charNums[aChar - 'a']++;
            }
            for (int i = 0; i < charNums.length; i++) {
                int num = charNums[i];
                if (num == 0) {
                    continue;
                }
                for (int n = 0; n < num; n++) {
                    builder.append((char) (i + 'a'));
                }
            }
            return builder.toString();
        }

        for (int i = 0; i < chars.length; i++) {
            if (builder.length() == 0) {
                builder.append(s);
                continue;
            }
            for (int j = 0; j < builder.length(); j++) {
                int newIndex = i + j;
                newIndex = newIndex >= builder.length() ? newIndex - builder.length() : newIndex;
                char newChar = chars[newIndex];
                char lastMinChar = builder.charAt(j);
                if (newChar == lastMinChar) {
                    continue;
                }
                if (newChar > lastMinChar) {
                    break;
                }
                builder.setLength(0);
                builder.append(s.substring(i));
                builder.append(s, 0, i);
            }
        }
        return builder.toString();
    }
}