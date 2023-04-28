package com.xt.util;

import com.xt.leetcode.Solution2034;

import java.util.List;

/**
 * 算法helper
 */
public class AlgorithmHelper {


    //二分查找插入法
    public static void middleInsert(List<Integer> list, int value) {
        if (list.size() == 0) {
            list.add(value);
            return;
        }
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            int middle = (start + end) / 2;
            int middleValue = list.get(middle);
            if (value > middleValue) {
                start = middle + 1;
                continue;
            }
            if (value < middleValue) {
                end = middle;
                continue;
            }
        }
        list.add(start, value);
    }

    //取模运算
    public static int ramainder(int dividend, int dividor) {
        return dividend - dividend / dividor * dividor;
    }

    public static int binarySearchForIndex(int[] array, int target) throws Exception {
        return binarySearchForIndex(array, target, 0, array.length - 1, false);
    }


    public static int binarySearchForIndex(int[] array, int target, boolean vague) {
        return binarySearchForIndex(array, target, 0, array.length - 1, vague);
    }

    /**
     * 正序
     *
     * @param array
     * @param target
     * @param start
     * @param end
     * @param vague
     * @return
     */
    public static int binarySearchForIndex(int[] array, int target, int start, int end, boolean vague) {
        if (target < array[0]) {
            if (vague) {
                return 0;
            }
            return -1;
        }
        if (target > array[array.length - 1]) {
            if (vague) {
                return array.length - 1;
            }
            return -1;
        }

        int middle = 0;
        while (true) {
            middle = (start + end) / 2;
            if (target == array[middle]) {
                break;
            }
            if (start == end) {
                //找不到时的处理逻辑
                if (vague) {
                    return start;
                }
                return -1;
            }
            if ((end - start) == 1) {
//                直接判断
                if (array[start] == target) {
                    return start;
                } else if (array[end] == target) {
                    return end;
                } else {
                    if (vague) {
                        return start;
                    }
                    return -1;
                }
            }
            if (array[middle] > target) {
                end = middle;
            } else {
                start = middle;
            }
        }
        return middle;
    }

    public static int binarySearchForIndexByReverse(int[] array, int target) throws Exception {
        return binarySearchForIndex(array, target, 0, array.length - 1, false);
    }


    public static int binarySearchForIndexByReverse(int[] array, int target, boolean vague) {
        return binarySearchForIndex(array, target, 0, array.length - 1, vague);
    }

    /**
     * 正序
     *
     * @param array
     * @param target
     * @param start
     * @param end
     * @param vague
     * @return
     */
    public static int binarySearchForIndexByReverse(int[] array, int target, int start, int end, boolean vague) {
        if (target > array[0]) {
            if (vague) {
                return 0;
            }
            return -1;
        }
        if (target < array[array.length - 1]) {
            if (vague) {
                return array.length - 1;
            }
            return -1;
        }

        int middle = 0;
        while (true) {
            middle = (start + end) / 2;
            if (target == array[middle]) {
                break;
            }
            if (start == end) {
                //找不到时的处理逻辑
                if (vague) {
                    return start;
                }
                return -1;
            }
            if ((end - start) == 1) {
//                直接判断
                if (array[start] == target) {
                    return start;
                } else if (array[end] == target) {
                    return end;
                } else {
                    if (vague) {
                        return start;
                    }
                    return -1;
                }
            }
            if (array[middle] < target) {
                end = middle;
            } else {
                start = middle;
            }
        }
        return middle;
    }
}
