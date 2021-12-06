package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 6.Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * done
 */
public class Solution6 {

    public String convert(String s, int numRows) {
            if (numRows == 1 || s.length() <= numRows) {
                return s;
            }
            char[] chars = s.toCharArray();
            List<char[]> listR = new ArrayList<>();
            List<char[]> listR1 = new ArrayList<>();
            int length = chars.length;
            int current = 0;
            int size = numRows + numRows - 2;
            while (true) {
                char[] chars1 = new char[numRows];
                char[] chars2 = new char[numRows - 2];
                listR.add(chars1);
                listR1.add(chars2);
                int surplus = length - current;
                if (surplus < size) {
                    if (surplus < numRows) {
                        System.arraycopy(chars, current, chars1, 0, surplus);
                        break;
                    }
                    System.arraycopy(chars, current, chars1, 0, numRows);
                    System.arraycopy(chars, current + numRows, chars2, 0, surplus - numRows);
                    break;
                }
                System.arraycopy(chars, current, chars1, 0, numRows);
                System.arraycopy(chars, current + numRows, chars2, 0, numRows - 2);
                current += size;
            }

            //取值
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                if (i == 0 || i == (numRows - 1)) {
                    for (char[] cs : listR) {
                        if (cs[i] != 0) {
                            builder.append(cs[i]);
                        }
                    }
                } else {
                    for (int k = 0; k < listR.size(); k++) {
                        char[] cs = listR.get(k);
                        char[] cs1 = listR1.get(k);
                        if (cs[i] != 0) {
                            builder.append(cs[i]);
                        }
                        if (cs1[cs1.length - 1 - (i - 1)] != 0) {
                            builder.append(cs1[cs1.length - 1 - (i - 1)]);
                        }
                    }
                }
            }
            return builder.toString();
    }
}