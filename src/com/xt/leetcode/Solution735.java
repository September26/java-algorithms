package com.xt.leetcode;

import java.util.*;

/**
 * 735. 行星碰撞
 * 每日一题：2022.07.13
 * 完成日期：2022.07.13
 * 链接：https://leetcode.cn/problems/asteroid-collision/
 * 描述：
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 * <p>
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 * <p>
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 * <p>
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3：
 * <p>
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/asteroid-collision
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 解这个题可以使用栈的方式。从左向右遍历，如果是正数则入栈。如果负数则拿绝对值和栈顶的数相比，
 * 大于的话则栈顶出栈，继续比较栈中的下一个；
 * 小于的话则继续循环；
 * 等于的话栈顶出栈，并且继续循环。
 * 这里存在一个情况，就是如果栈为空时，并且读到的还是负数，这种情况直接加入最终结果集合即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution735 {

    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            int value = asteroids[i];
            if (value > 0) {
                stack.add(value);
                continue;
            }
            int abs = Math.abs(value);
            while (stack.size() > 0) {
                Integer top = stack.peek();
                if (abs > top) {
                    stack.pop();
                } else if (abs < top) {
                    break;
                } else {
                    //value改为0，代表不入集合
                    value = 0;
                    stack.pop();
                    break;
                }
            }
            if (stack.size() == 0 && value != 0) {
                list.add(value);
            }
        }
        list.addAll(stack);
        int[] ints = list.stream().mapToInt(Integer::intValue).toArray();
        return ints;
    }
}