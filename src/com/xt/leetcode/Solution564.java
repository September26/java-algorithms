package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 564. 寻找最近的回文数
 * 每日一题：2022.03.02
 * 完成日期：2022.03.02
 * 链接：https://leetcode-cn.com/problems/find-the-closest-palindrome/
 * 描述：
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * <p>
 * “最近的”定义为两个整数差的绝对值最小。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = "123"
 * 输出: "121"
 * 示例 2:
 * <p>
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= n.length <= 18
 * n 只由数字组成
 * n 不含前导 0
 * n 代表在 [1, 1018 - 1] 范围内的整数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-closest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 穷举所有可能性，共有8种情况。把这8种情况的值全部算出来放到集合中，遍历集合求最合适的数字。
 * 第1种：123,12+1，最接近的为121。
 * 第2种：1234,12+21，最接近的为1221。
 * 第3种：11011，109+01，最接近为10901。
 * 第4种：110011，109+901，最接近为109901。
 * 第5种：111,最接近为101
 * 第6种：1111,最接近为1001
 * 第7种：100,最接近的应该为99。则10^3-1
 * 第8种：999,最接近的为1001，则10^3+1
 * <p>
 * <p>
 * state:done
 */
public class Solution564 {

    public String nearestPalindromic(String n) {
        long origin = Long.parseLong(n);
        List<Long> list = getList(n);
        long currentValue = 0L;
        long minChange = Long.MAX_VALUE;
        for (Long l : list) {
            if (l == origin) {
                continue;
            }
            long abs = Math.abs(origin - l);
            if (abs < minChange) {
                currentValue = l;
                minChange = abs;
            }
            if (abs == minChange && l < currentValue) {
                currentValue = l;
            }
        }
        return Long.toString(currentValue);
    }

    public List<Long> getList(String n) {
        List<Long> candidates = new ArrayList<>();


        int middle = n.length() / 2;

        long left = Long.parseLong(n.substring(0, (n.length() % 2 == 0) ? middle : middle + 1));

        candidates.add(buildNum(left, true));//123->121
        candidates.add(buildNum(left, false));//123->1221
//        candidates.add(buildNum(left + 1, true));
//        candidates.add(buildNum(left + 1, false));
        candidates.add(buildNum(left - 1, true));
        candidates.add(buildNum(left - 1, false));


        //比如100到999之间范围的数字，就加上99
        double pow = Math.pow(10, n.length() - 1);
        candidates.add((long) pow - 1);//解决100的情况，
        candidates.add((long) pow * 10 + 1);//解决999的情况。这种情况直接1000+1

        return candidates;
    }

    private Long buildNum(long left, boolean flag) {
        StringBuilder builder = new StringBuilder();
        //镜面数字
        builder.append(left);
        if (flag) {
            builder.setLength(builder.length() - 1);
        }
        builder.reverse();
        builder.insert(0, left);
        return Long.parseLong(builder.toString());
    }


}