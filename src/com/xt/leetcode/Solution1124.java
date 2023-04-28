package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 1124. 表现良好的最长时间段
 * 每日一题：2023.02.14
 * 完成日期：2023.02.14
 * 链接：https://leetcode.cn/problems/longest-well-performing-interval/solutions/
 * 描述：
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 * <p>
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 * <p>
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 * <p>
 * 请你返回「表现良好时间段」的最大长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：hours = [9,9,6,0,6,6,9]
 * 输出：3
 * 解释：最长的表现良好时间段是 [9,9,6]。
 * 示例 2：
 * <p>
 * 输入：hours = [6,6,6]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= hours.length <= 104
 * 0 <= hours[i] <= 16
 * 解题思路：
 * 每个数>8则标记为1，<=8则标记-1，然后求这个值的前缀和pres，
 * 如果i>j,并且pres[i]-pres[j]>1，则说明[j,i]这段区间是表现良好的区间。
 * 则我们遍历数组，得到每一个pres[i]后，
 * 如果pres[i]>0，则说明0到i范围内都是表现良好的区间。
 * 如果pres[i]<=0，则寻找j，其中pres[i]-pres[j]=1，如果存在这样的j，则[j,i]是表现良好的区间.
 * <p>
 * <p>
 * state:done
 */
public class Solution1124 {

    public int longestWPI(int[] hours) {
        int[] pres = new int[hours.length];
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < hours.length; i++) {
            int value = hours[i] > 8 ? 1 : -1;
            if (i == 0) {
                pres[i] = value;
            } else {
                pres[i] = pres[i - 1] + value;
            }
            if (pres[i] > 0) {
                maxLength = Math.max(maxLength, i + 1);
                continue;
            }
            map.putIfAbsent(pres[i], i);
            Integer index = map.get(pres[i] - 1);
            if (index != null) {
                maxLength = Math.max(maxLength, i - index);
            }

        }
        return maxLength;
    }
}