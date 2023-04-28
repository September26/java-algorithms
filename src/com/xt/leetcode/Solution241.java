package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 241. 为运算表达式设计优先级
 * 每日一题：2022.07.01
 * 完成日期：2022.07.01
 * 链接：https://leetcode.cn/problems/different-ways-to-add-parentheses/
 * 描述：
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 * <p>
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 10^4 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2：
 * <p>
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99] 
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/different-ways-to-add-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 我的思路效率感觉还是比较低的。
 * 这道题分解起来其实和符号无关，就是两两互相组合的过程。
 * 所以我的思路是先把值和符号拆分出来，分成两个集合，然后开始递归计算。
 * 每次遍历值的集合，然后选择相邻的两个进行计算，然后组成一个新的集合进入下一轮循环。这样每次遍历少一个值，当集合长度1时，说明遍历结束。
 * <p>
 * <p>
 * state:done
 */
public class Solution241 {
    Map<String, Integer> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {

        List<String> valueList = new ArrayList<>();
        List<String> symbolList = new ArrayList<>();

        char[] chars = expression.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= chars.length; i++) {
            if (i == chars.length) {
                valueList.add(builder.toString());
                break;
            }
            char aChar = chars[i];
            if (aChar == '+' || aChar == '-' || aChar == '*') {
                valueList.add(builder.toString());
                symbolList.add(String.valueOf(aChar));
                builder.setLength(0);
            } else {
                builder.append(aChar);
            }
        }
        List<Integer> sumList = new ArrayList<>();
        for (String s : valueList) {
            sumList.add(Integer.parseInt(s));
        }
        search(sumList, valueList, symbolList);
        return new ArrayList<>(map.values());
    }

    private void search(List<Integer> sumList, List<String> valueList, List<String> symbolList) {
        if (valueList.size() == 1) {
            String s = valueList.get(0);
            //计算结果
            map.put(s, sumList.get(0));
            return;
        }

        for (int i = 0; i < valueList.size() - 1; i++) {
            ArrayList<String> newList = new ArrayList<>(valueList);
            ArrayList<Integer> newNumList = new ArrayList<>(sumList);
            String remove = newList.remove(i);
            Integer leftValue = newNumList.remove(i);

            String s = newList.get(i);
            Integer rightValue = newNumList.get(i);

            String symbol = symbolList.get(i);
            newList.set(i, "(" + remove + symbol + s + ")");
            if (symbol.equals("+")) {
                newNumList.set(i, leftValue + rightValue);
            } else if (symbol.equals("-")) {
                newNumList.set(i, leftValue - rightValue);
            } else {
                newNumList.set(i, leftValue * rightValue);
            }
            symbolList.remove(i);
            search(newNumList, newList, symbolList);
            symbolList.add(i, symbol);
        }
    }
}