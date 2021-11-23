package com.xt.niuke;


/**
 * 描述
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“nowcoder. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a nowcoder.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 * <p>
 * 数据范围：1≤n≤100
 * 进阶：空间复杂度O(n)，时间复杂度O(n)，保证没有只包含空格的字符串
 * <p>
 * 输入：
 * "nowcoder. a am I"
 * 返回值：
 * "I am a nowcoder."
 */
public class Solution73 {
    public String ReverseSentence(String str) {
        StringBuilder builder = new StringBuilder();
        int end = str.length();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == 32) {
                builder.append(str, i + 1, end);
                builder.append(' ');
                end = i;
                continue;
            }
            if (i == 0) {
                builder.append(str, 0, end);
            }
        }
        return builder.toString();
    }

}
