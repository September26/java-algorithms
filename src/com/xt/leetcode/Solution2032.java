package com.xt.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 2032. 至少在两个数组中出现的值
 * 每日一题：2022.12.29
 * 完成日期：2022.12.29
 * 链接：https://leetcode.cn/problems/two-out-of-three/
 * 描述：
 * 给你三个整数数组 nums1、nums2 和 nums3 ，请你构造并返回一个 元素各不相同的 数组，且由 至少 在 两个 数组中出现的所有值组成。数组中的元素可以按 任意 顺序排列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
 * 输出：[3,2]
 * 解释：至少在两个数组中出现的所有值为：
 * - 3 ，在全部三个数组中都出现过。
 * - 2 ，在数组 nums1 和 nums2 中出现过。
 * 示例 2：
 * <p>
 * 输入：nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
 * 输出：[2,3,1]
 * 解释：至少在两个数组中出现的所有值为：
 * - 2 ，在数组 nums2 和 nums3 中出现过。
 * - 3 ，在数组 nums1 和 nums2 中出现过。
 * - 1 ，在数组 nums1 和 nums3 中出现过。
 * 示例 3：
 * <p>
 * 输入：nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
 * 输出：[]
 * 解释：不存在至少在两个数组中出现的值。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length, nums3.length <= 100
 * 1 <= nums1[i], nums2[j], nums3[k] <= 100
 * <p>
 * 解题思路：
 * 用一个map来记录出现的次数，key为出现的数字，value为出现的次数，则最终map中value超过2的key就是符合要求的数字。
 * 但是存在一个问题，就是一个数组多存在多个相同数字的情况，所以我们使用一个set来过滤
 *
 * state:done
 */
public class Solution2032 {

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Map<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums1) {
            if (set.contains(i)) {
                continue;
            }
            set.add(i);
            Integer num = map.getOrDefault(i, 0);
            map.put(i, num + 1);
        }
        set.clear();
        for (int i : nums2) {
            if (set.contains(i)) {
                continue;
            }
            set.add(i);
            Integer num = map.getOrDefault(i, 0);
            map.put(i, num + 1);
        }
        set.clear();
        for (int i : nums3) {
            if (set.contains(i)) {
                continue;
            }
            set.add(i);
            Integer num = map.getOrDefault(i, 0);
            map.put(i, num + 1);
        }
        return map.entrySet().stream().filter(entry -> entry.getValue() >= 2).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}