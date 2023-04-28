package com.xt.leetcode;

import java.util.Vector;

/**
 * 2335. 装满杯子需要的最短总时长
 * 每日一题：2023.02.15
 * 完成日期：2023.02.15
 * 链接：https://leetcode.cn/problems/minimum-amount-of-time-to-fill-cups/
 * 描述：
 * 现有一台饮水机，可以制备冷水、温水和热水。每秒钟，可以装满 2 杯 不同 类型的水或者 1 杯任意类型的水。
 * <p>
 * 给你一个下标从 0 开始、长度为 3 的整数数组 amount ，其中 amount[0]、amount[1] 和 amount[2] 分别表示需要装满冷水、温水和热水的杯子数量。返回装满所有杯子所需的 最少 秒数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：amount = [1,4,2]
 * 输出：4
 * 解释：下面给出一种方案：
 * 第 1 秒：装满一杯冷水和一杯温水。
 * 第 2 秒：装满一杯温水和一杯热水。
 * 第 3 秒：装满一杯温水和一杯热水。
 * 第 4 秒：装满一杯温水。
 * 可以证明最少需要 4 秒才能装满所有杯子。
 * 示例 2：
 * <p>
 * 输入：amount = [5,4,4]
 * 输出：7
 * 解释：下面给出一种方案：
 * 第 1 秒：装满一杯冷水和一杯热水。
 * 第 2 秒：装满一杯冷水和一杯温水。
 * 第 3 秒：装满一杯冷水和一杯温水。
 * 第 4 秒：装满一杯温水和一杯热水。
 * 第 5 秒：装满一杯冷水和一杯热水。
 * 第 6 秒：装满一杯冷水和一杯温水。
 * 第 7 秒：装满一杯热水。
 * 示例 3：
 * <p>
 * 输入：amount = [5,0,0]
 * 输出：5
 * 解释：每秒装满一杯冷水。
 * <p>
 * 解题思路：
 * 找到三个数中最大的那个，然后和另外两个的和对比。
 * 如果max*2<=sum，则说明小于另外两个的和，则sum/2，余数进位。
 * 反之，返回max即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution2335 {

    public int fillCups(int[] amount) {
        int max = amount[0];
        int sum = max;
        for (int i = 1; i < amount.length; i++) {
            sum += amount[i];
            max = Math.max(max, amount[i]);
        }
        if (max * 2 <= sum) {
            return (int) Math.ceil((double) sum / 2);
        }
        return max;
    }
}