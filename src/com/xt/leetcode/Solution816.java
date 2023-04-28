package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 816. 模糊坐标
 * 每日一题：2022.11.07
 * 完成日期：2022.11.07
 * 链接：https://leetcode.cn/problems/ambiguous-coordinates/
 * 描述：
 * 我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
 * <p>
 * 原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 * <p>
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * 输入: "(123)"
 * 输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
 * 示例 2:
 * 输入: "(00011)"
 * 输出:  ["(0.001, 1)", "(0, 0.011)"]
 * 解释:
 * 0.0, 00, 0001 或 00.01 是不被允许的。
 * 示例 3:
 * 输入: "(0123)"
 * 输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
 * 示例 4:
 * 输入: "(100)"
 * 输出: [(10, 0)]
 * 解释:
 * 1.0 是不被允许的。
 * <p>
 * 提示:
 * <p>
 * 4 <= S.length <= 12.
 * S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
 * <p>
 * 解题思路：
 * 首先插入,这个一共有s.length()-1中类型，这样分割成两个字符串。
 * 针对每个字符串，在插入.，这样一种有lat.length()*longi.length()种可能，
 * 针对每一种组合，检查其合法性，如果合法则加入list
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution816 {

    public List<String> ambiguousCoordinates(String s) {
        List<String> list = new ArrayList<>();
        s = s.substring(1, s.length() - 1);
        for (int i = 1; i < s.length(); i++) {
            searchStr(s.substring(0, i), s.substring(i), list);
        }
        return list;
    }

    public void searchStr(String lat, String longi, List<String> list) {
        for (int i = 0; i < lat.length(); i++) {
            String lastStr = lat.substring(i);
            if (i > 0) {
                lastStr = (lat.substring(0, i) + "." + lastStr);
            }
            for (int j = 0; j < longi.length(); j++) {
                String longiStr = longi.substring(j);
                if (j > 0) {
                    longiStr = (longi.substring(0, j) + "." + longiStr);
                }
                if (checkeAdd(lastStr) && checkeAdd(longiStr)) {
                    list.add("(" + lastStr + ", " + longiStr + ")");
                }
            }
        }
    }

    private boolean checkeAdd(String str) {
        String[] split = str.split("\\.");
        if (split[0].length() > 1 && split[0].startsWith("0")) {
            return false;
        }
        if (split.length > 1 && split[1].endsWith("0")) {
            return false;
        }
        return true;
    }

}