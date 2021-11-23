package com.xt.niuke;


/**
 * 描述
 * 现在有2副扑克牌，从扑克牌中随机五张扑克牌，我们需要来判断一下是不是顺子。
 * 有如下规则：
 * 1. A为1，J为11，Q为12，K为13，A不能视为14
 * 2. 大、小王为 0，0可以看作任意牌
 * 3. 如果给出的五张牌能组成顺子（即这五张牌是连续的）就输出true，否则就输出false。
 * 4.数据保证每组5个数字，每组最多含有4个零，数组的数取值为 [0, 13]
 * <p>
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(nlogn)O(nlogn)，本题也有时间复杂度 O(n)O(n) 的解法
 * 输入描述：
 * 输入五张扑克牌的值
 * 返回值描述：
 * 五张扑克牌能否组成顺子。
 * <p>
 * 求最大和最小值之间的差，和0的数量对比
 * 10月24
 * 已写完，未验证
 */
public class Solution61 {
    public boolean IsContinuous(int[] numbers) {
        int max = 0;
        int min = 13;
        int num = 0;
        for (int i : numbers) {
            if (i > max) {
                max = i;
                continue;
            }
            if (i < min) {
                min = i;
                continue;
            }
            if (i == 0) {
                num++;
            }
        }
        return (max - min) <= num;
    }
}
