package com.xt.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

/**
 * 532. 数组中的 k-diff 数对
 * 每日一题：2022.06.17
 * 完成日期：2022.06.17
 * 链接：https://leetcode.cn/problems/k-diff-pairs-in-an-array/
 * 描述：
 * 给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * <p>
 * k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
 * <p>
 * 0 <= i, j < nums.length
 * i != j
 * nums[i] - nums[j] == k
 * 注意，|val| 表示 val 的绝对值。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3, 1, 4, 1, 5], k = 2
 * 输出：2
 * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
 * 示例 2：
 * <p>
 * 输入：nums = [1, 2, 3, 4, 5], k = 1
 * 输出：4
 * 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5) 。
 * 示例 3：
 * <p>
 * 输入：nums = [1, 3, 1, 5, 4], k = 0
 * 输出：1
 * 解释：数组中只有一个 0-diff 数对，(1, 1) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-diff-pairs-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 使用动态区间的概念，首先对数组进行排序。
 * 然后规划一个区间[left,right]
 * 如果right-left>k，那么left向右移动找到不等于当前值的数。
 * 如果right-left<k，那么right向右移动找到不等于当前值的数。
 * 如果right-left=k，那么left向右移动，记录num++。
 * 如果key==0，则记录重复的数的个数即可。
 * state:done
 */
public class Solution532 {

    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);

        //如果k==0，则计算重复的即可
        int num = 0;
        if (k == 0) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
            for (int key : map.keySet()) {
                if (map.get(key) > 1) {
                    num++;
                }
            }
            return num;
        }

        int left = 0;
        int right = 1;

        while (left >= 0 && right >= 0) {
            int leftValue = nums[left];
            int rightValue = nums[right];
            int diff = rightValue - leftValue;
            if (diff < k) {
                right = findNext(nums, right);
            } else if (diff > k) {
                left = findNext(nums, left);
            } else {
                num++;
                left = findNext(nums, left);
            }
        }
        return num;
    }

    private int findNext(int[] nums, int index) {
        int value = nums[index];
        int nextValue = nums[index];
        while (value == nextValue) {
            if (++index == nums.length) {
                return -1;
            }
            nextValue = nums[index];
        }
        return index;
    }
}