package com.xt.leetcode;

import java.util.*;

/**
 * 933. 最近的请求次数
 * 每日一题：2022.05.06
 * 完成日期：2022.05.06
 * 链接：https://leetcode-cn.com/problems/number-of-recent-calls/
 * 描述：
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 * <p>
 * 请你实现 RecentCounter 类：
 * <p>
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * 输出：
 * [null, 1, 2, 3, 3]
 * <p>
 * 解释：
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
 * recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= t <= 109
 * 保证每次对 ping 调用所使用的 t 值都 严格递增
 * 至多调用 ping 方法 104 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-recent-calls
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 队列的结果来存储最为合适，因为题目提供的t是递增的，所以每次加入数字每次从队列前开始遍历，如果不符合范围这移除，否则则退出循环。
 * 最终只要返回队列数量即可。
 * <p>
 * <p>
 * state:
 */
public class Solution933 {

    class RecentCounter {

        Queue<Integer> queue = new ArrayDeque<>();

        public RecentCounter() {

        }

        public int ping(int t) {
            int i = t - 3000;
            Iterator<Integer> iterator = queue.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (next < i) {
                    iterator.remove();
                    continue;
                }
                break;
            }
            queue.offer(t);
            return queue.size();
        }
    }

}