package com.xt.leetcode;

/**
 * 393. UTF-8 编码验证
 * 每日一题：2022.03.13
 * 完成日期：2022.03.13
 * 链接：https://leetcode-cn.com/problems/utf-8-validation/
 * 描述：
 * 给定一个表示数据的整数数组 data ，返回它是否为有效的 UTF-8 编码。
 * <p>
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * <p>
 * 对于 1 字节 的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 * 对于 n 字节 的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 * 这是 UTF-8 编码的工作方式：
 * <p>
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：data = [197,130,1]
 * 输出：true
 * 解释：数据表示字节序列:11000101 10000010 00000001。
 * 这是有效的 utf-8 编码，为一个 2 字节字符，跟着一个 1 字节字符。
 * 示例 2：
 * <p>
 * 输入：data = [235,140,4]
 * 输出：false
 * 解释：数据表示 8 位的序列: 11101011 10001100 00000100.
 * 前 3 位都是 1 ，第 4 位为 0 表示它是一个 3 字节字符。
 * 下一个字节是开头为 10 的延续字节，这是正确的。
 * 但第二个延续字节不以 10 开头，所以是不符合规则的。
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= data.length <= 2 * 104
 * 0 <= data[i] <= 255
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/utf-8-validation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题主要分两种情况吧，
 * 第一种情况是num>0时，这时候代表前面是有积累的。则首位一定要为1，为1则num--。否则返回false。
 * 第二种情况是num==0时，这种代表是UTF-8的首个字符，则要计算该字符多长，即num的数量。num=1或者num>4都是不合法的。而且要减去自身的那一个，所以num--。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution393 {

    public boolean validUtf8(int[] data) {
        int i = 0;
        int num = 0;
        while (i < data.length) {
            int value = data[i++];
            boolean b = (value & 0b1000_0000) == 0b1000_0000;
            if (num > 0) {
                if (b) {
                    num--;
                    continue;
                }
                return false;
            }
            if (num == 0) {
                int flag = 0b1000_0000;
                while ((value & flag) == flag) {
                    num++;
                    flag = flag >> 1;
                    if (num > 4) {
                        return false;
                    }
                }
                if (num == 1) {
                    return false;
                }
                num = num > 0 ? num - 1 : 0;
            }
        }
        return num == 0;
    }
}