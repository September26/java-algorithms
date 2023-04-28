package com.xt.leetcode;

import java.util.Arrays;
import java.util.Vector;

/**
 * 1653. 使字符串平衡的最少删除次数
 * 每日一题：2023.03.06
 * 完成日期：2023.03.06
 * 链接：https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced/
 * 描述：
 * 给你一个字符串 s ，它仅包含字符 'a' 和 'b'​​​​ 。
 * <p>
 * 你可以删除 s 中任意数目的字符，使得 s 平衡 。当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
 * <p>
 * 请你返回使 s 平衡 的 最少 删除次数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aababbab"
 * 输出：2
 * 解释：你可以选择以下任意一种方案：
 * 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
 * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
 * 示例 2：
 * <p>
 * 输入：s = "bbaaaaabb"
 * 输出：2
 * 解释：唯一的最优解是删除最前面两个字符。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 105
 * s[i] 要么是 'a' 要么是 'b'​ 。​
 * <p>
 * 解题思路：
 * 首先，分别统计出a和b出现的次数。
 * 然后从头向尾遍历，如果读到了a，自然不需要处理。
 * 如果读到了b，则有两种可能：
 * 第一种是保留这个b，那么后面的a都要删除，则直接用a的总数减去已遍历过的即为最少删除次数。
 * 第二种是删掉这个b，那么次数已删除次数+1，继续遍历。总删除次数为已删除次数+后续的最少删除次数。
 * <p>
 * <p>
 * state:done
 */
public class Solution1653 {

    public int minimumDeletions(String s) {
        int aNum = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == 'a') {
                aNum++;
            }
        }
        int readANum = 0;
        int minNum = Integer.MAX_VALUE;
        int deleteBNum = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == 'a') {
                readANum++;
                continue;
            }
            //保留
            minNum = Math.min(minNum, aNum - readANum + deleteBNum);
            if (minNum == 53) {
                System.out.println("");
            }
            //删除b
            deleteBNum++;
        }
        minNum = Math.min(minNum, aNum - readANum + deleteBNum);
        return minNum == Integer.MAX_VALUE ? 0 : minNum;
    }
}