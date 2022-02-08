package com.xt.leetcode;

import java.util.*;

/**
 * 1405. 最长快乐字符串
 * 日期：2022.2.7
 * 描述
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * <p>
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 * <p>
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 示例 2：
 * <p>
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 示例 3：
 * <p>
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-happy-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 先对a,b,c按照数量排序。因为a,b,c和'a','b','c'是对应的，所以我最终选择了构建对象进行排序的方式求出数量1，2，3多的。这里假设a最多，b其次，c最少。
 * a>=b+c,那么一定要按照2个a+1个b或c的方式去排列。
 * 反之，则a,b,c依次取就好。
 * 中间有一些极端值的判断需要处理
 * <p>
 * <p>
 * state:
 */
public class Solution1405 {

    public String longestDiverseString(int a, int b, int c) {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node(a, 'a'));
        nodes.add(new Node(b, 'b'));
        nodes.add(new Node(c, 'c'));

        Comparator<Node> comparator = (o1, o2) -> {
            if (o1.num == o2.num) {
                return 0;
            }
            return o1.num > o2.num ? -1 : 1;
        };
        StringBuilder builder = new StringBuilder();
        char lastChar = 'd';
        while (true) {
            nodes.sort(comparator);
            Node node1 = nodes.get(0);
            Node node2 = nodes.get(1);
            Node node3 = nodes.get(2);
            if (node1.num <= 0 && node2.num <= 0 && node3.num <= 0) {
                break;
            }
            if (node1.num >= node2.num + node3.num) {
                if (lastChar == node1.c) {
                    if (node2.num > 0) {
                        builder.append(node2.c);
                        node2.num--;
                        lastChar = node2.c;
                        continue;
                    }
                    if (node3.num > 0) {
                        builder.append(node3.c);
                        node3.num--;
                        lastChar = node3.c;
                        continue;
                    }
                    break;
                }
                if (node1.num >= 2) {
                    builder.append(node1.c);
                    builder.append(node1.c);
                    node1.num -= 2;
                } else {
                    builder.append(node1.c);
                    node1.num--;
                }
                lastChar = node1.c;
                continue;
            }
            //依次取1个
            if (lastChar == node1.c) {
                if (node2.num > 0) {
                    builder.append(node2.c);
                    node2.num--;
                    lastChar = node2.c;
                    continue;
                }
                if (node3.num > 0) {
                    builder.append(node3.c);
                    node3.num--;
                    lastChar = node3.c;
                }
                continue;
            }
            builder.append(node1.c);
            node1.num--;
            lastChar = node1.c;
        }
        return builder.toString();
    }

    static class Node {
        int num;
        char c;

        public Node(int num, char c) {
            this.num = num;
            this.c = c;
        }
    }

}