package com.xt.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 39. 组合总和
 * <p>
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 * <p>
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 * <p>
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 示例 4：
 * <p>
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * 示例 5：
 * <p>
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都 互不相同
 * 1 <= target <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 递归的思路，
 * 递归方法中，接受参数为数组，当前数的位置，之前已经选择的数的结合，之前选择的数的总和，目标值
 * 比如2,3,6,7，target=7，那么分别取0,1,2,3个2，分别求出sum值带入下一个递归循环，sum值分别为0，2，4，6。
 * 这样递归传递下去，如果sum=target则把集合加入到lists中。
 * <p>
 * state:done
 */
public class Solution39 {

    List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        addNum(candidates, 0, new ArrayList<>(), 0, target);
        return lists;
    }

    public void addNum(int[] candidates, int index, List<Integer> list, int sum, int target) {
        if (index >= candidates.length) {
            return;
        }
        int value = candidates[index];
        int times = 0;
        int newSUm = 0;
        List<Integer> newList = new ArrayList<>(list);
        while (true) {
            newSUm = sum + times * value;
            if (times > 0) {
                newList.add(value);
            }
            if (newSUm == target) {
                lists.add(newList);
                break;
            }
            if (newSUm > target) {
                break;
            }
            addNum(candidates, index + 1, newList, newSUm, target);
            times++;
        }
    }
}