package com.xt.leetcode;

import java.util.Vector;

/**
 * 1605. 给定行和列的和求可行矩阵
 * 每日一题：2023.03.14
 * 完成日期：2023.03.14
 * 链接：https://leetcode.cn/problems/find-valid-matrix-given-row-and-column-sums/
 * 描述：
 * 给你两个非负整数数组 rowSum 和 colSum ，其中 rowSum[i] 是二维矩阵中第 i 行元素的和， colSum[j] 是第 j 列元素的和。换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。
 * <p>
 * 请找到大小为 rowSum.length x colSum.length 的任意 非负整数 矩阵，且该矩阵满足 rowSum 和 colSum 的要求。
 * <p>
 * 请你返回任意一个满足题目要求的二维矩阵，题目保证存在 至少一个 可行矩阵。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：rowSum = [3,8], colSum = [4,7]
 * 输出：[[3,0],
 * [1,7]]
 * 解释：
 * 第 0 行：3 + 0 = 3 == rowSum[0]
 * 第 1 行：1 + 7 = 8 == rowSum[1]
 * 第 0 列：3 + 1 = 4 == colSum[0]
 * 第 1 列：0 + 7 = 7 == colSum[1]
 * 行和列的和都满足题目要求，且所有矩阵元素都是非负的。
 * 另一个可行的矩阵为：[[1,2],
 * [3,5]]
 * 示例 2：
 * <p>
 * 输入：rowSum = [5,7,10], colSum = [8,6,8]
 * 输出：[[0,5,0],
 * [6,1,0],
 * [2,0,8]]
 * 示例 3：
 * <p>
 * 输入：rowSum = [14,9], colSum = [6,9,8]
 * 输出：[[0,9,5],
 * [6,0,3]]
 * 示例 4：
 * <p>
 * 输入：rowSum = [1,0], colSum = [1]
 * 输出：[[1],
 * [0]]
 * 示例 5：
 * <p>
 * 输入：rowSum = [0], colSum = [0]
 * 输出：[[0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= rowSum.length, colSum.length <= 500
 * 0 <= rowSum[i], colSum[i] <= 108
 * sum(rowSum) == sum(colSum)
 * <p>
 * 解题思路：
 * 这题说实话，没有想出解决方案。
 * 参考了题解写的。因为力扣出题保证都会有解决答案，所以求rowSum[y]和 colSum[x]的较小值，并且两者都减掉这个较小值，则其中一个一定会被减为0。
 * 如果colSum[x]减为0，则x++，继续计算下一列
 * <p>
 * state:
 */
public class Solution1605 {

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int x = 0;
        int y = 0;
        int[][] result = new int[rowSum.length][colSum.length];

        while (y < rowSum.length && x < colSum.length) {
            int value = Math.min(rowSum[y], colSum[x]);
            result[y][x] = value;
            rowSum[y] -= value;
            colSum[x] -= value;
            if (colSum[x] == 0) {
                x++;
            }
            if (rowSum[y] == 0) {
                y++;
            }
        }
        return result;
    }
}