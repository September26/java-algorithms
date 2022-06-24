package com.xt.leetcode;

import java.util.*;

/**
 * 385.迷你语法分析器
 * 每日一题：2022.04.15
 * 完成日期：2022.04.15
 * 链接：https://leetcode-cn.com/problems/mini-parser/
 * 描述：https://leetcode-cn.com/problems/mini-parser/
 *
 * 解题思路：
 * 这种对称的结构最适合使用栈的思想来接题目。
 * 由于本题当中，对象有两种形式，数组和数字形式。所以我们先生成对象加入到集合当中，后续遍历过程中再决定其类型。
 * 栈的话用来装载一层一层的对象，读到[则说明增加增加一层级。读到]则介绍一层级。
 * 针对字符从前向后遍历，每个字符分为以下几种情况：
 * 1.字符串为'['时，则说明当前对象是一个数组的形式，则生成一个对象加入到数组中（此时还未确定时数组还是数字形式）。
 * 2.字符串为']'时，则说明当前数组的循环结束，则从栈中pop出最上层节点。
 * 3.字符串为'.'时，则说明应该读取下一组对象。
 * 4.字符串为数字时，则记录一下，start++。除此之外，除了数字类型，都会触发字符串结束判断，生成一个数字类型的对象加入到最上层的栈对象中。
 *
 *
 *
 * state:done
 */
public class Solution385 {


    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger root = new NestedInteger();
        stack.add(root);
        StringBuilder builder = new StringBuilder();

        int start = 0;
        char[] chars = s.toCharArray();
        while (true) {
            if (start == chars.length) {
                addValue(builder, stack);
                break;
            }
            char aChar = chars[start];
            if (aChar == '[') {
                NestedInteger value = new NestedInteger();
                stack.peek().getList().add(value);
                stack.add(value);
                start++;
                continue;
            }
            if (aChar == ']') {
                addValue(builder, stack);
                stack.pop();
                start++;
                continue;
            }
            if (aChar == ',') {
                addValue(builder, stack);
                start++;
                continue;
            }
            builder.append(aChar);
            start++;
        }
        NestedInteger result = stack.pop().getList().get(0);
        return result;
    }

    private void addValue(StringBuilder builder, Stack<NestedInteger> stack) {
        if (builder.length() == 0) {
            return;
        }
        NestedInteger value = new NestedInteger(Integer.parseInt(builder.toString()));
        stack.peek().add(value);
        builder.setLength(0);
    }


    class NestedInteger {
        Integer mValue;
        List<NestedInteger> list = new ArrayList<>();

        public NestedInteger() {
        }

        public NestedInteger(int value) {
            this.mValue = value;
        }


        public boolean isInteger() {
            return mValue != null;
        }


        public Integer getInteger() {
            return mValue;
        }


        public void setInteger(Integer value) {
            this.mValue = value;
            this.list = null;
        }


        public void add(NestedInteger ni) {
            list.add(ni);
        }


        public List<NestedInteger> getList() {
            return list;
        }
    }

}