package com.xt.leetcode;

import java.util.*;

/**
 * 1282. 用户分组
 * 每日一题：2022.08.12
 * 完成日期：2022.08.12
 * 链接：https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/
 * 描述：
 * 有 n 个人被分成数量未知的组。每个人都被标记为一个从 0 到 n - 1 的唯一ID 。
 * <p>
 * 给定一个整数数组 groupSizes ，其中 groupSizes[i] 是第 i 个人所在的组的大小。例如，如果 groupSizes[1] = 3 ，则第 1 个人必须位于大小为 3 的组中。
 * <p>
 * 返回一个组列表，使每个人 i 都在一个大小为 groupSizes[i] 的组中。
 * <p>
 * 每个人应该 恰好只 出现在 一个组 中，并且每个人必须在一个组中。如果有多个答案，返回其中 任何 一个。可以 保证 给定输入 至少有一个 有效的解。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：groupSizes = [3,3,3,3,3,1,3]
 * 输出：[[5],[0,1,2],[3,4,6]]
 * 解释：
 * 第一组是 [5]，大小为 1，groupSizes[5] = 1。
 * 第二组是 [0,1,2]，大小为 3，groupSizes[0] = groupSizes[1] = groupSizes[2] = 3。
 * 第三组是 [3,4,6]，大小为 3，groupSizes[3] = groupSizes[4] = groupSizes[6] = 3。
 * 其他可能的解决方案有 [[2,1,6],[5],[0,4,3]] 和 [[5],[0,6,2],[4,3,1]]。
 * 示例 2：
 * <p>
 * 输入：groupSizes = [2,1,3,3,3,2]
 * 输出：[[1],[0,5],[2,3,4]]
 *  
 * <p>
 * 提示：
 * <p>
 * groupSizes.length == n
 * 1 <= n <= 500
 * 1 <= groupSizes[i] <= n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 按照groupSizes的值为key加入map的list中。
 * 然后遍历map,以对应的key为长度分组即可。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1282 {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int value = groupSizes[i];
            List<Integer> integers = map.get(value);
            if (integers == null) {
                integers = new ArrayList<>();
                map.put(value, integers);
            }
            integers.add(i);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (Integer key : map.keySet()) {
            List<Integer> integers = map.get(key);
            List<Integer> currentList = new ArrayList<>();
            for (int i = 0; i < integers.size(); i++) {
                if (i % key == 0) {
                    currentList = new ArrayList<>();
                    result.add(currentList);
                }
                currentList.add(integers.get(i));
            }
        }
        return result;
    }
}