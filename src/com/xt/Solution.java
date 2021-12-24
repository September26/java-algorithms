package com.xt;


import com.xt.leetcode.*;
import com.xt.model.ListNode;
import com.xt.util.IOHelper;

import java.io.File;
import java.io.InputStream;


/**
 *
 */
public class Solution {

    public static void main(String[] args) {
        try {
            Solution1705 solution = new Solution1705();
//            InputStream inputStream = IOHelper.fromFileToIputStream(new File("input.txt"));
//            String s = IOHelper.readStrByCode(inputStream, "utf-8");
//            String[] ms = s.split(",");
//            int[] ints1 = new int[ms.length];
//            int[] ints2 = new int[ms.length];
//            for (int i = 0; i < ints1.length; i++) {
//                ints1[i] = 20000;
//                ints2[i] = 20000;
//            }
            long l = System.currentTimeMillis();
            int[] ints1 = new int[]{2, 2, 3, 5, 2};
            int[] ints2 = new int[]{3, 2, 1, 4, 2};
            Object object = solution.eatenApples(ints1, ints2);//p*.aaa
            print(object);
            System.out.println("spendTIme:" + (System.currentTimeMillis() - l));
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        System.out.println(obejct);
    }

}
