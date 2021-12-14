package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 689. 三个无重叠子数组的最大和
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
 * <p>
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,1,2,6,7,5,1], k = 2
 * 输出：[0,3,5]
 * 解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
 * 也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
 * 输出：[0,2,4]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution689 {


    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;

        int max1Sum = 0;
        int max12Sum = 0;
        int max123Sum = 0;

        int max1Index = 0;
        int max22Index = 0;
        int max21Index = 0;


        int[] result = new int[3];

        for (int i = 0; i < nums.length - 2 * k; i++) {
            int currentValue1 = nums[i];
            int currentValue2 = nums[i + k];
            int currentValue3 = nums[i + k * 2];
            sum1 += currentValue1;
            sum2 += currentValue2;
            sum3 += currentValue3;
            if (i >= (k - 1)) {
                if (sum1 > max1Sum) {
                    max1Sum = sum1;
                    max1Index = i + 1 - k;
                }

                if (max1Sum + sum2 > max12Sum) {
                    max22Index = i + 1;
                    max21Index = max1Index;
                    max12Sum = max1Sum + sum2;
                }

                if (max12Sum + sum3 > max123Sum) {
                    result[0] = max21Index;
                    result[1] = max22Index;
                    result[2] = i + 1 + k;
                    max123Sum = max12Sum + sum3;
                }
                sum1 -= nums[i + 1 - k];
                sum2 -= nums[i + 1];
                sum3 -= nums[i + 1 + k];
            }
        }
        return result;
    }


//    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
//        List<Node> valueList = new ArrayList<>();
//        for (int i = 0; i <= nums.length - k; i++) {
//            int sum = 0;
//            for (int j = i; j < i + k; j++) {
//                sum += nums[j];
//            }
//            Node node = new Node(i, i + k - 1, sum);
//            valueList.add(node);
//        }
//        //从后向前遍历，生成maxMap
//        int count = valueList.size();
//        Map<Integer, Node> maxMap = new HashMap<>();
//        Node maxNode = valueList.get(count - 1);
//        Node node;
//        for (int i = count - 1; i >= 0; i--) {
//            node = valueList.get(i);
//            if (node.sum >= maxNode.sum) {
//                maxNode = node;
//            }
//            maxMap.put(i, maxNode);
//        }
//
//        //没有必要按照大小来匹配
//        int maxSum = 0;
//        List<Node> maxList = new ArrayList<>();
//
//        for (int i = 0; i < count; i++) {
//            Node node1 = valueList.get(i);
//            for (int j = i + k; j < count; j++) {
//                Node node2 = valueList.get(j);
//                Node node3 = maxMap.get(j + k);
//                if (node3 == null) {
//                    break;
//                }
//                int sum = node1.sum + node2.sum + node3.sum;
//                if (sum > maxSum) {
//                    maxList.clear();
//                    maxSum = sum;
//                    maxList.add(node1);
//                    maxList.add(node2);
//                    maxList.add(node3);
//                }
//            }
//        }
//
//        //从头开始找，找3*k个，找出最大值
//        int[] ints = new int[3];
//        for (int i = 0; i < maxList.size(); i++) {
//            ints[i] = maxList.get(i).start;
//        }
//        return ints;
//    }
//
//    private boolean isConflict(Node newNode, Node checkNode) {
//        if (newNode.start > checkNode.end) {
//            return false;
//        }
//        if (newNode.end < checkNode.start) {
//            return false;
//        }
//        return true;
//    }
//
//    static class Node {
//        int start;
//        int end;
//        int sum;
//
//        Node(int start, int end, int sum) {
//            this.start = start;
//            this.end = end;
//            this.sum = sum;
//        }
//    }

    //1, 1, 1, 2, 10, 9, 9, 1, 1, 5, 4, 9, 9, 8, 5, 1
//    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
//        int[] ans = new int[3];
//        int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
//        int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
//        int sum3 = 0, maxTotal = 0;
//        for (int i = k * 2; i < nums.length; ++i) {
//            sum1 += nums[i - k * 2];
//            sum2 += nums[i - k];
//            sum3 += nums[i];
//            System.out.println("index1:" + (i - k * 2) + ",index2:" + (i - k) + ",index3:" + i+",sum1:"+sum1+",sum2:"+sum2+",sum3:"+sum3);
//            if (i >= k * 3 - 1) {
//                if (sum1 > maxSum1) {
//                    maxSum1 = sum1;
//                    maxSum1Idx = i - k * 3 + 1;
//                }
//                if (maxSum1 + sum2 > maxSum12) {
//                    maxSum12 = maxSum1 + sum2;
//                    maxSum12Idx1 = maxSum1Idx;
//                    maxSum12Idx2 = i - k * 2 + 1;
//                }
//                if (maxSum12 + sum3 > maxTotal) {
//                    maxTotal = maxSum12 + sum3;
//                    ans[0] = maxSum12Idx1;
//                    ans[1] = maxSum12Idx2;
//                    ans[2] = i - k + 1;
//                }
//                sum1 -= nums[i - k * 3 + 1];
//                sum2 -= nums[i - k * 2 + 1];
//                sum3 -= nums[i - k + 1];
//            }
//        }
//        return ans;
//    }
}