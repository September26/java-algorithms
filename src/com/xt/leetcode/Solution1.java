package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution1 {


    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    public int[] twoSum(int[] nums, int target) {
        for (int index = 0; index < nums.length; index++) {
            int num = nums[index];
            ArrayList<Integer> integers = map.computeIfAbsent(num, k -> new ArrayList<>());
            integers.add(index);
        }

        for (int index = 0; index < nums.length; index++) {
            int num = nums[index];
            int expect = target - num;
            ArrayList<Integer> integers = map.get(expect);
            if (integers == null) {
                continue;
            }
            Integer index1 = integers.get(0);
            if (integers.size() == 1 && index1 == index) {
                continue;
            }
            int[] result = new int[2];
            result[0] = index;
            result[1] = index == index1 ? integers.get(1) : index1;
            return result;
        }
        return new int[0];
    }
}