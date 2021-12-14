package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * done
 */
public class Solution22 {
    //优化方向，添加map。根据forwardNum和reverseNum为key值直接返回List，不需要重复遍历

    public List<String> generateParenthesis(int n) {
        return generateParenthesis(n, n);
    }


    /**
     * @param forwardNum (的数量
     * @param reverseNum )的数量
     */
    public List<String> generateParenthesis(int forwardNum, int reverseNum) {
        ArrayList<String> list = new ArrayList<>();
        if (forwardNum == 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < reverseNum; i++) {
                builder.append(")");
            }
            list.add(builder.toString());
            return list;
        }
        if (forwardNum == 1) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < reverseNum; i++) {
                builder.append(")");
            }
            String s = builder.toString();
            for (int i = 0; i < reverseNum; i++) {
                String s1 = s.substring(0, i) + "(" + s.substring(i);
                list.add(s1);
            }
            return list;
        }
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i <= forwardNum; i++) {
            if ((reverseNum - 1) >= (forwardNum - i)) {
                List<String> stringList = generateParenthesis(forwardNum - i, reverseNum - 1);
                for (String str : stringList) {
                    list.add(prefix + ")" + str);
                }
            }
            prefix.append("(");
        }
        return list;
    }
}