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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 */
public class Solution {

    public static void main(String[] args) {
        try {
            Solution686 solution = new Solution686();
            Object object = solution.repeatedStringMatch("abc", "cabcabca");//
            print(object);
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
        System.out.print(obejct);
    }

}
