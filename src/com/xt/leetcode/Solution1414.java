package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1414. 和为 K 的最少斐波那契数字数目
 * 日期：2022.2.3
 * 描述
 * 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
 * <p>
 * 斐波那契数字定义为：
 * <p>
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
 * 数据保证对于给定的 k ，一定能找到可行解。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：k = 7
 * 输出：2
 * 解释：斐波那契数字为：1，1，2，3，5，8，13，……
 * 对于 k = 7 ，我们可以得到 2 + 5 = 7 。
 * 示例 2：
 * <p>
 * 输入：k = 10
 * 输出：2
 * 解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
 * 示例 3：
 * <p>
 * 输入：k = 19
 * 输出：3
 * 解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= 10^9
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 解决这题的核心其实就是斐波那契数列的特性。
 * 比如k=19,那么一定要取数列中的13，因为如果不取13，则再往前两个就是5和8，则数量就变多了。
 * 所以这题就变得简单了，
 * k=19,取13，k=6，num=1，
 * k=6,取5，k=1，num=2，
 * k=1,取1，k=0，num=3
 * <p>
 * <p>
 * state:done
 */
public class Solution1414 {

    Map<Integer, Integer> cache = new HashMap<>();

    public int findMinFibonacciNumbers(int k) {
        if (k <= 1) {
            return 1;
        }
        List<Integer> list = new ArrayList<>();
        int current = 1;
        int index = 1;
        while (current < k) {
            current = add(++index);
            list.add(current);
        }

        int num = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            Integer integer = list.get(i);
            if (integer > k) {
                continue;
            }
            if (integer == k) {
                num++;
                break;
            }
            k -= integer;
            num++;
        }
        return num;
    }

    private int add(int i) {
        if (i <= 2) {
            return 1;
        }
        Integer integer = cache.get(i);
        if (integer == null) {
            integer = add(i - 1) + add(i - 2);
            cache.put(i, integer);
        }
        return integer;
    }
}