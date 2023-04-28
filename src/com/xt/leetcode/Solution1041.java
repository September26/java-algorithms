package com.xt.leetcode;

import java.util.Vector;

/**
 * 1688.比赛中的配对次数
 * 每日一题：2023.04.11
 * 完成日期：2023.04.11
 * 链接：https://leetcode.cn/problems/robot-bounded-in-circle/
 * 描述：
 * 在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。注意:
 *
 * 北方向 是y轴的正方向。
 * 南方向 是y轴的负方向。
 * 东方向 是x轴的正方向。
 * 西方向 是x轴的负方向。
 * 机器人可以接受下列三条指令之一：
 *
 * "G"：直走 1 个单位
 * "L"：左转 90 度
 * "R"：右转 90 度
 * 机器人按顺序执行指令 instructions，并一直重复它们。
 *
 * 只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：instructions = "GGLLGG"
 * 输出：true
 * 解释：机器人最初在(0,0)处，面向北方。
 * “G”:移动一步。位置:(0,1)方向:北。
 * “G”:移动一步。位置:(0,2).方向:北。
 * “L”:逆时针旋转90度。位置:(0,2).方向:西。
 * “L”:逆时针旋转90度。位置:(0,2)方向:南。
 * “G”:移动一步。位置:(0,1)方向:南。
 * “G”:移动一步。位置:(0,0)方向:南。
 * 重复指令，机器人进入循环:(0,0)——>(0,1)——>(0,2)——>(0,1)——>(0,0)。
 * 在此基础上，我们返回true。
 * 示例 2：
 *
 * 输入：instructions = "GG"
 * 输出：false
 * 解释：机器人最初在(0,0)处，面向北方。
 * “G”:移动一步。位置:(0,1)方向:北。
 * “G”:移动一步。位置:(0,2).方向:北。
 * 重复这些指示，继续朝北前进，不会进入循环。
 * 在此基础上，返回false。
 * 示例 3：
 *
 * 输入：instructions = "GL"
 * 输出：true
 * 解释：机器人最初在(0,0)处，面向北方。
 * “G”:移动一步。位置:(0,1)方向:北。
 * “L”:逆时针旋转90度。位置:(0,1).方向:西。
 * “G”:移动一步。位置:(- 1,1)方向:西。
 * “L”:逆时针旋转90度。位置:(- 1,1)方向:南。
 * “G”:移动一步。位置:(- 1,0)方向:南。
 * “L”:逆时针旋转90度。位置:(- 1,0)方向:东方。
 * “G”:移动一步。位置:(0,0)方向:东方。
 * “L”:逆时针旋转90度。位置:(0,0)方向:北。
 * 重复指令，机器人进入循环:(0,0)——>(0,1)——>(- 1,1)——>(- 1,0)——>(0,0)。
 * 在此基础上，我们返回true。
 *
 *
 * 提示：
 *
 * 1 <= instructions.length <= 100
 * instructions[i] 仅包含 'G', 'L', 'R'
 * <p>
 * 解题思路：
 * 设置position记录坐标位置，设置angle记录角度，0代表正北，1代表正东，2代表正南，-1代表正西。
 * 然后遍历字符串，如果遇到G时判断angle角度，看情况进一步。
 * 如果遇到L或者R时，则调整角度，如果角度超过上限，则对应的加减。
 * <p>
 * <p>
 * state:done
 */
public class Solution1041 {

    public boolean isRobotBounded(String instructions) {
        int[] position = new int[]{0, 0};
        int angle = 0;
        char[] chars = instructions.toCharArray();
        for (int i = 0; i < 4; i++) {
            for (char c : chars) {
                if (c == 'G') {
                    if (angle == 0) {
                        position[1]++;
                    } else if (angle == 1) {
                        position[0]++;
                    } else if (angle == -1) {
                        position[0]--;
                    } else {
                        position[1]--;
                    }
                    continue;
                }
                if (c == 'L') {
                    angle--;
                } else if (c == 'R') {
                    angle++;
                }
                if (angle < -1) {
                    angle += 4;
                } else if (angle > 2) {
                    angle -= 4;
                }
            }
        }
        return position[0] == 0 && position[1] == 0;
    }
}