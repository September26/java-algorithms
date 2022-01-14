package com.xt.leetcode;

import java.util.*;

/**
 * 373. 查找和最小的K对数字
 * 描述
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * <p>
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * <p>
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 * <p>
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 * <p>
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= nums1.length, nums2.length <= 10^4
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1, nums2 均为升序排列
 * 1 <= k <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 贪心算法和最优解。
 * 构建一个model，代表num1中选择一个数，num2中选择一个数，求出和。
 * 构建一个队列，这个队列有序队列，使用二叉法插入。
 * 循环中，永远都取队列最前面那个数，因为那个数是最小的。
 * 读到一个Model，比如其index1=0,index2=0时，那么这次时候取完这个数之后，那么就会有两个数比这个数大，分别为(0,1)和(1,0)位置的。
 * 就这样，每次取完一个数之后，都计算出两个比当前数大的新Model，加入到有序队列中。继续循环
 * <p>
 * state:done
 */
public class Solution373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Set<String> haveAdd = new HashSet<>();
        List<List<Integer>> lists = new ArrayList<>();
        List<Model> sortList = new ArrayList<>();
        Model model = new Model(0, 0, nums1[0] + nums2[0], 0 + "_" + 0);
        haveAdd.add(model.key);
        sortList.add(model);

        while (lists.size() < k) {
            if (sortList.size() == 0) {
                break;
            }
            Model minModel = sortList.remove(0);
            //加入一个数
            List<Integer> addList = new ArrayList<>();
            addList.add(nums1[minModel.index1]);
            addList.add(nums2[minModel.index2]);
            lists.add(addList);

            //查找比这个最小数更大的两个数
            int index1 = minModel.index1 + 1;
            int index2 = minModel.index2;
            String key = index1 + "_" + index2;
            if (!haveAdd.contains(key) && minModel.index1 + 1 < nums1.length) {
                Model model1 = new Model(index1, index2, nums1[index1] + nums2[index2], key);
                haveAdd.add(model1.key);
                inseartModel(sortList, model1);
            }
            index1 = minModel.index1;
            index2 = minModel.index2 + 1;
            key = index1 + "_" + index2;
            if (!haveAdd.contains(key) && minModel.index2 + 1 < nums2.length) {
                Model model2 = new Model(index1, index2, nums1[index1] + nums2[index2], key);
                haveAdd.add(model2.key);
                inseartModel(sortList, model2);
            }
        }
        return lists;
    }


    //二分查找插入
    private void inseartModel(List<Model> list, Model model) {
        if (list.size() == 0) {
            list.add(model);
            return;
        }
        int start = 0;
        int end = list.size() - 1;
        int middle;
        while (true) {
            middle = (start + end) / 2;
            int middleValue = list.get(middle).sum;
            if (middle == end || middle == start) {
                //和比较
                if (model.sum > list.get(end).sum) {
                    list.add(end + 1, model);
                } else if (model.sum < list.get(start).sum) {
                    list.add(start, model);
                } else if (model.sum > list.get(middle).sum) {
                    list.add(middle + 1, model);
                } else {
                    list.add(middle, model);
                }
                break;
            }
            if (model.sum > middleValue) {
                start = middle;
            } else {
                end = middle;
            }
        }
    }


    static class Model {
        String key;
        int index1;
        int index2;
        int sum;

        Model(int index1, int index2, int sum, String key) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
            this.key = key;
        }
    }

}