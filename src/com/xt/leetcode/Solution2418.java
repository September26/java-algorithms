package com.xt.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 2418. 按身高排序
 * 每日一题：2023.04.25
 * 完成日期：2023.04.25
 * 链接：https://leetcode.cn/problems/sort-the-people/
 * 描述：
 * 给你一个字符串数组 names ，和一个由 互不相同 的正整数组成的数组 heights 。两个数组的长度均为 n 。
 * <p>
 * 对于每个下标 i，names[i] 和 heights[i] 表示第 i 个人的名字和身高。
 * <p>
 * 请按身高 降序 顺序返回对应的名字数组 names 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：names = ["Mary","John","Emma"], heights = [180,165,170]
 * 输出：["Mary","Emma","John"]
 * 解释：Mary 最高，接着是 Emma 和 John 。
 * 示例 2：
 * <p>
 * 输入：names = ["Alice","Bob","Bob"], heights = [155,185,150]
 * 输出：["Bob","Alice","Bob"]
 * 解释：第一个 Bob 最高，然后是 Alice 和第二个 Bob 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == names.length == heights.length
 * 1 <= n <= 103
 * 1 <= names[i].length <= 20
 * 1 <= heights[i] <= 105
 * names[i] 由大小写英文字母组成
 * heights 中的所有值互不相同
 * <p>
 * 解题思路：
 * 生成一个map，映射names和heights的关系。
 * 然后对heights排序，然后按照排序后的结果，从map中找对应，生成新的字符串数组result
 * state:done
 */
public class Solution2418 {

    public String[] sortPeople(String[] names, int[] heights) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            map.put(heights[i], names[i]);
        }
        String[] result = new String[names.length];
        List<Integer> collect = Arrays.stream(heights).boxed().collect(Collectors.toList());
        collect.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < collect.size(); i++) {
            result[i] = map.get(collect.get(i));
        }
        return result;
    }
}