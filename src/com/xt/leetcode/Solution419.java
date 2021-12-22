package com.xt.leetcode;

import java.util.Vector;

/**
 * 419. 甲板上的战舰
 * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
 * <p>
 * 战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/battleships-in-a-board
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 我的解题思路有一点low，不过先完成把。遍历每一个点。
 * 如果读到了'X'，则num++,并且把当前的节点的值改为'.',然后X轴，Y轴都向着增加的方向遍历，读到'X'后继续改为'.'。
 * 这样就避免了重复读到X了。
 * 状态:完成
 */
public class Solution419 {
    final char x = 'X';
    final char point = '.';

    public int countBattleships(char[][] board) {
        int num = 0;
        for (int i = 0; i < board.length; i++) {
            char[] chars = board[i];
            int i2 = 0;
            while (i2 < chars.length) {
                if (chars[i2] == point) {
                    i2++;
                    continue;
                }
                num++;
                chars[i2] = point;
                while (i2 + 1 < chars.length && chars[i2 + 1] == x) {
                    i2++;
                    chars[i2] = point;
                }
                int local = i;
                while (local + 1 < board.length && board[local + 1][i2] == x) {
                    local++;
                    board[local][i2] = point;
                }
            }

        }
        return num;
    }
}