package com.xt.leetcode;

import java.util.Vector;

/**
 * 258. 各位相加
 * 每日一题：2022.03.03
 * 完成日期：2022.03.03
 * 链接：https://leetcode-cn.com/problems/add-digits/
 * 描述：
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于 2 是一位数，所以返回 2。
 * 示例 1:
 * <p>
 * 输入: num = 0
 * 输出: 0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= num <= 2^31 - 1
 *  
 * <p>
 * 进阶：你可以不使用循环或者递归，在 O(1) 时间复杂度内解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 显示简单思路，就是递归，如果小于10则返回最终的值，计算num的和。
 * 官方O(1)的涉及到较深的数学知识，放弃。
 * <p>
 * <p>
 * state:done
 */
public class Solution258 {

    public int addDigits(int num) {
        if (num < 10) {
            return num;
        }
        int sum = 0;
        while (num >= 1) {
            sum += num % 10;
            num = num / 10;
        }
        return addDigits(sum);
    }
}