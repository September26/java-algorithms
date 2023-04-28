package com.xt.leetcode;

import java.util.Arrays;

/**
 * 1641. 统计字典序元音字符串的数目
 * 每日一题：2023.03.29
 * 完成日期：2023.03.29
 * 链接：https://leetcode.cn/problems/count-sorted-vowel-strings/description/
 * 描述：
 * 给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。
 * <p>
 * 字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 1
 * 输出：5
 * 解释：仅由元音组成的 5 个字典序字符串为 ["a","e","i","o","u"]
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：15
 * 解释：仅由元音组成的 15 个字典序字符串为
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"]
 * 注意，"ea" 不是符合题意的字符串，因为 'e' 在字母表中的位置比 'a' 靠后
 * 示例 3：
 * <p>
 * 输入：n = 33
 * 输出：66045
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 50
 * 解题思路：
 * 这题其实就是一个动态规划的题目。
 * 我们用一个二位数组ints来存放长度为i时分别只允许放入 (a, e, i, o, u)情况下的可能性（按字典序排列）。
 * 长度为1时，自然5个元音的可能性都为1。
 * 长度为2时，先放入u，则最后一位只能时u。所以长度为2并且第一位为u的可能性为1；也就是ints[i][0]=ints[i-1][0]
 * 同理，所以长度为2并且第一位为o的可能性为2；也就是ints[i][1]=ints[i-1][1]+ints[i-1][0]
 * 同理，所以长度为2并且第一位为i的可能性为3；也就是ints[i][2]=ints[i-1][2]+...;
 * 同理，所以长度为2并且第一位为e的可能性为4；也就是ints[i][3]=ints[i-1][3]+...;
 * 同理，所以长度为2并且第一位为a的可能性为5；也就是ints[i][4]=ints[i-1][4]+...;
 * 如果长度为3时，也可以按照上面的理论继续，因为如果第一位为u那么后面只能选择第二位也为u的可能性。
 * ints[3][0]=ints[2][0]
 * ints[3][1]=ints[2][1]++ints[2][0];
 * 最后，我们统计数组ints[n-1]的sum即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution1641 {

    public int countVowelStrings(int n) {
        int[][] ints = new int[n][5];
        Arrays.fill(ints[0], 1);
        for (int i = 1; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < 5; j++) {
                sum += ints[i - 1][j];
                ints[i][j] = sum;
            }
        }
        return Arrays.stream(ints[n - 1]).sum();
    }
}