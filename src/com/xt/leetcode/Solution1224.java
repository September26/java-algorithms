package com.xt.leetcode;


import java.util.*;

/**
 * 1224. 最大相等频率
 * 每日一题：2022.08.18
 * 完成日期：2022.08.18
 * 链接：https://leetcode.cn/problems/maximum-equal-frequency/
 * 描述：
 * 给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
 * <p>
 * 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
 * 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,2,1,1,5,3,3,5]
 * 输出：7
 * 解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
 * 示例 2：
 * <p>
 * 输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * 输出：13
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-equal-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题我们先说不考虑时间复杂度的解法，然后在这个解法之上做优化。
 * 首先构建一个map，key为数字的值，value为对应值出现的数量，
 * 然后设置一个两个变量，oneNum代表map的value中奇数的数量，result代表该前缀的长度，既返回值。
 * 然后遍历nums，找到对应的value，在其原来的num数量上+1。这时候遍历map的value，如果只有一个和其它不同，并且只比其它的多1，则i就是当前最大的前缀。
 * 但是这个时间复杂度是O(n2)，因为长度是10^5，所以时间超标了，所以我们就要想办法优化到O(n)级别。
 * 接下来我们开始优化，我们可以发现这样一个问题，我们每次都是遍历map计算的数据，其实只需要最大和最小值，那么这个map计算出来的数据是否可以缓存呢？
 *
 * <p>
 * state:done
 */
public class Solution1224 {

    public int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int res = 0, maxFreq = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count.getOrDefault(nums[i], 0) > 0) {
                freq.put(count.get(nums[i]), freq.get(count.get(nums[i])) - 1);
            }
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            maxFreq = Math.max(maxFreq, count.get(nums[i]));
            freq.put(count.get(nums[i]), freq.getOrDefault(count.get(nums[i]), 0) + 1);
            boolean ok = maxFreq == 1 ||
                    freq.get(maxFreq) * maxFreq + freq.get(maxFreq - 1) * (maxFreq - 1) == i + 1 && freq.get(maxFreq) == 1 ||
                    freq.get(maxFreq) * maxFreq + 1 == i + 1 && freq.get(1) == 1;
            if (ok) {
                res = Math.max(res, i + 1);
            }
        }
        return res;
    }

}