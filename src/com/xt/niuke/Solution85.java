package com.xt.niuke;


/**
 * 连续子数组的最大和(二)
 * 描述
 * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，找到一个具有最大和的连续子数组。
 * 1.子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
 * 2.如果存在多个最大和的连续子数组，那么返回其中长度最长的，该题数据保证这个最长的只存在一个
 * 3.该题定义的子数组的最小长度为1，不存在为空的子数组，即不存在[]是某个数组的子数组
 * 4.返回的数组不计入空间复杂度计算
 * <p>
 * 数据范围:
 * 1<=n<=10^51<=n<=10
 * 5
 * <p>
 * -100 <= a[i] <= 100−100<=a[i]<=100
 * <p>
 * 要求:时间复杂度O(n)，空间复杂度O(n)
 * 进阶:时间复杂度O(n)，空间复杂度O(1)
 * done
 */
public class Solution85 {
    //2356 13956
    public int[] FindGreatestSumOfSubArray(int[] array) {
        int max = -101;
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        if (max < 0) {
            return new int[]{max};
        }


        int[] maxRnage = new int[2];
        int lastMaxSum = 0;

        int start = 0;
        int end = 0;
        int sum = 0;

        //还差一个场景就是后面的sum值相同时长度前面的长
        while (end < array.length) {
            int current = array[end];
            if (current > 0) {
                sum += current;
                //更新最大值
                end++;
                if (sum > lastMaxSum) {
                    lastMaxSum = sum;
                    maxRnage[0] = start;
                    maxRnage[1] = end;
                } else if (sum == lastMaxSum) {
                    //判断长度
                    if ((maxRnage[1] - maxRnage[0]) < (end - start)) {
                        //更新位置
                        maxRnage[0] = start;
                        maxRnage[1] = end;
                    }
                }
            } else if (current < 0) {
                sum += current;
                //全部清空
                end++;
                if (sum < 0) {
                    start = end;
                    sum = 0;
                }
            } else {
                //判断长度
                if ((maxRnage[1] - maxRnage[0]) <= (end + 1 - start) && sum == lastMaxSum) {
                    //更新位置
                    maxRnage[0] = start;
                    maxRnage[1] = end + 1;
                } else if (sum <= 0) {
                    start = end;
                }
                end++;
            }
        }
        start = maxRnage[0];
        end = maxRnage[1];
        int[] ints = new int[end - start];
        System.arraycopy(array, start, ints, 0, end - start);
        return ints;
    }

}
