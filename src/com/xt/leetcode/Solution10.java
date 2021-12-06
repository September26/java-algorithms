package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution10 {

//    Pattern compile = Pattern.compile("^(a.*)(babca)(.*ca)$");
//    Matcher matcher = compile.matcher("(ac)(babca)(babca)");

    /**
     * @param s aaa5454
     * @param p a.*4/a..4/aaa54/a.*5
     * @return true/false/true/true
     * <p>
     * String p = "(ba)(.*)(a)(.*)(babca)(.*)(ca)";
     * String s = "(ba)()(a)(acac)(babca)(bab)(ca)";
     */
    public boolean isMatch(String s, String p) {
        if (!p.contains(".*")) {
            return isMatch2(s, p);
        }
        if (p.equals(".*")) {
            return true;
        }

        boolean isMatchSplit = true;
        int startPIndex = 0;
        List<String> listP = new ArrayList<>();
        while (true) {
            int i = p.indexOf(".*", startPIndex);
            if (i == -1) {
                listP.add(p.substring(startPIndex));
                break;
            }
            String sp = p.substring(startPIndex, i);
            if (sp.length() > 0) {
                listP.add(sp);
            }
            sp = p.substring(i, i + 2);
            listP.add(sp);
            startPIndex = i + 2;
        }
        int startSIndex = 0;
        List<String> listS = new ArrayList<>();
        for (int i = 0; i < listP.size(); i++) {
            String s1 = listP.get(i);
            if (s1.equals(".*")) {
                continue;
            } else {
                int i1 = s.indexOf(s1, startSIndex);
                if (i1 > 0) {
                    String substring = s.substring(startSIndex, i1);
                    listS.add(substring);
                }
                listS.add(s1);
                startSIndex = i1 + s1.length();
            }
        }


        for (int i = 0; i < listP.size(); i++) {
            String sp = listP.get(i);
            if (sp.equals(".*")) {
                continue;
            }
            String ss = listS.get(i);
            isMatchSplit = isMatchSplit & isMatch2(ss, sp);
        }
        return isMatchSplit;
    }

    /**
     * 不存在.*的情况
     *
     * @param sStr
     * @param pStr
     * @return
     */
    public boolean isMatch2(String sStr, String pStr) {
        List<String> sList = new ArrayList<>();
        List<String> pList = new ArrayList<>();
        int index = 0;
        StringBuilder selectStr = new StringBuilder();
        char[] chars = sStr.toCharArray();
        while (index < chars.length) {
            char aChar = chars[index];
            if (selectStr.length() > 0 && selectStr.charAt(selectStr.length() - 1) == aChar) {
                selectStr.append(aChar);
            } else {
                if (selectStr.length() > 0) {
                    sList.add(selectStr.toString());
                }
                selectStr.setLength(0);
                selectStr.append(aChar);
            }
            index++;
        }
        if (selectStr.length() > 0) {
            sList.add(selectStr.toString());
        }

        selectStr.setLength(0);
        index = 0;
        chars = pStr.toCharArray();
        while (index < chars.length) {
            char aChar = chars[index];
            if (selectStr.length() > 0 && (selectStr.charAt(0) == aChar)) {
                selectStr.append(aChar);
            } else if (selectStr.length() > 0 && aChar == "*".toCharArray()[0]) {
                selectStr.append("*");
            } else {
                if (selectStr.length() > 0) {
                    pList.add(selectStr.toString());
                }
                selectStr.setLength(0);
                selectStr.append(aChar);
            }
            index++;
        }
        if (selectStr.length() > 0) {
            pList.add(selectStr.toString());
        }
//        System.out.print(sList.size());
//        System.out.print(pList.size());

        return isMatchLittle(pList, sStr);
//        return true;
    }

    private boolean isMatchLittle(List<String> pList, String str) {
        int sIndex = 0;
        int pListIndex = 0;
        while (sIndex < str.length() || pListIndex < pList.size()) {
            if (pListIndex >= pList.size()) {
                return false;
            }
            if (sIndex > str.length() - 1) {
                return false;
            }
            String p = pList.get(pListIndex);
            String s = str.substring(sIndex, sIndex + 1);
            //如果不含*的情况
            if (getTimesBy(p) == 0) {
                if (!p.equals(s) && !p.equals(".")) {
                    return false;
                }
                sIndex++;
                pListIndex++;
                continue;
            }
            //有*的情况，则需要取str中连续的同样字符串
            int sTimes = 0;
            while (sIndex + sTimes < str.length()) {
                String substring = str.substring(sIndex + sTimes, sIndex + sTimes + 1);
                if (!substring.equals(s)) {
                    break;
                }
                sTimes++;
            }

            int nextPTimes = 0;//统计.的数量
            while (pListIndex + nextPTimes + 1 < pList.size()) {
                String s1 = pList.get(pListIndex + nextPTimes + 1);
                if (s1.equals(".")) {
                    nextPTimes++;
                } else {
                    break;
                }
            }

            System.out.println("p:" + p + ",s:" + s);
            if (nextPTimes == 0) {
                if (!s.substring(0, 1).equals(p.substring(0, 1))) {
                    pListIndex++;
                    continue;
                }
                boolean matchSplitLittle = isMatchSplitLittle(str.substring(sIndex, sIndex + sTimes), p);
                if (!matchSplitLittle) {
                    return false;
                } else {
                    pListIndex++;
                    sIndex += (sTimes);
                }
                continue;
            }
            boolean flag = false;
            //统计..的数量

            //计算pListIndex之后有几个.，挨个遍历尝试
            for (int i = 0; i < nextPTimes; i++) {
                //这里有问题
                System.out.println("pList:" + pList.subList(pListIndex, pList.size()) + ",s:" + str.substring(sIndex, sIndex + sTimes + nextPTimes + i));
                if (isMatchLittle(pList.subList(pListIndex, pList.size()), str.substring(sIndex, sIndex + sTimes + nextPTimes + i))) {
                    flag = true;
                    break;
                }
            }
            return flag;
        }
        return true;
    }


    /**
     * 同类型的字符串匹配，只要s的长度大于p的即可
     */
    private boolean isMatchSplitLittle(String s, String p) {
        if (!s.substring(0, 1).equals(p.substring(0, 1)) && !p.equals(".")) {
            return false;
        }
        int times = getTimesBy(p);//统计*的次数
        if (times == 0) {
            return s.length() == p.length();
        }
        int pTimes = p.length() - times * 2;//统计刨除a*的长度，比如aa*a的pTimes=2
        return s.length() >= pTimes;
    }

    /**
     * 统计字符串中*的数量
     *
     * @return
     */
    private int getTimesBy(String p) {
        int times = p.length() - p.replaceAll("\\*", "").length();
        return times;
    }


}
