package com.xt.leetcode;

import java.util.*;

/**
 * 13.罗马数字转整数
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * done
 */
public class Solution13 {

    //s= MMDCCXC
    public int romanToInt(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        int sum = 0;
        int index = s.length();
        int level = 1;
        while (index >= 1) {
            String sub = s.substring(index - 1, index);
            Integer integer = map.get(sub);
            if (level > integer) {
                sum -= integer;
            } else {
                sum += integer;
                level = integer;
            }
            index--;
        }
        return sum;
    }


    private int countTime(String s, String c) {
        return s.length() - s.replaceAll(s, c).length();
    }

}