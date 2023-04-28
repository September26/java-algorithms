package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 904. 水果成篮
 * 每日一题：2022.10.17
 * 完成日期：2022.10.17
 * 链接：https://leetcode.cn/problems/fruit-into-baskets/
 * 描述：
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 *
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 *
 * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
 * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：fruits = [1,2,1]
 * 输出：3
 * 解释：可以采摘全部 3 棵树。
 * 示例 2：
 *
 * 输入：fruits = [0,1,2,2]
 * 输出：3
 * 解释：可以采摘 [1,2,2] 这三棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
 * 示例 3：
 *
 * 输入：fruits = [1,2,3,2,2]
 * 输出：4
 * 解释：可以采摘 [2,3,2,2] 这四棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
 * 示例 4：
 *
 * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
 *
 *
 * 提示：
 *
 * 1 <= fruits.length <= 105
 * 0 <= fruits[i] < fruits.length
 * <p>
 * 解题思路：
 * 这题我的设计略微复杂了一些，用一个Node904来存放数据，Node904记录开始位置，value1和value2，Node最多只能存放两种值。
 * 然后开始遍历，如果node1为空，则创建；
 * 如果当前数值和上一个数值相同，则跳过；
 * 如果node1只存放了一个类型，那么读出新类型的时候，value=新类型，并且创建node2，node2.start=i；
 * 否则，说明node1已经有两种类型了，并且已经读出来了第三种类型。此时首先求出node1的长度，然后把node2赋值给node1，并且生成新的node2。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution904 {

    public int totalFruit(int[] fruits) {
        int maxValue = 0;
        Node904 node1 = null;
        Node904 node2 = null;
        int lastValue = 0;

        for (int i = 0; i < fruits.length; i++) {
            int value = fruits[i];
            if (node1 == null) {
                node1 = new Node904();
                node1.value1 = value;
                node1.startIndex = i;
                lastValue = value;
                continue;
            }
            if (lastValue == value) {
                continue;
            }
            if (!node1.haveFull()) {
                node1.value2 = value;

                node2 = new Node904();
                node2.value1 = value;
                node2.startIndex = i;
                lastValue = value;
                continue;
            }
            if (node1.haveValue(value)) {
                node2.value1 = value;
                node2.startIndex = i;
                lastValue = value;
                continue;
            }
            int length = node1.getLength(i);
            maxValue = Math.max(length, maxValue);
            node1 = node2;
            node1.value2 = value;

            node2 = new Node904();
            node2.value1 = value;
            node2.startIndex = i;
            lastValue = value;
        }
        maxValue = Math.max(maxValue, node1.getLength(fruits.length));
        return maxValue;
    }


    static class Node904 {
        int startIndex = 0;
        int value1 = -1;
        int value2 = -1;

        public boolean haveValue(int value) {
            return value == value1 || value == value2;
        }

        public boolean haveFull() {
            return -1 != value1 && -1 != value2;
        }

        public int getLength(int i) {
            return i - startIndex;
        }
    }


}