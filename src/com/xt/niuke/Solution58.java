package com.xt.niuke;


/**
 * 左旋转字符串
 * 描述
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列 S，请你把其循环左移 K 位后的序列输出（保证 K 小于等于 S 的长度）。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移 3 位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 * 示例1
 * 输入：
 * "abcXYZdef",3
 * 复制
 * 返回值：
 * "XYZdefabc"
 * 10月22
 */
public class Solution58 {
    public String LeftRotateString(String str, int n) {
        StringBuilder builder = new StringBuilder();
        builder.append(str, n, str.length() - 1);
        builder.append(str, 0, n);
        return builder.toString();
    }
}
