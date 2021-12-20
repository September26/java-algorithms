package com.xt.self;


import com.xt.Solution;
import com.xt.model.ListNode;

public class LianBiao {

    public static void main(String[] args) {
//
        LianBiao solution = new LianBiao();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;


        ListNode header = node1;
//
//        while (node1.next != null) {
//            ListNode n3 = node1.next.next;//记录3节点
//            ListNode next = node1.next;//把n1.next设置为头
//            next.next = header;
//            header = next;
//            node1.next = n3;
//        }
//
//        System.out.println(header);

        //反转链表
//        solution.fanzhuan(header);

        solution.fanzhuan2(header);
        System.out.println(header);
    }


    //非递归方式
    public ListNode fanzhuan2(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode header = node;
        ListNode nextNode = node.next;
        header.next = null;
        while (nextNode != null) {
            //4步
            ListNode local = nextNode.next;
            nextNode.next = header;
            header = nextNode;
            nextNode = local;

            Solution.print(header);
        }
        return header;

    }

    //递归方式
    public ListNode fanzhuan(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode topNode = fanzhuan(node.next);//5
        node = exchange(node, node.next);
        node.next.next = null;
        return topNode;//5
    }


    //交换链表节点
    public ListNode exchange(ListNode node1, ListNode node2) {
        ListNode next = node2.next;
        node1.next = next;
        node2.next = node1;
        return node2;
    }


}
