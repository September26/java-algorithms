package com.xt.leetcode;

import java.util.Vector;

/**
 * 2180. 统计各位数字之和为偶数的整数个数
 * 每日一题：2022.01.06
 * 完成日期：2022.01.06
 * 链接：https://leetcode.cn/problems/count-integers-with-even-digit-sum/
 * 描述：
 * 给你一个正整数 num ，请你统计并返回 小于或等于 num 且各位数字之和为 偶数 的正整数的数目。
 * <p>
 * 正整数的 各位数字之和 是其所有位上的对应数字相加的结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 4
 * 输出：2
 * 解释：
 * 只有 2 和 4 满足小于等于 4 且各位数字之和为偶数。
 * 示例 2：
 * <p>
 * 输入：num = 30
 * 输出：14
 * 解释：
 * 只有 14 个整数满足小于等于 30 且各位数字之和为偶数，分别是：
 * 2、4、6、8、11、13、15、17、19、20、22、24、26 和 28 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= num <= 1000
 * <p>
 * 解题思路：
 * 最简单的方式自然是一个数一个数的计算，这样效率太低。我们不讲。
 * 把一个数进行分割，比如251，
 * 先计算出200<n的符合条件的数量，
 * 然后计算出200<=n<250符合条件的数量，
 * 最后计算250<=n<=251的数量
 * <p>
 * <p>
 * state:done
 */
public class Solution2180 {

    public int countEven(int num) {
        if (num < 10) {
            return num / 2;
        }
        int oldNum = num;
        int level = 1000;
        int sum = 0;
        while (level > 0) {
            int num1 = getNum(oldNum, num / level, level);
            sum += num1;
            num -= num / level * level;
            level = level / 10;
        }
        return sum - 1;
    }

    private int getNum(int oldNum, int n, int level) {
        if (level > 1) {
            return n * level / 2;
        }
        int num = 0;
        for (int i = oldNum / 10 * 10; i <= oldNum; i++) {
            if (getSum(i) % 2 == 0) {
                num++;
            }
        }
        return num;
    }

    private int getSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += (i % 10);
            i = i / 10;
        }
        return sum;
    }

}