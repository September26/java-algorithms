package com.xt.leetcode;

import java.util.Vector;

/**
 * 1688.比赛中的配对次数
 * 每日一题：2022.04.28
 * 完成日期：2022.04.28
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity/
 * 描述：
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 *
 * 返回满足此条件的 任一数组 作为答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 遍历两遍，第一遍找出偶数，第二遍找出奇数
 * <p>
 * state:done
 */
public class Solution905 {

    public int[] sortArrayByParity(int[] nums) {
        int[] result = new int[nums.length];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num % 2 == 0) {
                result[index++] = num;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num % 2 != 0) {
                result[index++] = num;
            }
        }
        return result;
    }


}