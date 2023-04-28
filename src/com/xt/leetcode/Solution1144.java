package com.xt.leetcode;

import java.util.Arrays;
import java.util.Vector;

/**
 * 1144. 递减元素使数组呈锯齿状
 * 每日一题：2023.02.27
 * 完成日期：2023.02.27
 * 链接：https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag/
 * 描述：
 * 给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
 * <p>
 * 如果符合下列情况之一，则数组 A 就是 锯齿数组：
 * <p>
 * 每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * 或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * 返回将数组 nums 转换为锯齿数组所需的最小操作次数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：我们可以把 2 递减到 0，或把 3 递减到 1。
 * 示例 2：
 * <p>
 * 输入：nums = [9,6,1,6,2]
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 解题思路：
 * 分别尝试两种可能，即A[0]>A[1]的可能，以及A[0]<A[1]的可能，最后返回两者较小值。
 * 接下来我们讲遍历的过程，使用到了最优解的策略。
 * 比如[2,2,2,2]，A[0]>A[1]的情况，读到i=1时，发现A[1]=A[0]，则只能减少A[1]的值，count+1。A[1]减少，只会让A[2]更可能大于A[1]。
 * 同样[2,2,2,2]，A[0]<A[1]的情况，读到i=1时，发现A[1]=A[0]，则只能减少A[0]的值,count+1。A[0]减少，不会影响A[2]和A[1]之间的比较。
 * <p>
 * <p>
 * state:done
 */
public class Solution1144 {

    public int movesToMakeZigzag(int[] nums) {
        return Math.min(countSum(Arrays.copyOf(nums, nums.length), true), countSum(nums, false));
    }

    public int countSum(int[] nums, boolean firstBig) {

        boolean isBig = firstBig;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (isBig) {
                if (nums[i - 1] <= nums[i]) {
                    count += (nums[i] - nums[i - 1] + 1);
                    nums[i] = nums[i - 1] - 1;
                }
                isBig = false;
                continue;
            }
            if (nums[i - 1] >= nums[i]) {
                count += (nums[i - 1] - nums[i] + 1);
                nums[i - 1] = nums[i] - 1;
            }
            isBig = true;
        }
        return count;
    }
}