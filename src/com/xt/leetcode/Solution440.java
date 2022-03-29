package com.xt.leetcode;

import java.util.Vector;

/**
 * 440. 字典序的第K小数字
 * 每日一题：2022.03.23
 * 完成日期：2022.03.23
 * 链接：
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 13, k = 2
 * 输出: 10
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * 示例 2:
 * <p>
 * 输入: n = 1, k = 1
 * 输出: 1
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= k <= n <= 10^9
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 描述：
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:
 */
public class Solution440 {

    public int findKthNumber(int n, int k) {
        //n=2324,k=1358
        /**
         * 9999个话，每个是1111个。
         * 递归的思路
         * 1:1,10-19,100-199,1000-1999 =>1111个，
         * 2:2,20-29,200-299,2000-2324 =>111+325
         * 3:3,30-39,300-399->111
         * 4:->111
         * 5:->111
         * 6:->111
         * 7:->111
         * 8:->111
         * 9:->111
         *
         * 1358-1111=247
         * 247-111*2=>25
         * 25-11*2=>3
         * 2-1*2=>1
         *
         * 2,3,3,3
         *
         */
//        int result = 0;
//        //先求出1-9开头的数量
//        int[] ints = new int[10];
//        int base = 1;
//        flag:
//        while (true) {
//            for (int i = 1; i < 10; i++) {
//                if (n >= base) {
//                    n = n - base;
//                    ints[i] += base;
//                    continue;
//                }
//                ints[i] += n;
//                break flag;
//            }
//            base = base * 10;
//        }
//        int index = 0;
//        int sum = 0;
//        for (int i = 1; i < 10; i++) {
//            if (k > sum) {
//                sum += ints[i];
//                continue;
//            }
//            index = i;
//            break;
//        }

        //4
        int current = 0;
        int[] nums = new int[10];
        int numIndex = 0;
        int base = 1;
        /**
         * 1:1
         * 2:10
         * 11:19
         * 12:20
         * 21:29
         */
        while (current < k) {
            //尝试1，
            current += base;
            nums[numIndex]++;
            if (n > base * 10) {
                base *= 10;
                numIndex++;
                continue;
            }
            numIndex = 0;
            base = 1;
            int local = nums[0];
            nums = new int[10];
            nums[0] = local;


        }
        return 0;
    }

    private int getIndex(int n, int base, int mask, int index) {


        if (n > 10) {
            int i = n - 9;
            int i1 = i / 10;

        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += mask;
            if (n < sum) {
                return i;
            }

        }
        return 0;
    }


}