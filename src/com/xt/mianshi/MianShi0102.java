package com.xt.mianshi;

/**
 * 面试题 01.02. 判定是否互为字符重排
 * 每日一题：2022.09.27
 * 完成日期：2022.09.27
 * 链接：https://leetcode.cn/problems/check-permutation-lcci/
 * 描述：
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 * <p>
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 * <p>
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-permutation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 对s1构建一个数组，每个字符按照其assic决定位置，每个位置存放其出现的数量。
 * 然后对s2遍历，每个字符的位置-1。
 * 最后遍历ints，都为0则返回true，否则返回false。
 * <p>
 * <p>
 * state:done
 */
public class MianShi0102 {

    public boolean CheckPermutation(String s1, String s2) {
        int[] ints = new int[26];
        char[] chars = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        for (char c : chars) {
            ints[c - 'a']++;
        }
        for (char c : char2) {
            ints[c - 'a']--;
        }
        for (int i : ints) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

}















