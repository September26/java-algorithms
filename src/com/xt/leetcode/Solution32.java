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
 * 我们先看一下题目设定要求，字符串长度4W，说明这题至少是O(n*logn)的复杂度才行。我们就去找O(n)复杂度的解法。
 * 这题我的思路是这样的，
 * 首先构建一个栈结构，记录是第几层嵌套。
 * 然后构建一个map，key为第几层结构，value为对应的起始点。
 * 这样我们依次的去遍历字符串，如果读到"("则栈中增加一层，记录其位置。读到")"则栈做出栈处理，同时，记录一下其开始位置以及长度。
 * 如果依次读到两个),两个结构的层级是相同的，那么这两个结构的长度就可以累加，不同层级则不能累加。
 * 另外，如果结束了)的层级是第2层，那么大于2层的结构也需要被移除掉。
 * <p>
 * <p>
 * state:done
 */
public class Solution32 {
    public int longestValidParentheses(String s) {
        int max = 0;
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Node> nodeMap = new HashMap<>();
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