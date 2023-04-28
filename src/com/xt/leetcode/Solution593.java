package com.xt.leetcode;


import java.util.*;

/**
 * 593. 有效的正方形
 * 每日一题：2022.07.29
 * 完成日期：2022.07.29
 * 链接：https://leetcode.cn/problems/valid-square/
 * 描述：
 * 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
 * <p>
 * 点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
 * <p>
 * 一个 有效的正方形 有四条等边和四个等角(90度角)。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 * 示例 2:
 * <p>
 * 输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * 输出：false
 * 示例 3:
 * <p>
 * 输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * 输出：true
 *  
 * <p>
 * 提示:
 * <p>
 * p1.length == p2.length == p3.length == p4.length == 2
 * -10^4 <= xi, yi <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 是否是有效的正方形，我们可以判断4个点相互连接，共有六条线，这六条线中4个短的是否相等以及2个长的是否相等即可。
 * 因为求平方根有可能产生精度问题，所以我们直接使用平方。
 * <p>
 * <p>
 * state:done
 */
public class Solution593 {

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        List<int[]> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        TreeMap<Integer, Integer> numMap = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int[] pp1 = list.get(i);
                int[] pp2 = list.get(j);
                int s = (int) (Math.pow(pp1[0] - pp2[0], 2.0) + Math.pow(pp1[1] - pp2[1], 2.0));
                numMap.put(s, numMap.getOrDefault(s, 0) + 1);
            }
        }
        if (numMap.size() != 2) {
            return false;
        }
        Collection<Integer> values = numMap.values();
        Iterator<Integer> iterator = values.iterator();
        Integer next1 = iterator.next();
        Integer next2 = iterator.next();
        return next1 == 4 && next2 == 2;
    }
}