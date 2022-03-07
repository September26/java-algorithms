package com.xt.leetcode;

/**
 * 1688.比赛中的配对次数
 * 日期：2022.02.28
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-achievable-transfer-requests/
 * 描述:
 * 我们有 n 栋楼，编号从 0 到 n - 1 。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
 * <p>
 * 给你一个数组 requests ，其中 requests[i] = [fromi, toi] ，表示一个员工请求从编号为 fromi 的楼搬到编号为 toi 的楼。
 * <p>
 * 一开始 所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0 。意思是每栋楼 离开 的员工数目 等于 该楼 搬入 的员工数数目。比方说 n = 3 且两个员工要离开楼 0 ，一个员工要离开楼 1 ，一个员工要离开楼 2 ，如果该请求列表可行，应该要有两个员工搬入楼 0 ，一个员工搬入楼 1 ，一个员工搬入楼 2 。
 * <p>
 * 请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
 * 输出：5
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x 和 y ，且他们都想要搬到楼 1 。
 * 从楼 1 离开的员工为 a 和 b ，且他们分别想要搬到楼 2 和 0 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 0 。
 * 从楼 3 离开的员工为 c ，且他想要搬到楼 4 。
 * 没有员工从楼 4 离开。
 * 我们可以让 x 和 b 交换他们的楼，以满足他们的请求。
 * 我们可以让 y，a 和 z 三人在三栋楼间交换位置，满足他们的要求。
 * 所以最多可以满足 5 个请求。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 3, requests = [[0,0],[1,2],[2,1]]
 * 输出：3
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x ，且他想要回到原来的楼 0 。
 * 从楼 1 离开的员工为 y ，且他想要搬到楼 2 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 1 。
 * 我们可以满足所有的请求。
 * 示例 3：
 * <p>
 * 输入：n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 * 1 <= requests.length <= 16
 * requests[i].length == 2
 * 0 <= fromi, toi < n
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-achievable-transfer-requests
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 由于长度只有16，所以存在的可能性做多也就2^16，所以范围可控。
 * 比如选择了N条请求执行，那么这N条请求最后构成的对应的楼的变化一定是0。
 * 所以我们构建pointStates记录当前楼的变化数量，如果最终pointStates数组全为0的话，那么就是满足条件的，则这时候可以计算一下到底满足了多少条。
 * 所以每个request都有选择和不选择两种可能。我们可以用递归的方式来，每次方法的循环都产生两种可能性，不选择+选择。
 * 如果选择了的话，那么一定会对pointStates产生变化，则修改pointStates的状态。
 * 如果选择完成的话，则要把pointStates的状态改回，好不影响下一个循环的选择。
 * <p>
 * <p>
 * state:done
 */
public class Solution1601 {


    int ans = 0;//最大满足的request数
    int currentAns = 0;//当前满足的request数
    int[] pointStates;//当前点的状态
    int[] stepStates;//是否选中的状态

    public int maximumRequests(int n, int[][] requests) {
        pointStates = new int[n];
        stepStates = new int[requests.length];
        dfs(requests, 0);

        return ans;
    }

    public void dfs(int[][] requests, int pos) {
        if (pos == requests.length) {
            //判断value
            if (getNum() == 0) {
                ans = Math.max(currentAns, ans);
            }
            return;
        }

        //不选择
        dfs(requests, pos + 1);

        int[] request = requests[pos];
        stepStates[pos] = 1;
        pointStates[request[0]]--;
        pointStates[request[1]]++;
        currentAns++;
        //选择
        dfs(requests, pos + 1);


        stepStates[pos] = 0;
        pointStates[request[0]]++;
        pointStates[request[1]]--;
        currentAns--;

    }

    public int getNum() {
        int num = 0;
        for (int value : pointStates) {
            if (value != 0) {
                num++;
            }
        }
        return num;
    }

}