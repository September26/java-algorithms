package com.xt.leetcode;

import com.xt.model.ListNode;


/**
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * done
 */
public class Solution24 {

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode nextHeader = head.next;//新的头节点
        ListNode lastHeader = null;
        ListNode current = head;
        ListNode next = head.next;
        while (true) {
            current.next = next.next;//=>  1.next=3
            next.next = current;// => 2.next=1
            if (lastHeader != null) {
                lastHeader.next = next;
            }
            lastHeader = current;
            if (current.next == null || current.next.next == null) {
                break;
            }
            current = current.next;
            next = current.next;
        }
        return nextHeader;
    }
}