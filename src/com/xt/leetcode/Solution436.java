package com.xt.leetcode;

import java.util.*;

/**
 * 436. 寻找右区间
 * 每日一题：2022.05.20
 * 完成日期：
 * 链接：
 * 描述：
 * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
 * <p>
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
 * <p>
 * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,2]]
 * 输出：[-1]
 * 解释：集合中只有一个区间，所以输出-1。
 * 示例 2：
 * <p>
 * 输入：intervals = [[3,4],[2,3],[1,2]]
 * 输出：[-1,0,1]
 * 解释：对于 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间[3,4]具有最小的“右”起点;
 * 对于 [1,2] ，区间[2,3]具有最小的“右”起点。
 * 示例 3：
 * <p>
 * 输入：intervals = [[1,4],[2,3],[3,4]]
 * 输出：[-1,2,-1]
 * 解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= intervals.length <= 2 * 10^4
 * intervals[i].length == 2
 * -10^6 <= starti <= endi <= 10^6
 * 每个间隔的起点都 不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-right-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题涉及到两次排序，为了方便理解，我们构建Model来存储这关系。Model记录start,end,和在intervals的位置index。
 * 然后分别以start和end排序，生成两个集合startList和endList。
 * 然后遍历endList，同时也从startList中取值。如果startList中的start大于endlist中的end。则代表符合，则记录其index。
 * 如果找不到，则记录-1
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution436 {

    public int[] findRightInterval(int[][] intervals) {
        Map<Integer, Model> map = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            Model model = new Model(intervals[i][0], intervals[i][1], i);
            map.put(i, model);
        }
        List<Model> startList = new ArrayList<>(map.values());
        startList.sort(new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return o1.start - o2.start;
            }
        });
        List<Model> endList = new ArrayList<>(map.values());
        endList.sort(new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return o1.end - o2.end;
            }
        });

        int[] result = new int[intervals.length];
        int startIndex = 0;
        for (Model endModel : endList) {
            Model startModel = null;
            while (startIndex < startList.size()) {
                Model model = startList.get(startIndex);
                if (model.start >= endModel.end) {
                    startModel = model;
                    break;
                }
                startIndex++;
            }
            if (startModel == null) {
                result[endModel.index] = -1;
            } else {
                result[endModel.index] = startModel.index;
            }
        }
        return result;
    }

    static class Model {
        int start = 0;
        int end = 0;
        int index = 0;

        Model(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }

}