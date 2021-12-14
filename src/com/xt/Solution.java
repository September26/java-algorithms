package com.xt;


import com.xt.leetcode.*;
import com.xt.model.ListNode;
import com.xt.util.IOHelper;
import org.omg.IOP.IORHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 描述
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * 示例1
 * 输入：
 * {1,2,3,4,5,#,6,#,#,7}
 * 复制
 * 返回值：
 * 4
 */
public class Solution {

    public static void main(String[] args) {
//        int length = 56 * 49;
//        int seventh = (length >> 3) + (length >> 6) + 1;
//        System.out.print(seventh);
        try {
//            boolean flag1 = false;
//            boolean flag2 = false;
//            boolean flag3 = true;
//            boolean flag4 = true;
//            boolean flag5 = false;
//            boolean flag6 = true;
//            System.out.println(flag1&flag2&flag3|flag4&flag5&flag6);
            Solution630 solution = new Solution630();
//            ArrayList<Integer> integers = new ArrayList<>();
//            solution.addToList(2,integers);
//            solution.addToList(3,integers);
//            solution.addToList(0,integers);
//            solution.addToList(4,integers);
//            solution.addToList(6,integers);
//            solution.addToList(8,integers);
//            solution.addToList(5,integers);
//            print(integers);

//            Object obejct = solution.scheduleCourse(new int[][]{{914, 9927}, {333, 712}, {163, 5455}, {835, 5040}, {905, 8433}, {417, 8249}, {921, 9553}, {913, 7394}, {303, 7525}, {582, 8658}, {86, 957}, {40, 9152}, {600, 6941}, {466, 5775}, {718, 8485}, {34, 3903}, {380, 9996}, {316, 7755}});
            Object obejct = solution.scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}});
            print(obejct);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 输出结果
     *
     * @param obejct
     */
    private static void print(Object obejct) {
        if (obejct instanceof ListNode) {
            //依次打印链表
            ListNode node = (ListNode) obejct;
            StringBuilder builder = new StringBuilder();
            while (node != null) {
                builder.append(node.val);
                builder.append(",");
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
                builder.append(",");
            }
            System.out.print(builder.toString());
            return;
        }
        System.out.print(obejct);
    }

}
