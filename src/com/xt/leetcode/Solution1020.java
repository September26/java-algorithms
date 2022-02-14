package com.xt.leetcode;


import java.util.HashSet;
import java.util.Set;

/**
 * 1020. 飞地的数量
 * 日期：2022.2.12
 * 链接：https://leetcode-cn.com/problems/number-of-enclaves/
 * 描述
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 * <p>
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 * <p>
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 示例 2：
 * <p>
 * <p>
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 *  
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 的值为 0 或 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-enclaves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先遍历一遍grid数组中的所有点，
 * 如果位置为1，则sum++记录1的数量。
 * 如果是边界，则触发递归查找，上下左右四个方向，找到这个点出发所有可能到达的点。
 * 查找过程中可能会有重复的点查找，所以设置一个cache进行判断，避免重复查找，因为m,n<=500，所以y*1000+x就可以决定唯一的一个key值。
 * 如果点不重复，则num++,这样num就是所有边界出发能到达的所有点的数量。
 * sum-num则就是所有不能到达的数量了。
 * <p>
 * <p>
 * state:done
 */
public class Solution1020 {
    int num = 0;
    Set<Integer> cache = new HashSet<>();

    public int numEnclaves(int[][] grid) {
        int sum = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] != 1) {
                    continue;
                }
                sum++;
                if (y == 0 || x == 0 || y == grid.length - 1 || x == grid[0].length - 1) {
                    search(grid, y, x);
                }
            }
        }
        return sum - num;
    }

    private boolean hasAdd(int y, int x) {
        int i = y * 1000 + x;
        if (cache.contains(i)) {
            return true;
        }
        num++;
        cache.add(i);
        return false;
    }

    /**
     * @param grid
     * @param y
     * @param x
     */
    private void search(int[][] grid, int y, int x) {
        if (y < 0 || x < 0 || y >= grid.length || x >= grid[0].length) {
            return;
        }
        if (grid[y][x] == 0) {
            return;
        }
        if (hasAdd(y, x)) {
            return;
        }
        search(grid, y + 1, x);
        search(grid, y - 1, x);
        search(grid, y, x + 1);
        search(grid, y, x - 1);
    }


}