package com.xt.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

/**
 * 1042. 不邻接植花
 * 每日一题：2023.04.20
 * 完成日期：2023.04.20
 * 链接：https://leetcode.cn/problems/flower-planting-with-no-adjacent/
 * 描述：
 * 有 n 个花园，按从 1 到 n 标记。另有数组 paths ，其中 paths[i] = [xi, yi] 描述了花园 xi 到花园 yi 的双向路径。在每个花园中，你打算种下四种花之一。
 * <p>
 * 另外，所有花园 最多 有 3 条路径可以进入或离开.
 * <p>
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 * <p>
 * 以数组形式返回 任一 可行的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1、2、3、4 表示。保证存在答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 * 解释：
 * 花园 1 和 2 花的种类不同。
 * 花园 2 和 3 花的种类不同。
 * 花园 3 和 1 花的种类不同。
 * 因此，[1,2,3] 是一个满足题意的答案。其他满足题意的答案有 [1,2,4]、[1,4,2] 和 [3,2,1]
 * 示例 2：
 * <p>
 * 输入：n = 4, paths = [[1,2],[3,4]]
 * 输出：[1,2,1,2]
 * 示例 3：
 * <p>
 * 输入：n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * 输出：[1,2,3,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 104
 * 0 <= paths.length <= 2 * 104
 * paths[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * 每个花园 最多 有 3 条路径可以进入或离开
 * <p>
 * 解题思路：
 * 构建数组类型的List，List中的数据代表与当前花园相斥的花园。
 * 构建三个set，代表存放花种类1，2，3。
 * 然后遍历n，取出List中的数据，如果数据不和1冲突，则放入1，同理，依次尝试2，3，4。最终放入不冲突的花园。
 * 放入花园时记录到result中，最终返回result即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution1042 {

    public int[] gardenNoAdj(int n, int[][] paths) {
        ArrayList<Integer>[] arrayLists = new ArrayList[n + 1];
        for (int i = 0; i < arrayLists.length; i++) {
            arrayLists[i] = new ArrayList<>();
        }
        for (int[] ints : paths) {
            int a = ints[0];
            int b = ints[1];
            arrayLists[a].add(b);
            arrayLists[b].add(a);
        }

        int[] result = new int[n];
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        HashSet<Integer> set3 = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            ArrayList<Integer> arrayList = arrayLists[i];
            if (tryAdd(set1, arrayList, i)) {
                result[i - 1] = 1;
                continue;
            }
            if (tryAdd(set2, arrayList, i)) {
                result[i - 1] = 2;
                continue;
            }
            if (tryAdd(set3, arrayList, i)) {
                result[i - 1] = 3;
                continue;
            }
            result[i - 1] = 4;
//            set4.add(i);
        }
        return result;
    }

    private boolean tryAdd(HashSet<Integer> set, ArrayList<Integer> arrayList, int currentValue) {
        for (int value : arrayList) {
            if (set.contains(value)) {
                return false;
            }
        }
        set.add(currentValue);
        return true;
    }
}