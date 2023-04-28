package com.xt.leetcode;

/**
 * 09. 情感丰富的文字
 * 每日一题：2022.1.25
 * 完成日期：2022.1.25
 * 链接：https://leetcode.cn/problems/expressive-words/
 * 描述：
 * 有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" -> "hiii"。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。
 * <p>
 * 对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母 c ），然后往其中添加相同的字母 c 使其长度达到 3 或以上。
 * <p>
 * 例如，以 "hello" 为例，我们可以对字母组 "o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于 3。此外，我们可以进行另一种扩张 "ll" -> "lllll" 以获得 "helllllooo"。如果 S = "helllllooo"，那么查询词 "hello" 是可扩张的，因为可以对它执行这两种扩张操作使得 query = "hello" -> "hellooo" -> "helllllooo" = S。
 * <p>
 * 输入一组查询单词，输出其中可扩张的单词数量。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * S = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * 输出：1
 * 解释：
 * 我们能通过扩张 "hello" 的 "e" 和 "o" 来得到 "heeellooo"。
 * 我们不能通过扩张 "helo" 来得到 "heeellooo" 因为 "ll" 的长度小于 3 。
 * 提示：
 * <p>
 * 0 <= len(S) <= 100。
 * 0 <= len(words) <= 100。
 * 0 <= len(words[i]) <= 100。
 * S 和所有在 words 中的单词都只由小写字母组成。
 * 解题思路：
 * 拿s和words中的每个字符串使用compareStr方法匹配。
 * 方法中，找出每个字符连续出现的次数，如果length2 > length1或者length1 != length2 && length1 < 3则肯定不符合。
 * 否则继续匹配下个字符。
 *
 * <p>
 * state:done
 */
public class Solution809 {

    public int expressiveWords(String s, String[] words) {
        int result = 0;
        for (String word : words) {
            if (compareStr(s, word)) {
                result++;
            }
        }
        return result;
    }

    public boolean compareStr(String s, String str2) {
        int index1 = 0;
        int index2 = 0;
        char[] char1 = s.toCharArray();
        char[] char2 = str2.toCharArray();
        while (index1 < char1.length || index2 < char2.length) {
            int nextIndex1 = getLength(char1, index1);
            int nextIndex2 = getLength(char2, index2);
            if (nextIndex1 == -1 || nextIndex2 == -1) {
                return false;
            }
            if (char1[index1] != char2[index2]) {
                return false;
            }
            int length1 = nextIndex1 - index1;
            int length2 = nextIndex2 - index2;
            if (length2 > length1) {
                return false;
            }
            if (length1 != length2 && length1 < 3) {
                return false;
            }
            index1 = nextIndex1;
            index2 = nextIndex2;

        }
        return true;
    }

    private int getLength(char[] sChars, int index) {
        if (index >= sChars.length) {
            return -1;
        }
        char lastChar = sChars[index];
        while (index < sChars.length) {
            if (lastChar != sChars[index]) {
                return index;
            }
            index++;
        }
        return sChars.length;
    }
}