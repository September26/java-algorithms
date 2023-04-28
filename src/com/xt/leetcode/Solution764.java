package com.xt.leetcode;

import java.util.Vector;

/**
 * 764. 最大加号标志
 * 每日一题：2022.10.09
 * 完成日期：2022.10.09
 * 链接：https://leetcode.cn/problems/largest-plus-sign/
 * 描述：
 * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
 * <p>
 * 返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
 * <p>
 * 一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入: n = 5, mines = [[4, 2]]
 * 输出: 2
 * 解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入: n = 1, mines = [[0, 0]]
 * 输出: 0
 * 解释: 没有加号标志，返回 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 500
 * 1 <= mines.length <= 5000
 * 0 <= xi, yi < n
 * 每一对 (xi, yi) 都 不重复​​​​​​​
 * <p>
 * 解题思路：
 * 我们先用一种最笨的方法来实现，首先构建一个二维数组，对应的就是n*n的矩阵上的每个数字的值，然后把mines进行填充。
 * 然后从最中间开始搜索，一层一层往外搜索每个点所对应的最大阶数，如果最大阶数达到理论上的最大值，则直接返回即可。
 * 然后我们再看如何优化，每一行进行遍历搜索的时候，如果第n位读到了0，则说明至少n+i范围内，最大值就是i，而且如果存在，这个点的坐标一定是n+i。
 * 此时，我们可以让当前的最大长度result为i，直接跳过中间的部分，直接从n+result位开始搜索，大幅节省计算时间。
 * 这个优化方案就先不写了
 *
 * <p>
 * state:done
 */
public class Solution764 {

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 1;
            }
        }
        for (int[] mine : mines) {
            matrix[mine[0]][mine[1]] = 0;
        }
        int result = 0;
        int centerX = n % 2 == 1 ? (n - 1) / 2 : n / 2;
        for (int step = 0; step <= centerX; step++) {
            for (int y = centerX - step; y <= centerX + step; y++) {
                int maxLength = centerX - step + 1;
                for (int x = centerX - step; x <= centerX + step; x++) {
                    result = Math.max(result, searchMaxLength(x, y, matrix, maxLength));
                    if (maxLength == result) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

    private int searchMaxLength(int x, int y, int[][] matrix, int maxlength) {
        if (y < 0 || y >= matrix.length || x < 0 || x >= matrix.length) {
            return 0;
        }
        int value = matrix[y][x];
        if (value == 0) {
            return 0;
        }
        int length = 1;
        for (; length <= maxlength; ) {
            if (y + length >= matrix.length || matrix[y + length][x] == 0) {
                break;
            }
            if (y - length < 0 || matrix[y - length][x] == 0) {
                break;
            }
            if (x + length >= matrix.length || matrix[y][x + length] == 0) {
                break;
            }
            if (x - length < 0 || matrix[y][x - length] == 0) {
                break;
            }
            length++;
        }
        return length;
    }

}