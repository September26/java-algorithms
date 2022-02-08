package com.xt.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给你一个由候选元素组成的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个元素在每个组合中只能使用 一次 。
 * <p>
 * 注意：解集不能包含重复的组合。 
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 先对数组排序，这样相同大小的数字一定是相邻的。
 * 然后使用递归的方式，每次选择一个数加入到集合当中。
 * 如果这个数如果和循环当中的上一个值相同，则跳过。
 * 如果加上这个数等于目标值，则加入到结果当中。
 * 如果加上这个数大于目标值，则也跳过。
 * <p>
 * <p>
 * state:done
 */
public class Solution40 {

    List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        addNum(candidates, 0, new ArrayList<>(), 0, target);
        return lists;
    }


    public void addNum(int[] candidates, int index, List<Integer> list, int sum, int target) {
        if (index >= candidates.length) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int value = candidates[i];
            List<Integer> newList = new ArrayList<>(list);
            newList.add(value);
            int newSUm = sum + value;
            if (i > index && value == candidates[i - 1]) {
                continue;
            }
            if (newSUm == target) {
                lists.add(newList);
                continue;
            }
            if (newSUm > target) {
                continue;
            }
            addNum(candidates, i + 1, newList, newSUm, target);
        }
    }
}