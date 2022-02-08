package com.xt.leetcode;

import com.xt.model.ListNode;

import java.util.Random;

/**
 * 382. 链表随机节点
 * 描述
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 * <p>
 * 实现 Solution 类：
 * <p>
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 *  
 * <p>
 * 示例
 * 1->2->3：
 * <p>
 * 输入
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * 输出
 * [null, 1, 3, 2, 2, 3]
 * <p>
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // 返回 1
 * solution.getRandom(); // 返回 3
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 3
 * // getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。
 *  
 * <p>
 * 提示：
 * <p>
 * 链表中的节点数在范围 [1, 10^4] 内
 * -10^4 <= Node.val <= 10^4
 * 至多调用 getRandom 方法 10^4 次
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果链表非常大且长度未知，该怎么处理？
 * 你能否在不使用额外空间的情况下解决此问题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-random-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 1。
 * 我的解题思路还是要计算一下长度，然后根据长度生成一个随机数。获取数字的时候，重新遍历单链表，当等于随机数时返回。
 *
 * 2。
 * 看了一下官方题解，了解了怎么可以不生成长度去算随机数。
 * 那就是便利单链表的时候，每次都取计算一个随机数，
 * 举个例子，1，2，3。
 * 那么第一次循环，长度为1，那么注定返回1。
 * 第二次循环，长度为2，那么1/2的概率返回2。剩下的概率返回1。
 * 第三次循环，长度为3，1/3的概率返回3，剩下2/3的概率中乘以上面的概率。那就饿时2/3*1/2=1/3，返回1和2。
 * 所以，每次循环的时候，按照当前的长度计算一下是否命中当前的概率。如果命中返回当前的。
 * 不命中，则返回之前的。
 * <p>
 * <p>
 * state:
 */
public class Solution382 {

//    int num = 0;
//    ListNode head;
//
//    /**
//     * Your Solution object will be instantiated and called as such:
//     * Solution obj = new Solution(head);
//     * int param_1 = obj.getRandom();
//     */
//    public Solution382(ListNode head) {
//        this.head = head;
//        while (head != null) {
//            num++;
//            head = head.next;
//        }
//
//    }
//
//    public int getRandom() {
//        int value = new Random.nextInt(num);
//        int index = 0;
//        ListNode node = head;
//        while (index++ < value) {
//            node = node.next;
//        }
//        return node.val;
//    }

    ListNode head;
    Random random;

    public Solution382(ListNode head) {
        this.head = head;
        random = new Random();
    }

    public int getRandom() {
        int i = 1, ans = 0;
        for (ListNode node = head; node != null; node = node.next) {
            if (random.nextInt(i) == 0) { // 1/i 的概率选中（替换为答案）
                ans = node.val;
            }
            ++i;
        }
        return ans;
    }

}
