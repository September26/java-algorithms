package com.xt.leetcode;

/**
 * 2409. 统计共同度过的日子数
 * 每日一题：2023.04.17
 * 完成日期：2023.04.17
 * 链接：https://leetcode.cn/problems/count-days-spent-together/
 * 描述：
 * Alice 和 Bob 计划分别去罗马开会。
 * <p>
 * 给你四个字符串 arriveAlice ，leaveAlice ，arriveBob 和 leaveBob 。Alice 会在日期 arriveAlice 到 leaveAlice 之间在城市里（日期为闭区间），而 Bob 在日期 arriveBob 到 leaveBob 之间在城市里（日期为闭区间）。每个字符串都包含 5 个字符，格式为 "MM-DD" ，对应着一个日期的月和日。
 * <p>
 * 请你返回 Alice和 Bob 同时在罗马的天数。
 * <p>
 * 你可以假设所有日期都在 同一个 自然年，而且 不是 闰年。每个月份的天数分别为：[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31] 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
 * 输出：3
 * 解释：Alice 从 8 月 15 号到 8 月 18 号在罗马。Bob 从 8 月 16 号到 8 月 19 号在罗马，他们同时在罗马的日期为 8 月 16、17 和 18 号。所以答案为 3 。
 * 示例 2：
 * <p>
 * 输入：arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
 * 输出：0
 * 解释：Alice 和 Bob 没有同时在罗马的日子，所以我们返回 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 所有日期的格式均为 "MM-DD" 。
 * Alice 和 Bob 的到达日期都 早于或等于 他们的离开日期。
 * 题目测试用例所给出的日期均为 非闰年 的有效日期。
 * <p>
 * 解题思路：
 * 把每个月的日期数量转换为累加数组，然后把日期转换为每年的第多少天。
 * 这样，我们只要把两者最早走的日期，减去最晚达到的日期就是我们想要的结果。
 * state:done
 */
public class Solution2409 {

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] arr = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            arr[i] = sum;
        }
        int levelMin = Math.min(convert(leaveAlice, arr), convert(leaveBob, arr));
        int arriveMax = Math.max(convert(arriveAlice, arr), convert(arriveBob, arr));
        int i = levelMin - arriveMax + 1;
        return Math.max(i, 0);
    }

    private int convert(String str, int[] arr) {
        String[] split = str.split("-");
        return arr[Integer.parseInt(split[0]) - 1] + Integer.parseInt(split[1]);
    }

}