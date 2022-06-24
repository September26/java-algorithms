package com.xt.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 953. 验证外星语词典
 * 每日一题：2022.05.17
 * 完成日期：2022.05.17
 * 链接：https://leetcode.cn/problems/verifying-an-alien-dictionary/
 * 描述：某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 *
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * 输出：true
 * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
 * 示例 2：
 *
 * 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * 输出：false
 * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
 * 示例 3：
 *
 * 输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * 输出：false
 * 解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * 在 words[i] 和 order 中的所有字符都是英文小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/verifying-an-alien-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 把order转换为char和顺序对应关系的map，然后两两比较看是否符合字典序。
 * 比较的过程中，从前往后判断，每一位判断中，
 * 如果index1>index2，则不符合字典序；
 * 如果index1<index2，则符合字典序；
 * 如果index1==index2且不是最后一位，则判断下一位；
 * 如果index1==index2且最后一位，则不符合字典序。比如apple,app。
 * <p；
 * state:done
 */
public class Solution953 {

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = order.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], i);
        }
        for (int i = 1; i < words.length; i++) {
            boolean str1Bigger = isStr1Bigger(words[i - 1], words[i], map);
            if (!str1Bigger) {
                return false;
            }
        }
        return true;
    }

    private boolean isStr1Bigger(String str1, String str2, Map<Character, Integer> map) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (i >= chars2.length) {
                return false;
            }
            int index1 = map.get(chars1[i]);
            int index2 = map.get(chars2[i]);
            if (index1 > index2) {
                return false;
            }
            if (index1 < index2) {
                return true;
            }
            continue;
        }
        return true;
    }

}