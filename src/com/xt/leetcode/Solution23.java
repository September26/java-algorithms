package com.xt.leetcode;

import com.xt.model.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 解题思路：
 * 所有的链表取第一个节点构造成一个有序的集合，二叉树的方式去插入，最小的节点一定是在集合额第0位。
 * 每次加入到新链表的时候，都取集合中第0位，然后从集合中移除第0位，并且把第0位的next节点加入到集合当中。
 * state:done
 */
public class Solution23 {


    public ListNode mergeKLists(ListNode[] lists) {
        ListNode headNode = null;
        ListNode currentNode = null;
        List<ListNode> list = new ArrayList<>();
        for (ListNode node : lists) {
            middel2Insert(list, node);
        }

        while (list.size() > 0) {
            ListNode node = list.remove(0);
            if (node.next != null) {
                middel2Insert(list, node.next);
            }
            if (currentNode == null) {
                headNode = node;
                currentNode = headNode;
            } else {
                currentNode.next = node;
                currentNode = node;
            }
        }


        return headNode;
    }


    /**
     * 二分查找插入
     *
     * @param node
     */
    public List<ListNode> middel2Insert(List<ListNode> list, ListNode node) {
        if(node==null){
            return list;
        }
        if (list.size() == 0) {
            list.add(node);
            return list;
        }
        int start = 0;
        int end = list.size() - 1;
        while (start <= end) {
            ListNode startNode = list.get(start);
            ListNode endNode = list.get(end);
            if (node.val <= startNode.val) {
                list.add(start, node);
                return list;
            }
            start++;
            if (node.val >= endNode.val) {
                list.add(end + 1, node);
                return list;
            }
            end--;
        }
        list.add(start, node);
        return list;
    }


}