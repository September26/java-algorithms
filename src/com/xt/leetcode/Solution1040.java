package com.xt.leetcode;

import java.util.Arrays;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * 1040.比赛中的配对次数
 * 每日一题：2023.01.29
 * 完成日期：
 * 链接：
 * 描述：
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:
 */
public class Solution1040 {

    public int[] numMovesStonesII(int[] stones) {
        int length = stones.length;
        Set<Integer> collect = Arrays.stream(stones).boxed().collect(Collectors.toSet());
        int[] minNums = new int[stones.length];
        Arrays.sort(stones);
        int right = 0;
        int left = 0;
        int num = 0;
        while (right < stones.length) {
            while (stones[right] >= stones[left] + length) {
                minNums[left] = getMinValue(stones, collect, left, num);
                left++;
                num--;
            }
            right++;
            num++;
            minNums[left] = getMinValue(stones, collect, left, num);
        }
        int min = minNums[0];
        int max = minNums[0];
        for (int i = 0; i < minNums.length - 1; i++) {
            if (minNums[i] > max) {
                max = minNums[i];
            } else if (minNums[i] < min) {
                min = minNums[i];
            }
        }
        return new int[]{min, max};
    }

    private int getMinValue(int[] stones, Set<Integer> collect, int left, int num) {
        int diff = stones.length - num;
        if (collect.contains(stones[left] + stones.length - 1)) {
            return diff;
        }
        if (diff >= 2) {
            return diff;
        }
        return stones.length - num + 1;
    }


}