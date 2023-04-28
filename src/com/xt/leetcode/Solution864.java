package com.xt.leetcode;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Vector;

/**
 * 864. 获取所有钥匙的最短路径
 * 每日一题：2022.11.10
 * 完成日期：2022.11.10
 * 链接：https://leetcode.cn/problems/shortest-path-to-get-all-keys/
 * 描述：
 * 给定一个二维网格 grid ，其中：
 * <p>
 * '.' 代表一个空房间
 * '#' 代表一堵
 * '@' 是起点
 * 小写字母代表钥匙
 * 大写字母代表锁
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
 * <p>
 * 假设 k 为 钥匙/锁 的个数，且满足 1 <= k <= 6，字母表中的前 k 个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 * <p>
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = ["@.a.#","###.#","b.A.B"]
 * 输出：8
 * 解释：目标是获得所有钥匙，而不是打开所有锁。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：grid = ["@..aA","..B#.","....b"]
 * 输出：6
 * 示例 3:
 * <p>
 * <p>
 * 输入: grid = ["@Aa"]
 * 输出: -1
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 30
 * grid[i][j] 只含有 '.', '#', '@', 'a'-'f' 以及 'A'-'F'
 * 钥匙的数目范围是 [1, 6]
 * 每个钥匙都对应一个 不同 的字母
 * 每个钥匙正好打开一个对应的锁
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:
 */
public class Solution864 {
    int minStep = Integer.MAX_VALUE;
    int[][] intss = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int shortestPathAllKeys(String[] grid) {
        char[][] grids = new char[grid.length][grid[0].toCharArray().length];
        boolean[][] uses = new boolean[grid.length][grid[0].toCharArray().length];
        int keyNum = 0;
        int y = 0, x = 0;
        for (int i = 0; i < grids.length; i++) {
            grids[i] = grid[i].toCharArray();
            for (int j = 0; j < grids[0].length; j++) {
                char value = grids[i][j];
                if (value == '.') {
                    continue;
                }
                if (value == '#') {
                    uses[i][j] = true;
                    continue;
                }
                if (value == '@') {
                    y = i;
                    x = j;
                    uses[i][j] = true;
                    continue;
                }
                if (value >= 'a' && value <= 'z') {
                    keyNum++;
                }
            }
        }
        jumpStep(grids, new LinkedHashSet<>(), new HashSet<>(), keyNum, y, x, 0);
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }


    private void jumpStep(char[][] grids, LinkedHashSet<String> routeSet, HashSet<Character> keySet, int keyNum, int y, int x, int step) {
        char value = grids[y][x];
        if (value == '#') {
            return;
        }
        if (value >= 'a' && value <= 'z') {
            if (keySet.contains(value)) {
                //说明已经走过
                return;
            }
            keySet.add(value);
            routeSet.clear();
        }
        if (value >= 'A' && value <= 'Z') {
            char i = (char) (value + ('a' - 'A'));
            if (!keySet.contains(i)) {
                return;
            }
        }
        if (keyNum == keySet.size()) {
            minStep = Math.min(step, minStep);
            return;
        }
        for (int[] ints : intss) {
            int newY = y + ints[0];
            int newX = x + ints[1];
            if (newY < 0 || newX < 0 || newY >= grids.length || newX >= grids[0].length) {
                continue;
            }
            String key = newY + "_" + newX;
            if (routeSet.contains(key)) {
                continue;
            }
            routeSet.add(key);
            jumpStep(grids, routeSet, keySet, keyNum, newY, newX, step + 1);
        }
    }
}