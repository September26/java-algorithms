package com.xt.leetcode;


import java.util.*;

/**
 * 630. 课程表 III
 * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
 * <p>
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
 * <p>
 * 返回你最多可以修读的课程数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 输出：3
 * 解释：
 * 这里一共有 4 门课程，但是你最多可以修 3 门：
 * 首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
 * 第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
 * 第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
 * 第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
 * 示例 2：
 * <p>
 * 输入：courses = [[1,2]]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：courses = [[3,2],[4,3]]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 题解：参考官方的题解
 * state:done
 */
public class Solution630 {

    List<int[]> courList = new ArrayList<>();

    /**
     * 贪心算法，求每一步的最优解
     *
     * @param courses
     * @return
     */
    public int scheduleCourse(int[][] courses) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i][0] <= courses[i][1]) {
                courList.add(courses[i]);
            }
        }
        //排序
        courList.sort((o1, o2) -> {
            if (o1[1] > o2[1]) {
                return 1;
            }
            if (o1[1] < o2[1]) {
                return -1;
            }
            return 0;
        });

        int index = 0;// 位置
        int start = 0;//已花费的时间
        List<int[]> selectList = new ArrayList<>();//花费时间最少的在前面，最长的在尾端
        while (index < courList.size()) {
            int[] currentInts = courList.get(index);
            //判断能否直接加入
            if (start + currentInts[0] <= currentInts[1]) {
                addToList(currentInts, selectList);
                //更新start
                start = start + currentInts[0];
                index++;
                continue;
            }
            //不能加入，则替换队列中花费时间更长的
            int lastIndex = selectList.size() - 1;
            int[] spendMaxTime = selectList.get(lastIndex);
            if (spendMaxTime[0] > currentInts[0]) {
                //替换
                int[] remove = selectList.remove(lastIndex);
                start = start - remove[0];
                addToList(currentInts, selectList);
                start = start + currentInts[0];
            }
            index++;
        }
        return selectList.size();
    }

    /**
     * 二叉法插入
     *
     * @param insert
     * @param selectList 有序队列
     */
    public void addToList(int[] insert, List<int[]> selectList) {
        if (selectList.size() == 0) {
            selectList.add(insert);
            return;
        }
        int start = 0;
        int end = selectList.size() - 1;
        int middle;
        while (true) {
            middle = (start + end) / 2;
            int middleValue = selectList.get(middle)[0];
            if (middle == end || middle == start) {
                //和比较
                if (insert[0] > selectList.get(end)[0]) {
                    selectList.add(end + 1, insert);
                } else if (insert[0] < selectList.get(start)[0]) {
                    selectList.add(start, insert);
                } else if (insert[0] > selectList.get(middle)[0]) {
                    selectList.add(middle + 1, insert);
                } else {
                    selectList.add(middle, insert);
                }
                break;
            }
            if (insert[0] > middleValue) {
                start = middle;
            } else {
                end = middle;
            }
        }
    }


    /**
     * 二插法加入有序集合
     *
     * @param insert
     * @param selectList
     */
    public void addToList(int insert, List<Integer> selectList) {
        if (selectList.size() == 0) {
            selectList.add(insert);
            return;
        }
        int start = 0;
        int end = selectList.size() - 1;
        int middle;
        while (true) {
            middle = (start + end) / 2;
            int middleValue = selectList.get(middle);
            if (middle == end || middle == start) {
                //和比较
                if (insert > selectList.get(end)) {
                    selectList.add(end + 1, insert);
                } else if (insert < selectList.get(start)) {
                    selectList.add(start, insert);
                } else if (insert > selectList.get(middle)) {
                    selectList.add(middle + 1, insert);
                } else {
                    selectList.add(middle, insert);
                }
                break;
            }
            if (insert > middleValue) {
                start = middle;
            } else {
                end = middle;
            }
        }
    }

}