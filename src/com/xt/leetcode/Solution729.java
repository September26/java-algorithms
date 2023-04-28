package com.xt.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 729. 我的日程安排表 I
 * 每日一题：2022.07.05
 * 完成日期：
 * 链接：https://leetcode.cn/problems/my-calendar-i/
 * 描述：
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * <p>
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * <p>
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end 。
 * <p>
 * 实现 MyCalendar 类：
 * <p>
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 false 并且不要将该日程安排添加到日历中。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * 输出：
 * [null, true, false, true]
 * <p>
 * 解释：
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了。
 * myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20 ，且不包含时间 20 。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= start < end <= 10^9
 * 每个测试用例，调用 book 方法的次数最多不超过 1000 次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/my-calendar-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 用两个List装载开始和结束，list1存储开始的日期，list2存储结束的日期。
 * 每次一个新的日期start的时候，都查询在list1和start相等或者更小的数字s1，然后找其对应的结束日期e1。
 * 这里一共有四种情况
 * start>=e1：则把start和end插入到List中；
 * start<e1：不符合条件，返回false
 * <p>
 * state:done
 */
public class Solution729 {

    public static class MyCalendar {
        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            if (startList.size() == 0) {
                startList.add(start);
                endList.add(end);
                return true;
            }
            int i = middel2Search(startList, start);
            if (i == -1) {
                if (end > startList.get(0)) {
                    return false;
                }
                startList.add(0, start);
                endList.add(0, end);
                return true;
            }
            Integer e1 = endList.get(i);
            Integer s2 = Integer.MAX_VALUE;
            if (i < startList.size() - 1) {
                s2 = startList.get(i + 1);
            }
            if (start >= e1 && end <= s2) {
                startList.add(i + 1, start);
                endList.add(i + 1, end);
                return true;
            }
            return false;
        }

        /**
         * 二分查找,返回等于小于其的
         *
         * @param node
         */
        public int middel2Search(List<Integer> list, int node) {
            if (list.size() == 0) {
                return 0;
            }
            int start = 0;
            int end = list.size() - 1;
            while (start <= end) {
                int startNode = list.get(start);
                int endNode = list.get(end);
                if (node < startNode) {
                    return start - 1;
                }
                start++;
                if (node >= endNode) {
                    return end;
                }
                end--;
            }
            return start - 1;
        }
    }
}