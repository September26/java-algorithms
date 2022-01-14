package com.xt.leetcode;

import java.util.Vector;

/**
 * 306. 累加数
 * <p>
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * <p>
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * <p>
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："112358"
 * 输出：true
 * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 示例 2：
 * <p>
 * 输入："199100199"
 * 输出：true
 * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= num.length <= 35
 * num 仅由数字（0 - 9）组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/additive-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * <p>
 * state:done
 */
public class Solution306 {

    public boolean isAdditiveNumber(String num) {
        for (int i = 1; i <= num.length() / 2; i++) {
            for (int j = i + 1; j <= num.length() / 3 * 2; j++) {
                String s1 = num.substring(0, i);
                String s2 = num.substring(i, j);
                if ((s1.length() > 1 && s1.startsWith("0")) || (s2.length() > 1 && s2.startsWith("0"))) {
                    continue;
                }
                boolean match = isMatch(num, s1, s2, j);
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isMatch(String num, String s1, String s2, int start) {
        String s = getSum(s1, s2);
        int length = s.length();
        if (start + length > num.length()) {
            return false;
        }
        if (!num.substring(start, start + length).equals(s)) {
            return false;
        }
        if (start + length == num.length()) {
            return true;
        }
        return isMatch(num, s2, s, start + length);
    }

    /**
     * 计算两个数字想加
     *
     * @param s1
     * @param s2
     * @return
     */
    public String getSum(String s1, String s2) {
        int num = 0;
        int maxLength = Math.max(s1.length(), s2.length());
        boolean isJinWei = false;
        StringBuilder result = new StringBuilder();
        while (num < maxLength) {
            int i1 = 0;
            int i2 = 0;
            int sum = 0;
            if (s1.length() - num - 1 >= 0) {
                i1 = Integer.parseInt(s1.substring(s1.length() - num - 1, s1.length() - num));
            }
            if (s2.length() - num - 1 >= 0) {
                i2 = Integer.parseInt(s2.substring(s2.length() - num - 1, s2.length() - num));
            }
            sum = i1 + i2;
            if (isJinWei) {
                sum++;
            }
            if (sum >= 10) {
                result.insert(0, sum % 10);
                isJinWei = true;
            } else {
                result.insert(0, sum);
                isJinWei = false;
            }
            num++;
        }
        if (isJinWei) {
            result.insert(0, "1");
        }
        return result.toString();
    }
}