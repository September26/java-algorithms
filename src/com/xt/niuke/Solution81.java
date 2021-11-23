package com.xt.niuke;


/**
 * 调整数组顺序使奇数位于偶数前面(二)
 * 输入一个长度为 n 整数数组，数组里面可能含有相同的元素，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前面部分，所有的偶数位于数组的后面部分，对奇数和奇数，偶数和偶数之间的相对位置不做要求，但是时间复杂度和空间复杂度必须如下要求。
 * <p>
 * 数据范围：0 \le n \le 500000≤n≤50000，数组中每个数的值 0 \le val \le 100000≤val≤10000
 * 要求：时间复杂度 O(n)，空间复杂度 O(1)
 * done
 */
public class Solution81 {

    /**
     * 双指针，一个从头，一个从末尾开始
     *
     * @param array
     * @return
     */
    public int[] reOrderArrayTwo(int[] array) {
        int start = 0;
        int end = array.length - 1;

        boolean isStart = true;
        while (start < end) {
            if (isStart) {
                if (array[start] % 2 == 0) {
                    //调整顺序
                    isStart = false;
                } else {
                    start++;
                }
            } else {
                if (array[end] % 2 != 0) {
                    //调整顺序
                    isStart = true;//并且交换位置
                    int endValue = array[end];
                    array[end] = array[start];
                    array[start] = endValue;
                }
                end--;
            }
        }
        return array;
    }
}
