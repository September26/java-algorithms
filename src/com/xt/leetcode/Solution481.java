package com.xt.leetcode;

import java.util.Vector;

/**
 * 481. 神奇字符串
 * 每日一题：2022.10.31
 * 完成日期：2022.10.31
 * 链接：https://leetcode.cn/problems/magical-string/
 * 描述：
 * 神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：
 * <p>
 * 神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
 * s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。每组中 1 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s 自身。
 * <p>
 * 给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 6
 * 输出：3
 * 解释：神奇字符串 s 的前 6 个元素是 “122112”，它包含三个 1，因此返回 3 。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 105
 * <p>
 * 解题思路：
 * 首先，使用两个标记位来记录使用到的位置和插入的位置。
 * 前两个字符先用硬编码的方式写入，第三个字符开始，判断最末端的两个字符类型，一共有四种，11，12，21，22，
 * 然后结合插入1个还是2个字符，进行插入，并记录插入的1的数量。
 * <p>
 * state:done
 */
public class Solution481 {
    int num1Sum = 0;

    public int magicalString(int n) {
        int insertIndex = 0;
        int useIndex = 0;

        StringBuilder builder = new StringBuilder();
        while (true) {
            if (builder.length() == 0) {
                builder.append("1");
                insertIndex++;
                num1Sum++;
                continue;
            }
            if (builder.length() == 1) {
                builder.append("2");
                insertIndex++;
                useIndex++;
                continue;
            }
            String lastStr = builder.substring(builder.length() - 2, builder.length());
            char useChar = builder.charAt(useIndex);
            insertIndex = insertChar(builder, lastStr, useChar, insertIndex);
            useIndex++;
            if (insertIndex == n) {
                break;
            }
            if (insertIndex > n) {
                if (builder.charAt(builder.length() - 1) == '1') {
                    num1Sum--;
                }
                break;
            }
        }
        return num1Sum;
    }

    /**
     * @return
     */
    private int insertChar(StringBuilder builder, String lastStr, char useChar, int insertIndex) {
        //插入1个
        if (useChar == '1') {
            if (lastStr.equals("11")) {
                builder.append('2');
            } else if (lastStr.equals("12")) {
                builder.append('1');
                num1Sum += 1;
            } else if (lastStr.equals("21")) {
                builder.append('2');
            } else if (lastStr.equals("22")) {
                builder.append('1');
                num1Sum += 1;
            }
            return insertIndex + 1;
        }
        //插入2个
        if (lastStr.equals("11")) {
            builder.append("22");
        } else if (lastStr.equals("12")) {
            builder.append("21");
            num1Sum += 1;
        } else if (lastStr.equals("21")) {
            builder.append("12");
            num1Sum += 1;
        } else if (lastStr.equals("22")) {
            builder.append("11");
            num1Sum += 2;
        }
        return insertIndex + 2;
    }

}