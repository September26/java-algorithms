package com.xt.leetcode;


/**
 * 1748. 唯一元素的和
 * 日期：2022.2.6
 * 描述
 * 给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
 * <p>
 * 请你返回 nums 中唯一元素的 和 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,2]
 * 输出：4
 * 解释：唯一元素为 [1,3] ，和为 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：没有唯一元素，和为 0 。
 * 示例 3 ：
 * <p>
 * 输入：nums = [1,2,3,4,5]
 * 输出：15
 * 解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-unique-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 这题很简单，常规思路是先遍历一遍，找出那些不重复的数字，然后进行累加运算。
 * 我这里尝试用一遍遍历解决，所以我的想法是使用caches数组缓存出现数字出现的数量，
 * 如果出现一次，则sum = sum +value;
 * 如果出现二次，说明重复了，要把之前加上去的减回来，则sum = sum -value;
 * 如果出现了三次以及以上，则不需要处理
 * 这样一遍就可以算出来最终结果
 * <p>
 * <p>
 * state:done
 */
public class Solution1748 {

    public int sumOfUnique(int[] nums) {
        int[] caches = new int[101];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            caches[num]++;
            //出现一次
            if (caches[num] == 1) {
                sum += num;
                continue;
            }
            //出现二次
            if (caches[num] == 2) {
                sum -= num;
                continue;
            }
            //出现三次及以上
        }
        return sum;
    }
}