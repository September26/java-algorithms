package com.xt.leetcode;

import java.util.Arrays;
import java.util.Vector;

/**
 * 462. 最少移动次数使数组元素相等 II
 * 每日一题：2022.05.19
 * 完成日期：2022.05.19
 * 链接：https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/
 * 描述：
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 * <p>
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：
 * 只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * 示例 2：
 * <p>
 * 输入：nums = [1,10,2,9]
 * 输出：16
 *  
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 该题中，中位数为基准值一定是最少移动次数。
 * 所以先对nums进行排序，求其他数和中位数的差即可。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution462 {

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int middle = nums[nums.length / 2];
        int count = 0;
        for (int i : nums) {
            count += Math.abs(middle - i);
        }
        return count;

    }
}