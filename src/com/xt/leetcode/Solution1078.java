package com.xt.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 1078. Bigram 分词
 * 12.26
 * 给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，其中 second 紧随 first 出现，third 紧随 second 出现。
 * <p>
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
 * 输出：["girl","student"]
 * 示例 2：
 * <p>
 * 输入：text = "we will we will rock you", first = "we", second = "will"
 * 输出：["we","rock"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/occurrences-after-bigram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 依次遍历数组，
 * 先判断是否和first相等，
 * 1.1如果不相等，则index+1继续。
 * 1.2如果相等，则取index+1位的判断是否和second相等，
 * 2.1如果相等则取index+2位加入集合。
 * 2.2如果不相等则index+1继续遍历
 * state:done
 */
public class Solution1078 {

    public String[] findOcurrences(String text, String first, String second) {
        String[] s = text.split(" ");
        int index = 0;
        List<String> list = new ArrayList<>();
        while (index < s.length) {
            if (!first.equals(s[index++])) {
                continue;
            }
            if (index >= s.length) {
                break;
            }
            if (!second.equals(s[index])) {
                continue;
            }
            if (index + 1 >= s.length) {
                break;
            }
            list.add(s[index + 1]);
        }
        return list.toArray(new String[0]);
    }
}