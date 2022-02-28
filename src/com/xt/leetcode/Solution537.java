package com.xt.leetcode;

import java.util.Vector;

/**
 * 537. 复数乘法
 * 日期：2022.2.25
 * 链接：https://leetcode-cn.com/problems/complex-number-multiplication/
 * 描述：
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 * <p>
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2：
 * <p>
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 *  
 * <p>
 * 提示：
 * <p>
 * num1 和 num2 都是有效的复数表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/complex-number-multiplication
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * num1分割成n1,n2,
 * num2分割成n3,n4,
 * 然后最终的结果就是(n1*n3-n2*n4)  + (n1 * n4 + n2 * n3 +i)的字符串拼接
 * <p>
 * <p>
 * state:done
 */
public class Solution537 {

    public String complexNumberMultiply(String num1, String num2) {

        String[] split1 = num1.split("\\+");
        String[] split2 = num2.split("\\+");

        int n1 = Integer.parseInt(split1[0]);
        int n2 = Integer.parseInt(split1[1].substring(0, split1[1].length() - 1));

        int n3 = Integer.parseInt(split2[0]);
        int n4 = Integer.parseInt(split2[1].substring(0, split2[1].length() - 1));

//        System.out.print(n1 + "," + n2 + "," + n3 + "," + n4);

        int params1 = n1 * n3;//N
        int params2 = n1 * n4 + n2 * n3;//i
        int params3 = n2 * n4 * -1;//i2

        return params1 + params3 + "+" + params2 + "i";
    }
}