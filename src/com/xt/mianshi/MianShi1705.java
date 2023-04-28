package com.xt.mianshi;

import java.util.*;

/**
 * 面试题 17.05. 字母与数字
 * 每日一题：2023.03.16
 * 完成日期：2023.03.16
 * 链接：https://leetcode.cn/problems/find-longest-subarray-lcci/
 * 描述：
 * 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
 * <p>
 * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
 * <p>
 * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
 * 示例 2:
 * <p>
 * 输入: ["A","A"]
 * <p>
 * 输出: []
 * 提示：
 * <p>
 * array.length <= 100000
 * 解题思路：
 * 记录每个位置charNum-intNum的值，按照这个值来分组，满足统一条件的两个位置相减，就是符合条件的子数组。
 * 构建一个Map<Integer, List<Integer>>的结构，某个位置charNum-intNum的结果，value为满足这个结果的所有位置的集合。
 * 这里要特别注意一下diff=0的情况，0的话时候额外填充一个0。
 * 然后遍历map的value，取出集合，集合的最后一位减去第一位，就是满足条件的长度。求最长。
 *
 * state:done
 */
public class MianShi1705 {

    public String[] findLongestSubarray(String[] array) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int intNum = 0;
        int charNum = 0;
        for (int i = 0; i < array.length; i++) {
            String s = array[i];
            char c = s.toCharArray()[0];
            if ((c >= 'A' && c <= 'Z') || c >= 'a' && c <= 'z') {
                charNum++;
            } else {
                intNum++;
            }
            int diff = charNum - intNum;
            List<Integer> integers = obtainQueue(map, diff);
            if (diff == 0 && integers.size() == 0) {
                integers.add(0);
            }
            integers.add(i + 1);
        }

        int maxLength = 0;
        int maxStart = 0;
        int maxEnd = 0;
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            if (list.size() == 0) {
                continue;
            }
            int length;
            if (list.size() == 1) {
                continue;
            }
            length = list.get(list.size() - 1) - list.get(0);
            if (length > maxLength || (length == maxLength && list.get(0) < maxStart)) {
                maxLength = length;
                maxStart = list.get(0);
                maxEnd = list.get(list.size() - 1);
            }
        }
        if (maxLength == 0) {
            return new String[0];
        }
        String[] strings = Arrays.copyOfRange(array, maxStart, maxEnd);
        return strings;
    }

    private List<Integer> obtainQueue(Map<Integer, List<Integer>> map, int key) {
        List<Integer> integers = map.get(key);
        if (integers == null) {
            integers = new ArrayList<>();
            map.put(key, integers);
        }
        return integers;
    }

}















