package com.xt.leetcode;

import java.util.Vector;

/**
 * 917.仅仅反转字母
 * 日期：2022.2.23
 * 链接：https://leetcode-cn.com/problems/reverse-only-letters/
 * 描述:
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * <p>
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 * <p>
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 * <p>
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *  
 * <p>
 * 提示
 * <p>
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-only-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 双指针，前后同时开始遍历，如果都是字符串类型，则交换。
 * <p>
 * <p>
 * state:done
 */
public class Solution917 {

    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();

        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char leftChar = chars[left];
            char rightChar = chars[right];
            if (!isIn(leftChar)) {
                left++;
                continue;
            }
            if (!isIn(rightChar)) {
                right--;
                continue;
            }
            //交换
            chars[left] = rightChar;
            chars[right] = leftChar;
            left++;
            right--;
        }
        return new String(chars);
    }

    private boolean isIn(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

}