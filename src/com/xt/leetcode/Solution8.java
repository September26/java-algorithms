package com.xt.leetcode;

import java.util.*;

/**
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：s = "4193 with words"
 * 输出：4193
 * 解释：
 * 第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
 * ^
 * 第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * ^
 * 第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
 * ^
 * 解析得到整数 4193 。
 * 由于 "4193" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 4193 。
 * done
 */
public class Solution8 {


    public int myAtoi(String s) {
        return processString(s);
    }

    // 初始化最初状态为开始状态
    public static String currentState = "start";
    // 记录所解析数值的正负性
    public static int flag = 1;
    // 记录当前解析出的字符串型的数值
    public static StringBuilder builder = new StringBuilder();
    // 填充规则，待会儿依据该规则进行状态判断
    public static Map<String, String[]> states = new HashMap<>();

    static {

        states.put("start", new String[]{"start", "signed", "in-number", "end"});
        states.put("signed", new String[]{"end", "end", "in-number", "end"});
        states.put("in-number", new String[]{"end", "end", "in-number", "end"});
        states.put("end", new String[]{"end", "end", "end", "end"});

        // 默认赋值为0
        builder.append(0);

    }


    public static int processString(String s) {

        for (char c : s.toCharArray()) {

            processChar(c);
            // 若当前状态已被置为end，则直接将数值进行返回
            if ("end".equals(currentState)) {

                int num;
                try {

                    num = Integer.parseInt(builder.toString());

                } catch (NumberFormatException e) {

                    System.err.println("数值超限！");
                    num = Integer.MAX_VALUE;

                }

                return num * flag;

            } else {

                // 若仍旧处于数值合法状态，则依据是否处于标记状态进行数据拼接以及一些数据正负性的相关操作
                if ("signed".equals(currentState)) {

                    flag = ('+' == c) ? 1 : -1;

                } else if ("in-number".equals(currentState)) {

                    builder.append(c);

                }

            }

        }

        // 解决数值超出整形范围的问题
        int num;
        try {

            num = Integer.parseInt(builder.toString());

        } catch (NumberFormatException e) {

            System.err.println("数值超限！");
            num = Integer.MAX_VALUE;

        }

        return num * flag;


    }


    public static void processChar(char c) {

        // 取当前字符，依次按照规则进行状态匹配
        if (' ' == c) {

            currentState = states.get(currentState)[0];

        } else if (('+' == c) || ('-' == c)) {

            currentState = states.get(currentState)[1];

        } else if (Character.isDigit(c)) {

            currentState = states.get(currentState)[2];

        } else {

            currentState = states.get(currentState)[3];

        }

    }
    
    public int myAtoi2(String s) {
        int max = Integer.MAX_VALUE / 10;
        boolean isNegative = false;
        boolean isStart = false;//判断是否开始读取数字
        char[] chars = s.toCharArray();
        int result = 0;

        for (int aChar : chars) {
            //未开始
            if (!isStart) {
                //空格:32
                if (aChar == 32) {
                    continue;
                }
                isStart = true;
                //+:43 -:45
                if (aChar == 45) {
                    isNegative = true;
                    continue;
                }
                if (aChar == 43) {
                    continue;
                }
                if (aChar >= 48 && aChar <= 57) {
                    result = aChar - 48;
                    continue;
                }
                break;
            }
            aChar = aChar - 48;//-48为对应的阿拉伯数字
            if (aChar >= 0 && aChar <= 9) {
                if (result > max) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                if (result == max) {
//                    这里是防止int溢出的，result=max时，表明前面已经到了int溢出的边缘，就看最后一位了，MAX_VALUE的最大值位数为7，则大于7都溢出。负数时同理
                    if (isNegative) {
                        if (aChar > 8) {
                            return Integer.MIN_VALUE;
                        }
                    } else {
                        if (aChar > 7) {
                            return Integer.MAX_VALUE;
                        }
                    }
                }
                result = result * 10 + aChar;
            } else {
                break;
            }
        }

        result = isNegative ? result * -1 : result;
        return result;
    }

}