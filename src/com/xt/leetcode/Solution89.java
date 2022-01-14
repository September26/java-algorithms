package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 89. 格雷编码
 * n 位格雷码序列 是一个由 2^n 个整数组成的序列，其中：
 * 每个整数都在范围 [0, 2^n - 1] 内（含 0 和 2^n - 1）
 * 第一个整数是 0
 * 一个整数在序列中出现 不超过一次
 * 每对 相邻 整数的二进制表示 恰好一位不同 ，且
 * 第一个 和 最后一个 整数的二进制表示 恰好一位不同
 * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：[0,1,3,2]
 * 解释：
 * [0,1,3,2] 的二进制表示是 [00,01,11,10] 。
 * - 00 和 01 有一位不同
 * - 01 和 11 有一位不同
 * - 11 和 10 有一位不同
 * - 10 和 00 有一位不同
 * [0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
 * - 00 和 10 有一位不同
 * - 10 和 11 有一位不同
 * - 11 和 01 有一位不同
 * - 01 和 00 有一位不同
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[0,1]
 * <p>
 * 提示：
 * 1 <= n <= 16
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gray-code
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 2个的时候选择0和1，对应000,001
 * 4个的时候，2和3和1和2相比，只有从右向左的第三位不相同，所以我可以反过来排列，那就是000,001,101,100,
 * 8个的时候，第四位不太相同，那就把1-4位再次反过来，就这样不断的翻转即可。
 * 0,1,
 * 0,1,3,2
 * 0,1,3,2,6,7,5,4
 * 0,1,3,2,6,7,5,4,12,13,15,14,10,11,9,8
 *
 * <p>
 * state:done
 */
public class Solution89 {

    /**
     * 2^n 个整数组成的序列的解法
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        if (n == 1) {
            return list;
        }
        int times = 1;
        int value = 1;
        while (++times <= n) {
            value = value << 1;
            for (int i = list.size() - 1; i >= 0; i--) {
                Integer integer = list.get(i);
                list.add(integer | value);
            }

        }
        return list;
    }

    /**
     * 如果把上面的条件中所有的2^n改为2*n呢？下面的解法
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode2(int n) {
        int max = 1;
        int length = n * 2;
        int local = length;
        while ((local = local >> 1) > 0) {
            max++;
        }
        ArrayList<Integer>[] lists = new ArrayList[length];
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> integers = new ArrayList<>();
            int digit = 1;
            for (int k = 0; k < max; k++) {
                if ((i ^ digit) < length) {
                    integers.add(i ^ digit);
                }
                digit = digit << 1;
            }
            lists[i] = integers;
        }
        Set<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        set.add(0);
        result.add(0);
        while (result.size() < length) {
            Integer integer = result.get(result.size() - 1);
            for (int i = lists[integer].size() - 1; i >= 0; i--) {
                int value = lists[integer].get(i);
                if (!set.contains(value)) {
                    result.add(value);
                    set.add(value);
                    break;
                }
            }
        }
        return result;
    }
}