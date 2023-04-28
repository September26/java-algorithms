package com.xt.leetcode;

/**
 * 1574. 删除最短的子数组使剩余数组有序
 * 每日一题：2023.03.28
 * 完成日期：2023.03.28
 * 链接：https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
 * 描述：
 * 给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
 * <p>
 * 一个子数组指的是原数组中连续的一个子序列。
 * <p>
 * 请你返回满足题目要求的最短子数组的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,3,10,4,2,3,5]
 * 输出：3
 * 解释：我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5] 。
 * 另一个正确的解为删除子数组 [3,10,4] 。
 * 示例 2：
 * <p>
 * 输入：arr = [5,4,3,2,1]
 * 输出：4
 * 解释：由于数组是严格递减的，我们只能保留一个元素。所以我们需要删除长度为 4 的子数组，要么删除 [5,4,3,2]，要么删除 [4,3,2,1]。
 * 示例 3：
 * <p>
 * 输入：arr = [1,2,3]
 * 输出：0
 * 解释：数组已经是非递减的了，我们不需要删除任何元素。
 * 示例 4：
 * <p>
 * 输入：arr = [1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * 0 <= arr[i] <= 10^9
 * <p>
 * 解题思路：
 * 把区域分成三块。
 * 第一块，从头开始算，非递减的区域。
 * 第二块，从尾部开始算，非递减的区域。
 * 第三块就是剩下的区域。
 * 因为第一块是非递减的，所以开始遍历，我们设定位置是i，然后找第二块区域大于等于这个i的第一个数，我们设其位置位j，则i和j之间就是要被减去的子序列。
 * 就这样遍历完成第一块区域，找到最小的子序列长度，和(总长度-第一块长度)以及(总长度-第二块长度)比较，找出最小值。
 * <p>
 * <p>
 * state:done
 */
public class Solution1574 {

    public int findLengthOfShortestSubarray(int[] arr) {
        int length = arr.length;
        int j = length - 1;
        while (j >= 1 && arr[j - 1] <= arr[j]) {
            j--;
        }
        //第一块的最小值。
        int start = 0;
        while (arr[start + 1] >= arr[start] && start < length - 2) {
            start++;
        }

        if (j == 0) {
            return 0;
        }
        //第二块的最小值。
        int abs = j;
        int i = 0;
        while (i < j && j <= length - 1) {
            if (arr[i] <= arr[j]) {
                abs = Math.min(abs, j - i - 1);
                if (arr[i] > arr[i + 1]) {
                    break;
                }
                i++;
                continue;
            }
            j++;
        }
        return Math.min(abs, length - start - 1);
    }
}