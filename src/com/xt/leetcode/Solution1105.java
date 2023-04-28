package com.xt.leetcode;


/**
 * 1105. 填充书架
 * 每日一题：2023.04.23
 * 完成日期：2023.04.23
 * 链接：https://leetcode.cn/problems/filling-bookcase-shelves/description/
 * 描述：
 * 给定一个数组 books ，其中 books[i] = [thicknessi, heighti] 表示第 i 本书的厚度和高度。你也会得到一个整数 shelfWidth 。
 *
 * 按顺序 将这些书摆放到总宽度为 shelfWidth 的书架上。
 *
 * 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelfWidth ），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
 *
 * 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。
 *
 * 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
 * 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
 *
 * 以这种方式布置书架，返回书架整体可能的最小高度。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
 * 输出：6
 * 解释：
 * 3 层书架的高度和为 1 + 3 + 2 = 6 。
 * 第 2 本书不必放在第一层书架上。
 * 示例 2:
 *
 * 输入: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= books.length <= 1000
 * 1 <= thicknessi <= shelfWidth <= 1000
 * 1 <= heighti <= 1000
 * <p>
 * 解题思路：
 * 动态规划的思路。
 * 这道题的核心就是，每次求第n本书的最小高度时，是从后往前算。
 * 比如当前位置第i位，则分别在最后一排尝试放入第i-1，i-2,i-3等等。
 * 则此时的最小高度分别为：heightSum = dp[i-1] + books[i][1];
 * heightSum = dp[i-2] + Math.max(books[i][1],books[i-1][1]);
 * heightSum = dp[i-3] + Math.max(books[i][1],books[i-1][1],books[i-2][1]);
 * 一直这样尝试下去，直到宽度不足。这样，最小的heightSum就是dp[i]。
 * 最终返回dp[i-1]即可。
 * state:done
 */
public class Solution1105 {

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int[] dp = new int[1000 * 1000];
        int n = books.length;
        for (int i = 0; i < n; i++) {
            int widthSum = 0;
            int heightSum = Integer.MAX_VALUE;
            int maxHeight = 0;
            for (int j = i; j >= 0; j--) {
                int[] book = books[j];
                int width = book[0];
                maxHeight = Math.max(book[1], maxHeight);
                int height = maxHeight + (j > 0 ? dp[j - 1] : 0);
                widthSum += width;
                if (widthSum > shelfWidth) {
                    break;
                }
                heightSum = Math.min(heightSum, height);
            }
            dp[i] = heightSum;
        }
        return dp[n - 1];
    }
}