package com.xt.leetcode;

import java.util.*;

/**
 * 811. 子域名访问计数
 * 每日一题：2022.10.14
 * 完成日期：2022.10.14
 * 链接：https://leetcode.cn/problems/subdomain-visit-count/
 * 描述：
 * 网站域名 "discuss.leetcode.com" 由多个子域名组成。顶级域名为 "com" ，二级域名为 "leetcode.com" ，最低一级为 "discuss.leetcode.com" 。当访问域名 "discuss.leetcode.com" 时，同时也会隐式访问其父域名 "leetcode.com" 以及 "com" 。
 * <p>
 * 计数配对域名 是遵循 "rep d1.d2.d3" 或 "rep d1.d2" 格式的一个域名表示，其中 rep 表示访问域名的次数，d1.d2.d3 为域名本身。
 * <p>
 * 例如，"9001 discuss.leetcode.com" 就是一个 计数配对域名 ，表示 discuss.leetcode.com 被访问了 9001 次。
 * 给你一个 计数配对域名 组成的数组 cpdomains ，解析得到输入中每个子域名对应的 计数配对域名 ，并以数组形式返回。可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：cpdomains = ["9001 discuss.leetcode.com"]
 * 输出：["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
 * 解释：例子中仅包含一个网站域名："discuss.leetcode.com"。
 * 按照前文描述，子域名 "leetcode.com" 和 "com" 都会被访问，所以它们都被访问了 9001 次。
 * 示例 2：
 * <p>
 * 输入：cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * 输出：["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
 * 解释：按照前文描述，会访问 "google.mail.com" 900 次，"yahoo.com" 50 次，"intel.mail.com" 1 次，"wiki.org" 5 次。
 * 而对于父域名，会访问 "mail.com" 900 + 1 = 901 次，"com" 900 + 50 + 1 = 951 次，和 "org" 5 次。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= cpdomain.length <= 100
 * 1 <= cpdomain[i].length <= 100
 * cpdomain[i] 会遵循 "repi d1i.d2i.d3i" 或 "repi d1i.d2i" 格式
 * repi 是范围 [1, 104] 内的一个整数
 * d1i、d2i 和 d3i 由小写英文字母组成
 * <p>
 * 解题思路：
 * 用map来记录出现的次数，key为域名，value为出现的次数。
 * 录入一个域名和次数时，同时录入对应的父级域名的次数
 * <p>
 * state:done
 */
public class Solution811 {

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> timeMap = new HashMap<>();
        for (String str : cpdomains) {
            String[] s = str.split(" ");
            int time = Integer.parseInt(s[0]);
            String cpdomain = s[1];
            add2Map(timeMap, cpdomain, time);
        }
        List<String> list = new ArrayList<>();
        for (String key : timeMap.keySet()) {
            list.add(timeMap.get(key) + " " + key);
        }
        return list;
    }

    private void add2Map(Map<String, Integer> timeMap, String cpdomain, int time) {
        String[] split = cpdomain.split("\\.");
        StringBuilder builder = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (i == split.length - 1) {
                builder.insert(0, split[i]);
            } else {
                builder.insert(0, split[i] + ".");
            }
            String key = builder.toString();
            timeMap.put(key, timeMap.getOrDefault(key, 0) + time);
        }
    }
}