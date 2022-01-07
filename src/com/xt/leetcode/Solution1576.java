package com.xt.leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * 1576. 替换所有的问号
 * 给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
 * <p>
 * 注意：你 不能 修改非 '?' 字符。
 * <p>
 * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
 * <p>
 * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "?zs"
 * 输出："azs"
 * 解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两个 'z' 。
 * 示例 2：
 * <p>
 * 输入：s = "ubv?w"
 * 输出："ubvaw"
 * 解释：该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。因为 "ubvvw" 和 "ubvww" 都包含连续重复的字符。
 * 示例 3：
 * <p>
 * 输入：s = "j?qg??b"
 * 输出："jaqgacb"
 * 示例 4：
 * <p>
 * 输入：s = "??yw?ipkj?"
 * 输出："acywaipkja"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 100
 * <p>
 * s 仅包含小写英文字母和 '?' 字符
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解题思路：
 * 不存在连续重复的字符这个比较简单，遍历字符串，每次选择的时候，只要判断一下一下前和后的字符是否相同，如果相同就继续选择下一个，不同则选择即可。
 * 在这个题的基础上稍微改一下，改成不存在重复的字符串（长度等于大于2）。应该怎么写呢？
 * 我们可以这样想，所以重复的字符串，那么长度至少为2。长度为3的重复，那么其中长度为2的也一定重复。
 * 所以针对所有长度为2的字符串组合成一个map，判断map中是否存在即可。
 * 详细解法看解法2。
 * state:done
 */
public class Solution1576 {

    public String modifyString(String s) {
        char[] selects = new char[26];
        for (int i = 0; i < 26; i++) {
            selects[i] = (char) ('a' + i);
        }
        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '?') {
                char before = (i == 0) ? '.' : chars[i - 1];
                char after = (i == chars.length - 1) ? '.' : chars[i + 1];
                char suitable = getSuitable(selects, before, after, i);
                builder.append(suitable);
                chars[i] = suitable;
            } else {
                builder.append(aChar);
            }
        }
        return builder.toString();
    }

    public char getSuitable(char[] strings, char last, char after, int insert) {
        char value = '.';
        for (int i = 0; i < strings.length; i++) {
            value = strings[i];
            if (value == last) {
                continue;
            }
            if (value == after) {
                continue;
            }
            break;
        }
        return value;
    }


    Map<String, Integer> map = new HashMap<>();

    public String modifyString2(String s) {
        char[] selects = new char[26];
        for (int i = 0; i < 26; i++) {
            selects[i] = (char) ('a' + i);
        }
        for (int i = 0; i < s.length() - 1; i++) {
            String substring = s.substring(i, i + 2);
            map.put(substring, i);
        }
//        s = "??yw?ipkj?"
        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '?') {
                char before = (i == 0) ? '.' : chars[i - 1];
                char after = (i == chars.length - 1) ? '.' : chars[i + 1];
                char suitable = getSuitable2(selects, before, after, i);
                builder.append(suitable);
                chars[i] = suitable;
            } else {
                builder.append(aChar);
            }
        }
        return builder.toString();
    }

    public char getSuitable2(char[] strings, char last, char after, int insert) {
        char value = '.';
        for (int i = 0; i < strings.length; i++) {
            value = strings[i];
            if (last != '.') {
                if (value == last) {
                    continue;
                }
                if (map.get(last + String.valueOf(value)) != null) {
                    continue;
                }
                map.put(last + String.valueOf(value), insert);
            }
            if (after != '?') {
                if (value == after) {
                    continue;
                }
                if (map.get(value + String.valueOf(after)) != null) {
                    continue;
                }
                map.put(value + String.valueOf(after), insert + 1);
            }
            break;
        }
        return value;
    }

}