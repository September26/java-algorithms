package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 386. 字典序排数
 * 每日一题：2022.04.18
 * 完成日期：2022.04.18
 * 链接：https://leetcode-cn.com/problems/lexicographical-numbers/
 * 描述：
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * <p>
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：[1,2]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 5 * 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lexicographical-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 我们可以按照字典序的方式，使用递归的方式来解决该问题。
 * 如果输入0，可以推导出0-9。然后其中的每个数都可以扩展出10个，比如1又可以推导出10-19。同理，10又可以推导出100到109，都是1扩10的方式进行的。
 * 所以我们可以把输入的数，循环10遍每次加1，如果没超过则加入到队列中。并且对于每个数，都乘以10继续循环下去。
 * <p>
 * <p>
 * state:done
 */
public class Solution386 {

    public List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        add2List(result, 0, n);
        return result;
    }

    /**
     * @param result
     * @param base     1,10,100
     * @param maxValue
     */
    private void add2List(ArrayList<Integer> result, int base, int maxValue) {
        if (base > maxValue) {
            return;
        }
        for (int i = 0; i < 10; i++) {
            int current = base + i;
            if (current > maxValue) {
                break;
            }
            if (current == 0) {
                continue;
            }
            result.add(current);
            add2List(result, current * 10, maxValue);
        }
    }
}