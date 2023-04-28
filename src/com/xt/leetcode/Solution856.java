package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * 856. 括号的分数
 * 每日一题：2022.10.09
 * 完成日期：2022.10.09
 * 链接：https://leetcode.cn/problems/score-of-parentheses/
 * 描述：
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * <p>
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 * <p>
 * 输入： "(())"
 * 输出： 2
 * 示例 3：
 * <p>
 * 输入： "()()"
 * 输出： 2
 * 示例 4：
 * <p>
 * 输入： "(()(()))"
 * 输出： 6
 *  
 * <p>
 * 提示：
 * <p>
 * S 是平衡括号字符串，且只含有 ( 和 ) 。
 * 2 <= S.length <= 50
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/score-of-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 用栈的结构来解决这个问题是最好的。
 * 每个栈位置上存储其所对应的分数，如果读到(，就开启一个新的栈运算，读到)，关闭一个栈运算并且计算其分数。
 * 设置stack记录栈值，lastS记录前一个字符，currentS表示当前字符。
 * lastS = ( 并且 currentS = (，入栈值位0的数；
 * lastS = ) 并且 currentS = (，不做操作，因为此时既不入栈，也不出栈，仍是当前栈的操作
 * lastS = ( 并且 currentS = )，栈顶值+1
 * lastS = ) 并且 currentS = )，栈顶值出栈，*2之后和当前栈顶值相加
 * 最后一定只保留一层栈结构，其值就是结果
 * <p>
 * state:done
 */
public class Solution856 {

    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();
        char lastC = chars[0];
        stack.add(0);
        for (int i = 1; i < chars.length; i++) {
            char currentC = chars[i];
            if (lastC == '(' && currentC == '(') {
                stack.add(0);
            } else if (lastC == ')' && currentC == '(') {

            } else if (lastC == '(' && currentC == ')') {
                stack.set(stack.size() - 1, stack.peek() + 1);
            } else if (lastC == ')' && currentC == ')') {
                Integer value = stack.pop();
                Integer top = stack.peek();
                stack.set(stack.size() - 1, value * 2 + top);
            }
//            System.out.println(stack.size());
            lastC = currentC;
        }
        return stack.pop();
    }
}