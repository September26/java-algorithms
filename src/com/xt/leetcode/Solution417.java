package com.xt.leetcode;

import java.util.*;

/**
 * 417. 太平洋大西洋水流问题
 * 每日一题：2022.04.27
 * 完成日期：2022.04.27
 * 链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow/
 * 描述：
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * <p>
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * <p>
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * <p>
 * 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 示例 2：
 * <p>
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 *  
 * <p>
 * 提示：
 * <p>
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 总体思路，先找可以流到太平洋的，然后在找可以流到大西洋的。
 * 如何找流到太平洋的pacificset？可以利用递归的方式，先从x=0和y=0两行开始。分别寻找上下左右，如果已经在集合中则跳过，否则判断其海平面高度value是否符合，如果符合则加入并继续递归。
 * 同理找到流到大西洋的集合atlanticset。
 * 两个set集合分别保存了流到太平洋和大西洋的区域，则取交集则就是我们想要的结果。
 * 当然这个解法有一个小问题，就是递归判断的之后，因为每次都是无方向的上下左右都判断，导致重复查找的概率较高。
 * 更高效的解乏是一个一步一步的走，即先找到走一步能达到海边的集合，在找到二步能到达海边的，然后三步，继续递归下去。
 * <p>
 * <p>
 * state:done
 */
public class Solution417 {

    Set<String> pacificset = new HashSet<>();
    Set<String> atlanticset = new HashSet<>();

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        //上
        for (int x = 0; x < heights[0].length; x++) {
            String key = getKey(x, 0);
            pacificset.add(key);
            findInflow(heights, pacificset, heights[0][x], key, x, 1);
        }
        //左
        for (int y = 0; y < heights.length; y++) {
            String key = getKey(0, y);
            pacificset.add(key);
            findInflow(heights, pacificset, heights[y][0], key, 1, y);
        }
        //下
        for (int x = 0; x < heights[0].length; x++) {
            String key = getKey(x, heights.length - 1);
            atlanticset.add(key);
            findInflow(heights, atlanticset, heights[heights.length - 1][x], key, x, heights.length - 2);
        }
        //右
        for (int y = 0; y < heights.length; y++) {
            String key = getKey(heights[0].length - 1, y);
            atlanticset.add(key);
            findInflow(heights, atlanticset, heights[y][heights[0].length - 1], key, heights[0].length - 2, y);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (String key : pacificset) {
            if (!atlanticset.contains(key)) {
                continue;
            }
            String[] s = key.split("_");
            List<Integer> list = new ArrayList<>();
            for (String str : s) {
                list.add(Integer.parseInt(str));
            }
            result.add(list);
        }
        return result;
    }

    private void findInflow(int[][] heights, Set<String> set, int oldValue, String oldKey, int x, int y) {
        if (y < 0 || x < 0 || x >= heights[0].length || y >= heights.length) {
            return;
        }
        String key = getKey(x, y);
        if (set.contains(key)) {
            return;
        }
        int value = heights[y][x];
        if (oldValue > value) {
            return;
        }
        set.add(key);
        findInflow(heights, set, value, key, x + 1, y);
        findInflow(heights, set, value, key, x - 1, y);
        findInflow(heights, set, value, key, x, y + 1);
        findInflow(heights, set, value, key, x, y - 1);
    }

    private String getKey(int x, int y) {
        return y + "_" + x;
    }

}