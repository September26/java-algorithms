package com.xt.mst;

import java.util.*;

/**
 * 面试题 17.11. 单词距离
 * 每日一题：2022.05.27
 * 完成日期：2022.05.27
 * 链接：https://leetcode.cn/problems/find-closest-lcci/
 * 描述：
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 * <p>
 * 示例：
 * <p>
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 * 提示：
 * <p>
 * words.length <= 100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-closest-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 遍历words，添加到map当中。key为单词，value为继承，存储单词对应的位置。
 * 查找word1和word2之间最短距离时，找出两个对应的集合，求出两个集合之间的最小差即可。
 *
 *
 * <p>
 * <p>
 * state:done
 */
public class SolutionMST1711 {

    public int findClosest(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            List<Integer> integers = map.get(word);
            if (integers == null) {
                integers = new ArrayList<>();
                map.put(word, integers);
            }
            integers.add(i);
        }

        List<Integer> integers1 = map.get(word1);
        List<Integer> integers2 = map.get(word2);

        Collections.sort(integers1);
        Collections.sort(integers2);

        int minDiff = Integer.MAX_VALUE;
        int index1 = 0;
        int index2 = 0;
        while (index1 < integers1.size() && index2 < integers2.size()) {
            int integer1 = integers1.get(index1);
            int integer2 = integers2.get(index2);
            minDiff = Math.min(minDiff, Math.abs(integer1 - integer2));
            if (integer1 >= integer2) {
                index2++;
                continue;
            }
            index1++;
        }
        return minDiff;
    }
}