package com.xt.leetcode;

import java.util.Vector;

/**
 * 1475. 商品折扣后的最终价格
 * 每日一题：2022.09.01
 * 完成日期：2022.09.01
 * 链接：https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop/
 * 描述：
 * 给你一个数组 prices ，其中 prices[i] 是商店里第 i 件商品的价格。
 * <p>
 * 商店里正在进行促销活动，如果你要买第 i 件商品，那么你可以得到与 prices[j] 相等的折扣，其中 j 是满足 j > i 且 prices[j] <= prices[i] 的 最小下标 ，如果没有满足条件的 j ，你将没有任何折扣。
 * <p>
 * 请你返回一个数组，数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：prices = [8,4,6,2,3]
 * 输出：[4,2,4,2,3]
 * 解释：
 * 商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
 * 商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
 * 商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
 * 商品 3 和 4 都没有折扣。
 * 示例 2：
 * <p>
 * 输入：prices = [1,2,3,4,5]
 * 输出：[1,2,3,4,5]
 * 解释：在这个例子中，所有商品都没有折扣。
 * 示例 3：
 * <p>
 * 输入：prices = [10,1,1,6]
 * 输出：[9,0,1,6]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 500
 * 1 <= prices[i] <= 10^3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题如果使用双层for循环去做，那会十分的简单。每次子循环中只要找到满足j > i 且 prices[j] <= prices[i] 的 最小下标即可。
 * 我在想能不能找到一个时间复杂度O(n)的解法，当然是有的，如果我们从左向右遍历，那么自然需要双层循环遍历。
 * 但是如果从右向左遍历，并且把每次遍历的数量就会大大减少了。
 * <p>
 * <p>
 * state:
 */
public class Solution1475 {

    public int[] finalPrices(int[] prices) {
        int minValue = Integer.MAX_VALUE;
        int[] result = new int[prices.length];
        for (int i = prices.length - 1; i >= 0; i--) {
            int price = prices[i];
            if (price < minValue) {
                result[i] = price;
                minValue = price;
                continue;
            }
            for (int j = i + 1; j < prices.length; j++) {
                if (price >= prices[j]) {
                    result[i] = price - prices[j];
                    break;
                }
            }
        }
        return result;
    }
}