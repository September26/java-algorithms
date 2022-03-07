package com.xt.leetcode;

import java.util.Vector;

/**
 * 504. 七进制数
 * 每日一题：2022.03.07
 * 完成日期：2022.03.07
 * 链接：https://leetcode-cn.com/problems/base-7/
 * 描述：
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 * <p>
 * 输入: num = -7
 * 输出: "-10"
 *  
 * <p>
 * 提示：
 * <p>
 * -107 <= num <= 107
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/base-7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:done
 */
public class Solution504 {

    public String convertToBase7(int num) {
        StringBuilder builder = new StringBuilder();
        boolean isFu = false;
        if (num < 0) {
            isFu = true;
            num = num * -1;
        }
        do {
            int i = num % 7;
            builder.insert(0, i);
            num = num / 7;
        } while (num != 0);
        if (isFu) {
            builder.insert(0, "-");
        }
        return builder.toString();
    }
}