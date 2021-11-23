package com.xt.niuke;

/**
 * 描述
 * 一个整型数组里除了两个数字只出现一次，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * <p>
 * 数据范围：数组长度 2≤n≤1000，数组中每个数的大小 0<val≤1000000
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 * <p>
 * 提示：输出时按非降序排列。
 * 示例1
 * 输入：
 * [1,4,1,6]
 * 返回值：
 * [4,6]
 * 说明：
 * 返回的结果中较小的数排在前面
 * 示例2
 * 输入：
 * [1,2,3,3,2,9]
 * 返回值：
 * [1,9]
 */
public class Solution56 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param array int整型一维数组
     * @return int整型一维数组
     */
    public int[] FindNumsAppearOnce(int[] array) {
        // write code here
        int a = 0;
        int b = 0;
        //假设两个出现一次的数字是a,b
        int value = 0;
        for (int i : array) {
            value = value ^ i;
        }

        /**
         * a和b一定是不相等的，则可以找到最后一位相等的值。比如110和011，最后一位不相等的是1
         * 则第二次遍历的时候，根据最后一位是否为1，分为两个判断队列进行判断，就可以得到a和b.
         */
        int mask = getLastEqualOne(value);
        for (int i : array) {
            if ((i & mask) == mask) {
                a = a ^ i;
            } else {
                b = b ^ i;
            }
        }
        return a < b ? new int[]{a, b} : new int[]{b, a};
    }

    public int getLastEqualOne(int value) {
        //求value中最后一位不为0的
        for (int i = 0; i < 30; i++) {
            if (((value >> i) & 1) == 1) {
                return 1 << i;
            }
        }
        return 0;
    }
}
