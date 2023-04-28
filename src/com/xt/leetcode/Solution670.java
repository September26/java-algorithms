package com.xt.leetcode;

import java.util.Vector;

/**
 * 670. 最大交换
 * 每日一题：2022.09.13
 * 完成日期：2022.09.13
 * 链接：https://leetcode.cn/problems/maximum-swap/
 * 描述：
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * <p>
 * 示例 1 :
 * <p>
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 * <p>
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 * <p>
 * 给定数字的范围是 [0, 108]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-swap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先把num转换为数组，这个数组代表第N位之后最大的数。
 * 然后从左向右遍历，如果当前值小于数组中位置所对应的数，则替换并退出循环。
 * <p>
 * <p>
 * state:done
 */
public class Solution670 {

    public int maximumSwap(int num) {

        String string = Integer.toString(num);

        char[] chars = string.toCharArray();
        int currentIndex = chars.length - 1;
        int[] ints = new int[chars.length];
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] > chars[currentIndex]) {
                currentIndex = i;
            }
            ints[i] = currentIndex;
        }

        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            int index = ints[i];
            if (aChar >= chars[index]) {
                continue;
            }
            char local = chars[index];
            chars[index] = chars[i];
            chars[i] = local;
            break;
        }
        return Integer.parseInt(new String(chars));
    }
}