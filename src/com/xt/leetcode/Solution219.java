package com.xt.leetcode;

import java.util.*;

/**
 * 219. 存在重复元素 II
 * 描述
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 使用map进行缓存，key为数字，value是对应的位置。
 * 遍历值，如果不存在则加入到map中，如果存在，则取出对应的位置和当前的位置进行判断，看是否小于k。
 * <p>
 * <p>
 * state:done
 */
public class Solution219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.getOrDefault(nums[i], null);
            if (index == null) {
                map.put(nums[i], i);
                continue;
            }
            if ((i - index) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}