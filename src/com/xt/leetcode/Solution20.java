package com.xt.leetcode;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * done
 */
public class Solution20 {

    public boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String substring = s.substring(i, i + 1);
            if (substring.equals("(") || substring.equals("[") || substring.equals("{")) {
                stack.add(substring);
                continue;
            }
            if (stack.size() == 0) {
                return false;
            }
            String pop = stack.pop();
            if (pop.equals("(") && substring.equals(")")) {
            } else if (pop.equals("[") && substring.equals("]")) {
            } else if (pop.equals("{") && substring.equals("}")) {
            } else {
                return false;

            }
        }
        return stack.size() == 0;
    }
}