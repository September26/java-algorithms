package com.xt.util;

import com.xt.leetcode.Solution1792;

import java.util.List;

/**
 * 二分工具类
 * 提供：
 * 二分查找，二分插入等功能
 */
public class BinaryHelper {


    /**
     * list必须为有序集合。
     * 二分查找，如果由若干个值和目标值相等，则返回第一个。
     * 如果找不到，小于最小值则返回第0位，大于集合最大值则返回size位。
     *
     * @param list
     * @param target
     * @return
     */
    public static int binarySearch(List<ModelIntBase> list, ModelIntBase target) {
        if (target.value < list.get(0).value) {
            return 0;
        }
        int size = list.size();
        if (target.value > list.get(size - 1).value) {
            return size;
        }
        int start = 0;
        int end = size - 1;
        int result = end;
        do {
            int middle = (start + end) / 2;
            if (list.get(middle).value >= target.value) {
                result = middle;
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        } while (start <= end);

        return result;
    }


    public static class ModelIntBase {
        int value;

        public ModelIntBase(int value) {
            this.value = value;
        }
    }

    public static class ModelDouBase {
        double value;
    }
}
