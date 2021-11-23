package com.xt.niuke;


import java.util.ArrayList;
import java.util.Stack;


/**
 * 滑动窗口的最大值
 * 描述
 * 给定一个长度为 n 的数组 num 和滑动窗口的大小 size ，找出所有滑动窗口里数值的最大值。
 * <p>
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * <p>
 * 窗口大于数组长度或窗口长度为0的时候，返回空。
 * <p>
 * 数据范围： 1 \le n \le 100001≤n≤10000，0 \le size \le 100000≤size≤10000，数组中每个元素的值满足 |val| \le 10000∣val∣≤10000
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 * 10月23
 * 已写完，未验证
 */
public class Solution59 {


    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        Stack<Node> stack = new Stack<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            while (true) {
                if (stack.isEmpty()) {
                    stack.add(new Node(i, num[i]));
                    break;
                }
                if (stack.elementAt(0).index <= (i - size)) {
                    stack.remove(0);
                }
                Node peek = stack.peek();
                if (peek.val > num[i]) {
                    stack.push(new Node(i, num[i]));
                    break;
                }
                if (peek.val <= num[i]) {
                    stack.pop();
                }
            }
            if (i >= size - 1) {
                result.add(stack.elementAt(0).val);
            }
        }
        return result;
    }

    static class Node {
        int index;//所在的位置
        int val;

        Node(int index, int val) {
            this.index = index;
            this.val = val;
        }

    }
}
