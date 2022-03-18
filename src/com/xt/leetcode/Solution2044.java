package com.xt.leetcode;

/**
 * 2044. 统计按位或能得到最大值的子集数目
 * 每日一题：2022.03.15
 * 完成日期：2022.03.15
 * 链接：https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/
 * 描述：
 * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
 * <p>
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
 * <p>
 * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,1]
 * 输出：2
 * 解释：子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
 * - [3]
 * - [3,1]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2]
 * 输出：7
 * 解释：[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 2^3 - 1 = 7 个子集。
 * 示例 3：
 * <p>
 * 输入：nums = [3,2,1,5]
 * 输出：6
 * 解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
 * - [3,5]
 * - [3,1,5]
 * - [3,2,5]
 * - [3,2,1,5]
 * - [2,5]
 * - [2,1,5]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 16
 * 1 <= nums[i] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 分两步，第一步求出最大的mask（子集按位或可能的最大值是 7）。第二步则求出符合的数量
 * 第一步求最大的mask，我们可以使用或运算，每个数都与mask1进行或运算，则最终的mask1则是子集按位或可能的最大值。
 * 第二步因为每个数只有选择和不选择两种可能。则我们可以使用递归的方式，每次递归里面先使用不选择，然后使用选择。使用选择时，需要修改当前的或结果：currentValue
 * <p>
 * <p>
 * state:done
 */
public class Solution2044 {
    int sum = 0;

    public int countMaxOrSubsets(int[] nums) {
        //最大值mask
        int mask1 = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            mask1 |= value;
        }
        search(nums, 0, 0, mask1);
        return sum;
    }

    private void search(int[] nums, int index, int currentValue, int mask) {
        if (index >= nums.length) {
            if (currentValue == mask) {
                sum++;
            }
            return;
        }
        int num = nums[index];
        //不选
        search(nums, ++index, currentValue, mask);

        currentValue = currentValue | num;
        //选择
        search(nums, index, currentValue, mask);
    }
}