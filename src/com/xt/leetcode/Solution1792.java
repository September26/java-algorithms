package com.xt.leetcode;


import java.util.*;

/**
 * 1792. 最大平均通过率
 * 每日一题：2023.02.21
 * 完成日期：2023.02.21
 * 链接：https://leetcode.cn/problems/maximum-average-pass-ratio/
 * 描述：
 * 一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试。给你一个二维数组 classes ，其中 classes[i] = [passi, totali] ，表示你提前知道了第 i 个班级总共有 totali 个学生，其中只有 passi 个学生可以通过考试。
 * <p>
 * 给你一个整数 extraStudents ，表示额外有 extraStudents 个聪明的学生，他们 一定 能通过任何班级的期末考。你需要给这 extraStudents 个学生每人都安排一个班级，使得 所有 班级的 平均 通过率 最大 。
 * <p>
 * 一个班级的 通过率 等于这个班级通过考试的学生人数除以这个班级的总人数。平均通过率 是所有班级的通过率之和除以班级数目。
 * <p>
 * 请你返回在安排这 extraStudents 个学生去对应班级后的 最大 平均通过率。与标准答案误差范围在 10-5 以内的结果都会视为正确结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：classes = [[1,2],[3,5],[2,2]], extraStudents = 2
 * 输出：0.78333
 * 解释：你可以将额外的两个学生都安排到第一个班级，平均通过率为 (3/4 + 3/5 + 2/2) / 3 = 0.78333 。
 * 示例 2：
 * <p>
 * 输入：classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
 * 输出：0.53485
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= classes.length <= 105
 * classes[i].length == 2
 * 1 <= passi <= totali <= 105
 * 1 <= extraStudents <= 105
 * <p>
 * 解题思路：
 * 针对每个班级，统计添加1名好学生之后可以带来的提高值，按照提高值排序。
 * 然后每加入一个好学生之后，都删除当前节点，然后统计新的提高值，按照新的提高值二分插入。
 * 最后统计最后的结果
 * <p>
 * state:done
 */
public class Solution1792 {

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        List<Model> list = new ArrayList<>(classes.length);
        for (int[] classe : classes) {
            list.add(new Model(classe[0], classe[1]));
        }
        Collections.sort(list);
        int i = 0;
        int size = list.size();
        while (i < extraStudents) {
            Model model = list.get(size - 1);
            model.add();
            list.remove(size - 1);
            int i1 = binarySearch(list, model);
            list.add(i1, model);
            i++;
        }
        double sum = 0;
        for (Model model : list) {
            sum += ((double) model.passNum / (double) model.countNum);
        }
        return sum / classes.length;
    }


    public int binarySearch(List<Model> list, Model target) {
        if (target.addValue < list.get(0).addValue) {
            return 0;
        }
        int size = list.size();
        if (target.addValue > list.get(size - 1).addValue) {
            return size;
        }
        int start = 0;
        int end = size - 1;
        int result = end;
        do {
            int middle = (start + end) / 2;
            if (list.get(middle).addValue >= target.addValue) {
                result = middle;
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        } while (start <= end);

        return result;
    }


    static class Model implements Comparable<Model> {
        private int passNum;
        private int countNum;
        private double addValue;

        private Model(int passNum, int countNum) {
            this.passNum = passNum;
            this.countNum = countNum;
            addValue = ((double) (passNum + 1)) / (countNum + 1) - ((double) (passNum)) / (countNum);
        }

        private void add() {
            passNum++;
            countNum++;
            addValue = ((double) (passNum + 1)) / (countNum + 1) - ((double) (passNum)) / (countNum);
        }

        @Override
        public int compareTo(Model o2) {
            if (addValue == o2.addValue) {
                return 0;
            }
            return addValue > o2.addValue ? 1 : -1;
        }
    }
}