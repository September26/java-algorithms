package com.xt.leetcode;

import java.util.Vector;

/**
 * 498. 对角线遍历
 * 每日一题：2022.06.14
 * 完成日期：2022.06.14
 * 链接：https://leetcode.cn/problems/diagonal-traverse/
 * 描述：
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * 示例 2：
 *
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 *  
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/diagonal-traverse
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 设置两个方向，分别是左上和右下。forward==0时代表左上，forward==1时代表右下。
 * 以左上查找为例，如果(x+1，y-1)没有超出范围，那么就把这个值加入到数组中，否则，说明该反向查找了。
 * 把forward设置为1。
 * <p>
 * <p>
 * state:done
 */
public class Solution498 {

    public int[] findDiagonalOrder(int[][] mat) {
        int index = 0;
        int forward = 0;//0右上，1左下
        int[] result = new int[mat[0].length * mat.length];
        int x = 0, y = 0;
        while (index < result.length) {
            result[index++] = mat[y][x];
            if (forward == 0) {
                if ((x + 1) < mat[0].length && (y - 1) >= 0) {
                    x++;
                    y--;
                    continue;
                }
                if ((x + 1) < mat[0].length) {
                    x++;
                } else {
                    y++;
                }
                forward = 1;
                continue;
            }
            if ((x - 1) >= 0 && (y + 1) < mat.length) {
                x--;
                y++;
                continue;
            }
            if (y + 1 < mat.length) {
                y++;
            } else {
                x++;
            }
            forward = 0;
        }
        return result;
    }
}