package com.xt.leetcode;


import java.util.*;

/**
 * 901. 股票价格跨度
 * 每日一题：2022.10.21
 * 完成日期：2022.10.21
 * 链接：https://leetcode.cn/problems/online-stock-span/
 * 描述：
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * <p>
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * <p>
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * 输出：[null,1,1,1,2,1,4,6]
 * 解释：
 * 首先，初始化 S = StockSpanner()，然后：
 * S.next(100) 被调用并返回 1，
 * S.next(80) 被调用并返回 1，
 * S.next(60) 被调用并返回 1，
 * S.next(70) 被调用并返回 2，
 * S.next(60) 被调用并返回 1，
 * S.next(75) 被调用并返回 4，
 * S.next(85) 被调用并返回 6。
 * <p>
 * 注意 (例如) S.next(75) 返回 4，因为截至今天的最后 4 个价格
 * (包括今天的价格 75) 小于或等于今天的价格。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 调用 StockSpanner.next(int price) 时，将有 1 <= price <= 10^5。
 * 每个测试用例最多可以调用  10000 次 StockSpanner.next。
 * 在所有测试用例中，最多调用 150000 次 StockSpanner.next。
 * 此问题的总时间限制减少了 50%。
 * <p>
 * 解题思路：
 * 首先我们用一最简单的思路，用history保存历史所有的price，然后输入新的price时，查询一下history中所有符合的。
 * 这样能够满足需求，但是时间复杂度太高，所以我们设置result来缓存历史的结果，假设index=3,value=70，70的跨度为2，
 * 新读入的值如果为80的话，80>70，所以可以直接荣耀70的跨度，然后找到index=1的位置，而跳过index=2。
 * result中结构为开始和结束的跨度，结构为List<Interger[]>
 * <p>
 * <p>
 * state:
 */
public class Solution901 {

    public static class StockSpanner {

        List<Integer> history = new ArrayList<>();
        List<Integer[]> result = new ArrayList<>();

        public StockSpanner() {

        }

        public int next(int price) {
            int size = history.size();
            if (size == 0) {
                history.add(price);
                result.add(new Integer[]{0, 0});
                return 1;
            }
            int span = 0;
            for (int i = size - 1; i >= 0; ) {
                Integer value = history.get(i);
                if (price < value) {
                    break;
                }
                Integer[] integers = result.get(i);
                i = integers[0] - 1;
                span += (integers[1] - integers[0] + 1);
            }
            history.add(price);
            result.add(new Integer[]{size - span, size});
            return span + 1;
        }
    }

}