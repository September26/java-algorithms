package com.xt.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 522. 最长特殊序列 II
 * 每日一题：2022.06.27
 * 完成日期：2022.06.27
 * 链接：https://leetcode.cn/problems/longest-uncommon-subsequence-ii/
 * 描述：
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。
 * <p>
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 * <p>
 *  s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 * <p>
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: strs = ["aba","cdc","eae"]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: strs = ["aaa","aaa","aa"]
 * 输出: -1
 *  
 * <p>
 * 提示:
 * <p>
 * 2 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i] 只包含小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-uncommon-subsequence-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先找出字符串数组中所有重复的，重复的一定不是独有的子序列。
 * 然后对字符串数组排序，从长开始，向短遍历。
 * 如果当前的字符串是重复的，则加入到集合当中。
 * 如果不是则判断是不是集合中字符串的子序列，如果不是则就是想要的结果。如果是，则跳过继续找下一个。
 * <p>
 * state:done
 */
public class Solution522 {

    public int findLUSlength(String[] strs) {
        Set<String> set = new HashSet<>();
        Set<String> repeatSet = new HashSet<>();
        for (String str : strs) {
            if (set.contains(str)) {
                repeatSet.add(str);
            } else {
                set.add(str);
            }
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        Set<String> checkSet = new HashSet<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            if (repeatSet.contains(str)) {
                checkSet.add(str);
                continue;
            }
            //判断是不是checkSet中的子序列
            if (isChild(checkSet, str)) {
                continue;
            }
            return str.length();
        }
        return -1;
    }

    private boolean isChild(Set<String> checkSet, String str) {
        if (checkSet.size() == 0) {
            return false;
        }
        char[] strChars = str.toCharArray();
        for (String checkStr : checkSet) {
            //判断str是不是checkStr的子序列
            char[] checkChars = checkStr.toCharArray();
            int index = 0;
            for (int i = 0; i < checkChars.length; i++) {
                if (strChars[index] == checkChars[i]) {
                    index++;
                }
                if (index == str.length()) {
                    return true;
                }
            }
        }
        return false;
    }

}