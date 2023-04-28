package com.xt.leetcode;

/**
 * 777. 在LR字符串中交换相邻字符
 * 每日一题：2022.10.02
 * 完成日期：2022.10.02
 * 链接：https://leetcode.cn/problems/swap-adjacent-in-lr-string/
 * 描述：
 * 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
 * <p>
 *  
 * <p>
 * 示例 :
 * <p>
 * 输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * 输出: True
 * 解释:
 * 我们可以通过以下几步将start转换成end:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= len(start) = len(end) <= 10000。
 * start和end中的字符串仅限于'L', 'R'和'X'。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/swap-adjacent-in-lr-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这里看的也是官方题解，我本来的思路是分条件去判断的，最后发现要判断的条件太多了。
 * 官方的题解是跳过X，直接获取跳过X的字符是否相同，如果不相同一定会有问题。
 * 如果相同，则继续判断是L还是R，如果是L，则start中的indexL1>end中的indexL2。
 * R的逻辑也是类似。
 * <p>
 * <p>
 * state:done
 */
public class Solution777 {

    public boolean canTransform(String start, String end) {
        int n = start.length();
        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && end.charAt(j) == 'X') {
                j++;
            }
            if (i < n && j < n) {
                if (start.charAt(i) != end.charAt(j)) {
                    return false;
                }
                char c = start.charAt(i);
                if ((c == 'L' && i < j) || (c == 'R' && i > j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        while (i < n) {
            if (start.charAt(i) != 'X') {
                return false;
            }
            i++;
        }
        while (j < n) {
            if (end.charAt(j) != 'X') {
                return false;
            }
            j++;
        }
        return true;
    }

}