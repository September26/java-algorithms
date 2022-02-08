package com.xt.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

/**
 * 1996. 游戏中弱角色的数量
 * 描述
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
 * <p>
 * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
 * <p>
 * 返回 弱角色 的数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：properties = [[5,5],[6,3],[3,6]]
 * 输出：0
 * 解释：不存在攻击和防御都严格高于其他角色的角色。
 * 示例 2：
 * <p>
 * 输入：properties = [[2,2],[3,3]]
 * 输出：1
 * 解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * 示例 3：
 * <p>
 * 输入：properties = [[1,5],[10,4],[4,3]]
 * 输出：1
 * 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 我们的核心就是就是获取每个attacki值所对应的可允许的最大defensei值。
 * 首先统计一下每个attacki值下面defensei的最大值，然后从对attacki排序。
 * 排序后从大到小遍历，如果满足更大的attacki所对应的defensei，更小的attacki自然也对应。
 * 比如attacki=7时,defensei=10时，那么attacki=6，defensei=9时，defensei自然也改为10。
 * 其实我这里的写法有一些多余了。先对attacki排序，然后从大到小遍历其实一遍也能出同样的结果。
 * 这样求出了每个attacki所对应的最大defensei后，遍历一遍，只要判断一下defensei值是否小于最大defensei，小于sum++即可。
 * <p>
 * <p>
 * state:
 */
public class Solution1996 {

    public int numberOfWeakCharacters(int[][] properties) {
        //按照攻击排序，统计数量
        HashMap<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < properties.length; i++) {
            int[] property = properties[i];
            int attacki = property[0];
            int defensei = property[1];
            Node node = map.computeIfAbsent(attacki, f -> new Node());
            node.attacki = attacki;
            node.defenseiMax = Math.max(node.defenseiMax, defensei);
            node.num++;
        }
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        int defenseiMax = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            Integer attacki = list.get(i);
            Node node = map.get(attacki);
            int local = node.defenseiMax;
            node.compareDefensei = defenseiMax;

            defenseiMax = Math.max(local, defenseiMax);
        }
        int sum = 0;
        for (int i = 0; i < properties.length; i++) {
            int[] property = properties[i];
            int attacki = property[0];
            int defensei = property[1];
            Node node = map.get(attacki);
            if (defensei < node.compareDefensei) {
                sum++;
            }
        }
        return sum;
    }

    static class Node {
        public int attacki = 0;
        public int defenseiMax = 0;
        public int num = 0;
        public int compareDefensei = 0;
    }

}