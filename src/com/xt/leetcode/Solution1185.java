package com.xt.leetcode;

/**
 * 1185. 一周中的第几天
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 * <p>
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 * <p>
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：day = 31, month = 8, year = 2019
 * 输出："Saturday"
 * 示例 2：
 * <p>
 * 输入：day = 18, month = 7, year = 1999
 * 输出："Sunday"
 * 示例 3：
 * <p>
 * 输入：day = 15, month = 8, year = 1993
 * 输出："Sunday"
 *  
 * <p>
 * 提示：
 * <p>
 * 给出的日期一定是在 1971 到 2100 年之间的有效日期。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/day-of-the-week
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 判断是否是闰年，统计从19070年1月1日到指定日期之间的天数，除以7求余数即可。
 * <p>
 * state:done
 */
public class Solution1185 {

    public String dayOfTheWeek(int day, int month, int year) {
        String[] strings = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        //1970年1月1日 = 星期几
        int yearNum = 0;
        int monthNum = 0;
        for (int i = 1970; i < year; i++) {
            if (isLeapYear(i)) {
                yearNum += 366;
            } else {
                yearNum += 365;
            }
        }
        for (int i = 0; i < month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    monthNum += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    monthNum += 30;
                    break;
                case 2:
                    boolean leapYear = isLeapYear(year);
                    monthNum = monthNum + (leapYear ? 29 : 28);
            }
        }
        int sum = yearNum + monthNum + day - 1;
        int i = (sum + 4) % 7;

        return strings[i];
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0 || (year % 100 == 0 && year % 400 == 0));
    }

}