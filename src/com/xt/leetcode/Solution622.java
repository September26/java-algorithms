package com.xt.leetcode;

import java.util.Vector;

/**
 * 1688.比赛中的配对次数
 * 每日一题：2022.08.02
 * 完成日期：2022.08.02
 * 链接：https://leetcode.cn/problems/design-circular-queue/
 * 描述：
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 *
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *
 * 你的实现应该支持如下操作：
 *
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 *  
 *
 * 示例：
 *
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 *  
 *
 * 提示：
 *
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-circular-queue
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 栈和队列的方式一定用链表来表示是最合适的。
 * 设置头和尾节点，以及记录当前长度和最长长度的标记。
 * 加入节点时，先判断长度，如果头节点为空加入头节点，尾节点为空加入尾节点，否则生成新的节点替换尾节点。
 * 删除节点时，直接移除头节点即可。如果头节点为空时，同时要把尾节点也置空
 * <p>
 * <p>
 * state:done
 */
public class Solution622 {

    public static class MyCircularQueue {
        Node header;
        Node footer;
        int currentLength = 0;
        int maxLength;

        public MyCircularQueue(int k) {
            maxLength = k;
        }

        public boolean enQueue(int value) {
            if (currentLength == maxLength) {
                return false;
            }
            Node node = new Node();
            node.val = value;
            currentLength++;
            if (header == null) {
                header = node;
                return true;
            }
            if (footer == null) {
                footer = node;
                header.next = footer;
                return true;
            }
            footer.next = node;
            footer = node;
            return true;
        }

        public boolean deQueue() {
            if (header == null) {
                return false;
            }
            header = header.next;
            if (header == null) {
                footer = null;
            }
            currentLength--;
            return true;
        }

        public int Front() {
            return header == null ? -1 : header.val;
        }

        public int Rear() {
            if (footer == null) {
                return Front();
            }
            return footer.val;
        }

        public boolean isEmpty() {
            return currentLength == 0;
        }

        public boolean isFull() {
            return currentLength == maxLength;
        }

        static class Node {
            Node next;
            int val;
        }
    }
}