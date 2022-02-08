package com.xt.leetcode;

import java.util.*;

/**
 * 0.逃出大迷宫
 * 在一个 10^6 x 10^6 的网格中，每个网格上方格的坐标为 (x, y) 。
 * <p>
 * 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。数组 blocked 是封锁的方格列表，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。
 * <p>
 * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。
 * <p>
 * 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * 输出：false
 * 解释：
 * 从源方格无法到达目标方格，因为我们无法在网格中移动。
 * 无法向北或者向东移动是因为方格禁止通行。
 * 无法向南或者向西移动是因为不能走出网格。
 * 示例 2：
 * <p>
 * 输入：blocked = [], source = [0,0], target = [999999,999999]
 * 输出：true
 * 解释：
 * 因为没有方格被封锁，所以一定可以到达目标方格。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= blocked.length <= 200
 * blocked[i].length == 2
 * 0 <= xi, yi < 10^6
 * source.length == target.length == 2
 * 0 <= sx, sy, tx, ty < 10^6
 * source != target
 * 题目数据保证 source 和 target 不在封锁列表内
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/escape-a-large-maze
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 一开始想的思路是遍历blocked看能否成环，但是这样的实现逻辑有些复杂。
 * 所以换了一个思路，因为题目有个限制，最多200个点，200个点最多可以分割19900个数据。（PS：不同的点对应的最大数据量不同，这个会动态计算）
 * 那么我从source开始分别向上下左右递归寻找非边界非围栏数据，如果寻找数量超过19900个，则证明不在围栏里面，那么就可以判断两个点可以相连。
 * 如果找到了target点，那么自然也是可以相连。
 * 不过按照这个思路实现下来有几个问题：
 * 1。source搜索数量超过了最大值，但是围栏其实只包围了target点还是不够的，解决方案是起点和目标点两者都要大于最大值才能保证相连。
 * 2。递归寻找的时候，每个节点都递归一次，加3000节点就递归3000次会对栈溢出的。解决方案是改成统计某一个方向所有值，直到遇到围栏才停止，这样就能有效的减少递归层数。
 * <p>
 * <p>
 * state:done
 */
public class Solution1036 {
    static final int MAX_LENGTH = 1000000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length <= 1) {
            return true;
        }
        int degeLength = 2;
        int containerNum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (degeLength <= 200) {
            containerNum = containerNum + degeLength - 1;
            map.put(degeLength, degeLength * degeLength / 2);
            degeLength++;
        }
        Set<String> set = new HashSet<>();
        for (int i = 0; i < blocked.length; i++) {
            int[] ints = blocked[i];
            set.add(getKey(ints[0], ints[1]));
        }
        HashSet<String> selectSt = new HashSet<>();
        int result = searchNextPoint(set, map.get(blocked.length), selectSt, source[0], source[1], target);
        if (result == 0) {
            selectSt.clear();
            result = searchNextPoint(set, map.get(blocked.length), selectSt, target[0], target[1], source);
            if (result == 0) {
                result = 1;
            }
        }
        return result == 1;
    }

    /**
     * @return 0:数量达到上限，1:找到目标值，-1：原点在环里面并且找不到目标值;
     */
    public int searchNextPoint(Set<String> blockedSet, int maxSize, Set<String> selectSet, int x, int y, int[] target) {
        if (x == target[0] && y == target[1]) {
            return 1;
        }
        if (selectSet.size() >= maxSize) {
            return 0;
        }
        int result = addNextPoints(selectSet, blockedSet, maxSize, target, 0, x, y);
        if (result != -1) {
            return result;
        }
        result = addNextPoints(selectSet, blockedSet, maxSize, target, 1, x, y);
        if (result != -1) {
            return result;
        }
        result = addNextPoints(selectSet, blockedSet, maxSize, target, 2, x, y);
        if (result != -1) {
            return result;
        }
        result = addNextPoints(selectSet, blockedSet, maxSize, target, 3, x, y);
        if (result != -1) {
            return result;
        }
        return -1;
    }

    private int addNextPoints(Set<String> selectSet, Set<String> blockedSet, int maxSize, int[] target, int direction, int x, int y) {
        List<int[]> list = new ArrayList<>();
        int result;
        int addNum = 1;
        String key;
        do {
            int[] ints = new int[]{x, y};
            if (direction == 0) {
                ints[0] = x + addNum;
            } else if (direction == 1) {
                ints[0] = x - addNum;
            } else if (direction == 2) {
                ints[1] = y + addNum;
            } else {
                ints[1] = y - addNum;
            }
            key = getKey(ints[0], ints[1]);
            addNum++;
            //addNum
            if (addNum > maxSize) {
                return 0;
            }
            if (selectSet.contains(key)) {
                break;
            }
            if (blockedSet.contains(key)) {
                break;
            }
            if (direction == 0 && (x + addNum >= MAX_LENGTH)) {
                break;
            }
            if (direction == 1 && (x - addNum < 0)) {
                break;
            }
            if (direction == 2 && (y + addNum >= MAX_LENGTH)) {
                break;
            }
            if (direction == 3 && (y - addNum < 0)) {
                break;
            }
            list.add(ints);
            selectSet.add(key);
        } while (true);
        for (int[] ints : list) {
            result = searchNextPoint(blockedSet, maxSize, selectSet, ints[0], ints[1], target);
            if (result != -1) {
                return result;
            }
        }
        return -1;
    }


    private String getKey(int x, int y) {
        return x + "_" + y;
    }

}