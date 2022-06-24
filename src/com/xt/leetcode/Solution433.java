package com.xt.leetcode;

import java.util.Vector;

/**
 * 433. 最小基因变化
 * 每日一题：2022.04.07
 * 完成日期：2022.04.07
 * 链接：https://leetcode-cn.com/problems/minimum-genetic-mutation/
 * 描述：
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * <p>
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * <p>
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 * <p>
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * <p>
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * start.length == 8
 * end.length == 8
 * 0 <= bank.length <= 10
 * bank[i].length == 8
 * start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 一开始的思路是想直接找到最短路径，就只找那些start和end之间有变化的位置，但是发现并不可行，因为中间可能存在修改了未变化的位置又改回的情况。
 * 所以对于这种情况，只能遍历所有可能性。
 * 因为总量不多，所以我们可以每次遍历bank，寻找那些可以变化的项（只变更了一位），然后尝试去改动，并且设置数组use来记录是否已使用。
 * 如果没有找到可变化的项，则返回Integer.MAX_VALUE。
 * 如果最终返回的结果小于min，则修改min。
 * <p>
 * <p>
 * state:done
 */
public class Solution433 {

    public int minMutation(String start, String end, String[] bank) {
        int min = searchMinRoute(start, end, bank, 0, new int[bank.length]);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int searchMinRoute(String start, String end, String[] bank, int time, int[] use) {
        if (start.equals(end)) {
            return time;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < bank.length; i++) {
            if (use[i] == 1) {
                continue;
            }
            String current = bank[i];
            int diffNum = getDiffNum(start, current);
            if (diffNum > 1) {
                continue;
            }
            use[i] = 1;
            int i1 = searchMinRoute(current, end, bank, time + 1, use);
            min = Math.min(i1, min);
            use[i] = 0;
        }
        return min;
    }

    private int getDiffNum(String str1, String str2) {
        int num = 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[i]) {
                continue;
            }
            num++;
        }
        return num;
    }
}