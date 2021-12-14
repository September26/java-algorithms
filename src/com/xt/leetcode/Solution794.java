package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 794. 有效的井字游戏
 * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 * <p>
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 * <p>
 * 以下是井字游戏的规则：
 * <p>
 * 玩家轮流将字符放入空位（' '）中。
 * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * done
 */
public class Solution794 {


    public boolean validTicTacToe(String[] board) {

        List<String[]> boards = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            String[] split = board[i].split("");
            boards.add(split);
        }


        int xNum = 0;
        int oNum = 0;
        boolean xWin = false;
        boolean oWin = false;

        String[] line = new String[3];
        Boolean[] allEquals = new Boolean[]{true, true, true};
        for (int i = 0; i < boards.size(); i++) {
            String[] itemLine = boards.get(i);
            if (i == 0) {
                line = itemLine;
            } else {
                for (int j = 0; j < line.length; j++) {
                    allEquals[j] = allEquals[j] && line[j].equals(itemLine[j]);
                }
            }

            String firstChar = null;
            boolean allEqual = true;
            for (int j = 0; j < itemLine.length; j++) {
                String s = itemLine[j];
                if (s.equals("X")) {
                    xNum++;
                } else if (s.equals("O")) {
                    oNum++;
                }
                if (firstChar == null) {
                    firstChar = s;
                } else {
                    allEqual = allEqual && firstChar.equals(s);
                }

            }
            //计算横行是不是都一样
            if (allEqual) {
                if (firstChar.equals("X")) {
                    xWin = true;
                }
                if (firstChar.equals("O")) {
                    oWin = true;
                }
            }
        }


        //判断纵向是否相同
        for (int i = 0; i < allEquals.length; i++) {
            if (allEquals[i]) {
                if (line[i].equals("X")) {
                    xWin = true;
                }
                if (line[i].equals("O")) {
                    oWin = true;
                }
            }
        }

        //判断斜向是否相同
        String slantFirstChar = null;
        boolean isSlantAllEqual = true;
        for (int i = 0; i < boards.size(); i++) {
            if (slantFirstChar == null) {
                slantFirstChar = boards.get(i)[i];
            } else {
                isSlantAllEqual = isSlantAllEqual && slantFirstChar.equals(boards.get(i)[i]);
            }
        }
        if (isSlantAllEqual && slantFirstChar != null) {
            if (slantFirstChar.equals("X")) {
                xWin = true;
            }
            if (slantFirstChar.equals("O")) {
                oWin = true;
            }
        }

        slantFirstChar = null;
        isSlantAllEqual = true;
        for (int i = 0; i < boards.size(); i++) {
            String current = boards.get(i)[boards.get(i).length - 1 - i];
            if (slantFirstChar == null) {
                slantFirstChar = current;
            } else {
                isSlantAllEqual = isSlantAllEqual && slantFirstChar.equals(current);
            }
        }
        if (isSlantAllEqual && slantFirstChar != null) {
            if (slantFirstChar.equals("X")) {
                xWin = true;
            }
            if (slantFirstChar.equals("O")) {
                oWin = true;
            }
        }
        if (xNum < oNum) {
            return false;
        }
        if (xNum - 1 > oNum) {
            return false;
        }
        if (xWin && oWin) {
            return false;
        }
        if (xWin) {
            return xNum == oNum + 1;
        }
        if (oWin) {
            return xNum == oNum;
        }
        return true;
    }

}