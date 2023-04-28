package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1759. 统计同构子字符串的数目
 * 每日一题：2022.12.26
 * 完成日期：2022.12.26
 * 链接：https://leetcode.cn/problems/count-number-of-homogenous-substrings/
 * 描述：
 * 给你一个字符串 s ，返回 s 中 同构子字符串 的数目。由于答案可能很大，只需返回对 109 + 7 取余 后的结果。
 *
 * 同构字符串 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。
 *
 * 子字符串 是字符串中的一个连续字符序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abbcccaa"
 * 输出：13
 * 解释：同构子字符串如下所列：
 * "a"   出现 3 次。
 * "aa"  出现 1 次。
 * "b"   出现 2 次。
 * "bb"  出现 1 次。
 * "c"   出现 3 次。
 * "cc"  出现 2 次。
 * "ccc" 出现 1 次。
 * 3 + 1 + 2 + 1 + 3 + 2 + 1 = 13
 * 示例 2：
 *
 * 输入：s = "xy"
 * 输出：2
 * 解释：同构子字符串是 "x" 和 "y" 。
 * 示例 3：
 *
 * 输入：s = "zzzzz"
 * 输出：15
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 由小写字符串组成
 * <p>
 * 解题思路：
 * 求和即可。比如abbcccaa，则分别求出a,bb,ccc,aa的和
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1759 {

    Map<Integer, Long> cacheMap = new HashMap<>();
    int max = 0;
    int MOD = 10_0000_0007;

    public int countHomogenous(String s) {
        int index = 1;
        int currentLength = 1;
        char[] chars = s.toCharArray();
        char lastChar = chars[0];
        long sum = 0;
        while (index <= chars.length) {
            if (index == chars.length) {
                long value = getCacheMap(currentLength);
                sum += value;
                break;
            }
            char valueChar = chars[index];
            index++;
            if (lastChar == valueChar) {
                currentLength++;
                continue;
            }
            long value = getCacheMap(currentLength);
            sum += value;
            lastChar = valueChar;
            currentLength = 1;
        }
        return (int) (sum % MOD);
    }

    private long getCacheMap(int current) {
        if (current <= max) {
            return cacheMap.get(current);
        }
        long sum = cacheMap.getOrDefault(max, 0L);
        for (int i = max + 1; i <= current; i++) {
            sum += i;
            cacheMap.put(i, sum);
        }
        max = current;
        return cacheMap.get(current);
    }

}