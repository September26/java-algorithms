package com.xt.leetcode;

/**
 * 1781. 所有子字符串美丽值之和
 * 每日一题：2022.12.12
 * 完成日期：2022.12.12
 * 链接：https://leetcode.cn/problems/sum-of-beauty-of-all-substrings/
 * 描述：
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 * <p>
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aabcb"
 * 输出：5
 * 解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。
 * 示例 2：
 * <p>
 * 输入：s = "aabcbaa"
 * 输出：17
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 *
 * <p>
 * 解题思路：
 * 遍历所有可能性，每次都寻找最大最小值进行计算。
 *
 * <p>
 * state:done
 */
public class Solution1781 {

    public int beautySum(String s) {
        char[] chars = s.toCharArray();
        int[] times = new int[26];
        int[] copyTimes = new int[26];
        for (char c : chars) {
            times[c - 'a']++;
        }
        char maxChar;
        char minChar;
        int sum = 0;
        for (int left = 0; left < chars.length; left++) {
            maxChar = searchMaxOrMin(times, true);
            minChar = searchMaxOrMin(times, false);
            System.arraycopy(times, 0, copyTimes, 0, times.length);
            for (int right = chars.length - 1; right >= left; right--) {
                char currentChar = chars[right];
                if (copyTimes[minChar - 'a'] == 0 || copyTimes[maxChar - 'a'] == 0) {
                    continue;
                }
                int currentSum = copyTimes[maxChar - 'a'] - copyTimes[minChar - 'a'];
                sum += currentSum;
                copyTimes[currentChar - 'a']--;
                maxChar = searchMaxOrMin(copyTimes, true);
                minChar = searchMaxOrMin(copyTimes, false);
            }
            times[chars[left] - 'a']--;
        }
        return sum;
    }

    private char searchMaxOrMin(int[] times, boolean searchMax) {
        int index = 0;
        int compare = searchMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < times.length; i++) {
            if (times[i] == 0) {
                continue;
            }
            if (searchMax) {
                if (times[i] > compare) {
                    index = i;
                    compare = times[i];
                }
            } else {
                if (times[i] < compare) {
                    index = i;
                    compare = times[i];
                }
            }
        }
        return (char) (index + 'a');
    }
}