package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 1154. 一年中的第几天
 * 描述
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
 * <p>
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：date = "2019-01-09"
 * 输出：9
 * 示例 2：
 * <p>
 * 输入：date = "2019-02-10"
 * 输出：41
 * 示例 3：
 * <p>
 * 输入：date = "2003-03-01"
 * 输出：60
 * 示例 4：
 * <p>
 * 输入：date = "2004-03-01"
 * 输出：61
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/day-of-the-year
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * <p>
 * 1-10:    4,10
 * 11-20: ok
 */
public class Solution1154 {

    public int dayOfYear(String date) {
        String[] split = date.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);

        //判断是不是闰年
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0 || (year % 100 == 0 && year % 400 == 0));
        //1,3,5,7,8,10,12
        //4,6,9,11
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        map.put(2, sum += 31);
        map.put(3, isLeapYear ? (sum += 29) : (sum += 28));
        map.put(4, sum += 31);
        map.put(5, sum += 30);
        map.put(6, sum += 31);
        map.put(7, sum += 30);
        map.put(8, sum += 31);
        map.put(9, sum += 31);
        map.put(10, sum += 30);
        map.put(11, sum += 31);
        map.put(12, sum + 30);

        return map.get(month) + day;
    }
}