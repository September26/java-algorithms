package com.xt.leetcode;

import java.util.*;

/**
 * 997. 找到小镇的法官
 * 小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。
 * <p>
 * 如果小镇法官真的存在，那么：
 * <p>
 * 小镇法官不会信任任何人。
 * 每个人（除了小镇法官）都信任这位小镇法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 * <p>
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2, trust = [[1,2]]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：n = 3, trust = [[1,3],[2,3]]
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：n = 3, trust = [[1,3],[2,3],[3,1]]
 * 输出：-1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-town-judge
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解法：
 * 构建一个hashMap保存所有人的编号，value为相信他的人数。
 * 遍历trust数组，进行如下操作：
 * 1.如果某个编号的人被别人相信，则对应的相信人数+1；
 * 2.如果某个编号的人相信别人，则从map中移除。
 *
 * 遍历完成后，map中如果有剩余，那一定是不相信任何人的。对应的查看其value，如果是n-1,则一定是被除了自己之外其他人都相信的。
 *
 * state:done
 */
public class Solution997 {

    public int findJudge(int n, int[][] trust) {
        //被相信的人，相信他的人的数量
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 1; i <= n; i++) {
            map.put(i, 0);
        }
        for (int[] item : trust) {
            map.remove(item[0]);
            Integer integer = map.get(item[1]);
            if (integer != null) {
                map.put(item[1], integer + 1);
            }
        }
        if (map.size() == 1) {
            Integer next = map.keySet().iterator().next();
            if (map.get(next) == n - 1) {
                return next;
            }
        }
        return -1;
    }
}