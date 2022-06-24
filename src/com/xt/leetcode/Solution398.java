package com.xt.leetcode;

import java.util.*;

/**
 * 398. 随机数索引
 * 每日一题：2022.04.25
 * 完成日期：2022.04.25
 * 链接：https://leetcode-cn.com/problems/random-pick-index/
 * 描述：
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 *
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 *
 * 示例:
 *
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 *
 * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
 * solution.pick(3);
 *
 * // pick(1) 应该返回 0。因为只有nums[0]等于1。
 * solution.pick(1);
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 用map的key装载每一个值，value为其index值的集合。
 * pick时，取List然后随机取其中的值即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution398 {

    Map<Integer, List<Integer>> map = new HashMap<>();
    Random random = new Random();

    public Solution398(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.get(nums[i]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(nums[i], list);
            }
            list.add(i);
        }
    }

    public int pick(int target) {
        List<Integer> integers = map.get(target);
        int index = random.nextInt(integers.size());
        return integers.get(index);
    }
}