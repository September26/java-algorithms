package com.xt;


import com.xt.leetcode.*;
import com.xt.model.ListNode;
import com.xt.model.Node;
import com.xt.model.TreeNode;
import com.xt.mst.SolutionMST1711;
import com.xt.util.AlgorithmHelper;
import com.xt.util.IOHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 */
public class Solution {

    public static void main(String[] args) {
        try {
            Solution515 solution = new Solution515();

            int[] ints1 = new int[]{1, 3, 1, 5, 4};
            int[] ints2 = new int[]{9, 16, 14, 1, 5, 15, 6, 10, 1, 1, 7, 5, 11, 4, 4, 6};
            String[] str1 = new String[]{"bar", "foo", "the"};
            String[] str2 = new String[]{"KFC", "Burger King", "Tapioca Express", "Shogun"};

//            char[][] stringss = new char[][]{{'.', '.', '.', '2', '.', '.', '.', '6', '3'}, {'3', '.', '.', '.', '.', '5', '4', '.', '1'}, {'.', '.', '1', '.', '.', '3', '9', '8', '.'}, {'.', '.', '.', '.', '.', '.', '.', '9', '.'}, {'.', '.', '.', '5', '3', '8', '.', '.', '.'}, {'.', '3', '.', '.', '.', '.', '.', '.', '.'}, {'.', '2', '6', '3', '.', '.', '5', '.', '.'}, {'5', '.', '3', '7', '.', '.', '.', '.', '8'}, {'4', '7', '.', '.', '.', '1', '.', '.', '.'}};
//            int[][] intss = new int[][]{{0, 1}, {1, 1}, {2, 2}};
            int[][] intss = getValues();
//            ints1 = getValue();

//            Object object = solution.add();
            long l = System.currentTimeMillis();
//            int search = solution.search(integers, 5, true);
//            Object object = solution.deserialize("123");
//            Object object = solution.deserialize("[123,456,7,8]");
            TreeNode treeNode0 = new TreeNode(1);
            TreeNode treeNode1 = new TreeNode(3);
            TreeNode treeNode2 = new TreeNode(2);
            TreeNode treeNode3 = new TreeNode(5);
            TreeNode treeNode4 = new TreeNode(3);
            TreeNode treeNode6 = new TreeNode(9);
            treeNode0.left = treeNode1;
            treeNode0.right = treeNode2;
            treeNode1.left = treeNode3;
            treeNode1.right = treeNode4;
            treeNode2.right = treeNode6;


            Object object = solution.largestValues(treeNode0);
            System.out.println("spendTime:" + (System.currentTimeMillis() - l));
            print(object);
//            Solution solution2 = new Solution();
//            solution2.test();


//            int i1 = s1.hashCode();
//            int i2 = s2.hashCode();
//            System.out.println(s1.equals(s2));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        int[] ints = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 8,8, 9, 9, 10};
//        int i = binarySearch(ints, 8);
//        System.out.println(i);
    }

    final Solution4 solution4 = new Solution4();

    private void test() {
//        Solution4 solution4 = new Solution4();
    }

    private static int[] getValue() throws IOException {
        InputStream inputStream = IOHelper.fromFileToIputStream(new File("input.txt"));
        String s = IOHelper.readStrByCode(inputStream, "utf-8");
        String[] strings = s.split(",");
        int[] ints1 = new int[strings.length];
        for (int i = 0; i < ints1.length; i++) {
            ints1[i] = Integer.parseInt(strings[i].trim());
        }
        return ints1;
    }

    private static int[][] getValues() throws IOException {
        InputStream inputStream = IOHelper.fromFileToIputStream(new File("input.txt"));
        String s = IOHelper.readStrByCode(inputStream, "utf-8");
        s = s.substring(1, s.length() - 1);
        String[] strings = s.split("],");
        int[][] intss = new int[strings.length][];
        for (int i = 0; i < intss.length; i++) {
            String item = strings[i];
            String s1 = item.trim().replaceAll("\\[", "").replaceAll("\\]", "");
            String[] split = s1.split(",");
            int[] ints = new int[split.length];
            for (int j = 0; j < split.length; j++) {
                try {
                    ints[j] = Integer.parseInt(split[j].trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            intss[i] = ints;
        }
        return intss;
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
            builder.append('[');
            for (int i = 0; i < integers.length; i++) {
                builder.append(integers[i]);
                if (i < integers.length - 1) {
                    builder.append(',');
                }
            }
            builder.append(']');
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

    class A {
        public void test() {

        }
    }

    class B extends A {
        public void test() {

        }
    }
}
