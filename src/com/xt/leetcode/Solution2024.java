package com.xt.leetcode;

import java.util.Vector;

/**
 * 2024. 考试的最大困扰度
 * 每日一题：2022.03.29
 * 完成日期：2022.03.29
 * 链接：https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/
 * 描述：
 * 一位老师正在出一场由 n 道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F' 表示）。老师想增加学生对自己做出答案的不确定性，方法是 最大化 有 连续相同 结果的题数。（也就是连续出现 true 或者连续出现 false）。
 * <p>
 * 给你一个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。除此以外，还给你一个整数 k ，表示你能进行以下操作的最多次数：
 * <p>
 * 每次操作中，将问题的正确答案改为 'T' 或者 'F' （也就是将 answerKey[i] 改为 'T' 或者 'F' ）。
 * 请你返回在不超过 k 次操作的情况下，最大 连续 'T' 或者 'F' 的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：answerKey = "TTFF", k = 2
 * 输出：4
 * 解释：我们可以将两个 'F' 都变为 'T' ，得到 answerKey = "TTTT" 。
 * 总共有四个连续的 'T' 。
 * 示例 2：
 * <p>
 * 输入：answerKey = "TFFT", k = 1
 * 输出：3
 * 解释：我们可以将最前面的 'T' 换成 'F' ，得到 answerKey = "FFFT" 。
 * 或者，我们可以将第二个 'T' 换成 'F' ，得到 answerKey = "TFFF" 。
 * 两种情况下，都有三个连续的 'F' 。
 * 示例 3：
 * <p>
 * 输入：answerKey = "TTFTTFTT", k = 1
 * 输出：5
 * 解释：我们可以将第一个 'F' 换成 'T' ，得到 answerKey = "TTTTTFTT" 。
 * 或者我们可以将第二个 'F' 换成 'T' ，得到 answerKey = "TTFTTTTT" 。
 * 两种情况下，都有五个连续的 'T' 。
 *  
 * <p>
 * 提示：
 * <p>
 * n == answerKey.length
 * 1 <= n <= 5 * 10^4
 * answerKey[i] 要么是 'T' ，要么是 'F'
 * 1 <= k <= n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 只有两种可能，要么把T变为F，或者把F变为T，并且字符串一定是连续的。
 * 我们现以F->T为例。我们可以使用双指针策略开始遍历。
 * 首先右指针逻辑。
 * 如果读到了F，则currentNum++.
 * 如果读到了T，并且surplusNum<k，则surplusNum++，则currentNum++。
 * 如果读到了T，并且surplusNum=k，则触发左指针逻辑。
 * 左指针逻辑中，如果读到了F，则currentNum--。
 * 如果读到了T，则currentNum--，并且surplusNum--，并且触发右指针逻辑。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution2024 {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        char[] chars = answerKey.toCharArray();
        return Math.max(getMax(chars, 'F', k), getMax(chars, 'T', k));
    }

    public int getMax(char[] chars, char c, int k) {
        int maxNum = 0;//最大数量
        int currentNum = 0;//当前数量
        int surplusNum = 0;//使用的K数量
        int right = 0;
        int left = 0;
        boolean isRight = true;
        while (right < chars.length) {
            if (isRight) {
                //右指针逻辑
                char aChar = chars[right];
                if (aChar == c) {
                    right++;
                    currentNum++;
                    maxNum = Math.max(currentNum, maxNum);
                    continue;
                }
                if (surplusNum < k) {
                    right++;
                    surplusNum++;
                    currentNum++;
                    maxNum = Math.max(currentNum, maxNum);
                    continue;
                }
                isRight = false;
                continue;
            }
            //左指针逻辑
            char aChar = chars[left];
            if (aChar == c) {
                currentNum--;
                left++;
                continue;
            }
            left++;
            currentNum--;
            surplusNum--;
            isRight = true;
        }
        return maxNum;
    }

}