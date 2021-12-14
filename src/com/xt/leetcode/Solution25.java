package com.xt.leetcode;

import com.xt.model.ListNode;

import java.util.Vector;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * done
 */
public class Solution25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        //0:反转后的头节点，1:下一个头节点，2:k数量是否够
        ListNode[] fanzhuan;
        ListNode result = null;
        ListNode lastNode = null;//
        do {
            fanzhuan = fanzhuan(head, k);
            if (lastNode != null) {
                lastNode.next = fanzhuan[0];
            }
            lastNode = head;
            if (result == null) {
                result = fanzhuan[0];
            }
            head = fanzhuan[1];
        } while (head != null);
        return result;
    }

    public ListNode[] fanzhuan(ListNode node, int k) {
        if (k == 1) {
            return new ListNode[]{node, node.next, node};
        }
        if (node == null || node.next == null) {
            return new ListNode[]{node, null, null};
        }
        ListNode[] topNode = fanzhuan(node.next, k - 1);
        if (topNode[2] != null) {
            node = exchange(node, node.next);
            node.next.next = null;
            return topNode;
        }
        return new ListNode[]{node, null, null};//5
    }

    //交换链表节点
    public ListNode exchange(ListNode node1, ListNode node2) {
        node1.next = node2.next;
        node2.next = node1;
        return node2;
    }
}