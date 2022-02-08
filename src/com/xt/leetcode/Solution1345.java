package com.xt.leetcode;

import java.util.*;

/**
 * 1345. 跳跃游戏 IV
 * 描述
 * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 * <p>
 * 每一步，你可以从下标 i 跳到下标：
 * <p>
 * i + 1 满足：i + 1 < arr.length
 * i - 1 满足：i - 1 >= 0
 * j 满足：arr[i] == arr[j] 且 i != j
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 * <p>
 * 注意：任何时候你都不能跳到数组外面。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
 * 输出：3
 * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
 * 示例 2：
 * <p>
 * 输入：arr = [7]
 * 输出：0
 * 解释：一开始就在最后一个元素处，所以你不需要跳跃。
 * 示例 3：
 * <p>
 * 输入：arr = [7,6,9,6,9,6,9,7]
 * 输出：1
 * 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
 * 示例 4：
 * <p>
 * 输入：arr = [6,1,9]
 * 输出：2
 * 示例 5：
 * <p>
 * 输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题涉及到溯洄+最优解的算法。如果我们从0开始，这样会十分的不方便算。但是如果从终点开始算，则就方便的多。因为这时候每次算出来的值都是最少的，也就是最优解。
 * 所以我们可以先找出所有1步可以到达终点的点的集合，然后根据这集合，找出所有2步可到达终点的，然后递归找出3步，4步等等。
 * 每次遍历的时候，采取最优思想，用step数组来存储最优值。比如发现index=4时，如果在第3步的时候已经可以达到终点，那么第5步在计算到index=4时可直接跳过。
 * 就这边不断遍历，直到可以遍历的集合为空，或者找到index=0的点。
 * <p>
 * <p>
 * state:
 */
public class Solution1345 {
    boolean isEnd = false;
    Map<Integer, List<Integer>> indexMap = new HashMap<>();

    public int minJumps(int[] arr) {
        //递归的思路添加
        Integer[] steps = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            List<Integer> integers = indexMap.computeIfAbsent(arr[i], f -> new ArrayList<>());
            integers.add(i);
        }
        Set<Integer> list = new HashSet<>();
        list.add(arr.length - 1);
        addLevel(steps, arr, list, 0);

//        for (int i = 0; i < arr.length; i++) {
//            System.out.println("value:" + arr[i] + ",steps:" + steps[i]);
//        }
        return steps[0];
    }

    private void addLevel(Integer[] steps, int[] arr, Set<Integer> set, int step) {
        if (isEnd || set.size() == 0) {
            return;
        }
        Set<Integer> newSelect = new HashSet<>();
        for (Integer index : set) {
            if (steps[index] == null) {
                steps[index] = step;
            }
            if (index == 0) {
                isEnd = true;
                return;
            }
        }

        //分类型
        Set<Integer> hasAdd = new HashSet<>();
        for (Integer index : set) {//5W
            add2Set(steps, newSelect, index - 1);
            add2Set(steps, newSelect, index + 1);
            int value = arr[index];
            if (hasAdd.contains(value)) {
                continue;
            }
            hasAdd.add(value);
            List<Integer> indexList = indexMap.get(value);
            for (Integer nextIndex : indexList) {//5W
                add2Set(steps, newSelect, nextIndex);
            }

        }
        addLevel(steps, arr, newSelect, step + 1);
    }

    private void add2Set(Integer[] steps, Set<Integer> list, int index) {
        if (index >= steps.length) {
            return;
        }
        if (steps[index] != null) {
            return;
        }
        list.add(index);
    }

//    public int minJumps(int[] arr) {
//        Map<Integer, Integer> stepMap = new HashMap<>();//key为值，value为步数
//        int[] steps = new int[arr.length];
//
//        int step = 0;
//        for (int i = arr.length - 1; i >= 0; i--) {
//            steps[i] = step++;
//        }
//        for (int i = arr.length - 1; i >= 0; i--) {
//            int value = arr[i];
//            if (i == arr.length - 1) {
//                stepMap.put(value, steps[i]);
//                continue;
//            }
//            int minstep = steps[i];//本身要走的步数
//
//            //向前一步
//            int forwardStep = steps[i + 1];
//            minstep = Math.min(minstep, forwardStep + 1);
//
//            //向后一步的话，只有通过value值查找的方式才会用更少的步数，否则不可能。
//            if (i >= 1) {
//                Integer backStep = stepMap.get(arr[i - 1]);
//                if (backStep != null) {
//                    minstep = Math.min(minstep, backStep + 2);
//                }
//            }
//            //找值相同的
//            boolean isUpdate = false;
//            Integer valueStep = stepMap.get(value);//
//            if (valueStep != null) {
//                minstep = Math.min(minstep, valueStep + 1);
//                if (minstep < valueStep) {
//                    isUpdate = true;
//                    stepMap.put(value, minstep);
//                }
//            } else {
//                isUpdate = true;
//                stepMap.put(value, minstep);//
//            }
//            //如果更新了值，则向前检查是否有更合适的
//            if (value == -9082) {
//                System.out.print("");
//            }
//            steps[i] = minstep;
//            if (isUpdate) {
//                int k = i;
//                while (steps[k] + 1 < steps[k + 1]) {
//                    int value2 = arr[k + 1];
//                    steps[k + 1] = steps[k] + 1;
//                    Integer step2 = stepMap.get(value2);
//                    if (step2 == null || step2 > steps[k + 1]) {
//                        stepMap.put(value2, steps[k + 1]);
//                    }
//                    k++;
//                    stepMap.put(value, steps[i]);
//                }
//
//            }
//        }
//        for (int i = steps.length - 1; i >= 0; i--) {
//            System.out.println("value:" + arr[i] + ",minstep:" + steps[i]);
//        }
//
//        return steps[0];
//    }

    //尝试更新index位置的值
    public boolean tryUpdateStep(int[] steps, int index, int step) {
        if (steps[index] <= step) {
            return false;
        }
        steps[index] = step;
        //更新map
        return true;
    }

}