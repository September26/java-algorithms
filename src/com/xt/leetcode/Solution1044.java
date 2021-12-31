package com.xt.leetcode;


/**
 * 1044. 最长重复子串
 * 给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
 * <p>
 * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "banana"
 * 输出："ana"
 * 示例 2：
 * <p>
 * 输入：s = "abcd"
 * 输出：""
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-duplicate-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * state:done
 */
public class Solution1044 {

    /**
     * bananaaaaaaaab
     * banana =>
     * 1.b/map:[{b,0}],maxLength=0,maxStr=""
     * 2.ba/map:[{b,0},{a,1}],maxLength=0,maxStr=""
     * 3.ban/map:[{b,0},{a,1},{n,2}],maxLength=0,maxStr=""
     * 4.bana/map:[{b,0},{a,1/3},{n,2}],maxLength=1,maxStr="a",list:{(index:1,length:1)}
     * 5.banan/map:[{b,0},{a,1/3},{n,2/4}],maxLength=2,maxStr="an",list:{(index:1,length:2),(index:2,length:1)}
     * 6.banana/map:[{b,0},{a,1/3},{n,2/4}],maxLength=3,maxStr="ana",list:{(index:1,length:3),(index:2,length:2),(index:3,length:1)}
     */

//    public String longestDupSubstring(String s) {
//        Random random = new Random();
//        // 生成两个进制
//        int a1 = random.nextInt(75) + 26;
//        int a2 = random.nextInt(75) + 26;
//        // 生成两个模
//        int mod1 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
//        int mod2 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
//        int n = s.length();
//        // 先对所有字符进行编码
//        int[] arr = new int[n];
//        for (int i = 0; i < n; ++i) {
//            arr[i] = s.charAt(i) - 'a';
//        }
//        // 二分查找的范围是[1, n-1]
//        int l = 1, r = n - 1;
//        int length = 0, start = -1;
//        while (l <= r) {
//            int m = l + (r - l + 1) / 2;
//            int idx = check(arr, m, a1, a2, mod1, mod2);
//            if (idx != -1) {
//                // 有重复子串，移动左边界
//                l = m + 1;
//                length = m;
//                start = idx;
//            } else {
//                // 无重复子串，移动右边界
//                r = m - 1;
//            }
//        }
//        return start != -1 ? s.substring(start, start + length) : "";
//    }
//
//    public int check(int[] arr, int m, int a1, int a2, int mod1, int mod2) {
//        int n = arr.length;
//        long aL1 = pow(a1, m, mod1);
//        long aL2 = pow(a2, m, mod2);
//        long h1 = 0, h2 = 0;
//        for (int i = 0; i < m; ++i) {
//            h1 = (h1 * a1 % mod1 + arr[i]) % mod1;
//            h2 = (h2 * a2 % mod2 + arr[i]) % mod2;
//            if (h1 < 0) {
//                h1 += mod1;
//            }
//            if (h2 < 0) {
//                h2 += mod2;
//            }
//        }
//        // 存储一个编码组合是否出现过
//        Set<Long> seen = new HashSet<Long>();
//        seen.add(h1 * mod2 + h2);
//        for (int start = 1; start <= n - m; ++start) {
//            h1 = (h1 * a1 % mod1 - arr[start - 1] * aL1 % mod1 + arr[start + m - 1]) % mod1;
//            h2 = (h2 * a2 % mod2 - arr[start - 1] * aL2 % mod2 + arr[start + m - 1]) % mod2;
//            if (h1 < 0) {
//                h1 += mod1;
//            }
//            if (h2 < 0) {
//                h2 += mod2;
//            }
//
//            long num = h1 * mod2 + h2;
//            // 如果重复，则返回重复串的起点
//            if (!seen.add(num)) {
//                return start;
//            }
//        }
//        // 没有重复，则返回-1
//        return -1;
//    }
//
//    public long pow(int a, int m, int mod) {
//        long ans = 1;
//        long contribute = a;
//        while (m > 0) {
//            if (m % 2 == 1) {
//                ans = ans * contribute % mod;
//                if (ans < 0) {
//                    ans += mod;
//                }
//            }
//            contribute = contribute * contribute % mod;
//            if (contribute < 0) {
//                contribute += mod;
//            }
//            m /= 2;
//        }
//        return ans;
//    }

    /**
     * 动态区间的方案
     * @param s
     * @return
     */
    public String longestDupSubstring(String s) {
        String maxStr = "";
        String curStr = "";//滑动窗口
        int num = 0;
        //复杂度：n2
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (j == s.length() - 1) {
                break;
            }
            while (j < s.length()) {
                curStr = s.substring(i, j + 1);
                j++;
                num++;
                //如果匹配的字符串长度较多，这个比较合适，indexOf的效率高
                if (s.indexOf(curStr, i + 1) <= i) {
                    break;
                }
                if (curStr.length() > maxStr.length()) {
                    maxStr = curStr;
                }
            }
        }
        System.out.println(num);
        return maxStr;
    }

//    public String longestDupSubstring(String s) {
//        int num = 0;
//        Map<String, List<Integer>> map = new HashMap<>();
//        String maxStr = "";
//        int maxLength = 0;
//        List<Model> list = new ArrayList<>();
//        Set<Integer> set = new HashSet<>();
//
//        for (int i = 0; i < s.length(); i++) {
//            String item = s.substring(i, i + 1);
//            //新节点加入list
//            set.clear();
//            List<Integer> indexList = map.computeIfAbsent(item, f -> new ArrayList<>());
//            //遍历list，不符合的移除
//            Iterator<Model> iterator = list.iterator();
//            while (iterator.hasNext()) {
//                num++;
//                Model next = iterator.next();
//                String substring = s.substring(next.startIndex + next.length, next.startIndex + next.length + 1);
//                if (item.equals(substring)) {
//                    next.length++;
//                    if (next.length > maxLength) {
//                        maxLength = next.length;
//                        maxStr = s.substring(next.startIndex, next.startIndex + next.length);
//                    }
//                    set.add(next.startIndex + next.length);
//                } else {
//                    iterator.remove();
//                }
//            }
//            for (Integer index : indexList) {
//                if (!set.contains(index + 1)) {
//                    Model model = new Model();
//                    model.startIndex = index;
//                    model.str = item;
//                    model.length = 1;
//                    list.add(model);
//                    set.add(model.startIndex + model.length);
//                }
//                if (maxLength == 0) {
//                    maxLength = 1;
//                    maxStr = item;
//                }
//            }
//            indexList.add(i);
//        }
//        System.out.println("num:" + num);
//        return maxStr;
//    }
//
//    static class Model {
//        int startIndex = 0;
//        int length = 0;
//        String str = "";
//    }
}