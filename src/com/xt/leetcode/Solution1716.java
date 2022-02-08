package com.xt.leetcode;

import java.util.Vector;

/**
 * 1716. 计算力扣银行的钱
 * 描述
 * Hercy 想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。
 * <p>
 * 最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。
 * <p>
 * 给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4
 * 输出：10
 * 解释：第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
 * 示例 2：
 * <p>
 * 输入：n = 10
 * 输出：37
 * 解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
 * 示例 3：
 * <p>
 * 输入：n = 20
 * 输出：96
 * 解释：第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 1000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 分为两部分，
 * 第一部分是前面几周的money，前面几周的其实是一个等差数列28，28+7，28+7*2这样的结构。有多少周直接n/7即可。
 * 第二部分是最后一周的money，这个通过n%7来算出。最后一周周一的money=weekNum+i
 * <p>
 * <p>
 * state:done
 */
public class Solution1716 {

    public int totalMoney(int n) {

        int weekMoney = 1 + 2 + 3 + 4 + 5 + 6 + 7;
        int weekNum = n / 7;
        int dayNum = n % 7;

        int sum1 = weekMoney * weekNum + 7 * (weekNum - 1) * weekNum / 2;
        int sum2 = 0;

        for (int i = 1; i <= dayNum; i++) {
            sum2 += (weekNum + i);
        }
        return sum1 + sum2;
    }
}