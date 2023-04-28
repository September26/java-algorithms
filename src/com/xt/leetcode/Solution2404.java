package com.xt.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 2404. 出现最频繁的偶数元素
 * 每日一题：2023.04.28
 * 完成日期：2023.04.28
 * 链接：https://leetcode.cn/problems/most-frequent-even-element/
 * 描述：
 * 给你一个整数数组 nums ，返回出现最频繁的偶数元素。
 * <p>
 * 如果存在多个满足条件的元素，只需要返回 最小 的一个。如果不存在这样的元素，返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,2,2,4,4,1]
 * 输出：2
 * 解释：
 * 数组中的偶数元素为 0、2 和 4 ，在这些元素中，2 和 4 出现次数最多。
 * 返回最小的那个，即返回 2 。
 * 示例 2：
 * <p>
 * 输入：nums = [4,4,4,9,2,4]
 * 输出：4
 * 解释：4 是出现最频繁的偶数元素。
 * 示例 3：
 * <p>
 * 输入：nums = [29,47,21,41,13,37,25,7]
 * 输出：-1
 * 解释：不存在偶数元素。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2000
 * 0 <= nums[i] <= 105
 * 解题思路：
 * 把出现数字和次数统计到map中，然后遍历map的key，
 * 如果key不为偶数，则跳过；
 * 如果key<maxTimes，则跳过；
 * 如果key<maxTimes，则更新maxTimes和maxKey；
 * 如果key<maxKey，则更新maxKey；
 * <p>
 * state:done
 */
public class Solution2404 {

    public int mostFrequentEven(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).boxed().forEach(integer -> map.put(integer, map.getOrDefault(integer, 0) + 1));
        int maxTimes = 0;
        int maxKey = -1;
        for (Integer key : map.keySet()) {
            if (key % 2 != 0) {
                continue;
            }
            Integer time = map.get(key);
            if (time < maxTimes) {
                continue;
            }
            if (time > maxTimes) {
                maxTimes = time;
                maxKey = key;
                continue;
            }
            if (key < maxKey) {
                maxKey = key;
            }
        }
        return maxKey;
    }
}