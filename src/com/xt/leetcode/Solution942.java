package com.xt.leetcode;

import java.util.*;

/**
 * 942. 增减字符串匹配
 * 每日一题：2022.05.09
 * 完成日期：2022.05.09
 * 链接：https://leetcode.cn/problems/di-string-match/
 * 描述：
 * 由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
 * <p>
 * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I' 
 * 如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D' 
 * 给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "IDID"
 * 输出：[0,4,1,3,2]
 * 示例 2：
 * <p>
 * 输入：s = "III"
 * 输出：[0,1,2,3]
 * 示例 3：
 * <p>
 * 输入：s = "DDI"
 * 输出：[3,2,0,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 105
 * s 只包含字符 "I" 或 "D"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/di-string-match
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 先根据字符串的大小关系，对每个位置排个序。
 * 例如：IDID，
 * 则第一个I，则0,1，当前位置为1的位置
 * 第二个D，则0,2,1，当前位置为1的位置，因为1比2要大，所以2在前，1在后面。当前位置改为2的位置。
 * 第三个I，则0,2,3,1，同理如上
 * 第四个D，则0,2,4,3,1
 * 拍好顺序后，分别为顺序位置赋值，0在第0位，值为0。2在第1位，值为1，3在第2位，值为2。
 * 最终，按照值大小排序即可。结果为0,4,1,3,2
 * <p>
 * <p>
 * state:
 */
public class Solution942 {

    public int[] diStringMatch(String s) {
        List<Integer> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        int currentIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (i == 0) {
                list.add(currentIndex, i);
            }
            if (aChar == 'I') {
                list.add(++currentIndex, i + 1);
            } else {
                list.add(currentIndex, i + 1);
            }
        }

        Map<Integer, Integer> value = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            value.put(list.get(i), i);
        }
        int[] ints = new int[list.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = value.get(i);
        }
        return ints;
    }
}