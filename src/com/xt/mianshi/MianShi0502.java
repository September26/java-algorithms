package com.xt.mianshi;

/**
 * 面试题 05.02. 二进制数转字符串
 * 每日一题：2023.03.02
 * 完成日期：2023.03.02
 * 链接：https://leetcode.cn/problems/bianry-number-to-string-lcci/
 * 描述：
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 * <p>
 * 示例1:
 * <p>
 * 输入：0.625
 * 输出："0.101"
 * 示例2:
 * <p>
 * 输入：0.1
 * 输出："ERROR"
 * 提示：0.1无法被二进制准确表示
 * <p>
 * <p>
 * 提示：
 * <p>
 * 32位包括输出中的 "0." 这两位。
 * 题目保证输入用例的小数位数最多只有 6 位
 * <p>
 * 解题思路：
 * 用num-base，
 * 如果结果大于0，则后面追加1，并且修改num为结果；
 * 如果等于0，则追加1并退出；
 * 如果小于0，则追加0。
 *
 *
 * <p>
 * <p>
 * state:done
 */
public class MianShi0502 {

    public String printBin(double num) {

        double base = 0.5;
        StringBuilder builder = new StringBuilder("0.");
        while (base > 0.000001) {
            double value = num - base;
            base = base / 2;
            if (value == 0) {
                builder.append("1");
                return builder.toString();
            }
            if (value > 0) {
                num = value;
                builder.append("1");
                continue;
            }
            builder.append("0");
        }
        return "ERROR";
    }
}















