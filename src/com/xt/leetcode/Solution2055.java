package com.xt.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 2055. 蜡烛之间的盘子
 * 每日一题：2022.03.08
 * 完成日期：2022.03.08
 * 链接：https://leetcode-cn.com/problems/plates-between-candles/
 * 描述：
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
 * <p>
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
 * <p>
 * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
 * 输出：[2,3]
 * 解释：
 * - queries[0] 有两个盘子在蜡烛之间。
 * - queries[1] 有三个盘子在蜡烛之间。
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * 输出：[9,0,0,0,0]
 * 解释：
 * - queries[0] 有 9 个盘子在蜡烛之间。
 * - 另一个查询没有盘子在蜡烛之间。
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= s.length <= 10^5
 * s 只包含字符 '*' 和 '|' 。
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plates-between-candles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先根据s构建一个集合，集合中存储｜的位置。
 * 然后对queries进行遍历，取start和end，在集合中分别搜索比start相等或者更大的位置和比end相等或者更小的位置。
 * 如果返回的结果都为-1，说明集合为空，则int[i]=0;
 * 如果返回结果left>=right，说明start和end范围只有一个|，所以int[i]=0;
 * 剩余的情况就是满足条件的，则结果为list.get(right) - list.get(left) - (right - left);
 * 时间复杂度为O(n*lngN)
 * <p>
 * <p>
 * state:done
 */
public class Solution2055 {
//    public int[] platesBetweenCandles(String s, int[][] queries) {
//        int n = s.length();
//        int[] preSum = new int[n];
//        for (int i = 0, sum = 0; i < n; i++) {
//            if (s.charAt(i) == '*') {
//                sum++;
//            }
//            preSum[i] = sum;
//        }
//        int[] left = new int[n];
//        for (int i = 0, l = -1; i < n; i++) {
//            if (s.charAt(i) == '|') {
//                l = i;
//            }
//            left[i] = l;
//        }
//        int[] right = new int[n];
//        for (int i = n - 1, r = -1; i >= 0; i--) {
//            if (s.charAt(i) == '|') {
//                r = i;
//            }
//            right[i] = r;
//        }
//        int[] ans = new int[queries.length];
//        for (int i = 0; i < queries.length; i++) {
//            int[] query = queries[i];
//            int x = right[query[0]], y = left[query[1]];
//            ans[i] = x == -1 || y == -1 || x >= y ? 0 : preSum[y] - preSum[x];
//        }
//        return ans;
//    }


    public int[] platesBetweenCandles(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '|') {
                list.add(i);
            }
        }
        int[] ints = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];
            int left = selfBinarySearch(list, start, false);
            int right = selfBinarySearch(list, end, true);
            if (left >= right) {
                ints[i] = 0;
                continue;
            }
            ints[i] = list.get(right) - list.get(left) - (right - left);
        }
        return ints;
    }

    /**
     * 二分查找找到list中小于等于i和大于等于i的数
     * 大于等于i:ok
     * 小于等于i:
     */
    public int selfBinarySearch(List<Integer> list, int i, boolean isSmall) {
        if (list.size() == 0) {
            return -1;
        }
        int left = 0;
        int right = list.size() - 1;
        int middle;
        int ins = isSmall ? 0 : right;
        while (left <= right) {
            middle = (left + right) / 2;
            if (list.get(middle) == i) {
                ins = middle;
                break;
            }
            if (list.get(middle) > i) {
                if (!isSmall) {
                    ins = middle;
                }
                right = middle - 1;
            }
            if (list.get(middle) < i) {
                left = middle + 1;
                if (isSmall) {
                    ins = middle;
                }
            }
        }
        return ins;
    }

}