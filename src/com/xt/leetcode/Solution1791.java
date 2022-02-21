package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 1791. 找出星型图的中心节点
 * 日期：2022.2.18
 * 链接：https://leetcode-cn.com/problems/find-center-of-star-graph/
 * 描述
 * 有一个无向的 星型 图，由 n 个编号从 1 到 n 的节点组成。星型图有一个 中心 节点，并且恰有 n - 1 条边将中心节点与其他每个节点连接起来。
 * <p>
 * 给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间存在一条边。请你找出并返回 edges 所表示星型图的中心节点。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：edges = [[1,2],[2,3],[4,2]]
 * 输出：2
 * 解释：如上图所示，节点 2 与其他每个节点都相连，所以节点 2 是中心节点。
 * 示例 2：
 * <p>
 * 输入：edges = [[1,2],[5,1],[1,3],[1,4]]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * ui != vi
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-center-of-star-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 构建一个map，key表示数，value表示出现的次数。
 * 这边二维数组，统计每个数所出现的次数。
 * 最后编译一遍map如果有出现次数为size-1，则就是该数
 * <p>
 * <p>
 * state:done
 */
public class Solution1791 {

    public int findCenter(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] ints : edges) {
            Integer tims1 = map.getOrDefault(ints[0], 0);
            Integer tims2 = map.getOrDefault(ints[1], 0);
            map.put(ints[0], tims1 + 1);
            map.put(ints[1], tims2 + 1);
        }
        for (int i : map.keySet()) {
            if (map.get(i) == map.size() - 1) {
                return i;
            }
        }
        return 0;
    }
}