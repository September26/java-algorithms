package com.xt.niuke;


import com.xt.model.ListNode;

/**
 * 描述
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表 1->2->3->3->4->4->5  处理后为 1->2->5
 * <p>
 * 数据范围：链表长度满足1≤n≤1000，链表中的值满足0≤val≤1000
 * <p>
 * 进阶：空间复杂度O(n)，时间复杂度O(n)
 * <p>
 * 输入：
 * {1,2,3,3,4,4,5}
 * 返回值：
 * {1,2,5}
 * <p>
 * 输入：
 * {1,1,1,8}
 * 返回值：
 * {8}
 * 11/3
 */
public class Solution76 {
    public ListNode deleteDuplication(ListNode pHead) {
        int[] arrays = new int[1001];//可以用map替代
        ListNode listNode = pHead;
        while (listNode != null) {
            int val = listNode.val;
            arrays[val] = arrays[val] + 1;
            listNode = listNode.next;
        }
        ListNode head = new ListNode(0);
        ListNode current = head;
        while (pHead != null) {
            int val = pHead.val;
            int num = arrays[val];
            if (num == 1) {
                current.next = pHead;
                current = pHead;
            }
            pHead = pHead.next;
        }
        return head.next;
    }
}
