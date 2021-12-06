package com.xt.leetcode;

/**
 * 4.寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 未完成
 */
public class Solution4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }
        if (nums1.length == 0) {
            int middle = nums2.length / 2;
            return nums2.length % 2 == 0 ? (nums2[middle - 1] + nums2[middle]) / 2.0 : nums2[middle];
        }
        if (nums2.length == 0) {
            int middle = nums1.length / 2;
            return nums1.length % 2 == 0 ? (nums1[middle - 1] + nums1[middle]) / 2.0 : nums1[middle];
        }
        return findMedianSortedArraysByDouble(nums1, nums2) / 2.0;
    }

    private int findMedianSortedArraysByDouble(int[] nums1, int[] nums2) {

        int[] num1Middle = getMiddle(0, nums1.length, nums1.length);
        int[] num2Middle = getMiddle(0, nums2.length, nums2.length);

        while (true) {
            System.out.println("num1Middle[0]:" + num1Middle[0] + ",num1Middle[1]:" + num1Middle[1] + ",num2Middle[0]:" + num2Middle[0] + ",num1Middle[2]:" + num2Middle[1]);
            if (nums1[num1Middle[0]] == nums2[num2Middle[0]]) {
                //则前边界就是nums1[num1Start]
                int little = Math.min(nums1[num1Middle[1]], nums2[num2Middle[1]]);
                return (nums1[num1Middle[0]] + little);
            } else if (nums1[num1Middle[0]] < nums2[num2Middle[0]]) {
                if (nums1[num1Middle[1]] >= nums2[num2Middle[1]]) {
                    return nums2[num2Middle[0]] + nums2[num2Middle[1]];
                } else {
                    if (num2Middle[0] == 0 && num1Middle[0] == (nums1.length - 1)) {
                        return nums1[num1Middle[1]] + nums2[num2Middle[0]];
                    } else if (num1Middle[0] == (nums1.length - 1)) {
                        if (nums1[num1Middle[0]] < nums2[num2Middle[0]]) {
                            if (num2Middle[0] == num2Middle[1]) {
                                if (nums1[num1Middle[1]] < nums2[num2Middle[0] - 1]) {
                                    return nums2[num2Middle[0] - 1] + nums2[num2Middle[0]];
                                } else {
                                    return nums1[num1Middle[1]] + nums2[num2Middle[0]];
                                }
                            } else {
                                return nums2[num2Middle[0]] * 2;
                            }
                        } else {
                            return nums1[num1Middle[0]] + nums2[num2Middle[0]];
                        }
                    } else if (num2Middle[0] == 0) {
                        //中位数在num1当中

                    }
                    //判断是不是尾端，如果是尾端直接跳出循环
                    int[] middle = getMiddle(num1Middle[0], num1Middle[1], nums1.length);
                    num2Middle[0] = num2Middle[0] - (middle[1] - num1Middle[1]);
                    num2Middle[1] = num2Middle[1] - (middle[0] - num1Middle[0]);
                    num1Middle = middle;
                }
            } else if (nums1[num1Middle[0]] > nums2[num2Middle[0]]) {
                if (nums1[num1Middle[1]] <= nums2[num2Middle[1]]) {
                    return nums1[num1Middle[0]] + nums1[num1Middle[1]];
                } else {
                    //判断是不是尾端，如果是尾端直接跳出循环
                    if (num1Middle[0] == (nums1.length - 1) && num2Middle[0] == 0) {
                        return nums1[num1Middle[1]] + nums2[num2Middle[0]];
                    } else if (num1Middle[0] == (nums1.length - 1)) {
                        if (nums1[num1Middle[0]] < nums2[num2Middle[0]]) {
                            if (num2Middle[0] == num2Middle[1]) {
                                if (nums1[num1Middle[1]] < nums2[num2Middle[0] - 1]) {
                                    return nums2[num2Middle[0] - 1] + nums2[num2Middle[0]];
                                } else {
                                    return nums1[num1Middle[1]] + nums2[num2Middle[0]];
                                }
                            } else {
                                return nums2[num2Middle[0]] * 2;
                            }
                        } else {
                            return nums1[num1Middle[0]] + nums2[num2Middle[0]];
                        }
                    } else if (num2Middle[0] == 0) {
                        //中位数在num1当中

                    }
                    //判断是不是尾端，如果是尾端直接跳出循环
                    int[] middle = getMiddle(num2Middle[0], num2Middle[1], nums2.length);
                    num1Middle[0] = num1Middle[0] - (middle[1] - num2Middle[1]);
                    num1Middle[1] = num1Middle[1] - (middle[0] - num2Middle[0]);
                    num1Middle = middle;
                }
            } else {
                System.out.print("error");
            }
        }
    }


    private int[] getMiddle(int start, int oldEnd, int end) {
        int[] middle = new int[2];
        int sum = start + end;
        if (sum % 2 == 0) {
            middle[0] = sum / 2 - 1;
            middle[1] = sum / 2;
        } else {
            middle[0] = middle[1] = sum / 2;
        }
        if (middle[1] == oldEnd) {
            middle[0] = middle[1];
        }
        return middle;
    }

}