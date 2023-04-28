package com.xt.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1700. 无法吃午餐的学生数量
 * 每日一题：2022.10.19
 * 完成日期：2022.10.19
 * 链接：https://leetcode.cn/problems/number-of-students-unable-to-eat-lunch/
 * 描述：
 * 学校的自助午餐提供圆形和方形的三明治，分别用数字 0 和 1 表示。所有学生站在一个队列里，每个学生要么喜欢圆形的要么喜欢方形的。
 * 餐厅里三明治的数量与学生的数量相同。所有三明治都放在一个 栈 里，每一轮：
 * <p>
 * 如果队列最前面的学生 喜欢 栈顶的三明治，那么会 拿走它 并离开队列。
 * 否则，这名学生会 放弃这个三明治 并回到队列的尾部。
 * 这个过程会一直持续到队列里所有学生都不喜欢栈顶的三明治为止。
 * <p>
 * 给你两个整数数组 students 和 sandwiches ，其中 sandwiches[i] 是栈里面第 i​​​​​​ 个三明治的类型（i = 0 是栈的顶部）， students[j] 是初始队列里第 j​​​​​​ 名学生对三明治的喜好（j = 0 是队列的最开始位置）。请你返回无法吃午餐的学生数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：students = [1,1,0,0], sandwiches = [0,1,0,1]
 * 输出：0
 * 解释：
 * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,0,0,1]。
 * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,0,1,1]。
 * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [0,1,1]，三明治栈为 sandwiches = [1,0,1]。
 * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,1,0]。
 * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1,0]，三明治栈为 sandwiches = [0,1]。
 * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,1]。
 * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1]，三明治栈为 sandwiches = [1]。
 * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = []，三明治栈为 sandwiches = []。
 * 所以所有学生都有三明治吃。
 * 示例 2：
 * <p>
 * 输入：students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= students.length, sandwiches.length <= 100
 * students.length == sandwiches.length
 * sandwiches[i] 要么是 0 ，要么是 1 。
 * students[i] 要么是 0 ，要么是 1 。
 * <p>
 * 解题思路：
 * 因为长度最多为100，所以我们可以采取O(N2)的策略。
 * students转换为List，依次与sandwiches匹配，如果匹配成功则从list中移除。
 * 如果一轮循环中，List的数量没有减少，则下一轮循环一样不会减少，所以循环结束，返回list的长度。
 * <p>
 * <p>
 * <p>
 * state:done
 */
public class Solution1700 {

    public int countStudents(int[] students, int[] sandwiches) {
        List<Integer> studentList = Arrays.stream(students).boxed().collect(Collectors.toList());
        int lastNum = 0;
        int index2 = 0;
        while (lastNum != studentList.size()) {
            lastNum = studentList.size();

            Iterator<Integer> iterator = studentList.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (next == sandwiches[index2]) {
                    index2++;
                    iterator.remove();
                }
            }
        }
        return studentList.size();
    }
}