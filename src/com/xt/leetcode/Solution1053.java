package com.xt.leetcode;

/**
 * 1053. 交换一次的先前排列
 * 每日一题：2023.04.03
 * 完成日期：2023.04.03
 * 链接：https://leetcode.cn/problems/previous-permutation-with-one-swap/
 * 描述：
 * 给你一个正整数数组 arr（可能存在重复的元素），请你返回可在 一次交换（交换两数字 arr[i] 和 arr[j] 的位置）后得到的、按字典序排列小于 arr 的最大排列。
 *
 * 如果无法这么操作，就请返回原数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1]
 * 输出：[3,1,2]
 * 解释：交换 2 和 1
 * 示例 2：
 *
 * 输入：arr = [1,1,5]
 * 输出：[1,1,5]
 * 解释：已经是最小排列
 * 示例 3：
 *
 * 输入：arr = [1,9,4,6,7]
 * 输出：[1,7,4,6,9]
 * 解释：交换 9 和 7
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 104
 * 1 <= arr[i] <= 104
 *
 * 解题思路：
 * 使用优先解的策略。
 * 首先，替换的位置越靠后，字典序变动就越小。所以找到第一个arr[i]比arr[i + 1]小的数字进行替换。
 * 其次，两个交换的位置index和maxIndex，arr[maxIndex]的值越大，则字典序变动越小，所以找到index后小于arr[index]的最大值。
 * 最后，如果有多个这样的值，则取位置最靠前的。因为arr[index]>arr[maxIndex]，所以maxIndex的位置越靠前，交换后的值越大。
 *
 * <p>
 * state:done
 */
public class Solution1053 {

    public int[] prevPermOpt1(int[] arr) {
        int index = arr.length - 2;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                index = i;
                break;
            }
        }
        int maxIndex = 0;
        int max = 0;
        for (int i = arr.length - 1; i > index; i--) {
            int value = arr[i];
            if (value >= arr[index]) {
                continue;
            }
            if (value >= max) {
                maxIndex = i;
                max = value;
            }
        }
        int local = arr[index];
        arr[index] = arr[maxIndex];
        arr[maxIndex] = local;
        return arr;
    }
}