package com.xt.leetcode;

import java.util.*;

/**
 * 2100. 适合打劫银行的日子
 * 每日一题：2022.03.06
 * 完成日期：2022.03.06
 * 链接：https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank/
 * 描述：
 * 你和一群强盗准备打劫银行。给你一个下标从 0 开始的整数数组 security ，其中 security[i] 是第 i 天执勤警卫的数量。日子从 0 开始编号。同时给你一个整数 time 。
 * <p>
 * 如果第 i 天满足以下所有条件，我们称它为一个适合打劫银行的日子：
 * <p>
 * 第 i 天前和后都分别至少有 time 天。
 * 第 i 天前连续 time 天警卫数目都是非递增的。
 * 第 i 天后连续 time 天警卫数目都是非递减的。
 * 更正式的，第 i 天是一个合适打劫银行的日子当且仅当：security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
 * <p>
 * 请你返回一个数组，包含 所有 适合打劫银行的日子（下标从 0 开始）。返回的日子可以 任意 顺序排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：security = [5,3,3,3,5,6,2], time = 2
 * 输出：[2,3]
 * 解释：
 * 第 2 天，我们有 security[0] >= security[1] >= security[2] <= security[3] <= security[4] 。
 * 第 3 天，我们有 security[1] >= security[2] >= security[3] <= security[4] <= security[5] 。
 * 没有其他日子符合这个条件，所以日子 2 和 3 是适合打劫银行的日子。
 * 示例 2：
 * <p>
 * 输入：security = [1,1,1,1,1], time = 0
 * 输出：[0,1,2,3,4]
 * 解释：
 * 因为 time 等于 0 ，所以每一天都是适合打劫银行的日子，所以返回每一天。
 * 示例 3：
 * <p>
 * 输入：security = [1,2,3,4,5,6], time = 2
 * 输出：[]
 * 解释：
 * 没有任何一天的前 2 天警卫数目是非递增的。
 * 所以没有适合打劫银行的日子，返回空数组。
 * 示例 4：
 * <p>
 * 输入：security = [1], time = 5
 * 输出：[]
 * 解释：
 * 没有日子前面和后面有 5 天时间。
 * 所以没有适合打劫银行的日子，返回空数组。
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= security.length <= 10^5
 * 0 <= security[i], time <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题的长度是10^5，那么时间复杂度一定是O(n)，最高O(n*lngN)级别
 * 我们可以使用动态区间的方案。设置两个区间，left区间和right区间，长度为time。
 * left区间从0开始，right区间从time开始。
 * left区间中，如果新读取的数字，比上一个数字小于等于，则leftNum，表示连续递减的区间数量+1。反之则清0。
 * right区间原理类似。
 * 这样我们就可以求得leftNum和rightNum，分别代表左右区间连续的数量。如果数量大于等于time，则加入到list当中。
 * 当然，其实rightNum记录的并不仅仅是index右侧的连续区间数量，不过这对结果没有影响，就不去处理了。
 * <p>
 * <p>
 * state:done
 */
public class Solution2100 {

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        ArrayList<Integer> list = new ArrayList<>();

        int index = 0;
        int leftNum = 0;//单调递减
        int rightNum = 0;//单调递增
        while (index + time < security.length) {
            if (index > 0) {
                if (security[index] <= security[index - 1]) {
                    leftNum++;
                } else {
                    leftNum = 0;
                }
                if (security[index + time] >= security[index + time - 1]) {
                    rightNum++;
                } else {
                    rightNum = 0;
                }
            }
            if (leftNum >= time && rightNum >= time) {
                list.add(index);
            }
            index++;
        }
        return list;
    }
}