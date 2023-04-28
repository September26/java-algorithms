package com.xt.mianshi;

/**
 * 面试题 01.09. 字符串轮转
 * 每日一题：2022.09.29
 * 完成日期：2022.09.29
 * 链接：https://leetcode.cn/problems/string-rotation-lcci/
 * 描述：
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 * <p>
 * 示例1:
 * <p>
 * 输入：s1 = "waterbottle", s2 = "erbottlewat"
 * 输出：True
 * 示例2:
 * <p>
 * 输入：s1 = "aa", s2 = "aba"
 * 输出：False
 * 提示：
 * <p>
 * 字符串长度在[0, 100000]范围内。
 * 说明:
 * <p>
 * 你能只调用一次检查子串的方法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/string-rotation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先最简单的思路，自然是每个位置都变换重新组装尝试一下。但是这样效率不高。
 * 看了官方的题解，确实巧妙，组装两个s1位str，看str中是否包含s2。
 * <p>
 * state:done
 */
public class MianShi0109 {

    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() == 0) {
            return s2.length() == 0;
        }
        for (int i = 0; i < s1.length(); i++) {
            String local = s1.substring(i) + s1.substring(0, i);
            if (local.equals(s2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFlipedString2(String s1, String s2) {
        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }
}















