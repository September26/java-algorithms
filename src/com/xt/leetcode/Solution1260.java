package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 1260. 二维网格迁移
 * 每日一题：2022.07.20
 * 完成日期：2022.07.20
 * 链接：https://leetcode.cn/problems/shift-2d-grid/
 * 描述：
 * 给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
 * <p>
 * 每次「迁移」操作将会引发下述活动：
 * <p>
 * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
 * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
 * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
 * 请你返回 k 次迁移操作后最终得到的 二维网格。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * 输出：[[9,1,2],[3,4,5],[6,7,8]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * 输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * 示例 3：
 * <p>
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * 输出：[[1,2,3],[4,5,6],[7,8,9]]
 *  
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 50
 * 1 <= n <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shift-2d-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 用一个相同容量的数组grid2装载新的数据，然后遍历数组，i,j位置的数据被移动到i1,j1上。
 * j1=(j + k) % width; 一定和宽度的余数
 * i1 = i + (j + k) / width; 一定是原高度加宽度的除数
 * 如果i1超出了grid.length，则i1 = i1 % grid.length;即可
 * <p>
 * <p>
 * state:done
 */
public class Solution1260 {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        final int width = grid[0].length;
        int[][] grid2 = new int[grid.length][width];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < width; j++) {
                int j1 = (j + k) % width;
                int i1 = i + (j + k) / width;
                i1 = i1 % grid.length;
                grid2[i1][j1] = grid[i][j];
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < grid2.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            result.add(list);
            for (int j = 0; j < width; j++) {
                list.add(grid2[i][j]);
            }
        }
        return result;
    }
}