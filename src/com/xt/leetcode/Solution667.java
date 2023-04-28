package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 667. 优美的排列 II
 * 每日一题：2022.09.08
 * 完成日期：2022.09.08
 * 链接：https://leetcode.cn/problems/beautiful-arrangement-ii/
 * 描述：
 * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
 * <p>
 * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
 * 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, k = 1
 * 输出：[1, 2, 3]
 * 解释：[1, 2, 3] 包含 3 个范围在 1-3 的不同整数，并且 [1, 1] 中有且仅有 1 个不同整数：1
 * 示例 2：
 * <p>
 * 输入：n = 3, k = 2
 * 输出：[1, 3, 2]
 * 解释：[1, 3, 2] 包含 3 个范围在 1-3 的不同整数，并且 [2, 1] 中有且仅有 2 个不同整数：1 和 2
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k < n <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/beautiful-arrangement-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 如有有k个不同的值，假设为6，那么最小的方案一定是[1,6,2,5,3,4]这样逐渐收敛的形态。
 * 也就是说n=6的话k最大为5。那么如果k为2怎么办呢？我们可以分成3组，每组里面n=3,k=2,下面的形式[1,3,2,4,6,5]的形式，前三个为1组，后三个为1组。
 * 如果n=3,k=1时，由于n对应的k最大为n-1，所以有groupNum = n / (k + 1) == 0 ? n / (k + 1) : n / (k + 1) + 1;组，也就是2组。
 * 每组里面求出集合，加入到总集合中，最后转为树组即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution667 {

    public int[] constructArray(int n, int k) {
        int groupNum = n / (k + 1) == 0 ? n / (k + 1) : n / (k + 1) + 1;//分为几组
        List<Integer> allList = new ArrayList<>();
        int start = 1;
        for (int i = 0; i < groupNum; i++) {
            List<Integer> integers = groupArray(start, Math.min(n, start + k));//1,1,1
            allList.addAll(integers);
            start = start + k + 1;
        }
        return allList.stream().mapToInt(Integer::intValue).toArray();
    }


    public List<Integer> groupArray(int start, int end) {
        List<Integer> list = new ArrayList<>();
        while (start <= end) {
            list.add(start);
            start++;
            if (start > end) {
                break;
            }
            list.add(end);
            end--;
        }
        return list;
    }
}