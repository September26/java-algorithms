package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * done
 */
public class Solution12 {


    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();
        Map<Integer, String[]> map = new HashMap<>();
        map.put(0, new String[]{"I", "V", "X"});
        map.put(1, new String[]{"X", "L", "C"});
        map.put(2, new String[]{"C", "D", "M"});
        map.put(3, new String[]{"M", "", ""});
        int i = 0;
        while (num > 0) {
            int n = num % 10;
            String s = intToRoman(map.get(i), n);
            builder.insert(0, s);
            i++;
            num = num / 10;
        }
        return builder.toString();
    }


    private String intToRoman(String[] strs, int n) {
        StringBuilder builder = new StringBuilder();
        if (n <= 3) {
            for (int i = 0; i < n; i++) {
                builder.append(strs[0]);
            }
        } else if (n == 4) {
            builder.append(strs[0]);
            builder.append(strs[1]);
        } else if (n == 5) {
            builder.append(strs[1]);
        } else if (n == 9) {
            builder.append(strs[0]);
            builder.append(strs[2]);
        } else {
            builder.append(strs[1]);
            for (int i = 0; i < n - 5; i++) {
                builder.append(strs[0]);
            }
        }
        return builder.toString();
    }
}