package com.xt.leetcode;

import java.util.Vector;

/**
 * 1769. 移动所有球到每个盒子所需的最小操作数
 * 每日一题：2022.12.02
 * 完成日期：2022.12.02
 * 链接：https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 * 描述：
 * 有 n 个盒子。给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。
 * <p>
 * 在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1 。注意，操作执行后，某些盒子中可能会存在不止一个小球。
 * <p>
 * 返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。
 * <p>
 * 每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：boxes = "110"
 * 输出：[1,1,3]
 * 解释：每个盒子对应的最小操作数如下：
 * 1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
 * 2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
 * 3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
 * 示例 2：
 * <p>
 * 输入：boxes = "001011"
 * 输出：[11,8,5,4,3,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == boxes.length
 * 1 <= n <= 2000
 * boxes[i] 为 '0' 或 '1'
 * <p>
 * 解题思路：
 * 以i的位置来进行分割，分为left和right。
 * 先求出rightSum和rightNum，然后遍历boxes。
 * result[i]其实就等于把左侧的往右挪，把右侧的往左挪，最终求出其sum值和。
 * 所以遍历的时候，遍历一个位置，就可以对应计算leftNum和leftSum，然后重新加计算右侧的rightSum和rightNum。
 *
 * state:done
 */
public class Solution1769 {

    public int[] minOperations(String boxes) {

        int leftSum = 0;
        int leftNum = 0;
        int rightSum = 0;
        int rightNum = 0;
        char[] chars = boxes.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                continue;
            }
            rightNum++;
            rightSum += (i);
        }
        int[] result = new int[boxes.length()];
        for (int i = 0; i < chars.length; i++) {
            result[i] = Math.abs(rightSum - i * rightNum) + Math.abs(leftSum - i * leftNum);
            if (chars[i] == '1') {
                leftNum++;
                leftSum += i;
                rightSum -= i;
                rightNum--;
            }
        }
        return result;
    }
}