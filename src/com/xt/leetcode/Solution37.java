package com.xt.leetcode;

import java.util.*;

/**
 * 37. 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 * <p>
 * 数独的解法需 遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 我的解题思路是这样的，
 * 1。遍历board的所有位置，对每一个.位置的，都计算出其可填充的数字集合，然后包装成Range对象，Range中包含每个位置可以填充哪些数字的集合，以及对应的位置。
 * 2。筛选出List<Range>中所有集合长度为1的。（这里我通过排序的手段进行的，但是其实一遍就能找出来的，这里完全可以优化掉）
 * 3。因为长度为1的，只有一个选择那就是必选的。那么就把这些必须按填充近二维数组中，这里需要注意一点，每个数字填充之后都要再次做一个检查。
 * 4。填充完成必须按的之后，剩下的就是可选的。如果长度为0，那就结束了。
 * 5。如果长度大于0，那就从组合中选择一个，然后递归调用，继续执行步骤1。如果返回true则代表成功，false则代表失败，就换一种选择。
 * <p>
 * <p>
 * state:done
 */
public class Solution37 {

    char[][] result;

    public void solveSudoku(char[][] board) {
        result = new char[board.length][board[0].length];
        mustFillBoard(board);
        //递归遍历所有可能性
        copyBoard(result, board);
    }

    public boolean mustFillBoard(char[][] board) {
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
                addOrNot(xSet, c);
                addOrNot(ySet, c);
                addOrNot(rangeSet, c);
            }
        }
        Map<String, Range> map = new HashMap<>();//每个位置可以填充的数字范围
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                char c = board[y][x];
                if (c != '.') {
                    continue;
                }
                String key = y + "_" + x;
                Set<Character> xSet = xMap.get(x);
                Set<Character> ySet = yMap.get(y);
                Set<Character> rangSet = rangeMap.get(x / 3 + "_" + y / 3);
                Range range = new Range();
                range.y = y;
                range.x = x;
                range.key = key;
                for (char i = '1'; i <= '9'; i++) {
                    if (xSet.contains(i)) {
                        continue;
                    }
                    if (ySet.contains(i)) {
                        continue;
                    }
                    if (rangSet.contains(i)) {
                        continue;
                    }
                    range.list.add(i);
                }
                if (range.list.size() == 0) {
                    return false;
                }
                map.put(key, range);
            }
        }

        List<Range> valueList = new ArrayList<>(map.values());
        valueList.sort((o1, o2) -> {
            if (o1.list.size() == o2.list.size()) {
                return 0;
            }
            return o1.list.size() < o2.list.size() ? -1 : 1;
        });

        boolean haveLength1 = false;
        //排序后看是否有1的，有长度为1的，则全部填充，在重新计算
        for (int i = 0; i < valueList.size(); i++) {
            Range range = valueList.get(i);
            if (range.list.size() > 1) {
                break;
            }
            if (range.list.size() == 1) {
                haveLength1 = true;
                Character character = range.list.get(0);
                Set<Character> xSet = xMap.get(range.x);
                Set<Character> ySet = yMap.get(range.y);
                Set<Character> rangSet = rangeMap.get(range.x / 3 + "_" + range.y / 3);
                if (xSet.contains(character) || ySet.contains(character) || rangSet.contains(character)) {
                    return false;
                }
                board[range.y][range.x] = character;
                xSet.add(character);
                ySet.add(character);
                rangSet.add(character);
                continue;
            }
        }
        if (haveLength1) {
            //填充1个的不会有返回值
            boolean b = mustFillBoard(board);
            return b;
        }

        //根据可能性，尝试填充
        if (valueList.size() == 0) {
            result = board;
            return true;
        }
        Range range = valueList.get(0);
        for (char c : range.list) {
            char[][] copy = new char[board.length][board[0].length];
            copyBoard(board, copy);
            copy[range.y][range.x] = c;
            boolean result = mustFillBoard(copy);
            if (result) {
                return true;
            }
        }
        return false;
    }

    private void copyBoard(char[][] from, char[][] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = Arrays.copyOfRange(from[i], 0, from[i].length);
        }
    }

    public boolean addOrNot(Set<Character> set, Character c) {
        if (set.contains(c)) {
            return false;
        }
        set.add(c);
        return true;
    }

    static class Range {
        String key;
        int x;
        int y;
        List<Character> list = new ArrayList<>();
    }
}