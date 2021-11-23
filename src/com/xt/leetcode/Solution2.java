package com.xt.leetcode;

import com.xt.model.ListNode;

public class Solution2 {

    //第一种，数字累加，但是超长不合适。
    //第二种，
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;
        boolean isJinWei = false;
        while (l1 != null || l2 != null || isJinWei) {
            int i = 0;
            if (l1 != null) {
                i = l1.val;
            }
            if (l2 != null) {
                i += l2.val;
            }
            if (isJinWei) {
                i++;
                isJinWei = false;
            }
            ListNode node;
            if (i >= 10) {
                node = new ListNode(i - 10);
                isJinWei = true;
            } else {
                node = new ListNode(i);
            }
            if (head == null) {
                head = node;
                current = head;
            } else {
                current.next = node;
                current = node;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return head;
    }
}