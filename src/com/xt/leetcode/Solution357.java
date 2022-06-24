package com.xt.leetcode;

/**
 * 357. 统计各位数字都不同的数字个数
 * 每日一题：2022.04.11
 * 完成日期：2022.04.11
 * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits/
 * 描述：
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 我们可以把这道题理解为排列组合的形式。但是有一点例外，就是0开头的，
 * 譬如001，其值为1，也是符合条件的。
 * 假设n=3,我们排列组合分为两种，0开头和1-9开头的。
 * 1-9开头的，排列组合，9*9*8种可能。第一个为9因为不能0开头，第二个9因为可以选择0，但是要抛出第一个选择的数字。第三个8则抛出前两个选择的数字。
 * 0开头的，则找到n=2的所有可能即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution357 {


    public int countNumbersWithUniqueDigits(int n) {
        if (n < 1) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int result = 0;

        //分为两种情况，0开头和其余开头

        //计算0开头的情况
        result += countNumbersWithUniqueDigits(n - 1);

        //计算1-9开头其余开头的情况
        int base = 9;
        int zeroStartNum = 9;
        for (int i = 0; i < n - 1; i++) {
            zeroStartNum *= base;
            base--;
        }
        result += zeroStartNum;
        return result;
    }
}