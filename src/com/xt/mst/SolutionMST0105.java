package com.xt.mst;

/**
 * 面试题 01.05. 一次编辑
 * 每日一题：2022.05.13
 * 完成日期：2022.05.13
 * 链接：https://leetcode.cn/problems/one-away-lcci/
 * 描述：
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/one-away-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 判断长度，如果second和first相同则只可能是替换。少于则只可能是删，大于则只可能是加。
 * 分别尝试三种可能性，三种都不可能则返回false。
 * 然后我们可以再简化一下场景，增加和删除其实属于同一种场景，如果减少一个字符first可以转化为second，那么second增加一个字符也可以转化为first。
 * 所以我们只要考虑减少first一个字符串的场景就好了。如果second比first长，则两者交换。
 *
 * <p>
 * <p>
 * state:done
 */
public class SolutionMST0105 {

    public boolean oneEditAway(String first, String second) {
        char[] firstChars = first.toCharArray();
        char[] secondChars = second.toCharArray();
        int num = firstChars.length - second.length();
        int diffNum = 0;
        if (num == 0) {
            for (int i = 0; i < firstChars.length; i++) {
                if (firstChars[i] != secondChars[i]) {
                    diffNum++;
                }
            }
            return diffNum <= 1;
        }
        if (Math.abs(num) > 1) {
            return false;
        }
        if (secondChars.length > firstChars.length) {
            char[] local = secondChars;
            secondChars = firstChars;
            firstChars = local;
        }
        for (int i = 0; i < firstChars.length; i++) {
            int secondIndex = i - diffNum;
            if (secondIndex < 0 || secondIndex >= secondChars.length) {
                diffNum++;
            } else if (firstChars[i] != secondChars[secondIndex]) {
                diffNum++;
            }
            if (diffNum > 1) {
                return false;
            }
        }
        return true;
    }
}