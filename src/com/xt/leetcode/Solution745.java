package com.xt.leetcode;

import java.util.*;

/**
 * 745. 前缀和后缀搜索
 * 每日一题：2022.07.14
 * 完成日期：2022.07.14
 * 链接：https://leetcode.cn/problems/prefix-and-suffix-search/
 * 描述：
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 * <p>
 * 实现 WordFilter 类：
 * <p>
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * 输出
 * [null, 0]
 * 解释
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
 *  
 * 提示：
 * <p>
 * 1 <= words.length <= 10^4
 * 1 <= words[i].length <= 7
 * 1 <= pref.length, suff.length <= 7
 * words[i]、pref 和 suff 仅由小写英文字母组成
 * 最多对函数 f 执行 10^4 次调用
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/prefix-and-suffix-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 按照字典序排列，分别按照从左和从右的两种方式排序两个数组。
 * 输入pref和suff后，相同前缀的在上面生成的两个数组中一定是相邻的，所以只要找到开始和结束的，就能找到符合的两个范围，这两个范围中找相同的，就是我们想要的结果。
 * 由于本身要求的是返回其所在的位置，所以我们一开始对words进行一个缓存map，key为字符串，value为其位置。如果有相同的字符串，只记录其最大值即可。
 * 最终在所有符合我们要求的范围内，从map中找其最大值。
 * <p>
 * <p>
 * state:
 */
public class Solution745 {

    public static class WordFilter {

        Map<String, Integer> indexMap = new HashMap<>();
        String[] formLefts;
        String[] formRights;

        public WordFilter(String[] words) {
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                indexMap.put(word, i);//后面的i一定比前面加入的大
            }
            formLefts = new String[indexMap.size()];
            formRights = new String[indexMap.size()];
            int i = 0;
            for (String str : indexMap.keySet()) {
                formLefts[i] = str;
                formRights[i] = str;
                i++;
            }

            Arrays.sort(formLefts, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    char[] chars1 = o1.toCharArray();
                    char[] chars2 = o2.toCharArray();
                    for (int i = 0; i < chars1.length; i++) {
                        if (i >= chars2.length) {
                            return -1;
                        }
                        char c1 = chars1[i];
                        char c2 = chars2[i];
                        if (c1 == c2) {
                            continue;
                        }
                        return c1 - c2;
                    }
                    return 1;
                }
            });

            Arrays.sort(formRights, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    char[] chars1 = o1.toCharArray();
                    char[] chars2 = o2.toCharArray();
                    for (int i = 0; i < chars1.length; i++) {
                        char c1 = chars1[chars1.length - 1 - i];
                        int i2 = chars2.length - 1 - i;
                        if (i2 < 0) {
                            return 1;
                        }
                        char c2 = chars2[i2];
                        if (c1 == c2) {
                            continue;
                        }
                        return c1 - c2;
                    }
                    return 1;
                }
            });
        }

        public int f(String pref, String suff) {
            //二分查找找到开始的位置和结束的位置
            int leftStart = searchIndex(formLefts, pref, true, true);
            int leftEnd = searchIndex(formLefts, pref, false, true);
            if (leftStart == -1 || leftEnd == -1) {
                return -1;
            }
            HashSet<String> set = new HashSet<>();

            for (int i = leftStart; i <= leftEnd; i++) {
                set.add(formLefts[i]);
            }
            int rightStart = searchIndex(formRights, suff, true, false);
            int rightEnd = searchIndex(formRights, suff, false, false);
            if (rightStart == -1 || rightEnd == -1) {
                return -1;
            }
            HashSet<String> resultSet = new HashSet<>();
            for (int i = rightStart; i <= rightEnd; i++) {
                if (set.contains(formRights[i])) {
                    resultSet.add(formRights[i]);
                }
            }
            int max = 0;
            for (String str : resultSet) {
                max = Math.max(indexMap.get(str), max);
            }
            return max;
        }

        //寻找符合的起始和结束位置
        private int searchIndex(String[] strs, String find, boolean isStart, boolean isFromLeft) {
            int left = 0;
            int right = strs.length - 1;
            int middle;
            int ans = -1;

            /**
             *  isStart=true & isFromLeft = ture 时，找到从左侧开始满足find的第一个
             *  isStart=false& isFromLeft = true 时，找到从左侧开始满足find的最后一个
             *  isStart=true & isFromLeft = false时，找到从右侧开始满足find的第一个
             *  isStart=false &isFromLeft = false时，找到从右侧开始满足find的最后一个
             */
            while (left <= right) {
                middle = (left + right) / 2;
                String middleStr = strs[middle];
                char[] chars1 = middleStr.toCharArray();
                char[] findChars = find.toCharArray();
                boolean isFit = false;
                if (isFromLeft && middleStr.startsWith(find)) {
                    isFit = true;
                } else if (!isFromLeft && middleStr.endsWith(find)) {
                    isFit = true;
                }
                //判断是否满足str，如果满足，在修改ans的值，否则，值改left或right
                for (int i = 0; i <= chars1.length; i++) {
                    if (i >= findChars.length) {
                        if (isFit) {
                            ans = middle;
                        }
                        if (isStart) {
                            right = middle - 1;
                            break;
                        }
                        left = middle + 1;
                        break;

                    }
                    int i1 = isFromLeft ? i : chars1.length - 1 - i;
                    int i2 = isFromLeft ? i : findChars.length - 1 - i;
                    if (i1 < 0 || i >= chars1.length) {
                        break;
                    }

                    char middleChar = chars1[i1];
                    char searchChar = findChars[i2];
                    if (middleChar == searchChar) {
                        continue;
                    }
                    if (searchChar > middleChar) {
                        left = middle + 1;
                    } else {
                        right = middle - 1;
                    }
                    break;
                }
            }
            return ans;
        }
    }
}