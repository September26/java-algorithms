package com.xt.leetcode;

import java.util.Vector;

/**
 * 2379. 得到 K 个黑块的最少涂色次数
 * 每日一题：2023.03.09
 * 完成日期：2023.03.09
 * 链接：https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks/
 * 描述：
 * 给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。字符 'W' 和 'B' 分别表示白色和黑色。
 * <p>
 * 给你一个整数 k ，表示想要 连续 黑色块的数目。
 * <p>
 * 每一次操作中，你可以选择一个白色块将它 涂成 黑色块。
 * <p>
 * 请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：blocks = "WBBWWBBWBW", k = 7
 * 输出：3
 * 解释：
 * 一种得到 7 个连续黑色块的方法是把第 0 ，3 和 4 个块涂成黑色。
 * 得到 blocks = "BBBBBBBWBW" 。
 * 可以证明无法用少于 3 次操作得到 7 个连续的黑块。
 * 所以我们返回 3 。
 * 示例 2：
 * <p>
 * 输入：blocks = "WBWBBBW", k = 2
 * 输出：0
 * 解释：
 * 不需要任何操作，因为已经有 2 个连续的黑块。
 * 所以我们返回 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == blocks.length
 * 1 <= n <= 100
 * blocks[i] 要么是 'W' ，要么是 'B' 。
 * 1 <= k <= n
 * <p>
 * 解题思路：
 * 遍历blocks，如果当前位置是'W'，则whiteNum次数+1，如果i - k + 1位置是'W'，则whiteNum次数-1。
 * 如果i>=k-1，则统计whiteNum的最少出现次数即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution2379 {

    public int minimumRecolors(String blocks, int k) {
        char[] chars = blocks.toCharArray();
        int whiteNum = 0;
        int minNum = Integer.MAX_VALUE;

        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == 'W') {
                whiteNum++;
            }
            if (i < (k - 1)) {
                continue;
            }
            minNum = Math.min(whiteNum, minNum);
            aChar = chars[i - k + 1];
            if (aChar == 'W') {
                whiteNum--;
            }
        }
        return minNum;
    }
}