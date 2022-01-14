package com.xt.leetcode;

import java.util.*;

/**
 * 36. 有效的数独
 * <p>
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 *  
 * <p>
 * 注意：
 * <p>
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用 '.' 表示。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 构建三个map，分别记录每一行，每一列，范围内的字符。key为对应的行，列，范围，value为包含的字符。
 * 然后遍历所有字符，看对应的key的value里面是否包含对应的字符即可。
 * state:done
 */
public class Solution36 {

    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> xMap = new HashMap<>();
        Map<Integer, Set<Character>> yMap = new HashMap<>();
        Map<String, Set<Character>> rangeMap = new HashMap<>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                char c = board[y][x];
                Set<Character> xSet = xMap.computeIfAbsent(x, f -> new HashSet<>());
                Set<Character> ySet = yMap.computeIfAbsent(y, f -> new HashSet<>());
                Set<Character> rangeSet = rangeMap.computeIfAbsent(x / 3 + "_" + y / 3, f -> new HashSet<>());
                if (c == '.') {
                    continue;
                }
                if (!addOrNot(xSet, c)) {
                    return false;
                }
                if (!addOrNot(ySet, c)) {
                    return false;
                }
                if (!addOrNot(rangeSet, c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean addOrNot(Set<Character> set, Character c) {
        if (set.contains(c)) {
            return false;
        }
        set.add(c);
        return true;
    }
}