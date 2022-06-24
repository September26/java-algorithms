package com.xt.leetcode;

import java.util.Vector;

/**
 * 396. 旋转函数
 * 每日一题：2022.04.22
 * 完成日期：2022.04.22
 * 链接：https://leetcode-cn.com/problems/rotate-function/
 * 描述：
 * 给定一个长度为 n 的整数数组 nums 。
 * <p>
 * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
 * <p>
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回 F(0), F(1), ..., F(n-1)中的最大值 。
 * <p>
 * 生成的测试用例让答案符合 32 位 整数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,3,2,6]
 * 输出: 26
 * 解释:
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
 * 示例 2:
 * <p>
 * 输入: nums = [100]
 * 输出: 0
 *  
 * <p>
 * 提示:
 * <p>
 * n == nums.length
 * 1 <= n <= 105
 * -100 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-function
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 如果单纯的实现逻辑很简单，但是数组的长度是10W，如果我们按照要求去计算的话，就需要10W*10W的计算量，O(n2)的时间复杂度，那么一定会超时。
 * 因此核心是把时间复杂度降为O(n)级别，所以我们可以尝试去找规律。
 * 这个规律就是每个不同的函数，其求和的变化是有规律的。比如在示例1中，F(0)和F(1)。
 * nums = [4,3,2,6]
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * 我们可以这样理解，F(1)=F(0)+(4+3+2)-3*6=16
 * 所以这规律很明显了，只要每次都增加所有的数(不包含最后一个)，然后减去最后一个数乘以(nums.length - 1)即可。
 * <p>
 * state:done
 */
public class Solution396 {

    public int maxRotateFunction(int[] nums) {
        int f = 1;
        int sum = 0;
        int currentFK = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            currentFK += i * nums[i];
        }
        int maxFK = currentFK;
        while (f < nums.length) {
            int lastValue = nums[nums.length - f];
            currentFK = currentFK + (sum - lastValue) - lastValue * (nums.length - 1);
            maxFK = Math.max(currentFK, maxFK);
            f++;
        }

        return maxFK;
    }
}