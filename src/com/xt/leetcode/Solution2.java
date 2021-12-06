package com.xt.leetcode;

import com.xt.model.ListNode;

/**
 * 2.两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * state：done
 */
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