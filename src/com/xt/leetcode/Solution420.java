package com.xt.leetcode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 420. 强密码检验器
 * 每日一题：2022.04.02
 * 完成日期：2022.04.02
 * 链接：https://leetcode-cn.com/problems/strong-password-checker/
 * 描述：
 * 如果一个密码满足下述所有条件，则认为这个密码是强密码：
 * 由至少 6 个，至多 20 个字符组成。
 * 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。
 * 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)。
 * 给你一个字符串 password ，返回 将 password 修改到满足强密码条件需要的最少修改步数。如果 password 已经是强密码，则返回 0 。
 * <p>
 * 在一步修改操作中，你可以：
 * <p>
 * 插入一个字符到 password ，
 * 从 password 中删除一个字符，或
 * 用另一个字符来替换 password 中的某个字符。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：password = "a"
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：password = "aA1"
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：password = "1337C0d3"
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= password.length <= 50
 * password 由字母、数字、点 '.' 或者感叹号 '!'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/strong-password-checker
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:
 */
public class Solution420 {

    public int strongPasswordChecker(String password) {

        List<Node> list = new ArrayList<>();
        Node node = new Node();
        int noNum = 0;
        int upperNum = 0;
        int lowerNum = 0;
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar >= '0' && aChar <= '9') {
                noNum++;
            } else if (aChar >= 'A' && aChar <= 'Z') {
                upperNum++;
            } else if (aChar >= 'a' && aChar <= 'z') {
                lowerNum++;
            }
            if (node.c == aChar) {
                node.num++;
                continue;
            }
            if (node.num >= 3) {
                node.end = i - 1;
                list.add(node);
            }
            node = new Node();
            node.c = aChar;
            node.num = 1;
            node.start = i;
        }
        int result = 0;
        result += list.size();

        if (password.length() < 6) {
            result += (password.length() - 6);
        }

        if (noNum == 0) {
            result++;
        }
        if (lowerNum == 0) {
            result++;
        }
        if (upperNum == 0) {
            result++;
        }
        return result;
    }


    static class Node {
        int start = 0;
        int end = 0;
        int num = 0;
        char c;
    }
}