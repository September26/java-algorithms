package com.xt.leetcode;

import java.util.Vector;

/**
 * 1813. 句子相似性 III
 * 每日一题：2023.01.16
 * 完成日期：2023.01.16
 * 链接：https://leetcode.cn/problems/sentence-similarity-iii/
 * 描述：
 * 一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。比方说，"Hello World" ，"HELLO" ，"hello world hello world" 都是句子。每个单词都 只 包含大写和小写英文字母。
 * <p>
 * 如果两个句子 sentence1 和 sentence2 ，可以通过往其中一个句子插入一个任意的句子（可以是空句子）而得到另一个句子，那么我们称这两个句子是 相似的 。比方说，sentence1 = "Hello my name is Jane" 且 sentence2 = "Hello Jane" ，我们可以往 sentence2 中 "Hello" 和 "Jane" 之间插入 "my name is" 得到 sentence1 。
 * <p>
 * 给你两个句子 sentence1 和 sentence2 ，如果 sentence1 和 sentence2 是相似的，请你返回 true ，否则返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：sentence1 = "My name is Haley", sentence2 = "My Haley"
 * 输出：true
 * 解释：可以往 sentence2 中 "My" 和 "Haley" 之间插入 "name is" ，得到 sentence1 。
 * 示例 2：
 * <p>
 * 输入：sentence1 = "of", sentence2 = "A lot of words"
 * 输出：false
 * 解释：没法往这两个句子中的一个句子只插入一个句子就得到另一个句子。
 * 示例 3：
 * <p>
 * 输入：sentence1 = "Eating right now", sentence2 = "Eating"
 * 输出：true
 * 解释：可以往 sentence2 的结尾插入 "right now" 得到 sentence1 。
 * 示例 4：
 * <p>
 * 输入：sentence1 = "Luky", sentence2 = "Lucccky"
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= sentence1.length, sentence2.length <= 100
 * sentence1 和 sentence2 都只包含大小写英文字母和空格。
 * sentence1 和 sentence2 中的单词都只由单个空格隔开。
 * <p>
 * 解题思路：
 * 首先根据长度，来区分长的那个sentenceMax和短的sentenceMin，在求出其对应的数组maxs和mins。
 * 只有三种可能，短的mins中，要么插入左边，要么插入右边，要么就是中间。
 * 如果是左边，那么mins的一定和maxs从右侧开始mins.length的若干字符一定相同。
 * 如果是右侧，那么mins的一定和maxs从左侧开始mins.length的若干字符一定相同。
 * 如果是中间，那么(mins中和maxs左侧相同的数量+mins中和右侧相同的数量)>=mins的数量。
 * state:done
 */
public class Solution1813 {

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String sentenceMax = sentence1.length() >= sentence2.length() ? sentence1 : sentence2;
        String sentenceMin = sentence1.length() < sentence2.length() ? sentence1 : sentence2;

        String[] maxs = sentenceMax.split(" ");
        String[] mins = sentenceMin.split(" ");

        //左侧
        boolean isMatch = true;
        int rightNum = 0;
        int leftNum = 0;
        for (int i = 0; i < mins.length; i++) {
            if (mins[mins.length - 1 - i].equals(maxs[maxs.length - 1 - i])) {
                continue;
            }
            isMatch = false;
            rightNum = i;
            break;
        }
        if (isMatch) {
            return true;
        }
        isMatch = true;
        //右侧
        for (int i = 0; i < mins.length; i++) {
            if (mins[i].equals(maxs[i])) {
                continue;
            }
            leftNum = i;
            isMatch = false;
            break;
        }
        if (isMatch) {
            return true;
        }
        int num = leftNum + rightNum;
        //中间
        System.out.println(rightNum + "," + leftNum);
        return num >= mins.length;
    }
}