package com.xt.leetcode;

import java.util.Vector;

/**
 * 1750. 删除字符串两端相同字符后的最短长度
 * 每日一题：2022.12.28
 * 完成日期：2022.12.28
 * 链接：https://leetcode.cn/problems/minimum-length-of-string-after-deleting-similar-ends/description/
 * 描述：
 * 给你一个只包含字符 'a'，'b' 和 'c' 的字符串 s ，你可以执行下面这个操作（5 个步骤）任意次：
 *
 * 选择字符串 s 一个 非空 的前缀，这个前缀的所有字符都相同。
 * 选择字符串 s 一个 非空 的后缀，这个后缀的所有字符都相同。
 * 前缀和后缀在字符串中任意位置都不能有交集。
 * 前缀和后缀包含的所有字符都要相同。
 * 同时删除前缀和后缀。
 * 请你返回对字符串 s 执行上面操作任意次以后（可能 0 次），能得到的 最短长度 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ca"
 * 输出：2
 * 解释：你没法删除任何一个字符，所以字符串长度仍然保持不变。
 * 示例 2：
 *
 * 输入：s = "cabaabac"
 * 输出：0
 * 解释：最优操作序列为：
 * - 选择前缀 "c" 和后缀 "c" 并删除它们，得到 s = "abaaba" 。
 * - 选择前缀 "a" 和后缀 "a" 并删除它们，得到 s = "baab" 。
 * - 选择前缀 "b" 和后缀 "b" 并删除它们，得到 s = "aa" 。
 * - 选择前缀 "a" 和后缀 "a" 并删除它们，得到 s = "" 。
 * 示例 3：
 *
 * 输入：s = "aabccabba"
 * 输出：3
 * 解释：最优操作序列为：
 * - 选择前缀 "aa" 和后缀 "a" 并删除它们，得到 s = "bccabb" 。
 * - 选择前缀 "b" 和后缀 "bb" 并删除它们，得到 s = "cca" 。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 只包含字符 'a'，'b' 和 'c' 。
 * <p>
 * 解题思路：
 * 使用left和right分别记录左右的位置，
 * 每次统计字符串最两个的字符，如果相同，则统计其出现的数量，然后减去其出现的次数继续下一轮计算
 * <p>
 * <p>
 * state:done
 */
public class Solution1750 {

    public int minimumLength(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right) {
            int num = findNum(chars, chars[left], left, true);
            int num1 = findNum(chars, chars[right], right, false);
            if (chars[left] == chars[right]) {
                left += num;
                right -= num1;
                continue;
            }
            break;
        }
        return Math.max(right - left + 1, 0);
    }

    private int findNum(char[] chars, char c, int index, boolean isFromLeft) {
        int num = 0;
        if (isFromLeft) {
            while (index < chars.length) {
                if (chars[index] == c) {
                    num++;
                    index++;
                    continue;
                }
                break;
            }
        } else {
            while (index >= 0) {
                if (chars[index] == c) {
                    num++;
                    index--;
                    continue;
                }
                break;
            }
        }

        return num;
    }

}