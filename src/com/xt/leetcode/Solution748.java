package com.xt.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 748. 最短补全词
 * <p>
 * 给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词 。
 * <p>
 * 补全词 是一个包含 licensePlate 中所有的字母的单词。在所有补全词中，最短的那个就是 最短补全词 。
 * <p>
 * 在匹配 licensePlate 中的字母时：
 * <p>
 * 忽略 licensePlate 中的 数字和空格 。
 * 不区分大小写。
 * 如果某个字母在 licensePlate 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
 * 例如：licensePlate = "aBc 12c"，那么它的补全词应当包含字母 'a'、'b' （忽略大写）和两个 'c' 。可能的 补全词 有 "abccdef"、"caaacab" 以及 "cbca" 。
 * <p>
 * 请你找出并返回 words 中的 最短补全词 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 words 中 最靠前的 那个。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
 * 输出："steps"
 * 解释：最短补全词应该包括 "s"、"p"、"s"（忽略大小写） 以及 "t"。
 * "step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
 * "steps" 包含 "t"、"p" 和两个 "s"。
 * "stripe" 缺一个 "s"。
 * "stepple" 缺一个 "s"。
 * 因此，"steps" 是唯一一个包含所有字母的单词，也是本例的答案。
 * 示例 2：
 * <p>
 * 输入：licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
 * 输出："pest"
 * 解释：licensePlate 只包含字母 "s" 。所有的单词都包含字母 "s" ，其中 "pest"、"stew"、和 "show" 三者最短。答案是 "pest" ，因为它是三个单词中在 words 里最靠前的那个。
 * 示例 3：
 * <p>
 * 输入：licensePlate = "Ah71752", words = ["suggest","letter","of","husband","easy","education","drug","prevent","writer","old"]
 * 输出："husband"
 * 示例 4：
 * <p>
 * 输入：licensePlate = "OgEu755", words = ["enough","these","play","wide","wonder","box","arrive","money","tax","thus"]
 * 输出："enough"
 * 示例 5：
 * <p>
 * 输入：licensePlate = "iMSlpe4", words = ["claim","consumer","student","camera","public","never","wonder","simple","thought","use"]
 * 输出："simple"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-completing-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * state:done
 */
public class Solution748 {


    public String shortestCompletingWord(String licensePlate, String[] words) {
        Map<Character, Integer> timesMap = new HashMap<>();
        char[] chars = licensePlate.toCharArray();
        for (char c : chars) {
            if ((c >= 'A' && c <= 'Z')) {
                c += 32;
            }
            if ((c >= 'a' && c <= 'z')) {
                Integer integer = timesMap.computeIfAbsent(c, k -> 0);
                timesMap.put(c, integer + 1);
            }
        }
        String matchWord = null;
        for (int i = 0; i < words.length; i++) {
            //最短 ok
            //最靠前
            if (isMatch(timesMap, words[i]) >= 0) {
                if (matchWord == null || words[i].length() < matchWord.length()) {
                    matchWord = words[i];
                }
            }
        }
        return matchWord;
    }

    public int isMatch(Map<Character, Integer> timesMap, String word) {
        int indexSum = 0;
        //拆分word，看数量是否够
        char[] chars = word.toCharArray();
        Map<Character, List<Integer>> wordMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            List<Integer> indexList = wordMap.computeIfAbsent(c, k -> new ArrayList<>());
            indexList.add(i);
        }

        for (char key : timesMap.keySet()) {
            Integer needNum = timesMap.get(key);
            List<Integer> integers = wordMap.get(key);
            if (integers == null || integers.size() < needNum) {
                return -1;
            }
            for (int i = 0; i < needNum; i++) {
                indexSum += integers.get(i);
            }
        }
        return indexSum;
    }


}