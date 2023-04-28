package com.xt.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * 1200. 最小绝对差
 * 每日一题：2022.07.04
 * 完成日期：2022.07.04
 * 链接：https://leetcode.cn/problems/minimum-absolute-difference/
 * 描述：
 * 给你个整数数组 arr，其中每个元素都 不相同。
 * <p>
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 * 示例 2：
 * <p>
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 * 示例 3：
 * <p>
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-absolute-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题比较简单，排序之后，最小值一定是相邻的两者之间的最差值。
 * <p>
 * <p>
 * state:done
 */
public class Solution1200 {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int minDIff = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            int diffValue = arr[i] - arr[i - 1];
            if (diffValue < minDIff) {
                result.clear();
                result.add(createList(arr[i], arr[i - 1]));
                minDIff = diffValue;
                continue;
            }
            if (diffValue == minDIff) {
                result.add(createList(arr[i], arr[i - 1]));
                continue;
            }
        }
        return result;
    }

    private List<Integer> createList(int i1, int i2) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(i2);
        integers.add(i1);
        return integers;
    }

}