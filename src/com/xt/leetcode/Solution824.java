package com.xt.leetcode;

import java.util.Vector;

/**
 * 1688.比赛中的配对次数
 * 每日一题：2022.02.07
 * 完成日期：
 * 链接：
 * 描述：
 * 给你一个由若干单词组成的句子 sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。
 * <p>
 * 请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：
 * <p>
 * 如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
 * 例如，单词 "apple" 变为 "applema" 。
 * 如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
 * 例如，单词 "goat" 变为 "oatgma" 。
 * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
 * 例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
 * 返回将 sentence 转换为山羊拉丁文后的句子。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：sentence = "I speak Goat Latin"
 * 输出："Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * 示例 2：
 * <p>
 * 输入：sentence = "The quick brown fox jumped over the lazy dog"
 * 输出："heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= sentence.length <= 150
 * sentence 由英文字母和空格组成
 * sentence 不含前导或尾随空格
 * sentence 中的所有单词由单个空格分隔
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/goat-latin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 分割字符串，然后按照规则拼接就好
 * <p>
 * <p>
 * state:done
 */
public class Solution824 {

    public String toGoatLatin(String sentence) {
        String[] strs = sentence.split(" ");
        StringBuilder builder = new StringBuilder();
        StringBuilder aBuilder = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            aBuilder.append("a");
            builder.append(transform(str, aBuilder.toString()));
            if (i < strs.length-1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public String transform(String str, String aStr) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.startsWith("a") || lowerCase.startsWith("e") || lowerCase.startsWith("i") || lowerCase.startsWith("o") || lowerCase.startsWith("u")) {
            return str + "ma" + aStr;
        }

        return str.substring(1) + str.substring(0, 1) + "ma" + aStr;
    }

}