package com.xt.leetcode;

import java.util.HashMap;
import java.util.Vector;

/**
 * 1460. 通过翻转子数组使两个数组相等
 * 每日一题：2022.08.24
 * 完成日期：2022.08.24
 * 链接：https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays/
 * 描述：
 * 给你两个长度相同的整数数组 target 和 arr 。每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
 * <p>
 * 如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = [1,2,3,4], arr = [2,4,1,3]
 * 输出：true
 * 解释：你可以按照如下步骤使 arr 变成 target：
 * 1- 翻转子数组 [2,4,1] ，arr 变成 [1,4,2,3]
 * 2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3]
 * 3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
 * 上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。
 * 示例 2：
 * <p>
 * 输入：target = [7], arr = [7]
 * 输出：true
 * 解释：arr 不需要做任何翻转已经与 target 相等。
 * 示例 3：
 * <p>
 * 输入：target = [3,7,9], arr = [3,7,11]
 * 输出：false
 * 解释：arr 没有数字 9 ，所以无论如何也无法变成 target 。
 *  
 * <p>
 * 提示：
 * <p>
 * target.length == arr.length
 * 1 <= target.length <= 1000
 * 1 <= target[i] <= 1000
 * 1 <= arr[i] <= 1000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 只要可以两两翻转，那么数组中任何位置的两个数字一定能实现较换位置。
 * 所以，这题我们只要判断target中和aar中数字的数量是否相等即可。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1460 {

    public boolean canBeEqual(int[] target, int[] arr) {
        HashMap<Integer, Integer> targetMap = new HashMap<>();
        HashMap<Integer, Integer> arrMap = new HashMap<>();

        for (int i = 0; i < target.length; i++) {
            int value = target[i];
            Integer num = targetMap.getOrDefault(value, 0);
            targetMap.put(value, num + 1);

            int value2 = arr[i];
            Integer num2 = arrMap.getOrDefault(value2, 0);
            arrMap.put(value2, num2 + 1);
        }

        if (targetMap.size() != arrMap.size()) {
            return false;
        }

        for (int i : targetMap.keySet()) {
            Integer num1 = targetMap.getOrDefault(i, 0);
            Integer num2 = arrMap.getOrDefault(i, 0);
            if (num1 != num2) {
                return false;
            }
        }

        return true;
    }
}