package com.xt.leetcode;

import java.util.Vector;

/**
 * 693. 交替位二进制数
 * 每日一题：2022.03.28
 * 完成日期：2022.03.28
 * 链接：https://leetcode-cn.com/problems/binary-number-with-alternating-bits/
 * 描述：
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5
 * 输出：true
 * 解释：5 的二进制表示是：101
 * 示例 2：
 * <p>
 * 输入：n = 7
 * 输出：false
 * 解释：7 的二进制表示是：111.
 * 示例 3：
 * <p>
 * 输入：n = 11
 * 输出：false
 * 解释：11 的二进制表示是：1011.
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-number-with-alternating-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * last记录上一位的值。然后每次n/2，从右到左取每一位的值进行判断
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution693 {

    public boolean hasAlternatingBits(int n) {
        int last = -1;
        while (n > 0) {
            int i = n & 1;
            n = n >> 1;
            if (last == -1) {
                last = i;
                continue;
            }
            if (last == i) {
                return false;
            }
            last = i;
        }
        return true;
    }
}