package com.xt.leetcode;

import java.util.Vector;

/**
 * 2047. 句子中的有效单词数
 * 描述
 * 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
 * <p>
 * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
 * <p>
 * 仅由小写字母、连字符和/或标点（不含数字）。
 * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
 * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
 * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
 * <p>
 * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：sentence = "cat and  dog"
 * 输出：3
 * 解释：句子中的有效单词是 "cat"、"and" 和 "dog"
 * 示例 2：
 * <p>
 * 输入：sentence = "!this  1-s b8d!"
 * 输出：0
 * 解释：句子中没有有效单词
 * "!this" 不是有效单词，因为它以一个标点开头
 * "1-s" 和 "b8d" 也不是有效单词，因为它们都包含数字
 * 示例 3：
 * <p>
 * 输入：sentence = "alice and  bob are playing stone-game10"
 * 输出：5
 * 解释：句子中的有效单词是 "alice"、"and"、"bob"、"are" 和 "playing"
 * "stone-game10" 不是有效单词，因为它含有数字
 * 示例 4：
 * <p>
 * 输入：sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."
 * 输出：6
 * 解释：句子中的有效单词是 "he"、"bought"、"pencils,"、"erasers,"、"and" 和 "pencil-sharpener."
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= sentence.length <= 1000
 * sentence 由小写英文字母、数字（0-9）、以及字符（' '、'-'、'!'、'.' 和 ','）组成
 * 句子中至少有 1 个 token
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 按照以下测试用例挨个试下基本上就能过了
 * abc
 * a-a
 * a!a
 * aa-!
 * <p>
 * 感觉代码写的不是太好，看起来有些凌乱。
 * 针对题目中的每个条件，我都设置了一个标记变量去记录，这样虽然能完成这题，看是看逻辑并不是很方便。
 * 解题思路都写到了注释里面。
 * <p>
 * <p>
 * state:done
 */
public class Solution2047 {

    public int countValidWords(String sentence) {
        char[] chars = sentence.toCharArray();
        int sum = 0;//总数量
        int wordNum = 0;//字符的长度
        int divisionIndex = -1;//-的位置
        boolean haveOhterLetter = false;//是否匹配到其他字符
        boolean isFit = true;//是否是合适的单词
        for (int i = 0; i <= chars.length; i++) {
            //清空
            if (i == chars.length || chars[i] == ' ') {
                if (divisionIndex >= 0 && divisionIndex == (i - 1)) {
                    isFit = false;
                }
                //连续两个空格
                if (wordNum == 0 && !haveOhterLetter) {
                    isFit = false;
                }
                //如果合适则添加
                if (isFit) {
                    sum++;
                }
                wordNum = 0;
                divisionIndex = -1;
                haveOhterLetter = false;
                isFit = true;
                continue;
            }
            char aChar = chars[i];
            //已经已经匹配到了不合适的，就没必要继续匹配下去了
            if (!isFit) {
                continue;
            }
            if (haveOhterLetter) {
                isFit = false;
                continue;
            }
            if (aChar >= 'a' && aChar <= 'z') {
                wordNum++;
                continue;
            }
            if (aChar >= '0' && aChar <= '9') {
                isFit = false;
                continue;
            }
            if (aChar == '-') {
                if (wordNum == 0 || divisionIndex >= 0) {
                    isFit = false;
                }
                divisionIndex = i;
                continue;
            }
            if (i > 0 && chars[i - 1] == '-') {
                isFit = false;
                continue;
            }
            //匹配到其他字符
            haveOhterLetter = true;
        }
        return sum;
    }
}