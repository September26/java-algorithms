package com.xt.leetcode;

import java.util.Vector;

/**
 * 719. 找出第 K 小的数对距离
 * 每日一题：2022.06.15
 * 完成日期：2022.06.15
 * 链接：https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
 * 描述：
 * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
 *
 * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,1], k = 1
 * 输出：0
 * 解释：数对和对应的距离如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 距离第 1 小的数对是 (1,1) ，距离为 0 。
 * 示例 2：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [1,6,1], k = 3
 * 输出：5
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 2 <= n <= 10^4
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= n * (n - 1) / 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-k-th-smallest-pair-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解题思路：
 *
 *
 * state:
 */
public class Solution719 {

    public int smallestDistancePair(int[] nums, int k) {

        return 0;
    }
}