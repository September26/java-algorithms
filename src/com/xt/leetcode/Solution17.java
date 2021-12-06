package com.xt.leetcode;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * done
 */
public class Solution17 {

    public List<String> letterCombinations(String digits) {
        Map<Integer, List<String>> map = new HashMap<>();
        List<String> strings = map.computeIfAbsent(2, integer -> new ArrayList<>());
        strings.add("a");
        strings.add("b");
        strings.add("c");

        strings = map.computeIfAbsent(3, integer -> new ArrayList<>());
        strings.add("d");
        strings.add("e");
        strings.add("f");

        strings = map.computeIfAbsent(4, integer -> new ArrayList<>());
        strings.add("g");
        strings.add("h");
        strings.add("i");

        strings = map.computeIfAbsent(5, integer -> new ArrayList<>());
        strings.add("j");
        strings.add("k");
        strings.add("l");

        strings = map.computeIfAbsent(6, integer -> new ArrayList<>());
        strings.add("m");
        strings.add("n");
        strings.add("o");

        strings = map.computeIfAbsent(7, integer -> new ArrayList<>());
        strings.add("p");
        strings.add("q");
        strings.add("r");
        strings.add("s");

        strings = map.computeIfAbsent(8, integer -> new ArrayList<>());
        strings.add("t");
        strings.add("u");
        strings.add("v");

        strings = map.computeIfAbsent(9, integer -> new ArrayList<>());
        strings.add("w");
        strings.add("x");
        strings.add("y");
        strings.add("z");

        List<String> result = new ArrayList<>();

        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            String substring = digits.substring(i, i + 1);
            List<String> strings1 = map.get(Integer.parseInt(substring));
            list.add(strings1);
        }
        add(list, 0, "", result);
        return result;
    }

    private void add(List<List<String>> list, int index, String str, List<String> result) {
        if (index == (list.size())) {
            if (str.length() > 0) {
                result.add(str);
            }
            return;
        }
        List<String> stringList = list.get(index);
        for (String a : stringList) {
            add(list, index + 1, str + a, result);
        }
    }
}