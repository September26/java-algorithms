package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 728. 自除数
 * 每日一题：2022.03.31
 * 完成日期：2022.03.31
 * 链接：https://leetcode-cn.com/problems/self-dividing-numbers/
 * 描述：
 * 自除数 是指可以被它包含的每一位数整除的数。
 * <p>
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 * <p>
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：left = 1, right = 22
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * 示例 2:
 * <p>
 * 输入：left = 47, right = 85
 * 输出：[48,55,66,77]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= left <= right <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/self-dividing-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题比较简单，遍历left到right之间的数，每个数判断是否数自除数即可。
 * 比如128要需要使用128分别除以1，2，8（顺序无所谓）。
 * 我们可以使用128%10求余数，得出8。然后(128/10)%10求余数，得出2。这样循环下去求出1,2,8。
 * <p>
 * state:done
 */
public class Solution728 {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isDividing(i)) {
                list.add(i);
            }
        }
        return list;

    }

    private boolean isDividing(int i) {
        int oldNum = i;
        while (i > 0) {
            int i2 = i % 10;
            if (i2 == 0) {
                return false;
            }
            if (oldNum % i2 != 0) {
                return false;
            }
            i = i / 10;
        }
        return true;
    }


}