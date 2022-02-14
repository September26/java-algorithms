package com.xt.leetcode;

import java.util.Vector;

/**
 * 1189. “气球” 的最大数量
 * 日期：2022.2.13
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-balloons/
 * 描述
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * <p>
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：text = "nlaebolko"
 * 输出：1
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：text = "loonbalxballpoon"
 * 输出：2
 * <p>
 * 示例 3：
 * <p>
 * 输入：text = "leetcode"
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= text.length <= 10^4
 * text 全部由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 用一个数组存放'a'到'z'这26个字母。然后判断一下a,b,l/2,o/2,n这几个数的最小值就好。
 * <p>
 * <p>
 * state:done
 */
public class Solution1189 {

    public int maxNumberOfBalloons(String text) {
        char[] chars = text.toCharArray();
        int[] ints = new int[26];
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            ints[aChar - 'a']++;
        }
        int min = Math.min(ints[0], ints['b' - 'a']);
        min = Math.min(min, ints['l' - 'a'] / 2);
        min = Math.min(min, ints['o' - 'a'] / 2);
        min = Math.min(min, ints['n' - 'a']);
        return min;
    }
}