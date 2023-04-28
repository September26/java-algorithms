package com.xt.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * 672. 灯泡开关 Ⅱ
 * 每日一题：2022.09.15
 * 完成日期：2022.09.15
 * 链接：https://leetcode.cn/problems/bulb-switcher-ii/
 * 描述：
 * 房间中有 n 只已经打开的灯泡，编号从 1 到 n 。墙上挂着 4 个开关 。
 * <p>
 * 这 4 个开关各自都具有不同的功能，其中：
 * <p>
 * 开关 1 ：反转当前所有灯的状态（即开变为关，关变为开）
 * 开关 2 ：反转编号为偶数的灯的状态（即 2, 4, ...）
 * 开关 3 ：反转编号为奇数的灯的状态（即 1, 3, ...）
 * 开关 4 ：反转编号为 j = 3k + 1 的灯的状态，其中 k = 0, 1, 2, ...（即 1, 4, 7, 10, ...）
 * 你必须 恰好 按压开关 presses 次。每次按压，你都需要从 4 个开关中选出一个来执行按压操作。
 * <p>
 * 给你两个整数 n 和 presses ，执行完所有按压之后，返回 不同可能状态 的数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 1, presses = 1
 * 输出：2
 * 解释：状态可以是：
 * - 按压开关 1 ，[关]
 * - 按压开关 2 ，[开]
 * 示例 2：
 * <p>
 * 输入：n = 2, presses = 1
 * 输出：3
 * 解释：状态可以是：
 * - 按压开关 1 ，[关, 关]
 * - 按压开关 2 ，[开, 关]
 * - 按压开关 3 ，[关, 开]
 * 示例 3：
 * <p>
 * 输入：n = 3, presses = 1
 * 输出：4
 * 解释：状态可以是：
 * - 按压开关 1 ，[关, 关, 关]
 * - 按压开关 2 ，[关, 开, 关]
 * - 按压开关 3 ，[开, 开, 开]
 * - 按压开关 4 ，[关, 开, 开]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 1000
 * 0 <= presses <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bulb-switcher-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先，最多执行4次变化，就能得出所有的结果，不需要真的执行1000次。
 * 其次，每次计算时，只需要计算1-6的范围即可，不需要计算后面的，因为后面的都可以认为时1-6的重复。6是最小公倍数，开关1的公倍数是1，开关2/3的公倍数是2，开关4的公倍数是3，
 * 所以，我们最多执行4次开关，遍历求所有可能即可。
 * state:done
 */
public class Solution672 {

    public int flipLights(int n, int presses) {
        Set<String> lastSet = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append("1");
        }
        lastSet.add(builder.toString());
        Set<String> nextSet = new HashSet<>();
        for (int i = 0; i < Math.min(4, presses); i++) {
            nextSet = new HashSet<>();
            //第一种
            transformation(lastSet, nextSet, 1);
            transformation(lastSet, nextSet, 2);
            transformation(lastSet, nextSet, 3);
            transformation(lastSet, nextSet, 4);
            lastSet = nextSet;
        }
        return lastSet.size();
    }

    private Set<String> transformation(Set<String> lastSet, Set<String> nextSet, int mode) {
        StringBuilder builder = new StringBuilder();
        for (String str : lastSet) {
            builder.setLength(0);
            char[] chars = str.toCharArray();
            for (int k = 0; k < chars.length; k++) {
                boolean isReversal = false;
                if (mode == 1) {
                    isReversal = true;
                } else if (mode == 2) {
                    if (k % 2 == 0) {
                        isReversal = true;
                    }
                } else if (mode == 3) {
                    if (k % 2 != 0) {
                        isReversal = true;
                    }
                } else if (mode == 4) {
                    if (k % 3 == 0) {
                        isReversal = true;
                    }
                }
                if (!isReversal) {
                    builder.append(chars[k]);
                    continue;
                }
                if (chars[k] == '0') {
                    builder.append("1");
                } else {
                    builder.append("0");
                }
            }
            nextSet.add(builder.toString());
        }
        return nextSet;
    }
}