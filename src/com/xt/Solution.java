package com.xt;


import com.xt.leetcode.*;
import com.xt.model.ListNode;

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
//        Pattern compile = Pattern.compile("^(a.*)(babca)(.*ca)$");
//        Matcher matcher = compile.matcher("(ac)(babca)(babca)");

//        String p = "ba.*a.*babca.*ca";
//        String s = "abaaacacbabcababca";


//        String p = "(b)(a.*)(a.*)(babc)(a.*)(ca)";
//        String s = "(b)(aa)(acac)(babc)(abab)(ca)";
//        Pattern compile = Pattern.compile("^" + p + "$");
//        Matcher matcher = compile.matcher(s);
//        System.out.println(matcher.matches());
        Solution3 solution = new Solution3();
        Object obejct = solution.lengthOfLongestSubstring("abcadb");
        print(obejct);


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
