package com.xt.leetcode;


import java.util.*;

/**
 * 1001. 网格照明
 * 日期：2022.2.8
 * 描述
 * 在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态。
 * <p>
 * 给你一个由灯的位置组成的二维数组 lamps ，其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 的灯。即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态。
 * <p>
 * 当一盏灯处于打开状态，它将会照亮 自身所在单元格 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格 。
 * <p>
 * 另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj] 。对于第 j 个查询，如果单元格 [rowj, colj] 是被照亮的，则查询结果为 1 ，否则为 0 。在第 j 次查询之后 [按照查询的顺序] ，关闭 位于单元格 grid[rowj][colj] 上及相邻 8 个方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯。
 * <p>
 * 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果，1 表示照亮，0 表示未照亮。
 * <p>
 * 示例 1：
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
 * 输出：[1,0]
 * 解释：最初所有灯都是关闭的。在执行查询之前，打开位于 [0, 0] 和 [4, 4] 的灯。第 0 次查询检查 grid[1][1] 是否被照亮（蓝色方框）。该单元格被照亮，所以 ans[0] = 1 。然后，关闭红色方框中的所有灯。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
 * 输出：[1,1]
 * 示例 3：
 * <p>
 * 输入：n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
 * 输出：[1,1,0]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^9
 * 0 <= lamps.length <= 20000
 * 0 <= queries.length <= 20000
 * lamps[i].length == 2
 * 0 <= rowi, coli < n
 * queries[j].length == 2
 * 0 <= rowj, colj < n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/grid-illumination
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 这题看一下数据范围，n<10^9，很大所以不可能用数组来表示。lamps.length<=20000，说明时间复杂度必须是O(N)或者O(N*logN)级别的。
 * 所以我的解题方向就是找一个数据结构记录每一个lamp的点，每次查询之后针对这个数据结构进行依次O(1)级别的操作。O(1)级别的操作的数据结构那肯定就是map或者set了。
 * 我的解题思路是构建4个Map分别缓存x轴，y轴，右下-左上方向的轴，左下-右上方向的轴，这四条轴上的所有lamp点。
 * x和y轴的key值很简单，直接x，y值即可。但是斜向的两个轴，我选择的key值是映射到边界的值。比如右下-左上方向的轴上的一个点为(1,3)，那么映射到边界上为(0,2)。则key为0_2。
 * value值为set，记录这条轴上所有的lamp点。
 * 遍历queries时，取其x,y值，分别查询对应的四条轴上是否被照到，如果照到，则熄灭附近加自身共9盏灯。对应的对四个map也进行相应的处理。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1001 {
    Set<String> pointSet = new HashSet<>();
    Map<String, Set<String>> xMap = new HashMap<>();
    Map<String, Set<String>> yMap = new HashMap<>();
    Map<String, Set<String>> rightUpMap = new HashMap<>();
    Map<String, Set<String>> leftUpMap = new HashMap<>();

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int[] result = new int[queries.length];
        for (int i = 0; i < lamps.length; i++) {
            int[] lamp = lamps[i];
            int x = lamp[0];
            int y = lamp[1];
            String xs = String.valueOf(x);
            String ys = String.valueOf(y);
            String rightUpKey = getRightUpKey(x, y, n);
            String leftUpKey = getLeftUpKey(x, y, n);
            Set<String> xSet = create(xMap, xs);
            Set<String> ySet = create(yMap, ys);
            Set<String> rightUpSet = create(rightUpMap, rightUpKey);
            Set<String> leftUpSet = create(leftUpMap, leftUpKey);
            String key = lamp[0] + "_" + lamp[1];
            xSet.add(key);
            ySet.add(key);
            rightUpSet.add(key);
            leftUpSet.add(key);
            pointSet.add(x + "_" + y);
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int x = query[0];
            int y = query[1];
            String xS = String.valueOf(x);
            String yS = String.valueOf(y);

            boolean isBright = false;
            //查询x轴
            Set<String> xSet = xMap.get(xS);
            if (xSet != null && xSet.size() > 0) {
                isBright = true;
            }

            //查询y轴
            Set<String> ySet = yMap.get(yS);
            if (ySet != null && ySet.size() > 0) {
                isBright = true;
            }

            //查询左上
            String leftUpKey = getLeftUpKey(x, y, n);
            Set<String> leftUpSet = leftUpMap.get(leftUpKey);
            if (leftUpSet != null && leftUpSet.size() > 0) {
                isBright = true;
            }

            //查询右上
            String rightUpKey = getRightUpKey(x, y, n);
            Set<String> rightUpSet = rightUpMap.get(rightUpKey);
            if (rightUpSet != null && rightUpSet.size() > 0) {
                isBright = true;
            }

            if (isBright) {
                //记录
                result[i] = 1;
                //关闭周围的灯，对应的修改map
                checkRemove(x, y, n);
            }
        }
        return result;
    }

    private Set<String> create(Map<String, Set<String>> map, String key) {
        Set<String> set = map.get(key);
        if (set == null) {
            set = new HashSet<>();
            map.put(key, set);
        }
        return set;
    }

    private void checkRemove(int x, int y, int n) {

        for (int x1 = x - 1; x1 <= x + 1; x1++) {
            for (int y1 = y - 1; y1 <= y + 1; y1++) {
                String x1S = String.valueOf(x1);
                String y1S = String.valueOf(y1);
                if (x1 < 0 || y1 < 0) {
                    continue;
                }
                //查看是否在集合中
                String pointKey = x1 + "_" + y1;
                if (!pointSet.contains(pointKey)) {
                    continue;
                }
                remove(xMap, x1S, pointKey);
                remove(yMap, y1S, pointKey);
                remove(leftUpMap, getLeftUpKey(x1, y1, n), pointKey);
                remove(rightUpMap, getRightUpKey(x1, y1, n), pointKey);
            }
        }
    }

    private void remove(Map<String, Set<String>> map, String setKey, String pointKey) {
        Set<String> set = map.get(setKey);
        if (set != null) {
            set.remove(pointKey);
            if (set.size() == 0) {
                rightUpMap.remove(setKey);
            }
        }
    }

    private String getRightUpKey(int x, int y, int n) {
        int sum = x + y;
        if (sum <= n) {
            return "0_" + (y + x);
        }
        return (n - 1) + "_" + (y - (n - 1) + x);
    }

    private String getLeftUpKey(int x, int y, int n) {
        if (y >= x) {
            return "0_" + (y - x);
        }
        return (x - y) + "_0";
    }
}