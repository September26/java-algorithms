package com.xt.leetcode;

import java.util.Vector;

/**
 * 668. 乘法表中第k小的数
 * 每日一题：2022.05.18
 * 完成日期：2022.05.18
 * 链接：https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/
 * 描述：
 * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 * <p>
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 * <p>
 * 例 1：
 * <p>
 * 输入: m = 3, n = 3, k = 5
 * 输出: 3
 * 解释:
 * 乘法表:
 * 1	2	3
 * 2	4	6
 * 3	6	9
 * <p>
 * 第5小的数字是 3 (1, 2, 2, 3, 3).
 * 例 2：
 * <p>
 * 输入: m = 2, n = 3, k = 6
 * 输出: 6
 * 解释:
 * 乘法表:
 * 1	2	3
 * 2	4	6
 * <p>
 * 第6小的数字是 6 (1, 2, 2, 3, 4, 6).
 * 注意：
 * <p>
 * m 和 n 的范围在 [1, 30000] 之间。
 * k 的范围在 [1, m * n] 之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题我没想出答案，最终看题解才想通的。
 * 我还是有一个疑惑，每次二分法查找比middle小的数有几个的时候，怎么保证最后的那个middle+1或者right一定是乘法表中的数的？
 * <p>
 * <p>
 * state:done
 */
public class Solution668 {

    public int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n;
        int middle = 0;
        while (left < right) {
            middle = left + (right - left) / 2;
            //求小于middle的数量
            int count = 0;
            for (int i = 1; i <= m; i++) {
                int min = Math.min(m, middle / i);
                count += min;
            }
            if (count >= k) {
                right = middle;
                continue;
            }
            left = middle + 1;
        }
        return left;
    }
}