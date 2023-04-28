package com.xt.leetcode;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 592. 分数加减运算
 * 每日一题：2022.07.27
 * 完成日期：2022.07.27
 * 链接：https://leetcode.cn/problems/fraction-addition-and-subtraction/
 * 描述：
 * 给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。 
 * <p>
 * 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: expression = "-1/2+1/2"
 * 输出: "0/1"
 *  示例 2:
 * <p>
 * 输入: expression = "-1/2+1/2+1/3"
 * 输出: "1/3"
 * 示例 3:
 * <p>
 * 输入: expression = "1/3-1/2"
 * 输出: "-1/6"
 *  
 * <p>
 * 提示:
 * <p>
 * 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。 
 * 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fraction-addition-and-subtraction
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 把字符串拆分为一个一个的节点，每个节点包含分子，分母，以及运算符。
 * 第一个节点的话如果负数开头那运算符就是负，否则为正。
 * 把这些节点从头开始一个一个计算，分母相等直接计算，否则分母相乘。最后求出来的那个节点的值进行约分处理就是最后的结果。
 * <p>
 * state:done
 */
public class Solution592 {

    public String fractionAddition(String expression) {

        int lastIndex = 0;
        List<Node> list = new ArrayList<>();
        char[] chars = expression.toCharArray();
        for (int i = 0; i <= chars.length; i++) {
            if (i == expression.length()) {
                Node paser = paser(expression.substring(lastIndex, i));
                list.add(paser);
                continue;
            }
            int value = chars[i];
            if (value == '-' || value == '+') {
                Node paser = paser(expression.substring(lastIndex, i));
                if (paser != null) {
                    list.add(paser);
                }
                lastIndex = i;
                continue;
            }
        }

        Node lastNode = new Node();
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            lastNode = calculation(lastNode, node);
        }
        if (lastNode.current == 0) {
            lastNode.current2 = 1;
        } else {
            //约分计算
            int i = 2;
            while (i <= Math.abs(lastNode.current)) {
                if (lastNode.current % i == 0 && lastNode.current2 % i == 0) {
                    lastNode.current /= i;
                    lastNode.current2 /= i;
                    i = 2;
                    continue;
                }
                i++;
            }


        }

        return lastNode.current + "/" + lastNode.current2;
    }

    private Node paser(String str) {
        Node node = new Node();
        if (str.length() == 0) {
            return null;
        }
        int index = 0;
        int lastIndex = 0;
        char[] chars = str.toCharArray();
        while (index <= str.length()) {
            if (index == str.length()) {
                node.current = Integer.parseInt(str.substring(lastIndex));
                break;
            }
            int value = chars[index++];
            if (value == '-') {
                node.isAdd = false;
                lastIndex++;
                continue;
            }
            if (value == '/') {
                node.current = Integer.parseInt(str.substring(lastIndex, index - 1));
                node.current2 = Integer.parseInt(str.substring(index));
                break;
            }
        }
        return node;
    }


    private Node calculation(Node node1, Node node2) {
        Node node = new Node();
        if (node1.current2 == node2.current2) {
            node.current = node2.isAdd ? node1.current + node2.current : node1.current - node2.current;
            node.current2 = node1.current2;
            return node;
        }
        int i1 = node1.current * node2.current2;
        int i2 = node2.current * node1.current2;

        node.current = node2.isAdd ? i1 + i2 : i1 - i2;
        node.current2 = node1.current2 * node2.current2;
        return node;
    }

    public static class Node {
        boolean isAdd = true;
        int current = 0;//分子
        int current2 = 1;//分母

    }

}