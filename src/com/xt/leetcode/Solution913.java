package com.xt.leetcode;


import java.util.*;

/**
 * 913.猫和老鼠
 * 两位玩家分别扮演猫和老鼠，在一张 无向 图上进行游戏，两人轮流行动。
 * <p>
 * 图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点 b 组成。
 * <p>
 * 老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。
 * <p>
 * 在每个玩家的行动中，他们 必须 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 1 ，那么它必须移动到 graph[1] 中的任一节点。
 * <p>
 * 此外，猫无法移动到洞中（节点 0）。
 * <p>
 * 然后，游戏在出现以下三种情形之一时结束：
 * <p>
 * 如果猫和老鼠出现在同一个节点，猫获胜。
 * 如果老鼠到达洞中，老鼠获胜。
 * 如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
 * 给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏：
 * <p>
 * 如果老鼠获胜，则返回 1；
 * 如果猫获胜，则返回 2；
 * 如果平局，则返回 0 。
 * <p>
 * 示例一：
 * 输入：graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * 输出：0
 * <p>
 * 示例一：
 * 输入：graph = [[1,3],[0],[3],[0,2]]
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 3 <= graph.length <= 50
 * 1 <= graph[i].length < graph.length
 * 0 <= graph[i][j] < graph.length
 * graph[i][j] != i
 * graph[i] 互不相同
 * 猫和老鼠在游戏中总是移动
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cat-and-mouse
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 这题好多人没理解其意思，我先解释一下其意思吧。
 * <p>
 * <p>
 * state:
 */
public class Solution913 {

    HashMap<String, Integer> mouseWinCache = new HashMap<>();//key A_1
    //    HashMap<String, Integer> mouseDrawCacheMap = new HashMap<>();//key A_1
    HashMap<Integer, List<Integer>> sourceMap = new HashMap<>();
    boolean flag = false;
//

    /**
     * 自底向上的搜索方式
     *
     * @param graph
     * @return
     */
    public int catMouseGame(int[][] graph) {
//        int i = selectOne(graph, new HashMap<>(), 1, 2, -1);
        //构建map
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                List<Integer> integers = sourceMap.computeIfAbsent(graph[i][j], f -> new ArrayList<>());
                integers.add(i);
            }
        }
        //找出老鼠必胜的所有可能性
        List<int[]> mouseWinSelectList = new ArrayList<>();
        for (int i = 1; i < graph.length; i++) {
            boolean b = checkHave0(graph[i]);
            if (b) {
                for (int j = 1; j < graph.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    mouseWinSelectList.add(new int[]{i, j});
                }
            }
        }
        searchMouseWin(graph, mouseWinSelectList, 0);
        Integer integer = mouseWinCache.get("A1_B2");
        return integer == null ? 0 : integer;
    }

    /**
     *
     */
    public void searchMouseWin(int[][] graph, List<int[]> mouseWinSelectList, int step) {
        Iterator<int[]> iterator = mouseWinSelectList.iterator();
        while (iterator.hasNext()) {
            int[] ints = iterator.next();
            int mouseSelect = ints[0];
            int catSelect = ints[1];
            String key = "A" + mouseSelect + "_B" + catSelect;
            Integer integer = mouseWinCache.get(key);
            if (integer != null) {
                iterator.remove();
                continue;
            }
            mouseWinCache.put(key, 1);
        }
        List<int[]> newMouseWinSelectList = new ArrayList<>();
        for (int[] ints : mouseWinSelectList) {
            int mouseSelect = ints[0];
            int catSelect = ints[1];
            List<Integer> mouseList = sourceMap.get(mouseSelect);
            List<Integer> catList = sourceMap.get(catSelect);
            for (int select1 : mouseList) {

                for (int select2 : catList) {
                    if (select1 == select2) {
                        continue;
                    }
                    if (select1 == 0) {
                        continue;
                    }
                    if (select2 == 0) {
                        continue;
                    }

                    //mouse肯定选择能赢的，所以一定会从mouseList中选择mouseSelect


                    String key = "A" + select1 + "_B" + select2;

                    Integer integer = mouseWinCache.get(key);
                    if (integer != null) {
                        continue;
                    }
//                    newMouseWinSelectList.add(new int[]{select1, select2});
                }
            }
        }
        if (newMouseWinSelectList.size() == 0) {
            return;
        }
        searchMouseWin(graph, newMouseWinSelectList, step + 1);
    }

    public int selectMouse(int[][] graph, int mouseSelect, int catSelect, int step) {

        //判断mouseSelect和catSelect是不是mouse必赢的，如果必赢，则继续寻找

//        boolean isMouseStep = step % 2 == 0;
        List<Integer> mouseFromList = sourceMap.get(mouseSelect);
        List<Integer> catFromList = sourceMap.get(catSelect);
        for (int mouseFromSelect : mouseFromList) {
            boolean mouseWin = true;
            for (int catFromSelect : catFromList) {
                int result = selectMouse(graph, mouseFromSelect, catFromSelect, step + 1);
                if (result != 1) {
                    break;
                }
            }


        }
        return 0;
    }

//    public boolean selectCat(int[][] graph, int mouseSelect, int catSelect, int step) {
//        boolean isMouseStep = step % 2 == 0;
//        List<Integer> fromList = sourceMap.get(mouseSelect);
//        if (isMouseStep) {
//            for (int fromMouseSelect : fromList) {
//                boolean b = selectMouse(graph, fromMouseSelect, catSelect, step + 1);
//                if (b) {
//
//                }
//            }
//            return true;
//        }
//    }

    //
//    public int selectOne(int[][] graph, Map<String, Integer> selectMap, int mouseSelect, int catSelect, int lastCatSelect) {
//        if (mouseSelect == 4 && catSelect == 12) {
//            System.out.print("");
//        }
//        String key = "A" + mouseSelect + "_B" + catSelect + "_C" + lastCatSelect;
//        Integer integer = resultCacheMap.get(key);
//        if (integer != null) {
//            return integer;
//        }
//        int[] intsA = graph[mouseSelect];
//        int[] intsB = graph[catSelect];
//        if (mouseSelect == catSelect) {
//            return 2;
//        }
//        if (mouseSelect == lastCatSelect) {
//            return 2;
//        }
//        if (checkHave0(intsA)) {
//            return 1;
//        }
//        if (selectMap.get(key) != null) {
//            return 0;
//        }
//        selectMap.put(key, selectMap.size());
//        int result = 0;
//        Integer mouseWinSelect = null;
//        Integer mouseDrawSelect = null;
//        for (int i = 0; i < intsA.length; i++) {
//            int selectA = intsA[i];
//            int catWinSelect = -1;
//            int catDrawSelect = -1;
//
//            if (selectA == catSelect) {
//                continue;
//            }
//            for (int j = 0; j < intsB.length; j++) {
//                //cat不能选择0
//                int selectB = intsB[j];
//                if (selectB == 0) {
//                    continue;
//                }
//                Map<String, Integer> newSelectSet = new HashMap<>();
//                newSelectSet.putAll(selectMap);
//                if (selectA == 5 && selectB == 2) {
//                    System.out.print("");
//                }
//                int local = selectOne(graph, newSelectSet, selectA, selectB, catSelect);//判断是否存在赢的可能
//
//                //判断是会否存在平的可能
//                if (mouseSelect == 4 && catSelect == 12) {
//                    System.out.print("");
//                }
//                if (local == 2) {
//                    catWinSelect = j;
//                    break;
//                }
//                //如果重复，则
//                if (local == 0) {
//                    catDrawSelect = j;
//                }
//            }
//            if (catWinSelect >= 0) {
//                continue;
//            }
//            if (catDrawSelect >= 0) {
//                mouseDrawSelect = i;
//                continue;
//            }
//            mouseWinSelect = i;
//            break;
//        }
//        if (mouseWinSelect != null) {
//            result = 1;
//        } else if (mouseDrawSelect != null) {
//
//        } else {
//            result = 2;
//        }
//        resultCacheMap.put(key, result);
//        if (mouseSelect == 4) {
//            System.out.print("");
//        }
//        return result;
//    }
//
//
    public boolean checkHave0(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == 0) {
                return true;
            }
        }
        return false;
    }

}