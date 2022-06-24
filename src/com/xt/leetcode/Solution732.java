package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * 732. 我的日程安排表 III
 * 每日一题：2022.06.06
 * 完成日期：
 * 链接：
 * 描述：
 * 当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。
 * <p>
 * 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
 * <p>
 * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
 * <p>
 * MyCalendarThree() 初始化对象。
 * int book(int start, int end) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * 输出：
 * [null, 1, 1, 2, 3, 3, 3]
 * <p>
 * 解释：
 * MyCalendarThree myCalendarThree = new MyCalendarThree();
 * myCalendarThree.book(10, 20); // 返回 1 ，第一个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
 * myCalendarThree.book(50, 60); // 返回 1 ，第二个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
 * myCalendarThree.book(10, 40); // 返回 2 ，第三个日程安排 [10, 40) 与第一个日程安排相交，所以最大 k 次预订是 2 次预订。
 * myCalendarThree.book(5, 15); // 返回 3 ，剩下的日程安排的最大 k 次预订是 3 次预订。
 * myCalendarThree.book(5, 10); // 返回 3
 * myCalendarThree.book(25, 55); // 返回 3
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= start < end <= 109
 * 每个测试用例，调用 book 函数最多不超过 400次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/my-calendar-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 我的思路是这样的，首先把每一次预定，都分个成最小颗粒度。
 * 比如10-50，20-40，就分割成三块，10-20（1次），20-40（2次），40-50（1次）。
 * 就这样不断添加，就不断的按照最小颗粒度分割排序。最后遍历一遍，就知道最大次数是多少了。因为总的预定次数不超过400，每次分割最多增加2个区域，所以总的分割区域可控。
 * 该思路未实现，代码是官方题解代码
 * <p>
 * <p>
 * <p>
 * state:
 */
public class Solution732 {

    public static class MyCalendarThree {

        private TreeMap<Integer, Integer> cnt;

        public MyCalendarThree() {
            cnt = new TreeMap<Integer, Integer>();
        }

        public int book(int start, int end) {
            int ans = 0;
            int maxBook = 0;
            cnt.put(start, cnt.getOrDefault(start, 0) + 1);
            cnt.put(end, cnt.getOrDefault(end, 0) - 1);
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int freq = entry.getValue();
                maxBook += freq;
                ans = Math.max(maxBook, ans);
            }
            return ans;
        }

    }

}