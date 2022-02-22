package com.xt.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 969. 煎饼排序
 * 日期：2022.2.19
 * 链接：https://leetcode-cn.com/problems/pancake-sorting/
 * 描述:
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 * <p>
 * 一次煎饼翻转的执行过程如下：
 * <p>
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 * <p>
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,4,1]
 * 输出：[4,2,4,3]
 * 解释：
 * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
 * 初始状态 arr = [3, 2, 4, 1]
 * 第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
 * 第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
 * 第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
 * 第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。
 * 示例 2：
 * <p>
 * 输入：[1,2,3]
 * 输出：[]
 * 解释：
 * 输入已经排序，因此不需要翻转任何内容。
 * 请注意，其他可能的答案，如 [3，3] ，也将被判断为正确。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= arr.length
 * arr 中的所有整数互不相同（即，arr 是从 1 到 arr.length 整数的一个排列）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pancake-sorting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 先对数组拷贝，然后排序。这样队尾的一定是最大的数。
 * 我们可以按照这样的顺序来排序，每次排序，把最大的那个排到队尾。这样经过N轮排序，就是一个有序的数组了。
 * 接下来就是如何把一个数排到队尾了，比如4,3,6,5,1,2。我们可以通过两次煎饼排序把6排到队尾。
 * 首先把6排到最前面：6,3,4,5,1,2
 * 然后把6排到最后面：2,1,5,4,3,6
 *
 * state:done
 */
public class Solution969 {

    public List<Integer> pancakeSort(int[] arr) {
        //最大值放到尾部的方案
        List<Integer> list = new ArrayList<>();
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        Arrays.sort(copy);

        for (int i = copy.length - 1; i >= 0; i--) {
            int max = copy[i];
            for (int k = 0; k <= i; k++) {
                int value = arr[k];
                if (max != value) {
                    continue;
                }
                if (k == i) {
                    break;
                }
                reverseInts(arr, k);
                list.add(k + 1);
                reverseInts(arr, i);
                list.add(i + 1);
                //改变数组
                break;
            }
        }
        return list;
    }

    private void reverseInts(int[] ints, int k) {
        int start = 0;
        int end = k;
        while (end > start) {
            int local = ints[start];
            ints[start] = ints[end];
            ints[end] = local;
            end--;
            start++;

        }
    }
}