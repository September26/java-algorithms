package com.xt.leetcode;

import javafx.util.Pair;

import java.util.Vector;

/**
 * 640. 求解方程
 * 每日一题：2022.08.10
 * 完成日期：2022.08.10
 * 链接：https://leetcode.cn/problems/solve-the-equation/
 * 描述：
 * 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
 * <p>
 * 如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
 * <p>
 * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: equation = "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * 示例 2:
 * <p>
 * 输入: equation = "x=x"
 * 输出: "Infinite solutions"
 * 示例 3:
 * <p>
 * 输入: equation = "2x=x"
 * 输出: "x=0"
 *  
 * <p>
 * 提示:
 * <p>
 * 3 <= equation.length <= 1000
 * equation 只有一个 '='.
 * equation 方程由整数组成，其绝对值在 [0, 100] 范围内，不含前导零和变量 'x' 。 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/solve-the-equation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 分解字符串，用四个变量来记录，左边x的数量(xLeftNum)，左边常数的值(valueLeftNum)，右边x的数量(xRightNum)，右边常数的值(valueRightNum)，
 * 如果xLeftNum==xRightNum时，valueLeftNum=valueRightNum就是无限解，否则就是没有解。
 * 如果xLeftNum!=xRightNum时，x=(valueLeftNum - valueRightNum) / (xRightNum - xLeftNum)
 * <p>
 * <p>
 * state:done
 */
public class Solution640 {

    public String solveEquation(String equation) {
        String[] split = equation.split("=");
        Pair<Integer, Integer> pairLeft = getXAndValueNum(split[0]);
        Pair<Integer, Integer> pairRight = getXAndValueNum(split[1]);

        int xLeftNum = pairLeft.getKey();
        int valueLeftNum = pairLeft.getValue();
        int xRightNum = pairRight.getKey();
        int valueRightNum = pairRight.getValue();
        if (xLeftNum != xRightNum) {
            return "x=" + (valueLeftNum - valueRightNum) / (xRightNum - xLeftNum);
        }
        if (valueLeftNum == valueRightNum) {
            return "Infinite solutions";
        }
        return "No solution";
    }

    private Pair<Integer, Integer> getXAndValueNum(String str) {
        int xNum = 0;
        int valueNum = 0;
        boolean isPositive = true;
        StringBuilder builder = new StringBuilder();
        int index = 0;
        char[] chars = str.toCharArray();
        while (index++ < str.length()) {
            char aChar = 0;
            aChar = chars[index - 1];
            if (aChar != '-' && aChar != '+') {
                builder.append(aChar);
                if (index < str.length()) {
                    continue;
                }
            }

            String currentValue = builder.toString();
            builder.setLength(0);
            int num;
            if (!currentValue.endsWith("x")) {
                if (currentValue.length() == 0) {
                    num = 0;
                } else {
                    num = Integer.parseInt(currentValue);
                }
                if (isPositive) {
                    valueNum += num;
                } else {
                    valueNum -= num;
                }
                isPositive = aChar == '+';
                continue;
            }
            String substring = currentValue.substring(0, currentValue.length() - 1);
            if (substring.length() == 0) {
                num = 1;
            } else {
                num = Integer.parseInt(substring);
            }
            if (isPositive) {
                xNum += num;
            } else {
                xNum -= num;
            }
            isPositive = aChar == '+';
        }
        return new Pair<>(xNum, valueNum);
    }

}