package com.xt.leetcode;

import java.util.*;

/**
 * 18. 四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * O(n3)
 * 解题思路：
 * 想了一个O(n3)复杂度的。
 * 首先对数据排序，然后计算数组中某个数最后一次出现的位置，用map缓存。key为这个数的值，value为其位置。
 * 然后开始三重for循环累加，则可以求出i1+i2+i3的和，这时候从map查询，看map中是否存在target-sum的。如果存在，在判断当前index是否大于i3即可。
 * 当然，为了避免重复计算，所以同一层循环当中，如果当前值和前一个值相同，则要跳过本次循环。
 * <p>
 * state:done
 */
public class Solution18 {


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            cache.put(value, i);
        }

        //构建一个map，减少一层嵌套
        int currentSum = 0;
        for (int i1 = 0; i1 < nums.length; i1++) {
            if (i1 > 0 && nums[i1] == nums[i1 - 1]) {
                continue;
            }
            currentSum += nums[i1];
            for (int i2 = i1 + 1; i2 < nums.length; i2++) {
                if (i2 > i1 + 1 && nums[i2] == nums[i2 - 1]) {
                    continue;
                }
                currentSum += nums[i2];
                for (int i3 = i2 + 1; i3 < nums.length; i3++) {
                    if (i3 > i2 + 1 && nums[i3] == nums[i3 - 1]) {
                        continue;
                    }
                    currentSum += nums[i3];
                    Integer index = cache.get(target - currentSum);
                    if (index != null && index > i3) {
                        add(list, nums[i1], nums[i2], nums[i3], nums[index]);
                    }
                    currentSum -= nums[i3];
                }
                currentSum -= nums[i2];
            }
            currentSum -= nums[i1];
        }
        return list;
    }

    private void add(List<List<Integer>> list, int i1, int i2, int i3, int i4) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(i1);
        integers.add(i2);
        integers.add(i3);
        integers.add(i4);
        list.add(integers);
    }
}
