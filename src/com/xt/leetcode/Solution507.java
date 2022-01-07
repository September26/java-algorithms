package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 507.完美数
 * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * <p>
 * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 28
 * 输出：true
 * 解释：28 = 1 + 2 + 4 + 7 + 14
 * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
 * 示例 2：
 * <p>
 * 输入：num = 6
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：num = 496
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：num = 8128
 * 输出：true
 * 示例 5：
 * <p>
 * 输入：num = 2
 * 输出：false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 解题思路：
 * 使用end来记录num的值，
 * 把end这个数除以i=2，如果余数为0，就修改end为除以后的值并且继续除以2。
 * 否则则尝试除以3判断余数。
 * 这样继续除下去，直到i==end
 * 每次运算记录sum值，最后比较一下sum值和num就好了。
 * <p>
 * <p>
 * state:done
 */
public class Solution507 {

    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 0;
        int end = num;//
        int old = 1;
        for (int i = 2; i < end; ) {
            if (end % i == 0) {
                sum += i * old;
                sum += end / i;
                end = end / i;
                old = i * old;
                continue;
            }
            i++;
        }
        sum += 1;
        return sum == num;
    }
}