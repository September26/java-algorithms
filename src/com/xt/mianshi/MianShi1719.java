package com.xt.mianshi;

/**
 * 面试题 17.19. 消失的两个数字
 * 每日一题：2022.09.26
 * 完成日期：2022.09.26
 * 链接：https://leetcode.cn/problems/missing-two-lcci/
 * 描述：
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 * <p>
 * 以任意顺序返回这两个数字均可。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1]
 * 输出: [2,3]
 * 示例 2:
 * <p>
 * 输入: [2,3]
 * 输出: [1,4]
 * 提示：
 * <p>
 * nums.length <= 30000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/missing-two-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这种题只能使用位运算。
 * 异或运算有一个特性，如果一个数出现两次，则其结果为0。如果出现一次，则其结果就是这个数。
 * 所以我们对nums和1到N这两个数组进行异或运算，其结果为两个缺失的两个数字的异或结果。
 * 这两个数字一定有一位是不相同的，找到这个不相同的位。
 * 然后再次对两个数组进行异或运算，根据这个不相同的位走不同的判断条件，分别求出两个值，这两个值就是缺失的两个数。
 *
 * <p>
 * <p>
 * state:done
 */
public class MianShi1719 {

    public int[] missingTwo(int[] nums) {
        int flag = 1;
        int sum = ergodic(nums, flag, 0)[0];
        while (sum > 0) {
            if ((sum & 1) == 1) {
                break;
            }
            sum = sum >> 1;
            flag = flag << 1;
        }
        return ergodic(nums, flag, 1);
    }

    private int[] ergodic(int[] nums, int flag, int state) {
        int[] sum = new int[2];
        for (int i : nums) {
            if (state == 0) {
                sum[0] ^= i;
                continue;
            }
            if (state == 1) {
                if ((flag & i) == flag) {
                    sum[0] ^= i;
                } else {
                    sum[1] ^= i;
                }
            }
        }
        for (int i = 1; i <= nums.length + 2; i++) {
            if (state == 0) {
                sum[0] ^= i;
                continue;
            }
            if (state == 1) {
                if ((flag & i) == flag) {
                    sum[0] ^= i;
                } else {
                    sum[1] ^= i;
                }
            }
        }
        return sum;
    }


}















