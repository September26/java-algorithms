package com.xt.leetcode;

import java.util.Arrays;
import java.util.Vector;

/**
 * 875. 爱吃香蕉的珂珂
 * 每日一题：2022.06.07
 * 完成日期：2022.06.07
 * 链接：https://leetcode.cn/problems/koko-eating-bananas/
 * 描述：
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * <p>
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 * <p>
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * <p>
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * 示例 3：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/koko-eating-bananas
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题最简单的方式，我们可以从1开始尝试，一直尝试到数组中的最大值。但是这样时间复杂度就太高了，会导致超时。
 * 所以解决这样问题的方式就是二分查找，设置left和right，如果sum未超时，则right向左移动，反之则left向右移动。最终找到临界点就是我们想要的结果。
 *
 *
 * <p>
 * <p>
 * state:
 */
public class Solution875 {

    public int minEatingSpeed(int[] piles, int h) {
        int right = 0;
        for (int i : piles) {
            right = Math.max(i, right);
        }
        if (piles.length == h) {
            return right;
        }
        int result = 1;
        int left = 1;
        while (true) {
            int middle = (left + right) / 2;
            if (left > right) {
                break;
            }
            long sum = 0;
            for (int i : piles) {
                sum += i / middle;
                if (i % middle != 0) {
                    sum++;
                }
            }
            if (sum <= h) {
                result = middle;
                right = middle + (right - middle) / 2 - 1;
            } else {
                left = middle + 1;
            }
        }
        return result;
    }
}