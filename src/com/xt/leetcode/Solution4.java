package com.xt.leetcode;

/**
 * 4.寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解题思路：
 *
 *
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
        return findMedianSortedArraysByDouble(nums1, nums2);
    }

    private double findMedianSortedArraysByDouble(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) {
            int[] local = nums2;
            nums2 = nums1;
            nums1 = local;
        }
        //保证1的长度大于2
        int start1 = 0;
        int start2 = 0;
        int end1 = nums1.length - 1;
        int end2 = nums2.length - 1;
        while (true) {
            if (start1 == end1 || start2 == end2) {
                break;
            }
            int middleIndex1 = (start1 + end1) / 2;
            int middleIndex2 = (start2 + end2) / 2;
            boolean isValue1Two = false;
            boolean isValue2Two = false;
            double middle1Value;
            double middle2Value;
            if ((end1 - start1) % 2 == 0) {
                middle1Value = nums1[middleIndex1];
            } else {
                middle1Value = (nums1[middleIndex1] + nums1[middleIndex1 + 1]) / 2.0;
                isValue1Two = true;
            }

            if ((end2 - start2) % 2 == 0) {
                middle2Value = nums2[middleIndex2];
            } else {
                middle2Value = (nums2[middleIndex2] + nums2[middleIndex2 + 1]) / 2.0;
                isValue2Two = true;
            }

            if (middle1Value == middle2Value) {
                return middle1Value;
            }
            int change;
            if (middleIndex1 == start1) {
                if (middle1Value > middle2Value) {
                    start1 = end1 = isValue1Two ? middleIndex1 + 1 : middleIndex1;
                    start2 = end2 = isValue2Two ? middleIndex2 + 1 : middleIndex2;
                } else {
                    start1 = end1 = isValue1Two ? middleIndex1 + 1 : middleIndex1;
                    start2 = end2 = isValue2Two ? middleIndex2 : middleIndex2 + 1;
                }
                break;
            }
            if (middle1Value > middle2Value) {
                change = middleIndex2 - start2;
                start2 = middleIndex2;
                start1 = middleIndex1 - change;
                end1 = isValue1Two ? middleIndex1 + 1 : middleIndex1;
            } else {
                change = end2 - middleIndex2;
                end2 = isValue2Two ? middleIndex2 + 1 : middleIndex2;
                start1 = middleIndex1;
                end1 = middleIndex1 + change;
            }
            System.out.println("start1:" + start1 + ",end1:" + end1 + ",start2:" + start2 + ",end2:" + end2);
        }
        //当只剩下1个时候，直接插入到另外一个的数组，然后求中位数
        int[] ints;
        int insertValue;
        if (start1 == end1) {
            ints = new int[end2 - start2 + 1];
            System.arraycopy(nums2, start2, ints, 0, end2 - start2 + 1);
            insertValue = nums1[start1];
        } else {
            ints = new int[end1 - start1 + 1];
            System.arraycopy(nums1, start1, ints, 0, end1 - start1 + 1);
            insertValue = nums2[start2];
        }
        return getMiddleValue(ints, insertValue);
    }

    /**
     * 返回中位数
     *
     * @param nums
     * @param insertValue
     * @return
     */
    public double getMiddleValue(int[] nums, int insertValue) {
        int[] newNums = new int[nums.length + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (insertValue < value) {
                newNums[index++] = insertValue;
            }
            newNums[index++] = value;
        }
        if (index == nums.length) {
            newNums[index] = insertValue;
        }
        int i = newNums.length / 2;
        if (newNums.length % 2 == 0) {
            return ((double) newNums[i - 1] + (double) newNums[i]) / 2;
        }
        return newNums[i];
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