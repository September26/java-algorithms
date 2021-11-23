package com.xt.niuke;


/**
 * 把字符串转换成整数
 * 描述
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 * 输入描述：
 * 输入一个字符串,包括数字字母符号,可以为空
 * 返回值描述：
 * 如果是合法的数值表达则返回该数字，否则返回0
 * 示例1
 * 输入：
 * "+2147483647"
 * 返回值：
 * 2147483647
 * 示例2
 * 输入：
 * "1a33"
 * 返回值：
 * 0
 * 10月30
 */
public class Solution67 {
    public int StrToInt(String str) {
        boolean isFushu = false;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 48 && c <= 57) {
                builder.append(c);
            } else if (c == 43) {
                //+

            } else if (c == 45) {
                //-
                isFushu = true;
            } else if (c == 47) {
                //除 /

            } else if (c == 42) {
                //*

            } else {
                return 0;
            }
        }
        if (builder.length() == 0) {
            return 0;
        }
        int result = Integer.parseInt(builder.toString());
        if (isFushu) {
            return -result;
        }
        return result;
    }

}
