package com.xt.leetcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * 2367. 算术三元组的数目
 * 每日一题：2023.03.31
 * 完成日期：2023.03.31
 * 链接：https://leetcode.cn/problems/number-of-arithmetic-triplets/
 * 描述：
 * 给你一个下标从 0 开始、严格递增 的整数数组 nums 和一个正整数 diff 。如果满足下述全部条件，则三元组 (i, j, k) 就是一个 算术三元组 ：
 * <p>
 * i < j < k ，
 * nums[j] - nums[i] == diff 且
 * nums[k] - nums[j] == diff
 * 返回不同 算术三元组 的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,4,6,7,10], diff = 3
 * 输出：2
 * 解释：
 * (1, 2, 4) 是算术三元组：7 - 4 == 3 且 4 - 1 == 3 。
 * (2, 4, 5) 是算术三元组：10 - 7 == 3 且 7 - 4 == 3 。
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,8,9], diff = 2
 * 输出：2
 * 解释：
 * (0, 2, 4) 是算术三元组：8 - 6 == 2 且 6 - 4 == 2 。
 * (1, 3, 5) 是算术三元组：9 - 7 == 2 且 7 - 5 == 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 200
 * 0 <= nums[i] <= 200
 * 1 <= diff <= 50
 * nums 严格 递增
 * <p>
 * 解题思路：
 * 把nums转换为set，然后遍历nums，判断是否存在num + diff和num + 2 * diff的数即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution2367 {

    public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int abs = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (set.contains(num + diff) && set.contains(num + 2 * diff)) {
                abs++;
            }
        }
        return abs;
    }
}