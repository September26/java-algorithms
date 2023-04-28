package com.xt.leetcode;

import java.util.Vector;

/**
 * 41. 设计循环双端队列
 * 每日一题：2022.08.15
 * 完成日期：2022.08.15
 * 链接：https://leetcode.cn/problems/design-circular-deque/
 * 描述：
 * 设计实现双端队列。
 * <p>
 * 实现 MyCircularDeque 类:
 * <p>
 * MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
 * boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
 * boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
 * boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
 * int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
 * int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
 * boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
 * boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * 输出
 * [null, true, true, true, false, 2, true, true, true, 4]
 * <p>
 * 解释
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 *  
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull  调用次数不大于 2000 次
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-circular-deque
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 用集合和链表的方式都可以解决，但是链表的方式效率更高。
 * 构建一个具有头节点，尾节点，以及当前长度和最长长度的对象。
 * 插入式如果头节点为空，则头尾节点都设置为当前节点；
 * 否则插入到头节点之前。尾节点的方式类似
 * 删除头节点时，如果链表长度为1，则直接把头尾节点置为空，
 * 否则，删除头节点。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution641 {

    public static class MyCircularDeque {
        int currentLength = 0;
        int maxLength = 0;
        Node head;
        Node foot;

        public MyCircularDeque(int k) {
            this.maxLength = k;
        }

        public boolean insertFront(int value) {
            if (currentLength >= maxLength) {
                return false;
            }
            Node node = new Node();
            node.val = value;
            if (head == null) {
                head = node;
                foot = node;
            } else {
                node.next = head;
                head.previous = node;
                head = node;
            }
            currentLength++;
            return true;
        }

        public boolean insertLast(int value) {
            if (currentLength >= maxLength) {
                return false;
            }
            Node node = new Node();
            node.val = value;
            if (foot == null) {
                foot = node;
                head = node;
            } else {
                node.previous = foot;
                foot.next = node;
                foot = node;
            }
            currentLength++;
            return true;
        }

        public boolean deleteFront() {
            if (currentLength == 0) {
                return false;
            }
            if (currentLength == 1) {
                head = null;
                foot = null;
            } else {
                Node next = head.next;
                next.previous = null;
                head = next;
            }
            currentLength--;
            return true;

        }

        public boolean deleteLast() {
            if (currentLength == 0) {
                return false;
            }
            if (currentLength == 1) {
                head = null;
                foot = null;
            } else {
                Node previous = foot.previous;
                previous.next = null;
                foot = previous;
            }
            currentLength--;
            return true;
        }

        public int getFront() {
            if (head == null) {
                return -1;
            }
            return head.val;
        }

        public int getRear() {
            if (foot == null) {
                return -1;
            }
            return foot.val;
        }

        public boolean isEmpty() {
            return currentLength == 0;
        }

        public boolean isFull() {
            return currentLength == maxLength;
        }

        public static class Node {
            int val = 0;
            Node previous = null;
            Node next = null;
        }
    }

}