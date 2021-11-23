package com.xt.niuke;


import java.util.*;


/**
 * 描述
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。
 * 求按从小到大的顺序的第 n个丑数。
 * <p>
 * 数据范围：0≤n≤2000
 * 要求：空间复杂度 O(n)， 时间复杂度 O(n)
 */
public class Solution49 {

    Set<Integer> set = new HashSet<>();

//    public int GetUglyNumber_Solution(int index) {
//        int i = 0;
//        int num2 = 0;
//        int num3 = 0;
//        int num5 = 0;
//        int[] result = new int[index];
//        result[0] = 1;
//
//        while (i++ < index - 1) {
//            int i2 = result[num2] * 2;
//            int i3 = result[num3] * 3;
//            int i5 = result[num5] * 5;
//            int min = Math.min(Math.min(i2, i3), i5);
//            result[i] = min;
//            if (min == i2) {
//                num2++;
//            }
//            if (min == i3) {
//                num3++;
//            }
//            if (min == i5) {
//                num5++;
//            }
//        }
//        return result[index - 1];
//    }

    //从小到大直接找
    public int GetUglyNumber_Solution(int index) {
        if (index <= 5) {
            return index;
        }
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        int num = 5;
        int i = 6;
        while (num < index) {
            if (isChoushu(i)) {
                num++;
            }
            i++;
        }
        return --i;
    }

    private boolean isChoushu(int i) {
        if (i % 2 == 0) {
            if (set.contains(i / 2)) {
                set.add(i);
                return true;
            }
        }
        if (i % 3 == 0) {
            if (set.contains(i / 3)) {
                set.add(i);
                return true;
            }
        }
        if (i % 5 == 0) {
            if (set.contains(i / 5)) {
                set.add(i);
                return true;
            }
        }
        return false;
    }
}
