package com.xt.leetcode;

/**
 * 334.递增的三元子序列
 * 描述
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * <p>
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * 示例 2：
 * <p>
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * 示例 3：
 * <p>
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 *  
 * <p>
 * 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 解题的思路是最优解。
 * 本地的核心是判断是否存在3个递增的数字，那么我们可以这样，设置两个数来记录，first和second。
 * first记录依次遍历中读到的最小值，second记录读到最小值后大于最小值的那个数字。
 * 依次遍历数组，first记录依次遍历中读到的最小时。读到的最小值。如果获取到一个数字大于first，则记录second.
 * 如果再次遍历有数大于second，那么就满足三个递增数组了。
 * 举个例子：{13，14，9，10，11}
 * 第1步：first=13
 * 第2步：first=13,second=14
 * 第3步：first=9,second=14
 * 第4步：first=9,second=10
 * 第5步：11>10,返回true
 * <p>
 * state:done
 */
public class Solution334 {

    public boolean increasingTriplet(int[] nums) {
        Integer first = null;
        Integer second = null;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (first == null) {
                first = value;
                continue;
            }
            if (value == first) {
                continue;
            }
            if (second != null) {
                if (value > second) {
                    return true;
                }
            }
            if (value > first) {
                second = value;
            } else {
                first = value;
            }
        }
        return false;
    }
}