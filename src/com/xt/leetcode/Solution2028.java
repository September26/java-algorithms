package com.xt.leetcode;

import java.util.Vector;

/**
 * 2028. 找出缺失的观测数据
 * 每日一题：2022.03.27
 * 完成日期：2022.03.27
 * 链接：https://leetcode-cn.com/problems/find-missing-observations/
 * 描述：
 * 现有一份 n + m 次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。观测数据中缺失了 n 份，你手上只拿到剩余 m 次投掷的数据。幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
 * <p>
 * 给你一个长度为 m 的整数数组 rolls ，其中 rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
 * <p>
 * 返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
 * <p>
 * k 个数字的 平均值 为这些数字求和后再除以 k 。
 * <p>
 * 注意 mean 是一个整数，所以 n + m 次投掷的总和需要被 n + m 整除。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：rolls = [3,2,4,3], mean = 4, n = 2
 * 输出：[6,6]
 * 解释：所有 n + m 次投掷的平均值是 (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4 。
 * 示例 2：
 * <p>
 * 输入：rolls = [1,5,6], mean = 3, n = 4
 * 输出：[2,3,2,2]
 * 解释：所有 n + m 次投掷的平均值是 (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3 。
 * 示例 3：
 * <p>
 * 输入：rolls = [1,2,3,4], mean = 6, n = 4
 * 输出：[]
 * 解释：无论丢失的 4 次数据是什么，平均值都不可能是 6 。
 * 示例 4：
 * <p>
 * 输入：rolls = [1], mean = 3, n = 1
 * 输出：[5]
 * 解释：所有 n + m 次投掷的平均值是 (1 + 5) / 2 = 3 。
 *  
 * <p>
 * 提示：
 * <p>
 * m == rolls.length
 * 1 <= n, m <= 105
 * 1 <= rolls[i], mean <= 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-missing-observations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 先求出总和sum=mean*(rolls.length+n)，
 * 然后减去现有值的总和sum=sum - currentSum;
 * 接下来就是把sum如何分配到n个位置上。
 * 最简单的方式自然是平均分，但是平均分有个问题，比如sum=27,n=6时，最后一个位置就是7超过6的氛围了，这也是本题的难点。
 * 我的解决方案是先判断是否可以分配。um > 6 * n || sum < n都是不可以的。
 * 如果可以分配，则求出最后一个值。比如上面的7，则7-6=1，plus1Num=1。前plus1Num个数都额外+1。
 * <p>
 * <p>
 * state:done
 */
public class Solution2028 {

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int sum = (rolls.length + n) * mean;
        int currentSum = 0;
        for (int i : rolls) {
            currentSum += i;
        }
        sum = sum - currentSum;
        if (sum > 6 * n || sum < n) {
            return new int[0];
        }
        int[] result = new int[n];
        int average = sum / n;
        int last = sum - average * (n - 1);
        int plus1Num = 0;
        if (last > 6) {
            plus1Num = last - 6;
        }
        for (int i = 0; i < result.length; i++) {
            if (i == result.length - 1) {
                result[i] = sum;
                break;
            }
            if (i < plus1Num) {
                result[i] = average + 1;
            } else {
                result[i] = average;
            }
            sum -= result[i];
        }
        return result;
    }
}