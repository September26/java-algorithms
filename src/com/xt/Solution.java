package com.xt;


import com.xt.leetcode.*;
import com.xt.model.ListNode;
import com.xt.model.TreeNode;
import com.xt.util.IOHelper;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;


/**
 *
 */
public class Solution {

    public static void main(String[] args) {
        try {
//            1708
            Solution846 solution = new Solution846();
            InputStream inputStream = IOHelper.fromFileToIputStream(new File("input.txt"));
            String s = IOHelper.readStrByCode(inputStream, "utf-8");
//            String[] strings = s.split(",");
//            for (int i = 0; i < ints1.length; i++) {
//                ints1[i] = 20000;
//                ints2[i] = 20000;
//            }
            long l = System.currentTimeMillis();
//            rfkqyuqfjkx
            String[] strings = {"aaaaac", "a", "aa", "aaa", "aaaa",};
            int[] ints = new int[]{2, 1, 3, 2, 3, 4, 8, 7, 6};
            Object object = solution.isNStraightHand(ints,3);//预期96
            print(object);
            System.out.println("spendTime:" + (System.currentTimeMillis() - l));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        int[] ints = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 8,8, 9, 9, 10};
//        int i = binarySearch(ints, 8);
//        System.out.println(i);
    }

    //找到数组中比target更小的那个
    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int ans = 0;
        int middle = 0;
        do {
            middle = (left + right) / 2;
            int value = nums[middle];
            if (value > target) {
                ans = middle;
                right = middle;
                continue;
            }
            left = middle;
        } while ((right - left) > 1);
        return ans;
    }

    /**
     * 输出结果
     *
     * @param obejct
     */
    public static void print(Object obejct) {
        if (obejct instanceof ListNode) {
            //依次打印链表
            ListNode node = (ListNode) obejct;
            StringBuilder builder = new StringBuilder();
            while (node != null) {
                builder.append(node.val);
                builder.append(',');
                node = node.next;
            }
            System.out.print(builder.toString());
            return;
        }
        if (obejct instanceof int[]) {
            StringBuilder builder = new StringBuilder();
            int[] integers = (int[]) obejct;
            for (int i = 0; i < integers.length; i++) {
                builder.append(integers[i]);
                builder.append(',');
            }
            System.out.print(builder.toString());
            return;
        }
        if (obejct instanceof String[]) {
            StringBuilder builder = new StringBuilder();
            String[] integers = (String[]) obejct;
            for (int i = 0; i < integers.length; i++) {
                builder.append(integers[i]);
                builder.append(',');
            }
            System.out.print(builder.toString());
            return;
        }
        System.out.println(obejct);
    }

}
