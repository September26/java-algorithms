package com.xt.niuke;


import java.util.ArrayList;

/**
 * 描述
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列?
 * <p>
 * 数据范围：0<n≤100
 * 进阶：时间复杂度 O(n)
 * 返回值描述：
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 * <p>
 * 输入：
 * 9
 * 返回值：
 * [[2,3,4],[4,5]]
 * <p>
 * 快慢指针的方式
 */
public class Solution74 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        int start = 0;
        int end = 1;
        int currentSum = start + end;
        while (end <= (sum / 2) + 1) {
            if (currentSum == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                     list.add(i);
                }
                lists.add(list);
                start = start + 2;
                end++;
                currentSum = currentSum - start * 2 + 3;
                currentSum = currentSum + end;
            } else if (currentSum > sum) {
                currentSum -= start;
                start++;
            } else {
                end++;
                currentSum += end;
            }
        }
        return lists;
    }

    public ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        //至少包含两个，则从sum/2开始找
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (sum <= 1) {
            return lists;
        }
        int i = 0;
        while (i <= (sum / 2 + 1)) {
            recursionFind(lists, new ArrayList<>(), sum, i);
            i++;
        }
        return lists;
    }

    public void recursionFind(ArrayList<ArrayList<Integer>> lists, ArrayList<Integer> list, int currentSum,
                              int currentValue) {
        //至少包含两个，则从sum/2开始找
        if (currentValue == 0) {
            return;
        }
        if (currentValue > currentSum) {
            return;
        }
        if (currentValue == currentSum) {
            list.add(currentValue);
            lists.add(list);
            return;
        }
        list.add(currentValue);
        recursionFind(lists, list, currentSum - currentValue, ++currentValue);
    }

}
