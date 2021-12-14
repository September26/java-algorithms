package com.xt.leetcode;

import java.util.*;

/**
 * 18. 四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * O(n3)
 * state:done
 */
public class Solution18 {

    List<List<Integer>> list = new ArrayList<>();
    Map<Integer, Integer> indexMap = new HashMap<>();
    Map<Integer, Integer> timesMap = new HashMap<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            indexMap.putIfAbsent(nums[i], i);
            Integer times = timesMap.getOrDefault(nums[i], 0);
            timesMap.put(nums[i], times + 1);
        }

        addByNum(0, target, 4, nums, 0, 0, 0, 0);
        return list;
    }

    //
    private void addByNum(int start, int target, int haveTimes, int[] nums, int i1, int i2, int i3, int i4) {
//        System.out.println("i1:" + i1 + ",i2:" + i2 + ",i3:" + i3 + ",i4:" + i4 + ",t:" + haveTimes);
        if (haveTimes <= 0) {
            if (target == 0) {
                add(i1, i2, i3, i4);
            }
            return;
        }
        if (haveTimes == 1) {
            Integer targetTimes = timesMap.getOrDefault(target, 0);
            Integer index3 = indexMap.getOrDefault(target, -1);
            if (targetTimes > 0 && index3 >= start) {
                add(i1, i2, i3, target);
            }
            return;
        }

        for (int i = start; i < nums.length; ) {
            int value = nums[i];
            Integer times = timesMap.getOrDefault(value, 0);
            Integer index = indexMap.get(value);
            int nextStart = index + times;
            i += times;
            if (haveTimes == 4) {
                addByNum(nextStart, target - value, haveTimes - 1, nums, value, 0, 0, 0);
                if (times >= 2) {
                    addByNum(nextStart, target - value * 2, haveTimes - 2, nums, value, value, 0, 0);
                }
                if (times >= 3) {
                    addByNum(nextStart, target - value * 3, haveTimes - 3, nums, value, value, value, 0);
                }
                if (times >= haveTimes) {
                    if (haveTimes * value == target) {
                        add(value, value, value, value);
                    }
                }
                continue;
            }
            if (haveTimes == 3) {
                addByNum(nextStart, target - value, haveTimes - 1, nums, i1, value, 0, 0);
                if (times >= 2) {
                    addByNum(nextStart, target - value * 2, haveTimes - 2, nums, i1, value, value, 0);
                }
                if (times >= 3) {
                    addByNum(nextStart, target - value * 3, haveTimes - 3, nums, i1, value, value, value);
                }
                continue;
            }
            if (haveTimes == 2) {
                addByNum(nextStart, target - value, haveTimes - 1, nums, i1, i2, value, 0);
                if (times >= 2) {
                    addByNum(nextStart, target - value * 2, haveTimes - 2, nums, i1, i2, value, value);
                }
            }

        }
    }


    private void add(int i1, int i2, int i3, int i4) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(i1);
        integers.add(i2);
        integers.add(i3);
        integers.add(i4);
        list.add(integers);
    }
}
