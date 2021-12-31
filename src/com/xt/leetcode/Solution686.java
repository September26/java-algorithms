package com.xt.leetcode;

/**
 * 686. 重复叠加字符串匹配
 * 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 * <p>
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = "abcd", b = "cdabcdab"
 * 输出：3
 * 解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
 * 示例 2：
 * <p>
 * 输入：a = "a", b = "aa"
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：a = "a", b = "a"
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：a = "abc", b = "wxyz"
 * 输出：-1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-string-match
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 思路1：
 * 首先肯定是最简单的一个解法，直接用java的提供的API。计算一下a和b的长度。
 * 如果b的长度是a的4倍，那么只要比较一下4倍长度的a,5倍长度的a，6倍长度的a分别是否包含b即可。
 * 同理，如果b的长度并不如a长，那么只要比较一下1倍长度的a，2倍长度的a,三倍长度的a是否包含b即可。
 *
 * 思路2：
 * 思路1中，我们要频繁调用contains，这是比较耗时的一个操作。可以的话，我们还是尽量避免使用。
 * 所以我的一个思路是依次的拼接，即a.substring().equals(b.substring())。
 * 但是这样逻辑就显的比较的复杂了，需要先找到b在a中的位置，然后每次向后取a的长度。
 * 而且还有一些异常情况，比如aaaab,ba的情况，这种情况目前我的解决方案还是相对简单一点，aaaab和aaaab拼接一下看是否存在ba。
 */
public class Solution686 {

    /**
     * 第二种解法
     *
     * @param a
     * @param b
     * @return
     */
    public int repeatedStringMatch(String a, String b) {
        if (a.contains(b)) {
            return 1;
        }
        int index = b.indexOf(a);
        if (index == -1) {
            String newA = a + a;
            if (newA.contains(b)) {
                return 2;
            }
            return -1;
        }
        boolean forward = true;
        int aLength = a.length();
        int bLength = b.length();
        int num = 0;
        int currentIndex = index;
        while (true) {
            if (forward) {
                if (currentIndex + aLength >= bLength) {
                    if (!a.startsWith(b.substring(currentIndex, bLength))) {
                        return -1;
                    }
                    num++;
                    if (index == 0) {
                        break;
                    }
                    forward = false;
                    currentIndex = index - aLength;
                    continue;
                } else {
                    if (!a.equals(b.substring(currentIndex, currentIndex + aLength))) {
                        return -1;
                    }
                    num++;
                    currentIndex += aLength;
                }
                continue;
            }
            if (currentIndex <= 0) {
                if (!a.endsWith(b.substring(0, currentIndex + aLength))) {
                    return -1;
                }
                num++;
                break;
            }
            //判断是否相等
            if (!a.equals(b.substring(currentIndex, currentIndex + aLength))) {
                return -1;
            }
            currentIndex -= aLength;

        }
        return num;
    }


//    第一种解法
//    public int repeatedStringMatch(String a, String b) {
//        int aLength = a.length();
//        int bLength = b.length();
//        int num = bLength / aLength;
//        StringBuilder builder = new StringBuilder();
//        for (int i = num; i < num+2; i++) {
//            builder.append(a);
//            if (builder.toString().contains(b)) {
//                return i + 1;
//            }
//        }
//        return -1;
//    }
}