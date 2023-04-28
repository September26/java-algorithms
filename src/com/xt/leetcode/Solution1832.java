package com.xt.leetcode;

import java.util.HashSet;
import java.util.Vector;

/**
 * 1832. 判断句子是否为全字母句
 * 每日一题：2022.12.13
 * 完成日期：2022.12.13
 * 链接：https://leetcode.cn/problems/check-if-the-sentence-is-pangram/
 * 描述：
 * 全字母句 指包含英语字母表中每个字母至少一次的句子。
 * <p>
 * 给你一个仅由小写英文字母组成的字符串 sentence ，请你判断 sentence 是否为 全字母句 。
 * <p>
 * 如果是，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：sentence = "thequickbrownfoxjumpsoverthelazydog"
 * 输出：true
 * 解释：sentence 包含英语字母表中每个字母至少一次。
 * 示例 2：
 * <p>
 * 输入：sentence = "leetcode"
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= sentence.length <= 1000
 * sentence 由小写英语字母组成
 * <p>
 * 解题思路：
 * 用一个set来装载，size达到26就返回true。
 * state:
 */
public class Solution1832 {

    public boolean checkIfPangram(String sentence) {
        HashSet<Character> set = new HashSet<>();
        char[] chars = sentence.toCharArray();
        for (char c : chars) {
            set.add(c);
            if(set.size()>=26){
                return true;
            }
        }
        return false;
    }
}