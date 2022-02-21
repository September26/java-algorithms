package com.xt.leetcode;

import java.util.*;

/**
 * 1688.比赛中的配对次数
 * 日期：2022.2.16
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-reconstruct-a-tree/
 * 描述
 * 给你一个数组 pairs ，其中 pairs[i] = [xi, yi] ，并且满足：
 * <p>
 * pairs 中没有重复元素
 * xi < yi
 * 令 ways 为满足下面条件的有根树的方案数：
 * <p>
 * 树所包含的所有节点值都在 pairs 中。
 * 一个数对 [xi, yi] 出现在 pairs 中 当且仅当 xi 是 yi 的祖先或者 yi 是 xi 的祖先。
 * 注意：构造出来的树不一定是二叉树。
 * 两棵树被视为不同的方案当存在至少一个节点在两棵树中有不同的父节点。
 * <p>
 * 请你返回：
 * <p>
 * 如果 ways == 0 ，返回 0 。
 * 如果 ways == 1 ，返回 1 。
 * 如果 ways > 1 ，返回 2 。
 * 一棵 有根树 指的是只有一个根节点的树，所有边都是从根往外的方向。
 * <p>
 * 我们称从根到一个节点路径上的任意一个节点（除去节点本身）都是该节点的 祖先 。根节点没有祖先。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：pairs = [[1,2],[2,3]]
 * 输出：1
 * 解释：如上图所示，有且只有一个符合规定的有根树。
 * 示例 2：
 * <p>
 * <p>
 * 输入：pairs = [[1,2],[2,3],[1,3]]
 * 输出：2
 * 解释：有多个符合规定的有根树，其中三个如上图所示。
 * 示例 3：
 * <p>
 * 输入：pairs = [[1,2],[2,3],[2,4],[1,5]]
 * 输出：0
 * 解释：没有符合规定的有根树。
 *  
 * 提示：
 * <p>
 * 1 <= pairs.length <= 10^5
 * 1 <= xi < yi <= 500
 * pairs 中的元素互不相同。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-reconstruct-a-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 *
 * <p>
 * <p>
 * state:
 */
public class Solution1719 {

    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int[] pair : pairs) {
            int i1 = pair[0];
            int i2 = pair[0];
            Set<Integer> integers1 = map.computeIfAbsent(i1, k -> new HashSet<>());
            integers1.add(i2);
            Set<Integer> integers2 = map.computeIfAbsent(i1, k -> new HashSet<>());
            integers2.add(i1);

            set.add(i1);
            set.add(i2);
        }

        int num = 0;
        for (int root : set) {
            //root为根节点
            if (num >= 2) {
                break;
            }

            //判断root为根节点时，是否是有根树

            Set<Integer> integers = map.get(root);

            num++;
        }


        return num;
    }
}