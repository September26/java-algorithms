package com.xt.niuke;


import java.util.HashMap;

/**
 * 构建乘积数组
 * 描述
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 * 对于A长度为1的情况，B无意义，故而无法构建，因此该情况不会存在。
 * <p>
 * 示例1
 * 输入：
 * [1,2,3,4,5]
 * 返回值：
 * [120,60,40,30,24]
 * 10月29
 * done
 */
public class Solution66 {

    HashMap<Integer, Integer> startCacheMap = new HashMap<>();
    HashMap<Integer, Integer> endCacheMap = new HashMap<>();

    public int[] multiply(int[] A) {
        int n = A.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            Integer startValue = startCacheMap.get(i - 1);
            if (startValue == null) {
                startValue = getMuleValue(true, i - 1, A);
            }
            Integer endValue = endCacheMap.get(i + 1);
            if (endValue == null) {
                endValue = getMuleValue(false, i + 1, A);
            }
            result[i] = startValue * endValue;
        }
        return result;
    }

    private int getMuleValue(boolean isStart, int k, int[] ints) {
        int result = 1;
        if (isStart) {
            for (int i = 0; i <= k; i++) {
                result = result * ints[i];
            }
        } else {
            for (int i = k; i < ints.length; i++) {
                result = result * ints[i];
            }
        }
        return result;

    }
}
