package com.xt.leetcode;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 下一步就是优化
 * 题解：
 *
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution10 {

    /**
     * @param s aaa5454
     * @param p a.*4/a..4/aaa54/a.*5
     * @return true/false/true/true
     * <p>
     * String p = "(ba)(.*)(a)(.*)(babca)(.*)(ca)";
     * String s = "(ba)()(a)(acac)(babca)(bab)(ca)";
     */
    public boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }

    public boolean isMatch(String s, String p, int sIndex, int pIndex) {
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }
        String sChar = "";
        if (sIndex < s.length()) {
            sChar = s.substring(sIndex, sIndex + 1);
        }
        String pChar = p.substring(pIndex, pIndex + 1);
        String nextPChar = "";
        if (pIndex + 1 < p.length()) {
            nextPChar = p.substring(pIndex + 1, pIndex + 2);
        }
        if (nextPChar.equals("*")) {
            //判断匹配0,1,2,3,等是否符合
            int localIndex = 0;
            do {
                if (isMatch(s, p, sIndex + localIndex, pIndex + 2)) {
                    return true;
                }
                if (sIndex + localIndex >= s.length()) {
                    break;
                }
                //越界就break
                sChar = s.substring(sIndex + localIndex, sIndex + 1 + localIndex);
                localIndex++;
            } while (pChar.equals(sChar) || pChar.equals("."));//如果pChar为.的话，则通配
            return false;
        }
        if (sChar.equals(pChar)) {
            return isMatch(s, p, sIndex + 1, pIndex + 1);
        }
        if (pChar.equals(".")) {
            return isMatch(s, p, sIndex + 1, pIndex + 1);
        }
        return false;
    }
}
