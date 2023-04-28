package com.xt.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 1742. 盒子中小球的最大数量
 * 每日一题：2022.11.23
 * 完成日期：2022.11.23
 * 链接：https://leetcode.cn/problems/maximum-number-of-balls-in-a-box/
 * 描述：
 * 你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束（包括 lowLimit 和 highLimit ，即 n == highLimit - lowLimit + 1）。另有无限数量的盒子，编号从 1 到 infinity 。
 * <p>
 * 你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。
 * <p>
 * 给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：lowLimit = 1, highLimit = 10
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
 * 小球数量：2 1 1 1 1 1 1 1 1 0  0  ...
 * 编号 1 的盒子放有最多小球，小球数量为 2 。
 * 示例 2：
 * <p>
 * 输入：lowLimit = 5, highLimit = 15
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
 * 小球数量：1 1 1 1 2 2 1 1 1 0  0  ...
 * 编号 5 和 6 的盒子放有最多小球，每个盒子中的小球数量都是 2 。
 * 示例 3：
 * <p>
 * 输入：lowLimit = 19, highLimit = 28
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 12 ...
 * 小球数量：0 1 1 1 1 1 1 1 1 2  0  0  ...
 * 编号 10 的盒子放有最多小球，小球数量为 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= lowLimit <= highLimit <= 105
 * <p>
 * 解题思路：
 * 这题最简单的方式，一定是依次求每个数的sum，然后加入到map中计数。
 * 但是我们其实还有一种更高效的方案，比如120的sum=3，那么120到129的sum就是3，4，5...，不挨个计算的。
 * <p>
 * <p>
 * state:
 */
public class Solution1742 {

    public int countBalls(int lowLimit, int highLimit) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int sum = getSum(i);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        Integer integer = map.values().stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }).get();
        return integer;
    }

    private int getSum(int lowLimitLocal) {
        int[] ints = new int[6];
        int sum = 0;
        int index = 0;
        while (lowLimitLocal > 0) {
            ints[index] = lowLimitLocal % 10;
            sum += ints[index];
            lowLimitLocal = lowLimitLocal / 10;
            index++;
        }
        return sum;
    }
}