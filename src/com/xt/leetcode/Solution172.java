package com.xt.leetcode;

import java.util.Vector;

/**
 * 172. 阶乘后的零
 * 每日一题：2022.03.25
 * 完成日期：2022.03.25
 * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 * 描述：
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * <p>
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 * 示例 3：
 * <p>
 * 输入：n = 0
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 10^4
 *  
 * <p>
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 求0的数量，我们拆分一下，只有2*5的时候才会出现0。其余的4*5，3*10等等，拆最小公约数，也都是2和5。
 * 所以我们只要求2和5的数量，取两者的较小值即可。实际操作中，2的数量远远大于5的数量，所以我们只要求5出现的数量即可
 * <p>
 * <p>
 * state:done
 */
public class Solution172 {

    public int trailingZeroes(int n) {
        int fiveNum = 0;
        for (int i = 5; i <= n; i+=5) {
            //求包含5个数量
            int local = i;
            while (local % 5 == 0) {
                fiveNum++;
                local = local / 5;
            }
        }
        return fiveNum;
    }


}