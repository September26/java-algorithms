package com.xt.leetcode;

import java.util.*;

/**
 * 1606. 找到处理最多请求的服务器
 * 每日一题：2022.03.30
 * 完成日期：2022.03.30
 * 链接：https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/
 * 描述：
 * 你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。请求分配到服务器的规则如下：
 * <p>
 * 第 i （序号从 0 开始）个请求到达。
 * 如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。
 * 如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。
 * 否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。比方说，如果第 i 个服务器在忙，那么会查看第 (i+1) 个服务器，第 (i+2) 个服务器等等。
 * 给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，其中 load[i] 表示第 i 个请求的工作量（也就是服务器完成它所需要的时间）。你的任务是找到 最繁忙的服务器 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。
 * <p>
 * 请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3]
 * 输出：[1]
 * 解释：
 * 所有服务器一开始都是空闲的。
 * 前 3 个请求分别由前 3 台服务器依次处理。
 * 请求 3 进来的时候，服务器 0 被占据，所以它呗安排到下一台空闲的服务器，也就是服务器 1 。
 * 请求 4 进来的时候，由于所有服务器都被占据，该请求被舍弃。
 * 服务器 0 和 2 分别都处理了一个请求，服务器 1 处理了两个请求。所以服务器 1 是最忙的服务器。
 * 示例 2：
 * <p>
 * 输入：k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
 * 输出：[0]
 * 解释：
 * 前 3 个请求分别被前 3 个服务器处理。
 * 请求 3 进来，由于服务器 0 空闲，它被服务器 0 处理。
 * 服务器 0 处理了两个请求，服务器 1 和 2 分别处理了一个请求。所以服务器 0 是最忙的服务器。
 * 示例 3：
 * <p>
 * 输入：k = 3, arrival = [1,2,3], load = [10,12,11]
 * 输出：[0,1,2]
 * 解释：每个服务器分别处理了一个请求，所以它们都是最忙的服务器。
 * 示例 4：
 * <p>
 * 输入：k = 3, arrival = [1,2,3,4,8,9,10], load = [5,2,10,3,1,2,2]
 * 输出：[1]
 * 示例 5：
 * <p>
 * 输入：k = 1, arrival = [1], load = [1]
 * 输出：[0]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= 10^5
 * 1 <= arrival.length, load.length <= 10^5
 * arrival.length == load.length
 * 1 <= arrival[i], load[i] <= 10^9
 * arrival 保证 严格递增 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 最简单的思路，自然是每次循环遍历所有服务器，找出一台合适的进行操作。但是这样时间复杂度太高了，是O(k*n)。
 * 所以我们可以每一次循环找合适服务器的时候，把那些不符合条件的服务器剔除掉，只保留合适的，这样可以实现每次找适合服务器的时候，实现O(1)的复杂度进行查找。
 * 保留合适的服务器，我们可以使用TreeSet去记录，这样方便找目标index的后一个位置，TreeSet按照index来排序。
 * 而保留正在执行的服务器，我们则可以使用PriorityQueue去记录，PriorityQueue按照结束时间来排序。
 * 每次循环的时候，首先遍历PriorityQueue，找出那些end时间小于等于arrive时间的，并且把这些加入TreeSet中。
 * 因为TreeSet中都是满足条件的，所以我们只要从TreeSet中找出适合的那一个就好。如果TreeSet为空，则代码没有适合的。则跳过
 * <p>
 * <p>
 * state:done
 */
public class Solution1606 {


    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        //所有符合条件的集合，按照id排序
        Node[] nodes = new Node[k];
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            nodes[i] = new Node(i);
            available.add(i);
        }

        int maxExecNum = 0;
        List<Integer> result = new ArrayList<>();

        //按照结束时间的优先级排序
        PriorityQueue<Node> busy = new PriorityQueue<>(k, Comparator.comparingInt(o -> o.end));

        for (int i = 0; i < arrival.length; i++) {
            int arrive = arrival[i];
            int loadTime = load[i];
            while (busy.size() > 0) {
                Node peek = busy.peek();
                if (peek.end <= arrive) {
                    busy.poll();
                    available.add(peek.id);
                    continue;
                }
                break;
            }
            if (available.size() == 0) {
                continue;
            }
            //从index位开始找
            Integer ceiling = available.ceiling(i % k);
            if (ceiling == null) {
                ceiling = available.first();
            }
            Node node = nodes[ceiling];
            node.end = arrive + loadTime;
            node.execNum++;
            busy.offer(node);
            available.remove(ceiling);
            if (node.execNum > maxExecNum) {
                result.clear();
                result.add(node.id);
                maxExecNum = node.execNum;
                continue;
            }
            if (node.execNum == maxExecNum) {
                result.add(node.id);
            }
        }
        return result;
    }

    /**
     * 服务器
     */
    static class Node {
        public int id;
        public int end = 0;//结束时间
        public int execNum = 0;//使用次数

        Node(int id) {
            this.id = id;
        }
    }
}