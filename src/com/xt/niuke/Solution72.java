package com.xt.niuke;


/**
 * 数字在升序数组中出现的次数
 * 描述
 * 给定一个长度为 n 的非降序数组和一个非负数整数 k ，要求统计 k 在数组中出现的次数
 * <p>
 * 数据范围：0≤n≤1000,0≤k≤100，数组中每个元素的值满足0≤val≤100
 * 要求：空间复杂度 O(1)，时间复杂度 O(logn)
 * 11/4
 */
public class Solution72 {
    public int GetNumberOfK(int[] array, int k) throws Exception {
        try {
            int binarysearchIndex = binarysearch(array, k);
            int num = 1;
            int middle = binarysearchIndex;
            while ((--middle >= 0) && array[middle] == k) {
                num++;
            }
            while ((++binarysearchIndex <= (array.length - 1)) && array[binarysearchIndex] == k) {
                num++;
            }
            return num;
        } catch (Exception e) {
        }

        return 0;
    }

    /**
     * 二分查找找位置
     */
    public int binarysearch(int[] array, int k) throws Exception {
        if (k < array[0] || k > array[array.length - 1]) {
            throw new Exception("error");
        }
        int start = 0;
        int end = array.length - 1;
        int middle = 0;
        while (true) {
            middle = (start + end) / 2;
            if (k == array[middle]) {
                break;
            }
            if (start == end) {
                throw new Exception("error");
            }
            if ((end - start) == 1) {
//                直接判断
                if (array[start] == k) {
                    return start;
                } else if (array[end] == k) {
                    return end;
                } else {
                    throw new Exception("error");
                }
            }
            if (array[middle] > k) {
                end = middle;
            } else {
                start = middle;
            }
        }
        return middle;
    }
}
