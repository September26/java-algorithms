package com.xt.leetcode;

import java.util.*;

/**
 * 1688.比赛中的配对次数
 * 日期：2022.02.22
 * 链接：https://leetcode-cn.com/problems/the-number-of-good-subsets/
 * 描述
 * 给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。
 * <p>
 * 比方说，如果 nums = [1, 2, 3, 4] ：
 * [2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
 * [1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
 * 请你返回 nums 中不同的 好 子集的数目对 10^9 + 7 取余 的结果。
 * <p>
 * nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：6
 * 解释：好子集为：
 * - [1,2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [1,2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [1,3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * 示例 2：
 * <p>
 * 输入：nums = [4,2,3,15]
 * 输出：5
 * 解释：好子集为：
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同质数 2 和 3 的乘积。
 * - [2,15]：乘积为 30 ，可以表示为互不相同质数 2，3 和 5 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [15]：乘积为 15 ，可以表示为互不相同质数 3 和 5 的乘积。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 30
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-number-of-good-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 *
 * <p>
 * <p>
 * state:
 */
public class Solution1994 {

    static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    static final int NUM_MAX = 30;
    static final int MOD = 1000000007;

    public int numberOfGoodSubsets(int[] nums) {
        int[] freq = new int[NUM_MAX + 1];
        for (int num : nums) {
            ++freq[num];
        }

        int[] f = new int[1 << PRIMES.length];
        f[0] = 1;
        for (int i = 0; i < freq[1]; ++i) {
            f[0] = f[0] * 2 % MOD;
        }

        for (int i = 2; i <= NUM_MAX; ++i) {
            if (freq[i] == 0) {
                continue;
            }

            // 检查 i 的每个质因数是否均不超过 1 个
            int subset = 0, x = i;
            boolean check = true;
            for (int j = 0; j < PRIMES.length; ++j) {
                int prime = PRIMES[j];
                if (x % (prime * prime) == 0) {
                    check = false;
                    break;
                }
                if (x % prime == 0) {
                    subset |= (1 << j);
                }
            }
            if (!check) {
                continue;
            }

            // 动态规划
            for (int mask = (1 << PRIMES.length) - 1; mask > 0; --mask) {
                if ((mask & subset) == subset) {
                    f[mask] = (int) ((f[mask] + ((long) f[mask ^ subset]) * freq[i]) % MOD);
                }
            }
        }

        int ans = 0;
        for (int mask = 1, maskMax = (1 << PRIMES.length); mask < maskMax; ++mask) {
            ans = (ans + f[mask]) % MOD;
        }

        return ans;
    }

//    public int numberOfGoodSubsets(int[] nums) {
//
//        Map<Integer, Node> zMap = new HashMap<>();
//        //  每个数拆分成质数
//        for (int i = 1; i <= 30; i++) {
//            Set<Integer> set = getSet(i);
//            if (set.size() > 0) {
//                Node node = new Node();
//                node.set = set;
//                zMap.put(i, node);
//            }
//        }
//        Map<Integer, Node> copyMap = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            Node node = zMap.get(nums[i]);
//            if (node == null) {
//                continue;
//            }
//            copyMap.put(nums[i], node);
//            node.num++;
//        }
//        ArrayList<Integer> list = new ArrayList<>(copyMap.keySet());
//        Collections.sort(list, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                if (o1 == o2) {
//                    return 0;
//                }
//                return o1 > o2 ? -1 : 1;
//            }
//        });
//        int num = 0;
//        Set<Integer> hasAdd = new HashSet<>();
//        for (int key : list) {
//            //冲突
//            System.out.println(key);
//            Node node = copyMap.get(key);
//            if (num == 0) {
//                num = node.num;
//            } else {
//                //判断冲突
//                int i = haveNum(hasAdd, key, copyMap);
//                num += (node.num + num - i);
//            }
//            hasAdd.add(key);
//        }
//        num = num - copyMap.getOrDefault(1, new Node()).num;
//        return num;
//    }
//
//    /**
//     * 判断冲突的数量
//     *
//     * @param hasAdd
//     * @param key
//     * @param copyMap
//     * @return
//     */
//    public int haveNum(Set<Integer> hasAdd, int key, Map<Integer, Node> copyMap) {
//        int num = 0;
//
//        for (Integer i : hasAdd) {
//            Node node1 = copyMap.get(i);
//            Node node2 = copyMap.get(key);
//            boolean flag = false;
//            for (int i1 : node1.set) {
//                for (int i2 : node2.set) {
//                    if (i1 == i2) {
//                        flag = true;
//                        break;
//                    }
//                }
//                if (flag) {
//                    break;
//                }
//            }
//            if (flag) {
//                num++;
//            }
//        }
//
//        return num;
//    }
//
//    public Set<Integer> getSet(int k) {
//        Set<Integer> set = new HashSet<>();
//        if (k == 1) {
//            set.add(1);
//            return set;
//        }
//        int i = 2;
//        while (k >= 2) {
//            if (k % i == 0) {
//                if (set.contains(i)) {
//                    set.clear();
//                    return set;
//                }
//                set.add(i);
//                k = k / i;
//                i = 2;
//                continue;
//            }
//            i++;
//        }
//        return set;
//    }
//
//    static class Node {
//        Set<Integer> set = new HashSet<>();
//        int num = 0;
//    }


}