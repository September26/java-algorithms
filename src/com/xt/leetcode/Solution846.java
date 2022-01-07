package com.xt.leetcode;


import java.util.*;

/**
 * 846.一手顺子
 * <p>
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * <p>
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * 输出：true
 * 解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 * <p>
 * 输入：hand = [1,2,3,4,5], groupSize = 4
 * 输出：false
 * 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= hand.length <= 104
 * 0 <= hand[i] <= 109
 * 1 <= groupSize <= hand.length
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hand-of-straights
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 首先判断一下长度，如果长度不是groupSize的倍数肯定就是false了。
 * 然后统计一下每个数字出现的次数，组装成一个map，key为对应的数字值，value则是对象，对象中包含对应的值和出现的次数。
 * 对这个map的value排序一下组装成list，从小往大取值，
 * 比如groupSize=3，取1时，则依次从map中取1，2，3，然后对应的对象times次数减去1的times。
 * 如果对象的times为0则跳过。
 * <p>
 * state:done
 */
public class Solution846 {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        int size = hand.length % groupSize;
        if (size != 0) {
            return false;
        }
        Map<Integer, Time> timesMap = new HashMap<>();
        for (int num : hand) {
            Time time = timesMap.getOrDefault(num, new Time());
            time.value = num;
            time.times++;
            timesMap.put(num, time);
        }
        List<Time> list = new ArrayList<>(timesMap.values());
        list.sort((o1, o2) -> o1.value > o2.value ? 1 : -1);

        for (Time time : list) {
            int value = time.value;
            int times = time.times;
            if (times == 0) {
                continue;
            }
            System.out.println("value" + value);
            for (int i = 0; i < groupSize; i++) {
                Time time1 = timesMap.get(value + i);
                if (time1 == null || time1.times - times < 0) {
                    return false;
                }
                time1.times -= times;
            }
        }
        return true;
    }

    static class Time {
        int value = 0;
        int times = 0;
    }

}