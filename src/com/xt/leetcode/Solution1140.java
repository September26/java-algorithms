package com.xt.leetcode;

import java.util.*;

/**
 * 1140. 石子游戏 II
 * 每日一题：2023.02.22
 * 完成日期：2023.02.22
 * 链接：https://leetcode.cn/problems/stone-game-ii/
 * 描述：
 * 爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 * <p>
 * 爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
 * <p>
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 * <p>
 * 游戏一直持续到所有石子都被拿走。
 * <p>
 * 假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
 * 示例 2:
 * <p>
 * 输入：piles = [1,2,3,4,5,100]
 * 输出：104
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 104
 * <p>
 * 解题思路：
 * 我的解法是两人互弈，分别构建对象A和，A先走，分别尝试取1堆和2堆，分别调用battle方法，该方法返回的对方可能取到的最大值。所以遍历的1，2过程中，会取让对方更小的那个来尝试。
 * 进入到battle流程后，等于身份切换，切换到B的身份，也走上述同样的逻辑，一直这样执行下去。
 * 如果剩余的堆数少于m，那么一定是全部取完。
 * 但是这样穷举所有可能的方式会导致算法超时，所以我们做一个优化。用map来存储结果。
 * key为执行到的步数和m值，value则为可能取到的最大值。则不同场景下走到同样的key，直接返回即可，节省运算量。
 *
 * state:done
 */
public class Solution1140 {

    Model playA;
    Model playB;
    int sumValue;
    Map<String, Integer> mapA = new HashMap<>();
    Map<String, Integer> mapB = new HashMap<>();

    public int stoneGameII(int[] piles) {
        playA = new Model("A");
        playB = new Model("B");
        sumValue = Arrays.stream(piles).sum();
        return battle(piles, 0, 2, true, 0);
    }

    /**
     * 返回值为当前对象可能的最大值
     */
    private int battle(int[] piles, int index, int m, boolean isARun, int step) {
        int sum = 0;
        if (m >= (piles.length - index)) {
            for (int i = index; i < piles.length; i++) {
                sum += piles[i];
            }
            return sum;
        }
        Map<String, Integer> map = isARun ? mapA : mapB;
        String key = index + "_" + m;
        if (map.get(key) != null) {
            return map.get(key);
        }
        Model play = isARun ? playA : playB;
        Model other = isARun ? playB : playA;
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            sum += piles[index + i];
            play.sum += sum;
            //这里的battle是对方运行时可能的最大值。
            int battle = battle(piles, index + i + 1, Math.max(m, 2 * (i + 1)), !isARun, step + 1);
            play.sum -= sum;
            if (battle < minSum) {
                minSum = battle;
            }
        }
        int value = sumValue - play.sum - other.sum - minSum;
        map.put(key, value);
        return value;
    }

    static class Model {
        String name;
        int sum;

        Model(String name) {
            this.name = name;
        }
    }
}