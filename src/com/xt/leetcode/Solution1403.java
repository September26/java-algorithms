package com.xt.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * 1403. 非递增顺序的最小子序列
 * 每日一题：2022.08.04
 * 完成日期：2022.08.04
 * 链接：https://leetcode.cn/problems/minimum-subsequence-in-non-increasing-order/
 * 描述：
 * 给你一个数组 nums，请你从中抽取一个子序列，满足该子序列的元素之和 严格 大于未包含在该子序列中的各元素之和。
 * <p>
 * 如果存在多个解决方案，只需返回 长度最小 的子序列。如果仍然有多个解决方案，则返回 元素之和最大 的子序列。
 * <p>
 * 与子数组不同的地方在于，「数组的子序列」不强调元素在原数组中的连续性，也就是说，它可以通过从数组中分离一些（也可能不分离）元素得到。
 * <p>
 * 注意，题目数据保证满足所有约束条件的解决方案是 唯一 的。同时，返回的答案应当按 非递增顺序 排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,3,10,9,8]
 * 输出：[10,9]
 * 解释：子序列 [10,9] 和 [10,8] 是最小的、满足元素之和大于其他各元素之和的子序列。但是 [10,9] 的元素之和最大。 
 * 示例 2：
 * <p>
 * 输入：nums = [4,4,7,6,7]
 * 输出：[7,7,6]
 * 解释：子序列 [7,7] 的和为 14 ，不严格大于剩下的其他元素之和（14 = 4 + 4 + 6）。因此，[7,6,7] 是满足题意的最小子序列。注意，元素按非递增顺序返回。
 * 示例 3：
 * <p>
 * 输入：nums = [6]
 * 输出：[6]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 500
 * 1 <= nums[i] <= 100
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-subsequence-in-non-increasing-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 排序，然后求所有数的和。
 * 然后从尾开始遍历，并记录最大的序列的值。
 * 当最大的序列的值大于sum时，跳出循环。
 * <p>
 * <p>
 * state:done
 */
public class Solution1403 {

    public List<Integer> minSubsequence(int[] nums) {
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            sum += num;
        }
        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<>();
        int maxSum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            maxSum += nums[i];
            sum -= nums[i];
            list.add(nums[i]);
            if (maxSum > sum) {
                break;
            }
        }
        return list;
    }
}