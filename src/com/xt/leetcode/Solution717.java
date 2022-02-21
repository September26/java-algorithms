package com.xt.leetcode;

/**
 * 717. 1 比特与 2 比特字符
 * 日期：2022.2.20
 * 链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters/
 * 描述:
 * 有两种特殊字符：
 * <p>
 * 第一种字符可以用一比特 0 表示
 * 第二种字符可以用两比特（10 或 11）表示
 * 给你一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一个一比特字符，则返回 true 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: bits = [1, 0, 0]
 * 输出: true
 * 解释: 唯一的解码方式是将其解析为一个两比特字符和一个一比特字符。
 * 所以最后一个字符是一比特字符。
 * 示例 2:
 * <p>
 * 输入：bits = [1,1,1,0]
 * 输出：false
 * 解释：唯一的解码方式是将其解析为两比特字符和两比特字符。
 * 所以最后一个字符不是一比特字符。
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= bits.length <= 1000
 * bits[i] 为 0 或 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先，倒数第二个位置如果是0，则一定是true。
 * 则接下来的结果一定是10结尾的，10继续往前找，如果为1，则会影响10，比如110就会影响后面的10，如果是0则没有影响。
 * 所以，只要统计10之前1的连续数量即可，如果为奇数，则结果为true，比如110。反之则false了。
 * <p>
 * <p>
 * state:
 */
public class Solution717 {

    /**
     * 010  => false
     * 1110 =>false
     *
     * @param bits
     * @return
     */
    public boolean isOneBitCharacter(int[] bits) {
        if (bits.length == 1) {
            return bits[0] == 0;
        }
        int index = bits.length - 2;
        int value = bits[index];
        if (value == 0) {
            return true;
        }
        int num = 0;
        while (index >= 0 && bits[index] == 1) {
            num++;
            index--;
        }
        return num % 2 == 0;
    }
}