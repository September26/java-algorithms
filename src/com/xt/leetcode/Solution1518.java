package com.xt.leetcode;

import java.util.Vector;

/**
 * 1518. 换酒问题
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 * <p>
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * <p>
 * 请你计算 最多 能喝到多少瓶酒。
 * <p>
 * 示例 1：
 * 输入：numBottles = 9, numExchange = 3
 * 输出：13
 * 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
 * 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/water-bottles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 递归的思路，每次传入当前酒数量，空瓶数量，以及对换值。返回对应的酒的数量。递归截止条件就是当前酒数量+空瓶小于对换值。
 *
 * state:done
 */
public class Solution1518 {

    /**
     * 这是错误的
     * @param numBottles
     * @param numExchange
     * @return
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        //while循环解决
        int sum = numBottles;
        int emplyBottles = 0;
        while ((numBottles + numBottles) >= numExchange) {
            numBottles = (numBottles + emplyBottles) / numExchange;
            emplyBottles = numBottles % numExchange;
            sum += numBottles;
        }
        return sum;
    }

    public int numWaterBottles(int numBottles, int emptyBottles, int numExchange) {
        if ((numBottles + emptyBottles) < numExchange) {
            return numBottles;
        }
        int nextNumBottles = (numBottles + emptyBottles) / numExchange;
        emptyBottles = (numBottles + emptyBottles) % numExchange;
        int i = numWaterBottles(nextNumBottles, emptyBottles, numExchange);
        return numBottles + i;
    }
}