package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 911. 在线选举
 * <p>
 * 给你两个整数数组 persons 和 times 。在选举中，第 i 张票是在时刻为 times[i] 时投给候选人 persons[i] 的。
 * <p>
 * 对于发生在时刻 t 的每个查询，需要找出在 t 时刻在选举中领先的候选人的编号。
 * <p>
 * 在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 * <p>
 * 实现 TopVotedCandidate 类：
 * <p>
 * TopVotedCandidate(int[] persons, int[] times) 使用 persons 和 times 数组初始化对象。
 * int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。
 *  
 * 示例：
 * <p>
 * 输入：
 * ["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
 * [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
 * 输出：
 * [null, 0, 1, 1, 0, 0, 1]
 * <p>
 * 解释：
 * TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
 * topVotedCandidate.q(3); // 返回 0 ，在时刻 3 ，票数分布为 [0] ，编号为 0 的候选人领先。
 * topVotedCandidate.q(12); // 返回 1 ，在时刻 12 ，票数分布为 [0,1,1] ，编号为 1 的候选人领先。
 * topVotedCandidate.q(25); // 返回 1 ，在时刻 25 ，票数分布为 [0,1,1,0,0,1] ，编号为 1 的候选人领先。（在平局的情况下，1 是最近获得投票的候选人）。
 * topVotedCandidate.q(15); // 返回 0
 * topVotedCandidate.q(24); // 返回 0
 * topVotedCandidate.q(8); // 返回 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/online-election
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * state:done
 */
public class Solution911 {

    public static class TopVotedCandidate {
        /**
         * key:选举人
         * value:票数
         */
        Map<Integer, Integer> voteMap = new HashMap<>();
        /**
         * 时间点对应的胜者
         */
        List<Integer> maxList = new ArrayList<>();

        int[] times;


        public TopVotedCandidate(int[] persons, int[] times) {
            this.times = times;
            int win = 0;

            for (int i = 0; i < times.length; i++) {
                int person = persons[i];
//                int time = times[i];//时间点
                Integer integer = voteMap.getOrDefault(person, 0);
                voteMap.put(person, ++integer);
                Integer integer1 = voteMap.get(win);
                if (integer >= integer1) {
                    win = person;
                }
                maxList.add(win);
            }
        }

        public int q(int t) {
            //查找t在time中的位置
            int index = middleSelect(t);
            Integer win = maxList.get(index);
            return win;
        }

        /**
         * @param t
         * @return index位置
         */
        private int middleSelect(int t) {
            int start = 0;
            int end = times.length - 1;
            if (t >= times[end]) {
                return end;
            }
            int middle;
            do {
                middle = (start + end) / 2;
                if (t == times[middle]) {
                    return middle;
                }
                if (t > times[middle]) {
                    if (t < times[middle + 1]) {
                        return middle;
                    }
                    start = middle;
                } else {
                    if (t > times[middle - 1]) {
                        return middle - 1;
                    }
                    end = middle;
                }
            } while (true);
        }

    }

}


/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */