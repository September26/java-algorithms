package com.xt.util;

/**
 * 算法helper
 */
public class AlgorithmHelper {

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
