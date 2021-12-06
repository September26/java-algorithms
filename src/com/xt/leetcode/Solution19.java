package com.xt.leetcode;

import com.xt.model.ListNode;

import java.util.Vector;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * done
 */
public class Solution19 {

    ListNode mHead;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        mHead = head;
        if (getNode(null, head, n) == 1) {
            return null;
        }
        return mHead;
    }

    public int getNode(ListNode lastNode, ListNode node, int n) {
        int level;
        if (node.next == null) {
            level = 1;
        } else {
            level = getNode(node, node.next, n) + 1;
        }
        if (level == n) {
            if (lastNode != null) {
                lastNode.next = node.next;
            } else {
                mHead = node.next;
            }
        }
        return level;
    }
}