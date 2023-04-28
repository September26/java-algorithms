package com.xt.leetcode;

import com.xt.model.ListNode;

import java.util.Vector;

/**
 * 1669. 合并两个链表
 * 每日一题：2023.01.30
 * 完成日期：2023.01.30
 * 链接：https://leetcode.cn/problems/merge-in-between-linked-lists/
 * 描述：
 * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
 * <p>
 * 请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。
 * <p>
 * 下图中蓝色边和节点展示了操作后的结果：
 * <p>
 * <p>
 * 请你返回结果链表的头指针。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * 输出：[0,1,2,1000000,1000001,1000002,5]
 * 解释：我们删除 list1 中下标为 3 和 4 的两个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
 * 示例 2：
 * <p>
 * <p>
 * 输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
 * 输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
 * 解释：上图中蓝色的边和节点为答案链表。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= list1.length <= 104
 * 1 <= a <= b < list1.length - 1
 * 1 <= list2.length <= 104
 * <p>
 * 解题思路：
 * 记录三个节点，分别为头节点，list1中a位置的前一个节点start，list1中b位置的后一个节点end。
 * start的下一个节点就是list2的头节点，完成拼接，
 * list2的尾节点之后拼接end节点，
 * 这样就完成了拼接。
 * state:done
 */
public class Solution1669 {

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode header = list1;
        ListNode start = null;
        ListNode end = null;
        int index = 0;
        while (list1.next != null) {
            if ((a - 1) == index) {
                start = list1;
            }
            if (b == index) {
                end = list1.next;
            }
            list1 = list1.next;
            index++;
        }
        //拼接list2的头节点
        start.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        //list2的尾节点拼接list1的后半部分
        list2.next = end;
        return header;
    }
}