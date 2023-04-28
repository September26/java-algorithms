package com.xt.leetcode;

import java.util.TreeMap;
import java.util.Vector;

/**
 * 1450. 在既定时间做作业的学生人数
 * 每日一题：2022.08.19
 * 完成日期：2022.08.19
 * 链接：https://leetcode.cn/problems/number-of-students-doing-homework-at-a-given-time/
 * 描述：
 * 给你两个整数数组 startTime（开始时间）和 endTime（结束时间），并指定一个整数 queryTime 作为查询时间。
 *
 * 已知，第 i 名学生在 startTime[i] 时开始写作业并于 endTime[i] 时完成作业。
 *
 * 请返回在查询时间 queryTime 时正在做作业的学生人数。形式上，返回能够使 queryTime 处于区间 [startTime[i], endTime[i]]（含）的学生人数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：startTime = [1,2,3], endTime = [3,2,7], queryTime = 4
 * 输出：1
 * 解释：一共有 3 名学生。
 * 第一名学生在时间 1 开始写作业，并于时间 3 完成作业，在时间 4 没有处于做作业的状态。
 * 第二名学生在时间 2 开始写作业，并于时间 2 完成作业，在时间 4 没有处于做作业的状态。
 * 第三名学生在时间 3 开始写作业，预计于时间 7 完成作业，这是是唯一一名在时间 4 时正在做作业的学生。
 * 示例 2：
 *
 * 输入：startTime = [4], endTime = [4], queryTime = 4
 * 输出：1
 * 解释：在查询时间只有一名学生在做作业。
 * 示例 3：
 *
 * 输入：startTime = [4], endTime = [4], queryTime = 5
 * 输出：0
 * 示例 4：
 *
 * 输入：startTime = [1,1,1,1], endTime = [1,3,2,4], queryTime = 7
 * 输出：0
 * 示例 5：
 *
 * 输入：startTime = [9,8,7,6,5,4,3,2,1], endTime = [10,10,10,10,10,10,10,10,10], queryTime = 5
 * 输出：5
 *  
 *
 * 提示：
 *
 * startTime.length == endTime.length
 * 1 <= startTime.length <= 100
 * 1 <= startTime[i] <= endTime[i] <= 1000
 * 1 <= queryTime <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-students-doing-homework-at-a-given-time
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这个问题很适合用treeMap来解决，开始时+1，结束时-1。
 * 最后遍历treemap，用currentValue来记录当前的值，key>queryTime跳出循环。
 * 但是本题有个问题，就是前后都包的，比如4开始，4结束，查询4时，要求生效。
 * 所以我们稍微做一点变更，把endTime+1即可。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1450 {

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < startTime.length; i++) {
            int value = startTime[i];
            Integer addNum = map.getOrDefault(value, 0);
            map.put(value, addNum + 1);

            int value2 = endTime[i] + 1;
            Integer reduceNum = map.getOrDefault(value2, 0);
            map.put(value2, reduceNum - 1);
        }
        int currentValue = 0;
        for (int key : map.keySet()) {
            if (key > queryTime) {
                break;
            }
            currentValue += map.get(key);
        }
        return currentValue;
    }
}