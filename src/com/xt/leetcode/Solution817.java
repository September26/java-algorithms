package com.xt.leetcode;

import com.xt.model.ListNode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 817. 链表组件
 * 每日一题：2022.10.12
 * 完成日期：2022.10.12
 * 链接：https://leetcode.cn/problems/linked-list-components/
 * 描述：
 * 给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表 nums，该列表是上述链表中整型值的一个子集。
 * <p>
 * 返回列表 nums 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 nums 中）构成的集合。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入: head = [0,1,2,3], nums = [0,1,3]
 * 输出: 2
 * 解释: 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入: head = [0,1,2,3,4], nums = [0,3,1,4]
 * 输出: 2
 * 解释: 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点数为n
 * 1 <= n <= 104
 * 0 <= Node.val < n
 * Node.val 中所有值 不同
 * 1 <= nums.length <= n
 * 0 <= nums[i] < n
 * nums 中所有值 不同
 * <p>
 * 解题思路：
 * 用set来记录是否存在对应的数字，用result来记录段数，isIng代表是否正处于连续的子集中。
 * 然后遍历链表，如果对应的数字在set中存在，则isIng = false，下一次读到数字需要result++。
 * 否则isIng==true时，直接跳过，isIng==false时，result++。
 * <p>
 * <p>
 * state:done
 */
public class Solution817 {

    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int result = 0;
        boolean isIng = false;
        while (head != null) {
            int val = head.val;
            head = head.next;
            if (!set.contains(val)) {
                isIng = false;
                continue;
            }
            if (!isIng) {
                isIng = true;
                result++;
            }

        }
        return result;
    }
}