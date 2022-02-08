package com.xt.leetcode;

/**
 * 1220. 统计元音字母序列的数目
 * 描述
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 * <p>
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音 'a' 后面都只能跟着 'e'
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
 * 每个元音 'i' 后面 不能 再跟着另一个 'i'
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
 * 每个元音 'u' 后面只能跟着 'a'
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 1
 * 输出：5
 * 解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：10
 * 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
 * 示例 3：
 * <p>
 * 输入：n = 5
 * 输出：68
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2 * 10^4
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-vowels-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 首先我们转换一下描述
 * a：{e}
 * e：{a,i}
 * i：{a,e,o,u}
 * o：{u,i}
 * u：{a}
 * 我们每次运算之后，只要分别统计最后一位"a", "e", "i" , "o" 和 "u"的数量即可。
 * 这样有了每一种字符的数量，我们就可以知道长度多一位时有多少种可能。
 * 比如a=4时，对应4种可能。e=3时对应3*2=6种可能。
 * 不过本题的难点时取模，我的方案时每一次运算的时候都取模。
 * state:done
 */
public class Solution1220 {

    public int countVowelPermutation(int n) {
        int aNum = 0;
        int eNum = 0;
        int iNum = 0;
        int oNum = 0;
        int uNum = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                aNum++;
                eNum++;
                iNum++;
                oNum++;
                uNum++;
                sum = 5;
                continue;
            }
            int sumLocal = 0;
            int aNumLocal = 0;
            int eNumLocal = 0;
            int iNumLocal = 0;
            int oNumLocal = 0;
            int uNumLocal = 0;

            //a
            eNumLocal = getValue(eNumLocal, aNum);
            sumLocal = getValue(sumLocal, aNum);

            //e
            aNumLocal = getValue(aNumLocal, eNum);
            sumLocal = getValue(sumLocal, eNum);

            iNumLocal = getValue(iNumLocal, eNum);
            sumLocal = getValue(sumLocal, eNum);

            //i
            aNumLocal = getValue(aNumLocal, iNum);
            sumLocal = getValue(sumLocal, iNum);

            eNumLocal = getValue(eNumLocal, iNum);
            sumLocal = getValue(sumLocal, iNum);

            oNumLocal = getValue(oNumLocal, iNum);
            sumLocal = getValue(sumLocal, iNum);

            uNumLocal = getValue(uNumLocal, iNum);
            sumLocal = getValue(sumLocal, iNum);


            //o
            iNumLocal = getValue(iNumLocal, oNum);
            sumLocal = getValue(sumLocal, oNum);

            uNumLocal = getValue(uNumLocal, oNum);
            sumLocal = getValue(sumLocal, oNum);

            //u
            aNumLocal = getValue(aNumLocal, uNum);
            sumLocal = getValue(sumLocal, uNum);

            //取模
            aNum = aNumLocal;
            eNum = eNumLocal;
            iNum = iNumLocal;
            oNum = oNumLocal;
            uNum = uNumLocal;
            sum = sumLocal;
        }
        return sum;
    }

    public int getValue(int oldValue, int addValue) {
        return (oldValue + addValue) % 1000000007;
    }
}