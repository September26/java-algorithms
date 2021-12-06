package com.xt.leetcode;

import java.util.*;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * done
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> integers = indexMap.computeIfAbsent(nums[i], integer -> new ArrayList<>());
            integers.add(i);
        }

        Integer[] integers = new Integer[indexMap.keySet().size()];
        indexMap.keySet().toArray(integers);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < integers.length; i++) {
            map.put(integers[i], i);
        }

        //三个不同数字
        for (int i = 0; i < integers.length; i++) {
            for (int j = i + 1; j < integers.length; j++) {
                int expect = -integers[i] - integers[j];
                Integer expectIndex = map.get(expect);
                if (expectIndex == null) {
                    continue;
                }
                if (expectIndex <= j) {
                    continue;
                }
                Integer integer1 = integers[i];
                Integer integer2 = integers[j];
                Integer integer3 = expect;
                addList(integer1, integer2, integer3, list);
            }
        }

        //有两个相同数字
        for (int i = 0; i < integers.length; i++) {
            Integer value = integers[i];
            List<Integer> integers1 = indexMap.get(value);
            if (integers1.size() < 2) {
                continue;
            }
            int expect = value * -2;
            if (indexMap.get(expect) != null && indexMap.get(expect).size() > 0 && expect != value) {
                addList(value, value, expect, list);
            }
        }

        //三个数字都相同
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] == 0 && indexMap.get(integers[i]).size() >= 3) {
                addList(0, 0, 0, list);
            }
        }
        return list;
    }

    public void addList(int i1, int i2, int i3, List<List<Integer>> result) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(i1);
        integers.add(i2);
        integers.add(i3);
        result.add(integers);
    }

}