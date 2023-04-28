package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 1238. 循环码排列
 * 每日一题：2023.02.23
 * 完成日期：2023.02.23
 * 链接：https://leetcode.cn/problems/circular-permutation-in-binary-representation/
 * 描述：
 * 给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：
 * <p>
 * p[0] = start
 * p[i] 和 p[i+1] 的二进制表示形式只有一位不同
 * p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2, start = 3
 * 输出：[3,2,0,1]
 * 解释：这个排列的二进制表示是 (11,10,00,01)
 * 所有的相邻元素都有一位是不同的，另一个有效的排列是 [3,1,0,2]
 * 示例 2：
 * <p>
 * 输出：n = 3, start = 2
 * 输出：[2,6,7,5,4,0,1,3]
 * 解释：这个排列的二进制表示是 (010,110,111,101,100,000,001,011)
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 16
 * 0 <= start < 2^n
 * <p>
 * 解题思路：
 * 其实我们可以构造一个环，这个环中任意两个数相邻都是只相差一位。
 * 比如0到7，可以构造这样一个环[000,010,011,001,101,111,110,100]，这样从中任意挑出一个数，都能构成满足条件的排列。
 * 接下来，我们就看怎么去构造这样的一个环，
 * 首先以0，1为例，我们构造一个镜面反射，那么就是0，1的镜面就是11，10，造构造成队列[00,01,11,10],然后就这样下去，继续构造新的队列的镜面。
 * 最终会生成这样的一个环，找到start所在的位置，重新组成一个新的排列即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution1238 {

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        List<Integer> integers = makeList(list, 0, 1 << n);
        int index = integers.indexOf(start);
        List<Integer> result = integers.subList(index, integers.size());
        result.addAll(integers.subList(0, index));
        return result;
    }

    public List<Integer> makeList(List<Integer> list, int nIndex, int length) {
        List<Integer> newList = new ArrayList<>(list);
        int mask = 1 << nIndex;
        for (int i = list.size() - 1; i >= 0; i--) {
            newList.add(mask | list.get(i));
        }
        if (newList.size() == length) {
            return newList;
        }
        return makeList(newList, nIndex + 1, length);
    }
}