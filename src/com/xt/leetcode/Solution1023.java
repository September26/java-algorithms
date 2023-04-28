package com.xt.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

/**
 * 1023. 驼峰式匹配
 * 每日一题：2023.04.14
 * 完成日期：2023.04.14
 * 链接：https://leetcode.cn/problems/camelcase-matching/
 * 描述：
 * 如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
 * <p>
 * 给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * 输出：[true,false,true,true,false]
 * 示例：
 * "FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
 * "FootBall" 可以这样生成："F" + "oot" + "B" + "all".
 * "FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
 * 示例 2：
 * <p>
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * 输出：[true,false,true,false,false]
 * 解释：
 * "FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
 * "FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
 * 示例 3：
 * <p>
 * 输出：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
 * 输入：[false,true,false,false,false]
 * 解释：
 * "FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= queries.length <= 100
 * 1 <= queries[i].length <= 100
 * 1 <= pattern.length <= 100
 * 所有字符串都仅由大写和小写英文字母组成。
 * <p>
 * 解题思路：
 * isMatch来判断对应的字符是否满足，用两个int值index1和index2记录位置。
 * 如果chars1[index1]==chars2[index2]，则index1++，index2。
 * 否则index1++。等于说只挪动如果chars1中的位置，如果这个位置是大些字母，则直接返回false。
 * 遍历完chars1后，如果发现还有未遍历的chars2，则说明没有覆盖全字符串pattern，则false。
 * state:done
 */
public class Solution1023 {

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> list = new ArrayList<>();
        Arrays.stream(queries).forEach(s -> list.add(isMatch(s, pattern)));
        return list;
    }

    public Boolean isMatch(String querie, String pattern) {
        char[] chars1 = querie.toCharArray();
        char[] chars2 = pattern.toCharArray();
        int index1 = 0;
        int index2 = 0;
        while (index1 < chars1.length && index2 < chars2.length) {
            char queriChar = chars1[index1];
            if (queriChar == chars2[index2]) {
                index1++;
                index2++;
                continue;
            }
            index1++;
            if (queriChar >= 'A' && queriChar <= 'Z') {
                return false;
            }
        }
        if (index2 < chars2.length) {
            return false;
        }
        while (index1 < chars1.length) {
            char queriChar = chars1[index1];
            if (queriChar >= 'A' && queriChar <= 'Z') {
                return false;
            }
            index1++;
        }
        return true;
    }
}