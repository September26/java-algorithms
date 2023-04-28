package com.xt.leetcode;

import java.util.Vector;

/**
 * 769. 最多能完成排序的块
 * 每日一题：2022.10.13
 * 完成日期：
 * 链接：https://leetcode.cn/problems/max-chunks-to-make-sorted/
 * 描述：
 * 给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
 * <p>
 * 我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
 * <p>
 * 返回数组能分成的最多块数量。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: arr = [4,3,2,1,0]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
 * 示例 2:
 * <p>
 * 输入: arr = [1,0,2,3,4]
 * 输出: 4
 * 解释:
 * 我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
 * 然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
 * <p>
 * <p>
 * 提示:
 * <p>
 * n == arr.length
 * 1 <= n <= 10
 * 0 <= arr[i] < n
 * arr 中每个元素都 不同
 * <p>
 * 解题思路：
 * 这题的核心就是看分区内的数字，是否在对应的位置。假设分区内是2,3,4（顺序无所谓），则在对应的aar数组中，这三个数字一定要在2到4位才可以。
 * 所以，这里我们可以用use数组来记录对应的数字是否出现过，因为这题保证元素唯一。
 * 然后遍历arr数组，如果读出来的数字是2，则遍历数组中当前位到第2位的位置，其中如果读到了更大的数，则动态更改到最大的数。Math.max(value, value2);，同时使用use来记录是否出现过。
 * 遍历完成后，判断use是否全出现过，如果全出现，则说明这个分区是OK的。
 * <p>
 * state:done
 */
public class Solution769 {

    public int maxChunksToSorted(int[] arr) {
        boolean[] use = new boolean[arr.length];
        int result = 0;
        int index = 0;
        while (index < arr.length) {
            int value = arr[index];
            for (int i = index; i <= value; i++) {
                int value2 = arr[i];
                value = Math.max(value, value2);
                use[i] = true;
            }
            boolean flag = true;
            for (int i = index; i <= value; i++) {
                flag &= use[i];
            }
            if (!flag) {
                return 1;
            }
            result++;
            index = value + 1;
        }
        return result;
    }
}