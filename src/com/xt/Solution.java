package com.xt;


import com.xt.leetcode.*;
import com.xt.mianshi.MianShi0502;
import com.xt.mianshi.MianShi1705;
import com.xt.model.ListNode;
import com.xt.model.TreeNode;
import com.xt.offer.Offer47;
import com.xt.util.BinaryHelper;
import com.xt.util.IOHelper;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;


/**
 *
 */
public class Solution {


    public static void main(String[] args) {
        try {
            Solution2404 solution = new Solution2404();
            //开始和结束节点只能是正数
            int[] ints1 = new int[]{4, 4, 4, 9, 2, 4};
            int[] ints2 = new int[]{2, 1, 2, 1};
            int[] ints3 = new int[]{2, 3, 5};

            String[] str1 = new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
//            String[] str1 = new String[]{"gta", "gta(1)", "gta", "avalon"};
//            String[] str2 = new String[]{"13:00", "13:20", "14:00", "18:00", "18:51", "19:30", "19:49"};

//            char[][] stringss = new char[][]{{'.', '.', '.', '2', '.', '.', '.', '6', '3'}, {'3', '.', '.', '.', '.', '5', '4', '.', '1'}, {'.', '.', '1', '.', '.', '3', '9', '8', '.'}, {'.', '.', '.', '.', '.', '.', '.', '9', '.'}, {'.', '.', '.', '5', '3', '8', '.', '.', '.'}, {'.', '3', '.', '.', '.', '.', '.', '.', '.'}, {'.', '2', '6', '3', '.', '.', '5', '.', '.'}, {'5', '.', '3', '7', '.', '.', '.', '.', '8'}, {'4', '7', '.', '.', '.', '1', '.', '.', '.'}};
//            int[][] intss = new int[][]{{0, 1}, {1, 1}, {2, 2}};
            int[][] intss = getValuess();
//            String[] str = getStrings();
//            int[] ints4 = getValues();
//            int[][] intss1 = new int[][]{{1, 1}, {4, 5}, {3, 8}};
//            int[][] intss2 = new int[][]{{3, 1}, {1, 5}};

            long l = System.currentTimeMillis();
//            Object object2 = solution.isMatch("e","bc");
            Object object = solution.mostFrequentEven(ints1);
            print(object);
            System.out.println("spendTime:" + (System.currentTimeMillis() - l));
//            print(object);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test(String... str) {
        List<String> map = new ArrayList<>();
        map.add("A");
        map.add("BB");
        map.add("CC");
        map.add("D");
        map.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if (s.length() == 2) {
                    return true;
                }
                return false;
            }
        });
        System.out.println(map.size());
    }


    private static Solution558.Node create(int isLeaf, int val, String name) {
        Solution558.Node node0 = new Solution558.Node(name);
        node0.isLeaf = isLeaf == 1;
        node0.val = val == 1;
        return node0;
    }


    private static int[] getValues() throws IOException {
        InputStream inputStream = IOHelper.fromFileToIputStream(new File("input.txt"));
        String s = IOHelper.readStrByCode(inputStream, "utf-8");
        s = s.replaceAll("[\\{\\}\\[\\]]", "");
        String[] strings = s.split(",");
        int[] ints1 = new int[strings.length];
        for (int i = 0; i < ints1.length; i++) {
            ints1[i] = Integer.parseInt(strings[i].trim());
        }
        return ints1;
    }

    private static String[] getStrings() throws IOException {
        InputStream inputStream = IOHelper.fromFileToIputStream(new File("input.txt"));
        String s = IOHelper.readStrByCode(inputStream, "utf-8").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
        return s.split(",");
    }

    private static String getInput() throws IOException {
        InputStream inputStream = IOHelper.fromFileToIputStream(new File("input.txt"));
        String s = IOHelper.readStrByCode(inputStream, "utf-8");
        return s;
    }

    private static int[][] getValuess() throws IOException {
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
        if (obejct instanceof int[][]) {
            for (int[] integers : (int[][]) obejct) {
                StringBuilder builder = new StringBuilder();
                builder.append('[');
                for (int i = 0; i < integers.length; i++) {
                    builder.append(integers[i]);
                    if (i < integers.length - 1) {
                        builder.append(',');
                    }
                }
                builder.append(']');
                System.out.println(builder.toString());
            }
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
