package com.xt.leetcode;

import java.util.*;

/**
 * 870. 优势洗牌
 * 每日一题：2022.10.08
 * 完成日期：2022.10.08
 * 链接：https://leetcode.cn/problems/advantage-shuffle/
 * 描述：
 * 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
 * <p>
 * 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * 输出：[2,11,7,15]
 * 示例 2：
 * <p>
 * 输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * 输出：[24,32,8,12]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length <= 105
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/advantage-shuffle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * nums1中任一位，找相对于nums2的优势，可以满足如下条件。nums1[i]是大于nums2[i]的最小值。
 * 首先拷贝nums2，生成nums2copy保存nums2的顺序。
 * 所以我们对nums1和nums2的拷贝nums2进行排序，使用双指针，index1代表nums1的位置，index2代表nums2的位置。
 * 使用Map<Integer, Stack<Integer>> map来记录对应关系，其中key为nums2中的值，value为nums1中的值。因为nums2中有可能存在相同的值，所以value为栈结构。
 * 如果nums1[index1]>nums2[index2]，则index1++,index2++,并且使用map来记录其对应关系。
 * 如果nums1[index1]<=nums2[index2],则index1++。
 * 如果index1=nums1.length，则结束。
 * 最后遍历nums2copy，按照对应关系生成结果数组返回。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution870 {

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int[] nums2copy = Arrays.copyOf(nums2, nums2.length);
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        Map<Integer, Stack<Integer>> map = new HashMap<>();

        while (index1 < nums1.length) {
            int value1 = nums1[index1];
            int value2 = nums2[index2];
            if (value1 > value2) {
                nums1[index1] = -1;
                index1++;
                index2++;
                add2Map(map, value2, value1);
                continue;
            }
            index1++;
        }
        for (int i = 0; i < nums1.length; i++) {
            int value1 = nums1[i];
            if (value1 == -1) {
                continue;
            }
            int value2 = nums2[index2++];
            add2Map(map, value2, value1);
        }
        int[] result = new int[nums2copy.length];

        for (int i = 0; i < nums2copy.length; i++) {
            int key = nums2copy[i];
            Stack<Integer> integers = map.get(key);
            result[i] = integers.pop();
        }
        return result;
    }

    private void add2Map(Map<Integer, Stack<Integer>> map, int value2, int value1) {
        Stack<Integer> integers = map.get(value2);
        if (integers == null) {
            integers = new Stack<>();
            map.put(value2, integers);
        }
        integers.add(value1);
    }

}