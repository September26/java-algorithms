package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1688.比赛中的配对次数
 * 每日一题：2022.11.03
 * 完成日期：
 * 链接：https://leetcode.cn/problems/maximum-repeating-substring/description/
 * 描述：
 * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
 * <p>
 * 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：sequence = "ababc", word = "ab"
 * 输出：2
 * 解释："abab" 是 "ababc" 的子字符串。
 * 示例 2：
 * <p>
 * 输入：sequence = "ababc", word = "ba"
 * 输出：1
 * 解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
 * 示例 3：
 * <p>
 * 输入：sequence = "ababc", word = "ac"
 * 输出：0
 * 解释："ac" 不是 "ababc" 的子字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= sequence.length <= 100
 * 1 <= word.length <= 100
 * sequence 和 word 都只包含小写英文字母。
 * 解题思路：
 * 遍历sequence，找出符合word的index的所有位置，存储到数组indexArr中。
 * 然后遍历indexArr数组，如果indexArr[i]==1,则word.length()长度继续寻找，直到找不到，然后返回当前寻找的次数。
 * 值得注意的是，找完了之后要把indexArr[i]=0，避免后续重复查找
 *
 * <p>
 * state:done
 */
public class Solution1668 {

    public int maxRepeating(String sequence, String word) {
        int[] indexArr = new int[sequence.length()];
        for (int i = 0; i < sequence.length(); ) {
            int index = sequence.indexOf(word, i);
            if (index < 0) {
                break;
            }
            i = index;
            indexArr[index] = 1;
            i++;
        }
        int maxTime = 0;
        for (int i = 0; i < indexArr.length; i++) {
            if (indexArr[i] == 0) {
                continue;
            }
            maxTime = Math.max(maxTime, getMaxLength(i, indexArr, word.length()));
        }
        return maxTime;

    }

    private int getMaxLength(int i, int[] indexArr, int length) {
        int currentTime = 0;
        while (i < indexArr.length) {
            if (indexArr[i] == 1) {
                indexArr[i] = 0;
                currentTime++;
                i += length;
                continue;
            }
            break;
        }
        return currentTime;
    }
}