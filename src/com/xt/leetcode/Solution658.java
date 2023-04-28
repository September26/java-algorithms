package com.xt.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * 658. 找到 K 个最接近的元素
 * 每日一题：2022.08.25
 * 完成日期：2022.08.25
 * 链接：https://leetcode.cn/problems/find-k-closest-elements/
 * 描述：
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * <p>
 * 整数 a 比整数 b 更接近 x 需要满足：
 * <p>
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr 按 升序 排列
 * -104 <= arr[i], x <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-k-closest-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 先找到最贴近x的位置，然后从这个位置开始向两侧开始寻找
 * <p>
 * <p>
 * state:done
 */
public class Solution658 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            if (x - value < 0) {
                index = i;
                break;
            }
        }
        index--;
        int left = index;
        int right = index + 1;
        List<Integer> result = new ArrayList<>();
        while (result.size() < k) {
            if (left < 0) {
                result.add(arr[right]);
                right++;
                continue;
            }
            if (right >= arr.length) {
                result.add(arr[left]);
                left--;
                continue;
            }
            int leftValue = arr[left];
            int rightValue = arr[right];
            if (Math.abs(leftValue - x) > Math.abs(rightValue - x)) {
                result.add(rightValue);
                right++;
                continue;
            }
            result.add(leftValue);
            left--;
        }
        Collections.sort(result);
        return result;
    }
}