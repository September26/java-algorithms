package com.xt.leetcode;

import java.util.Vector;

/**
 * 1089. 复写零
 * 每日一题：2022.06.17
 * 完成日期：2022.06.17
 * 链接：https://leetcode.cn/problems/duplicate-zeros/
 * 描述：
 * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 *
 * 注意：请不要在超过该数组长度的位置写入元素。
 *
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,0,2,3,0,4,5,0]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 * 示例 2：
 *
 * 输入：[1,2,3]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/duplicate-zeros
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 复制一个数组，分别记录和写两个index，每次readIndex+1，如果(value == 0 && writeIndex < arr.length - 1)，则writeIndex+2,否则+1
 * <p>
 * state:done
 */
public class Solution1089 {

    public void duplicateZeros(int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        int writeIndex = 0;
        int readIndex = 0;
        while (writeIndex < arr.length) {
            int value = copy[readIndex++];
            if (value == 0 && writeIndex < arr.length - 1) {
                arr[writeIndex++] = 0;
                arr[writeIndex++] = 0;
            } else {
                arr[writeIndex++] = value;
            }
        }
    }
}