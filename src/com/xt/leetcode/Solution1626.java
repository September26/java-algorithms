package com.xt.leetcode;


import java.util.Arrays;

/**
 * 1688.比赛中的配对次数
 * 每日一题：2023.03.22
 * 完成日期：2023.03.22
 * 链接：https://leetcode.cn/problems/best-team-with-no-conflicts/
 * 描述：
 * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
 * <p>
 * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
 * <p>
 * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * 输出：34
 * 解释：你可以选中所有球员。
 * 示例 2：
 * <p>
 * 输入：scores = [4,5,6,5], ages = [2,1,2,1]
 * 输出：16
 * 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
 * 示例 3：
 * <p>
 * 输入：scores = [1,2,3,5], ages = [8,9,10,1]
 * 输出：6
 * 解释：最佳的选择是前 3 名球员。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 10^6
 * 1 <= ages[i] <= 1000
 * <p>
 * 解题思路：
 * 因为要判断两个条件，年龄和分数，所以我们先以其中一个条件排序，排序后筛选满足另外一个条件的所有球员。
 * 首先，以分数排序，构造二维数组people，people[i][0]代表排序为i的分数，people[i][0]代表排序为i的年龄。
 * 然后我们构建dp数组，dp[i]代表排序小于第i位球员，并且年龄小于第i位球员的所有球员的分数之和。
 * 由于排序是按照分数排的，所以上面满足条件的球员，其分数一定也小于等于第i位球员。
 * 因此，我们只要判断年龄小于第i位球员，就是满足条件的，我们在这些满足条件的里面，找出来dp最大的，这也代表分数是最大的。
 * 这个最大的分数，加上scores[i]就是第i位最大的分数值。
 * 这样求出所有的dp[i]的值，最终求出最大的dp[i]即可。
 * <p>
 * state:done
 */
public class Solution1626 {

    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] people = new int[n][2];
        for (int i = 0; i < n; ++i) {
            people[i] = new int[]{scores[i], ages[i]};
        }
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < people.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (people[j][1] <= people[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += people[i][0];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}