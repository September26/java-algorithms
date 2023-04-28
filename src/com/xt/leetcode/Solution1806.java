package com.xt.leetcode;

import java.util.Vector;

/**
 * 1806. 还原排列的最少操作步数
 * 每日一题：2022.01.09
 * 完成日期：2022.01.09
 * 链接：https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/
 * 描述：
 * 给你一个偶数 n​​​​​​ ，已知存在一个长度为 n 的排列 perm ，其中 perm[i] == i​（下标 从 0 开始 计数）。
 * <p>
 * 一步操作中，你将创建一个新数组 arr ，对于每个 i ：
 * <p>
 * 如果 i % 2 == 0 ，那么 arr[i] = perm[i / 2]
 * 如果 i % 2 == 1 ，那么 arr[i] = perm[n / 2 + (i - 1) / 2]
 * 然后将 arr​​ 赋值​​给 perm 。
 * <p>
 * 要想使 perm 回到排列初始值，至少需要执行多少步操作？返回最小的 非零 操作步数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：1
 * 解释：最初，perm = [0,1]
 * 第 1 步操作后，perm = [0,1]
 * 所以，仅需执行 1 步操作
 * 示例 2：
 * <p>
 * 输入：n = 4
 * 输出：2
 * 解释：最初，perm = [0,1,2,3]
 * 第 1 步操作后，perm = [0,2,1,3]
 * 第 2 步操作后，perm = [0,1,2,3]
 * 所以，仅需执行 2 步操作
 * 示例 3：
 * <p>
 * 输入：n = 6
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 1000
 * n​​​​​​ 是一个偶数
 * 解题思路：
 * 这题，既然说变回愿状态，那么我只需要抓出一个点知道其每一轮的n-2的位置即可。
 * 比如0，1，2，3，4，5，0和5的位置一直不变，所以我们不管，我们只要一直关注4的位置即可。
 * 4重新回到4的位置时，是从2移动的，2的位置又是1移动的。我们可以得到下面这一个推论：
 * f(4)<-f(3)<-f(1)<-f(2)<-f(4)，所以经过4轮。
 *
 *
 * <p>
 * state:done
 */
public class Solution1806 {

    public int reinitializePermutation(int n) {
        if (n == 2) {
            return 1;
        }
        int current = 0;
        int num = 0;
        while (current != (n - 2)) {
            if (current == 0) {
                current = n - 2;
            }
            current = f(current, n);
            num++;
            System.out.println(current);
        }
        return num;
    }

    private int f(int i, int n) {
        if (i % 2 == 0) {
            return i / 2;
        }
        return n / 2 + (i - 1) / 2;

    }

}