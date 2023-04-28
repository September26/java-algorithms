package com.xt.mianshi;

/**
 * 面试题 01.02. 判定是否互为字符重排
 * 每日一题：2022.09.30
 * 完成日期：2022.09.30
 * 链接：https://leetcode.cn/problems/zero-matrix-lcci/
 * 描述：
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * 输出：
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 * 示例 2：
 * <p>
 * 输入：
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * 输出：
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zero-matrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 用两个数组来记录，分别记录y轴和x轴是否存在值为0的数字。
 * 然后遍历第二遍，如果存在则把其值改为0。
 * <p>
 * <p>
 * state:done
 */
public class MianShi0108 {

    public void setZeroes(int[][] matrix) {
        int[] xInts = new int[matrix[0].length];
        int[] yInts = new int[matrix.length];
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                int value = matrix[y][x];
                if (value == 0) {
                    xInts[x] = 1;
                    yInts[y] = 1;
                }
            }
        }
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (xInts[x] == 1 || yInts[y] == 1) {
                    matrix[y][x] = 0;
                }
            }
        }
    }
}















