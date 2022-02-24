package com.xt.leetcode;

/**
 * 1706.球会落何处
 * 日期：2022.2.24
 * 链接：https://leetcode-cn.com/problems/where-will-the-ball-fall/
 * 描述:
 * 用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
 * <p>
 * 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
 * <p>
 * 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
 * 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
 * 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
 * <p>
 * 返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
 * 输出：[1,-1,-1,-1,-1]
 * 解释：示例如图：
 * b0 球开始放在第 0 列上，最终从箱子底部第 1 列掉出。
 * b1 球开始放在第 1 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
 * b2 球开始放在第 2 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
 * b3 球开始放在第 3 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
 * b4 球开始放在第 4 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
 * 示例 2：
 * <p>
 * 输入：grid = [[-1]]
 * 输出：[-1]
 * 解释：球被卡在箱子左侧边上。
 * 示例 3：
 * <p>
 * 输入：grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 * 输出：[0,1,2,3,4,-1]
 *  
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] 为 1 或 -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/where-will-the-ball-fall
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 1.构造一个数组result，index代表其出发点的位置。value代表当前其位置。
 * 2.然后遍历n轮，每轮都会第一步的数组进行遍历。
 * 3.对数组result遍历的时候，获取当前层级的currentIndex值，如果当前值为-1，则跳过，因为代表已经被拦截了没有进入下一层。
 * 4.如果当前值>0，则求其对应的currentIndex值在坐标系中的值value，value==1时，currentIndex == m-1或者levels[currentIndex + 1] == -1都进入不到下一层。
 * 5.同理value==-1时也是相似的逻辑
 * 6.遍历完返回reuslt即可
 * <p>
 * <p>
 * state:done
 */
public class Solution1706 {

    public int[] findBall(int[][] grid) {
        int m = grid[0].length;
        int n = grid.length;
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            result[i] = i;
        }

        for (int level = 0; level < n; level++) {
            int[] levels = grid[level];
            System.out.println(result.length);
            for (int i = 0; i < result.length; i++) {
                int currentIndex = result[i];
                if (currentIndex == -1) {
                    continue;
                }
                int value = levels[currentIndex];
                if (value == 1) {
                    if (currentIndex == m - 1) {
                        result[i] = -1;
                        continue;
                    }
                    if (levels[currentIndex + 1] == -1) {
                        result[i] = -1;
                        continue;
                    }
                    result[i]++;
                    continue;
                }
                if (currentIndex == 0) {
                    result[i] = -1;
                    continue;
                }
                if (levels[currentIndex - 1] == 1) {
                    result[i] = -1;
                    continue;
                }
                result[i]--;
            }
        }
        return result;
    }

}