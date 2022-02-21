package com.xt.leetcode;

import java.util.Vector;

/**
 * 838.推多米诺
 * 日期：2022.2.21
 * 链接：https://leetcode-cn.com/problems/push-dominoes/
 * 描述：
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * <p>
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * <p>
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * <p>
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * <p>
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * <p>
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * 示例 2：
 * <p>
 * <p>
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 *  
 * <p>
 * 提示：
 * <p>
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] 为 'L'、'R' 或 '.'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/push-dominoes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 一张牌往哪边倒，只有两个因素决定，当前的方向，以及起始位置的方向。
 * 所以就分拆成9种情况进行分析。比如起始'.'，当前'L'，那么起始和当前之间全部设置为L。
 * <p>
 * <p>
 * state:
 */
public class Solution838 {

    public String pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();

        int start = 0;

        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            char startChar = chars[start];
            if (startChar == '.') {
                if (currentChar == '.') {
                    continue;
                }
                if (currentChar == 'L') {
                    setRangeValue(chars, start, i, currentChar);
                    continue;
                }
                if (currentChar == 'R') {
                    start = i;
                    continue;
                }
            }

            if (startChar == 'L') {
                start = i;
                if (currentChar == '.') {
                    continue;
                }
                if (currentChar == 'L') {
                    continue;
                }
                if (currentChar == 'R') {
                    continue;
                }
                continue;
            }

            if (startChar == 'R') {
                if (currentChar == '.') {
                    if (i == chars.length - 1) {
                        setRangeValue(chars, start, i, 'R');
                        break;
                    }
                    continue;
                }
                if (currentChar == 'R') {
                    setRangeValue(chars, start, i, 'R');
                    start = i;
                    continue;
                }
                if (currentChar == 'L') {
                    int length = i - start;
                    int middle = length / 2;
                    if (length % 2 == 0) {
                        middle--;
                    }
                    setRangeValue(chars, start, start + middle, 'R');
                    setRangeValue(chars, i - middle, i, 'L');
                    start = i;
                    continue;
                }
                continue;
            }

        }
        return new String(chars);
    }

    private void setRangeValue(char[] chars, int start, int end, char c) {
        for (int i = start; i <= end; i++) {
            chars[i] = c;
        }
    }

}