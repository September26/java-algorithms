package com.xt.leetcode;


import java.util.*;

/**
 * 32. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 0 <= s.length <= 3 * 10^4
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * <p>
 * 输入：s = ""
 * 输出：0
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 本题的难点是()(()()这样的字符串，这样的字符串最大的长度是从第4位开始的。
 * 我们先看一下题目设定要求，字符串长度4W，说明这题至少是O(n*logn)的复杂度才行。我们就去找O(n)复杂度的解法
 * <p>
 * <p>
 * <p>
 * state:done
 */
public class Solution32 {
    public int longestValidParentheses(String s) {
        int max = 0;
        char[] chars = s.toCharArray();
        Map<Integer, Node> nodeMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        while (index < chars.length) {
            char aChar = chars[index];
            if (aChar == '(') {
                stack.add(index);
                index++;
                continue;
            }
            int size = stack.size();//层级
            if (size == 0) {
                index++;
                nodeMap.clear();
                continue;
            }
            Integer start = stack.pop();//取层级的头部
            Node node = nodeMap.get(size);
            if (node == null) {
                nodeMap.keySet().removeIf(next -> next > size);//map改类型
                node = new Node();
                node.startIndex = start;
                node.length = index - start + 1;
                node.level = size;
                nodeMap.put(size, node);
            } else {
                nodeMap.keySet().removeIf(next -> next > size);
                node.length = node.length + (index - start + 1);
            }
            max = Math.max(node.length, max);
            index++;
        }
        return max;
    }

    static class Node {
        int startIndex = 0;
        int length = 0;
        int level = 0;
    }

}