package com.xt.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 698. 划分为k个相等的子集
 * 每日一题：2022.09.20
 * 完成日期：2022.09.20
 * 链接：https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/
 * 描述：
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 示例 2:
 * <p>
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-to-k-equal-sum-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 因为长度最多16，所以使用的还是一个穷举的策略。
 * 首先进行一个基本检查，看是否满足sum % k == 0以及max <= average这两个条件。
 * 然后对数组排序，生成一个容量位K的数组ints，每隔位置的最大值限定为average，超过则不能放入，否则和其原来位置上的数累加。
 * 从大到小开始遍历数组，每一个数value，都尝试放入ints数组中任意一个不为0的位置。这样等于穷举所有可能性，只要有一种满足，则就可以返回true。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution698 {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        TreeMap<Integer, Integer> timesMap = new TreeMap<>();
        int sum = 0;
        int max = 0;
        for (int i : nums) {
            sum += i;
            max = Math.max(i, max);
            Integer time = timesMap.getOrDefault(i, 0);
            timesMap.put(i, time + 1);
        }
        if (sum % k != 0) {
            return false;
        }
        int average = sum / k;
        if (max > average) {
            return false;
        }
        Arrays.sort(nums);
        int[] ints = new int[k];
        return searchAdd(nums, nums.length - 1, ints, average);
    }

    private boolean searchAdd(int[] nums, int index, int[] ints, int average) {
        if (index == -1) {
            for (int anInt : ints) {
                if (anInt != average) {
                    return false;
                }
            }
            return true;
        }
        int value = nums[index];
        for (int n = 0; n < ints.length; n++) {
            if (ints[n] + value > average) {
                continue;
            }
            ints[n] += value;
            boolean flag = searchAdd(nums, index - 1, ints, average);
            if (flag) {
                return true;
            }
            if (ints[n] == value) {
                ints[n] -= value;
                break;
            }
            ints[n] -= value;
        }
        return false;
    }
}