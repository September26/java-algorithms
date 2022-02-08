package com.xt.leetcode;

import java.util.*;

/**
 * 1219. 黄金矿工
 * 日期：2022.2.5
 * 描述
 * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。
 * 每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
 * <p>
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 * <p>
 * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
 * 矿工每次可以从当前位置向上下左右四个方向走。
 * 每个单元格只能被开采（进入）一次。
 * 不得开采（进入）黄金数目为 0 的单元格。
 * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
 * 输出：24
 * 解释：
 * [[0,6,0],
 * [5,8,7],
 * [0,9,0]]
 * 一种收集最多黄金的路线是：9 -> 8 -> 7。
 * 示例 2：
 * <p>
 * 输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * 输出：28
 * 解释：
 * [[1,0,7],
 * [2,0,6],
 * [3,4,5],
 * [0,3,0],
 * [9,0,20]]
 * 一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * 最多 25 个单元格中有黄金。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-with-maximum-gold
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 遍历每个不是0的节点，然后递归向四个方向寻找这个点出发的最大值。
 * 以原点出发后，如何避免选择重复节点这个问题，一开始我用的是List，每次都构造一个新的List去存储已选择的节点，然后判断是否List中是否存在，但是这样效率比较低。
 * 后来看了题解，选择了之后把选择的点改为0，用完之后再改回来。这个就完美解决了已选择节点的问题。
 * <p>
 * <p>
 * state:done
 */
public class Solution1219 {
//    Set<String> hasComputer = new HashSet<>();

    public int getMaximumGold(int[][] grid) {
        int maxValue = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                int value = grid[y][x];
                if (value == 0) {
                    continue;
                }
//                if (hasComputer.contains(x + "_" + y)) {
//                    continue;
//                }
                maxValue = Math.max(maxValue, searchMax(grid, x, y, 0));
            }
        }
        return maxValue;
    }

    private int searchMax(int[][] grid, int x, int y, int valueSum) {
        if (x < 0 || y < 0 || x >= grid[0].length || y >= grid.length) {
            return valueSum;
        }
        if (grid[y][x] == 0) {
            return valueSum;
        }
        int i = grid[y][x];
        int newValueSum = valueSum + grid[y][x];
        grid[y][x] = 0;

        int sum1 = searchMax(grid, x, y - 1, newValueSum);

        int sum2 = searchMax(grid, x, y + 1, newValueSum);

        int sum3 = searchMax(grid, x - 1, y, newValueSum);

        int sum4 = searchMax(grid, x + 1, y, newValueSum);

        int max = Math.max(sum1, sum2);
        max = Math.max(max, sum3);
        max = Math.max(max, sum4);

        grid[y][x] = i;
        return max;
    }
}