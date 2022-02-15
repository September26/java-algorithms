package com.xt.leetcode;

import java.util.*;

/**
 * 1380.矩阵中的幸运数
 * 日期：2022.2.15
 * 链接：https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix/
 * 描述：
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 * <p>
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * <p>
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 3：
 * <p>
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]
 *  
 * <p>
 * 提示：
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 10^5
 * 矩阵中的所有元素都是不同的
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 分别找出来行上面最小的，列上面最大的。这辆会有两个数组，这两个数组中重复的，就是幸运数
 * <p>
 * <p>
 * state:done
 */
public class Solution1380 {

    public List<Integer> luckyNumbers(int[][] matrix) {
        Integer[] xInts = new Integer[matrix.length];
        Integer[] yInts = new Integer[matrix[0].length];
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                int value = matrix[y][x];
                if (xInts[y] == null) {
                    xInts[y] = value;
                } else {
                    //x轴
                    xInts[y] = Math.min(xInts[y], value);
                }
                if (yInts[x] == null) {
                    yInts[x] = value;
                } else {
                    //y轴
                    yInts[x] = Math.max(yInts[x], value);
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        Set<Integer> cache = new HashSet<>(Arrays.asList(xInts));
        for (Integer i : yInts) {
            if (cache.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }
}