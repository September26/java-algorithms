package com.xt.leetcode;

import java.util.Stack;
import java.util.Vector;

/**
 * 946. 验证栈序列
 * 每日一题：2022.08.31
 * 完成日期：2022.08.31
 * 链接：https://leetcode.cn/problems/validate-stack-sequences/
 * 描述：
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= pushed.length <= 1000
 * 0 <= pushed[i] <= 1000
 * pushed 的所有元素 互不相同
 * popped.length == pushed.length
 * popped 是 pushed 的一个排列
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-stack-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 利用栈来的功能来实现，遍历pushed，如果和popped中index位不同，则pushed中元素value加入stack栈。
 * 如果相同，则index++，并且从上开始遍历stack，如果stack中头和index位置值相等，则index++。
 * 最终判断index和pushed.length长度是否相等即可。
 *
 * <p>
 * state:done
 */
public class Solution946 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < pushed.length; i++) {
            int value = pushed[i];
            if (value != popped[index]) {
                stack.add(value);
                continue;
            }
            index++;
            while (stack.size() > 0 && stack.peek() == popped[index]) {
                index++;
                stack.pop();
            }
        }
        return index == popped.length;
    }
}