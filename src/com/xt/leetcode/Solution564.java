package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 564. 寻找最近的回文数
 * 每日一题：2022.03.02
 * 完成日期：2022.03.02
 * 链接：https://leetcode-cn.com/problems/find-the-closest-palindrome/
 * 描述：
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * <p>
 * “最近的”定义为两个整数差的绝对值最小。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = "123"
 * 输出: "121"
 * 示例 2:
 * <p>
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= n.length <= 18
 * n 只由数字组成
 * n 不含前导 0
 * n 代表在 [1, 1018 - 1] 范围内的整数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-closest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:
 */
public class Solution564 {

    public String nearestPalindromic(String n) {
        long origin = Long.parseLong(n);
        List<Long> list = getList(n);

        long min = 0L;
        long currentValue = 0L;
        for (Long l : list) {
            long currentChange = Math.abs(l - origin);
            if (currentChange < min) {
                currentValue = l;
            }
            if (currentChange == min && l < currentValue) {
                currentValue = l;
            }
        }
        return Long.toString(currentValue);
    }

    private List<Long> getList(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<Long>() {{
            add((long) Math.pow(10, len - 1) - 1);
            add((long) Math.pow(10, len) + 1);
        }};
        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            sb.append(suffix.substring(len & 1));
            String candidate = sb.toString();
            candidates.add(Long.parseLong(candidate));
        }
        return candidates;
    }


    public boolean isPalindromic(char[] chars) {
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}