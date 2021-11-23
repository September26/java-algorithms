package com.xt;


import com.xt.leetcode.Solution2;
import com.xt.model.ListNode;


/**
 * 描述
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * 示例1
 * 输入：
 * {1,2,3,4,5,#,6,#,#,7}
 * 复制
 * 返回值：
 * 4
 */
public class Solution {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums = new int[]{2, 7, 11, 15};

        //9999999
        //9999

        //89990001
        ListNode node11 = new ListNode(9);
        ListNode node12 = new ListNode(9);
        ListNode node13 = new ListNode(9);
        ListNode node14 = new ListNode(9);
        ListNode node15 = new ListNode(9);
        ListNode node16 = new ListNode(9);
        ListNode node17 = new ListNode(9);
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;
        node15.next = node16;
        node16.next = node17;

        ListNode node21 = new ListNode(9);
        ListNode node22 = new ListNode(9);
        ListNode node23 = new ListNode(9);
        ListNode node24 = new ListNode(9);
        node21.next = node22;
        node22.next = node23;
        node23.next = node24;

        Object obejct = solution.addTwoNumbers(node11, node21);
        print(obejct);
    }


    /**
     * 输出结果
     *
     * @param obejct
     */
    private static void print(Object obejct) {
        if (obejct instanceof ListNode) {
            //依次打印链表
            ListNode node = (ListNode) obejct;
            StringBuilder builder = new StringBuilder();
            while (node != null) {
                builder.append(node.val);
                builder.append(",");
                node = node.next;
            }
            System.out.print(builder.toString());
            return;
        }
        if (obejct instanceof int[]) {
            StringBuilder builder = new StringBuilder();
            int[] integers = (int[]) obejct;
            for (int i = 0; i < integers.length; i++) {
                builder.append(integers[i]);
                builder.append(",");
            }
            System.out.print(builder.toString());
            return;
        }
        System.out.print(obejct);
    }

}
