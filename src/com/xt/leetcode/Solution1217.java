package com.xt.leetcode;

import java.util.Vector;

/**
 * 1217. 玩筹码
 * 每日一题：2022.07.08
 * 完成日期：2022.07.08
 * 链接：
 * 有 n 个筹码。第 i 个筹码的位置是 position[i] 。
 * <p>
 * 我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:
 * <p>
 * position[i] + 2 或 position[i] - 2 ，此时 cost = 0
 * position[i] + 1 或 position[i] - 1 ，此时 cost = 1
 * 返回将所有筹码移动到同一位置上所需要的 最小代价 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：position = [1,2,3]
 * 输出：1
 * 解释：第一步:将位置3的筹码移动到位置1，成本为0。
 * 第二步:将位置2的筹码移动到位置1，成本= 1。
 * 总成本是1。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：position = [2,2,2,3,3]
 * 输出：2
 * 解释：我们可以把位置3的两个筹码移到位置2。每一步的成本为1。总成本= 2。
 * 示例 3:
 * <p>
 * 输入：position = [1,1000000000]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= chips.length <= 100
 * 1 <= chips[i] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-cost-to-move-chips-to-the-same-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 描述：
 * <p>
 * 解题思路：
 * 这题比较简单，我们只需要记录奇数位置数量和偶数位置数量即可。
 * 1挪动到2需要cost1，1挪到4也需要cost1。所以一个数挪到任意位置，只需要cost=0或1。
 * 总结一下，就是奇数位置挪到偶数位置需要cost1，挪到奇数位置cost0。
 * 所以，我们只要统计更少的那一组，挪更少的那一组即可。
 *
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution1217 {

    public int minCostToMoveChips(int[] position) {
        int evenNum = 0;
        int oddNum = 0;
        for (int i = 0; i < position.length; i++) {
            int value = position[i];
            if (value % 2 == 0) {
                oddNum++;
            } else {
                evenNum++;
            }
        }
        return Math.min(evenNum, oddNum);
    }
}