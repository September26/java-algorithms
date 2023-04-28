package com.xt.leetcode;

import java.util.Vector;

/**
 * 754. 到达终点数字
 * 每日一题：2022.11.04
 * 完成日期：2022.11.04
 * 链接：https://leetcode.cn/problems/reach-a-number/
 * 描述：
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 * <p>
 * 你可以做一些数量的移动 numMoves :
 * <p>
 * 每次你可以选择向左或向右移动。
 * 第 i 次移动（从  i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
 * 给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves ) 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: target = 2
 * 输出: 3
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 -1 。
 * 第三次移动，从 -1 到 2 。
 * 示例 2:
 * <p>
 * 输入: target = 3
 * 输出: 2
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 3 。
 * <p>
 * <p>
 * 提示:
 * <p>
 * -109 <= target <= 109
 * target != 0
 * <p>
 * 解题思路：
 * 首先，target正负其实无所谓，结果都是一样的，所以求target的绝对值。
 * 我们假设target=8,1+2+3+4=10，两者相差2，则把1改为-1，其值-2，则就符合了。所以差值只要为偶数，就会出现符合的情况，所以此时返回4即可。
 * 假设差值为奇数，比如target=9，1+2+3+4=10,相差1。则我们加入5，则差值为6，又变成了偶数，所以也就变成了符合的情况，次数返回5即可。
 * 因此，我们只要求差值，然后找到下一个让差值变为偶数的数即可。
 * <p>
 * state:done
 */
public class Solution754 {

    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            k++;
            target -= k;
        }
        if (target % 2 == 0) {
            return k;
        }
        return k + 1 + k % 2;
    }

}