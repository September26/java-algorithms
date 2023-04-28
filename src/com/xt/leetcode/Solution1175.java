package com.xt.leetcode;

import com.xt.util.AlgorithmHelper;

import java.util.Vector;

/**
 * 1175. 质数排列
 * 每日一题：2022.06.30
 * 完成日期：2022.06.30
 * 链接：https://leetcode.cn/problems/prime-arrangements/
 * 描述：
 * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
 * <p>
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 * <p>
 * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5
 * 输出：12
 * 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
 * 示例 2：
 * <p>
 * 输入：n = 100
 * 输出：682289015
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/prime-arrangements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 求出质数和非质数的数量，然后分别排序。乘积就是方案总数。
 *
 * <p>
 * state:done
 */
public class Solution1175 {

    public int numPrimeArrangements(int n) {
        long primeNum = 0;
        for (long i = 1; i <= n; i++) {
            if (isPrime(i)) {
                primeNum++;
            }
        }
        long result = 1;
        for (long i = 2; i <= primeNum; i++) {
            result = ramainder(result * i, 10_0000_0000 + 7);
        }
        for (long i = 2; i <= (n - primeNum); i++) {
            result = ramainder(result * i, 10_0000_0000 + 7);
        }
        return (int) result;
    }

    private boolean isPrime(long k) {
        if (k < 2) {
            return false;
        }
        for (int i = 2; i < k; i++) {
            if (k % i == 0) {
                return false;
            }
        }
        return true;
    }

    //取模运算
    public static long ramainder(long dividend, long dividor) {
        return dividend % dividor;
    }
}