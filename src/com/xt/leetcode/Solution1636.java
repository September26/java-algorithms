package com.xt.leetcode;

import java.util.*;

/**
 * 1636. 按照频率将数组升序排序
 * 每日一题：2022.09.19
 * 完成日期：
 * 链接：https://leetcode.cn/problems/sort-array-by-increasing-frequency/
 * 描述：
 * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。 
 * <p>
 * 请你返回排序后的数组。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2,2,2,3]
 * 输出：[3,1,1,2,2,2]
 * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,3,1,3,2]
 * 输出：[1,3,3,2,2]
 * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
 * 示例 3：
 * <p>
 * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
 * 输出：[5,-1,4,4,-6,-6,1,1,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-array-by-increasing-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先统计每隔数字出现的次数，然后生成一个map为local，次数为key，value为出现的次数。
 * 然后对这个local进行排序，生成新的数组返回。
 * <p>
 * <p>
 * state:done
 */
public class Solution1636 {

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> maps = new HashMap<>();
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };

        for (Integer i : nums) {
            Integer num = maps.getOrDefault(i, 0);
            maps.put(i, num + 1);
        }
        Map<Integer, List<Integer>> local = new HashMap<>();
        for (Integer i : maps.keySet()) {
            List<Integer> integers = local.get(maps.get(i));
            if (integers == null) {
                integers = new ArrayList<>();
                local.put(maps.get(i), integers);
            }
            integers.add(i);
            Collections.sort(integers, comparator);
        }
        List<Integer> integers = new ArrayList<>(local.keySet());
        Collections.sort(integers);
        int index = 0;
        int[] result = new int[nums.length];
        for (int times : integers) {
            List<Integer> integers1 = local.get(times);
            for (int value : integers1) {
                for (int i = 0; i < times; i++) {
                    result[index++] = value;
                }
            }
        }
        return result;
    }
}