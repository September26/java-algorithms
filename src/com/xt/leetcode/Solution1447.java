package com.xt.leetcode;

import java.util.*;

/**
 * 1688.比赛中的配对次数
 * 日期：2022.2.10
 * 描述
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：["1/2"]
 * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 * 示例 2：
 * <p>
 * 输入：n = 3
 * 输出：["1/2","1/3","2/3"]
 * 示例 3：
 * <p>
 * 输入：n = 4
 * 输出：["1/2","1/3","1/4","2/3","3/4"]
 * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 * 示例 4：
 * <p>
 * 输入：n = 1
 * 输出：[]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simplified-fractions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * <p>
 * 解题思路：
 * 一开始我理解错条件了，以为分母只能等于N。所以按照这个条件去写的。
 * 找出N的所有质数。举个例子，比如10的话，就是2和5。
 * 然后从0开始，
 * 1没问题。
 * 2的话在集合中，则跳过并且把2改成4。
 * 3没问题，
 * 4在集合中，则跳过把4改成6。
 * 5在集合中，则跳过把5改成10。
 * 就这样继续下去，则可以找出分母为N的所有结果。
 * 理解对条件后，则多了一层遍历，对2到N之间的所有数执行一遍上面的操作，则得出最终结果。
 *
 * <p>
 * state:
 */
public class Solution1447 {

    public List<String> simplifiedFractions(int n) {
        //求所有的约数
        ArrayList<String> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            searchAdd(i, list);
        }
        return list;
    }

    public void searchAdd(int n, List<String> list) {
        //求所有的约数
        int k = 2;
        int localN = n;
        Set<Integer> set = new HashSet<>();
        while (k <= localN && k < n) {
            if (localN % k == 0) {
                set.add(k);
                localN = localN / k;
                continue;
            }
            k++;
        }
        Integer[] bases = new Integer[set.size()];
        Integer[] currents = new Integer[set.size()];
        set.toArray(bases);
        System.arraycopy(bases, 0, currents, 0, set.size());
        for (int i = 1; i < n; i++) {
            boolean isMatch = true;
            for (int j = 0; j < currents.length; j++) {
                if (i == currents[j]) {
                    currents[j] += bases[j];
                    isMatch = false;
                }
            }
            if (isMatch) {
                list.add(i + "/" + n);
            }
        }
    }

}