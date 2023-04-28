package com.xt.leetcode;

import java.util.*;

/**
 * 1798. 你能构造出连续值的最大数目
 * 每日一题：2023.02.10
 * 完成日期：2023.02.10
 * 链接：https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make/description/
 * 描述：
 * 给你一个长度为 n 的整数数组 coins ，它代表你拥有的 n 个硬币。第 i 个硬币的值为 coins[i] 。如果你从这些硬币中选出一部分硬币，它们的和为 x ，那么称，你可以 构造 出 x 。
 * <p>
 * 请返回从 0 开始（包括 0 ），你最多能 构造 出多少个连续整数。
 * <p>
 * 你可能有多个相同值的硬币。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1,3]
 * 输出：2
 * 解释：你可以得到以下这些值：
 * - 0：什么都不取 []
 * - 1：取 [1]
 * 从 0 开始，你可以构造出 2 个连续整数。
 * 示例 2：
 * <p>
 * 输入：coins = [1,1,1,4]
 * 输出：8
 * 解释：你可以得到以下这些值：
 * - 0：什么都不取 []
 * - 1：取 [1]
 * - 2：取 [1,1]
 * - 3：取 [1,1,1]
 * - 4：取 [4]
 * - 5：取 [4,1]
 * - 6：取 [4,1,1]
 * - 7：取 [4,1,1,1]
 * 从 0 开始，你可以构造出 8 个连续整数。
 * 示例 3：
 * <p>
 * 输入：nums = [1,4,10,3,1]
 * 输出：20
 * <p>
 * <p>
 * 提示：
 * <p>
 * coins.length == n
 * 1 <= n <= 4 * 104
 * 1 <= coins[i] <= 4 * 104
 * 解题思路：
 * 假设[0,X]范围内的数都是连续可以表示的，那么假如一个新的数Y，则[0,X]和[Y,X+Y]范围内都可以连续。
 * 如果Y<=X+1，则[0,X+Y]范围内都可以连续。
 * 所以，我们按照从小到大排序，只要看是否存在Y>X+1的数即可。
 *
 * state:done
 */
public class Solution1798 {

    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);
        int sum = 1;
        for (int i : coins) {
            if (i > sum) {
                break;
            }
            sum += i;
        }
        return sum;
    }
}