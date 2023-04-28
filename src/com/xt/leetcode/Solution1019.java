package com.xt.leetcode;

import com.xt.model.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.function.Consumer;

/**
 * 1019. 链表中的下一个更大节点
 * 每日一题：2023.04.10
 * 完成日期：2023.04.10
 * 链接：https://leetcode.cn/problems/next-greater-node-in-linked-list/
 * 描述：
 * <p>
 * 解题思路：
 * 构建一个栈结构stack，从后向前遍历。
 * 如果当前值>=栈顶的值，则从栈顶pop出这个值。继续取栈顶的值进行比较。
 * 如果栈为空，则说明后面没有更大的值了，则设置当前位置的值位0。
 * 如果当前值<栈顶的值，则从说明当前位置的更大值就是栈顶的值。
 * <p>
 * <p>
 * state:
 */
public class Solution1019 {

    public int[] nextLargerNodes(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] arr = new int[list.size()];
        for (int i = list.size() - 1; i >= 0; i--) {
            Integer value = list.get(i);
            while (stack.size() > 0) {
                Integer peek = stack.peek();
                if (value >= peek) {
                    stack.pop();
                } else {
                    break;
                }
            }
            if (stack.size() == 0) {
                arr[i] = 0;
            } else {
                arr[i] = stack.peek();
            }
            stack.add(value);
        }
        return arr;
    }
}