package com.xt.niuke;


import java.util.ArrayList;


/**
 * 描述
 * 输入一个递增排序的数组array和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，返回任意一组即可，如果无法找出这样的数字，返回一个空数组即可。
 * <p>
 * 数据范围:
 * 0<=len(array)<=10的5次方
 * 1<=array[i]<=10的6次方
 * 示例1
 * 输入：
 * [1,2,4,7,11,15],15
 * 复制
 * 返回值：
 * [4,11]
 * 复制
 * 说明：
 * 返回[4,11]或者[11,4]都是可以的
 * 示例2
 * 输入：
 * [1,5,11],10
 * 复制
 * 返回值：
 * []
 * 复制
 * 说明：
 * 不存在，返回空数组
 * 示例3
 * 输入：
 * [1,2,3,4],5
 * 复制
 * 返回值：
 * [1,4]
 * 复制
 * 说明：
 * 返回[1,4],[4,1],[2,3],[3,2]都是可以的
 */
public class Solution57 {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> integers = new ArrayList<>();
        int startIndex = 0;
        int endIndex = array.length - 1;
        while (startIndex < endIndex) {
            int expectValue = sum - array[startIndex];
            int endValue = array[endIndex];
            if (expectValue == endValue) {
                integers.add(array[startIndex]);
                integers.add(endValue);
                return integers;
            }
            if (expectValue < endValue) {
                endIndex--;
            } else {
                startIndex++;
            }
        }
        return integers;
    }
}
