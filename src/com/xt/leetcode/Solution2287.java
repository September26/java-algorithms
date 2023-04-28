package com.xt.leetcode;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.Vector;

/**
 * 2287. 重排字符形成目标字符串
 * 每日一题：2022.01.13
 * 完成日期：2022.01.13
 * 链接：https://leetcode.cn/problems/rearrange-characters-to-make-target-string/
 * 描述：
 * 给你两个下标从 0 开始的字符串 s 和 target 。你可以从 s 取出一些字符并将其重排，得到若干新的字符串。
 * <p>
 * 从 s 中取出字符并重新排列，返回可以形成 target 的 最大 副本数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ilovecodingonleetcode", target = "code"
 * 输出：2
 * 解释：
 * 对于 "code" 的第 1 个副本，选取下标为 4 、5 、6 和 7 的字符。
 * 对于 "code" 的第 2 个副本，选取下标为 17 、18 、19 和 20 的字符。
 * 形成的字符串分别是 "ecod" 和 "code" ，都可以重排为 "code" 。
 * 可以形成最多 2 个 "code" 的副本，所以返回 2 。
 * 示例 2：
 * <p>
 * 输入：s = "abcba", target = "abc"
 * 输出：1
 * 解释：
 * 选取下标为 0 、1 和 2 的字符，可以形成 "abc" 的 1 个副本。
 * 可以形成最多 1 个 "abc" 的副本，所以返回 1 。
 * 注意，尽管下标 3 和 4 分别有额外的 'a' 和 'b' ，但不能重用下标 2 处的 'c' ，所以无法形成 "abc" 的第 2 个副本。
 * 示例 3：
 * <p>
 * 输入：s = "abbaccaddaeea", target = "aaaaa"
 * 输出：1
 * 解释：
 * 选取下标为 0 、3 、6 、9 和 12 的字符，可以形成 "aaaaa" 的 1 个副本。
 * 可以形成最多 1 个 "aaaaa" 的副本，所以返回 1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 100
 * 1 <= target.length <= 10
 * s 和 target 由小写英文字母组成
 * 解题思路：
 * 分别就出s和target中每个字符出现的次数。
 * 然后遍历target的每个字符，找出其在target中出现的次数和s中出现的次数，用后者除以前者得到i，求i的最小值。
 *
 * <p>
 * state:done
 */
public class Solution2287 {

    public int rearrangeCharacters(String s, String target) {
        TreeMap<Character, Integer> treeMap = new TreeMap<>();

        int[] nums = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            nums[chars[i] - 'a']++;
        }
        char[] chars1 = target.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            char c = chars1[i];
            Integer num = treeMap.getOrDefault(c, 0);
            treeMap.put(c, num + 1);
        }
        int min = Integer.MAX_VALUE;
        for (char key : treeMap.keySet()) {
            int i = nums[key - 'a'] / treeMap.get(key);
            min = Math.min(min, i);
        }
        return min;
    }
}