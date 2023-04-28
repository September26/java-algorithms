package com.xt.mianshi;

/**
 * 面试题 17.09. 第 k 个数
 * 每日一题：2022.09.28
 * 完成日期：2022.09.28
 * 链接：https://leetcode.cn/problems/get-kth-magic-number-lcci/
 * 描述：
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 * <p>
 * 示例 1:
 * <p>
 * 输入: k = 5
 * <p>
 * 输出: 9
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/get-kth-magic-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 几个数比大小，如果5*7是当前最小值时，那么最后一位为7的比35大的最小数，一定是7*7=49。
 * 同理，最后一位是3和5的时候也是这样的逻辑。
 * 所以我们只要分三种类型，最后一位为3，5，7这三种，分别用是那个变量来记录其最小值的位置n3,n5,n7。
 * 下一次寻求最小值时，只要比较这三种类型的最小值即可。
 * 另外有一点要注意的是，比如3*5和5*3，值相同，但是最后一位其实是不一样的，针对于这种情况，n3和n5都要向后挪一位才行。
 * <p>
 * state:done
 */
public class MianShi1709 {

    public int getKthMagicNumber(int k) {
        int[] ints = new int[k];
        ints[0] = 1;
        int n3 = 0;
        int n5 = 0;
        int n7 = 0;
        for (int i = 1; i < ints.length; i++) {
            int n3value = ints[n3] * 3;
            int n5value = ints[n5] * 5;
            int n7value = ints[n7] * 7;
            ints[i] = Math.min(n3value, Math.min(n5value, n7value));
            if (ints[i] == n3value) {
                n3++;
            }
            if (ints[i] == n5value) {
                n5++;
            }
            if (ints[i] == n7value) {
                n7++;
            }
        }
        return ints[k - 1];
    }
}















