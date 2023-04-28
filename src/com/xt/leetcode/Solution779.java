package com.xt.leetcode;

import java.util.Vector;

/**
 * 779. 第K个语法符号
 * 每日一题：2022.10.20
 * 完成日期：2022.10.20
 * 链接：https://leetcode.cn/problems/k-th-symbol-in-grammar/
 * 描述：
 * 我们构建了一个包含 n 行( 索引从 1  开始 )的表。首先在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 * <p>
 * 例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
 * 给定行数 n 和序数 k，返回第 n 行中第 k 个字符。（ k 从索引 1 开始）
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 1, k = 1
 * 输出: 0
 * 解释: 第一行：0
 * 示例 2:
 * <p>
 * 输入: n = 2, k = 1
 * 输出: 0
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * 示例 3:
 * <p>
 * 输入: n = 2, k = 2
 * 输出: 1
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= n <= 30
 * 1 <= k <= 2n - 1
 * <p>
 * 解题思路：
 * 这题我们只要求第k位的值就行，而不是所有的值，所以我们可以进行二分运算，每增加一行n扩容为两倍时，只保留取k做在的那一部分。
 * 这样，就算执行30行循环，我们最终保留的仍然只有一位。
 * <p>
 * <p>
 * state:done
 */
public class Solution779 {

    public int kthGrammar(int n, int k) {
        int level = 1;
        int base = 0;
        k--;
        while (level < n) {
            /**
             * flag =
             */
            int flag = k >> (n - level) - 1;
            if (flag == 0) {
                //保留左侧

            } else {
                //保留右侧
                base = base == 0 ? 1 : 0;
                k = k - (flag << (n - level) - 1);
            }
            level++;
        }
        return base;
    }
}