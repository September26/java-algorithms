package com.xt.leetcode;

import java.util.Arrays;
import java.util.Vector;

/**
 * 473. 火柴拼正方形
 * 每日一题：2022.06.01
 * 完成日期：2022.06.01
 * 链接：https://leetcode.cn/problems/matchsticks-to-square/
 * 描述：
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 * <p>
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: matchsticks = [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * 示例 2:
 * <p>
 * 输入: matchsticks = [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 108
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/matchsticks-to-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先累加，求出平均数average。
 * 然后我们开始递归循环，先从数组中取出0位置的数，放到a1位置。
 * 再取出1位置的数，则有两种可能，第一是和a1累加，第二是放到a2位置。
 * 再取出2位置的数，则有三种可能，第一是和a1累加，第二是和a2累加，第三是放到a3位置。
 * 依次类推，如果a1,a2,a3,a4有任何一个数大于平均数average，则失败。如果index位置等于-1，则证明没问题。
 * 当然，针对上面可以有两点优化，第一数预先对数组排序，这样有利于更快的进入false或者true的逻辑。
 * 第二是我们可以把a1,a2,a3,a4用数组表示，使用完之后把值改回去即可。
 * <p>
 * <p>
 * state:
 */
public class Solution473 {

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int i : matchsticks) {
            sum += i;
        }
        if (sum % 4 != 0) {
            return false;
        }
        int average = sum / 4;
        Arrays.parallelSort(matchsticks);
        return recursion(matchsticks, matchsticks.length - 1, average, 0, 0, 0, 0);
    }

    private boolean recursion(int[] matchsticks, int index, int average, int a1, int a2, int a3, int a4) {
        if (a1 > average || a2 > average || a3 > average || a4 > average) {
            return false;
        }
        if (index == -1) {
            return true;
        }
        int value = matchsticks[index];
        index--;
        if (a1 == 0) {
            return recursion(matchsticks, index, average, value, a2, a3, a4);
        }
        if (a2 == 0) {
            return recursion(matchsticks, index, average, a1 + value, a2, a3, a4) || recursion(matchsticks, index, average, a1, value, a3, a4);
        }
        if (a3 == 0) {
            return recursion(matchsticks, index, average, a1 + value, a2, a3, a4) || recursion(matchsticks, index, average, a1, a2 + value, a3, a4) || recursion(matchsticks, index, average, a1, a2, a3 + value, a4);
        }
        if (a4 == 0) {
            return recursion(matchsticks, index, average, a1 + value, a2, a3, a4) || recursion(matchsticks, index, average, a1, a2 + value, a3, a4) || recursion(matchsticks, index, average, a1, a2, a3 + value, a4) || recursion(matchsticks, index, average, a1, a2, a3, value);
        }
        return recursion(matchsticks, index, average, a1 + value, a2, a3, a4) || recursion(matchsticks, index, average, a1, a2 + value, a3, a4) || recursion(matchsticks, index, average, a1, a2, a3 + value, a4) || recursion(matchsticks, index, average, a1, a2, a3, a4 + value);
    }

}