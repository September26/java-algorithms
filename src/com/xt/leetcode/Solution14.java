package com.xt.leetcode;

import java.util.Vector;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * done
 */
public class Solution14 {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        int index = 1;
        while (index <= strs[0].length()) {
            String sub = strs[0].substring(0, index);
            for (int i = 1; i < strs.length; i++) {
                if (index > strs[i].length()) {
                    return strs[0].substring(0, index - 1);
                }
                if (!strs[i].substring(0, index).equals(sub)) {
                    return strs[0].substring(0, index - 1);
                }
            }
            index++;
        }
        return strs[0].substring(0, index - 1);
    }
}