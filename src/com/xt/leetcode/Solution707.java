package com.xt.leetcode;

/**
 * 707. 设计链表
 * 每日一题：2022.09.23
 * 完成日期：2022.09.23
 * 链接：https://leetcode.cn/problems/design-linked-list/
 * 描述：
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *  
 * <p>
 * 示例：
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *  
 * <p>
 * 提示：
 * <p>
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 不考虑效率的情况下，使用单链表解决。
 * 设置两个变量，size记录链表长度，head记录头节点。
 * 获取插入删除时，判断是否是处理的头节点，如果是头节点，则直接处理。否则，找到对应位置的节点处理。
 * 插入尾节点，就等于addAtIndex(size, val);
 * <p>
 * <p>
 * state:
 */
public class Solution707 {

    public static class MyLinkedList {

        int size = 0;
        public Node head = null;

        public MyLinkedList() {

        }

        public int get(int index) {
            if (index >= size) {
                return -1;
            }
            int i = 0;
            Node local = head;
            while (i++ < index) {
                local = local.next;
            }
            return local.val;
        }

        public void addAtHead(int val) {
            Node node = new Node(val);
            node.next = head;
            head = node;
            size++;
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            size++;
            Node node = new Node(val);
            if (index == 0) {
                node.next = head;
                head = node;
                return;
            }
            int i = 0;
            Node local = head;
            while (++i < index) {
                local = local.next;
            }
            Node next = local.next;
            local.next = node;
            if (next == null) {
                return;
            }
            node.next = next;

        }

        public void deleteAtIndex(int index) {
            if (index >= size) {
                return;
            }
            size--;
            if (size == 0) {
                head = null;
                return;
            }
            if (index == 0) {
                head = head.next;
                return;
            }
            int i = 0;
            Node local = head;
            while (++i < index) {
                local = local.next;
            }
            Node next = local.next;
            if (next == null) {
                return;
            }
            local.next = next.next;
        }

        public static class Node {
            private final int val;
            private Node next;

            Node(int val) {
                this.val = val;
            }
        }

    }
}