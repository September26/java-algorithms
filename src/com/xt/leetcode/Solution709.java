package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 709. 转换成小写字母
 * <p>
 * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "Hello"
 * 输出："hello"
 * 示例 2：
 * <p>
 * 输入：s = "here"
 * 输出："here"
 * 示例 3：
 * <p>
 * 输入：s = "LOVELY"
 * 输出："lovely"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/to-lower-case
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution709 {


    public String toLowerCase(String s) {
        int length = 'a' - 'A';
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                char i1 = (char) (chars[i] + length);
                builder.append(i1);
            } else {
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }

}