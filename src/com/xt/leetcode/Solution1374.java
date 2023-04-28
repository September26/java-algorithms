package com.xt.leetcode;

import java.util.Vector;

/**
 * 1374. 生成每种字符都是奇数个的字符串
 * 每日一题：2022.08.01
 * 完成日期：2022.08.01
 * 链接：https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts/
 * 描述：
 * 给你一个整数 n，请你返回一个含 n 个字符的字符串，其中每种字符在该字符串中都恰好出现 奇数次 。
 * <p>
 * 返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4
 * 输出："pppz"
 * 解释："pppz" 是一个满足题目要求的字符串，因为 'p' 出现 3 次，且 'z' 出现 1 次。当然，还有很多其他字符串也满足题目要求，比如："ohhh" 和 "love"。
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出："xy"
 * 解释："xy" 是一个满足题目要求的字符串，因为 'x' 和 'y' 各出现 1 次。当然，还有很多其他字符串也满足题目要求，比如："ag" 和 "ur"。
 * 示例 3：
 * <p>
 * 输入：n = 7
 * 输出："holasss"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 判断n是否是偶数，如果是偶数则最后一位加b,否则加a
 * <p>
 * state:
 */
public class Solution1374 {

    public String generateTheString(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                builder.append("a");
                continue;
            }
            if (i % 2 != 0) {
                builder.append("b");
            } else {
                builder.append("a");
            }
        }
        return builder.toString();
    }
}