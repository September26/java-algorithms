package com.xt.leetcode;

import java.util.Vector;

/**
 * 796. 旋转字符串
 * 每日一题：2022.04.07
 * 完成日期：2022.04.07
 * 链接：https://leetcode-cn.com/problems/rotate-string/
 * 描述：
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 * <p>
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。 
 * <p>
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcde", goal = "cdeab"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "abcde", goal = "abced"
 * 输出: false
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, goal.length <= 100
 * s 和 goal 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * <p>
 * 解题思路：
 * 没想到什么好的思路，简单的一个一个拼接去比对
 * <p>
 * <p>
 * state:
 */
public class Solution796 {

    public boolean rotateString(String s, String goal) {
        for (int i = 0; i < s.length(); i++) {
            String str = s.substring(i) + s.substring(0, i);
            if (goal.equals(str)) {
                return true;
            }
        }
        return false;
    }
}