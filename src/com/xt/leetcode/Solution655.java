package com.xt.leetcode;

import com.xt.model.TreeNode;
import javafx.util.Pair;

import java.util.*;

/**
 * 655. 输出二叉树
 * 每日一题：2022.08.23
 * 完成日期：2022.08.23
 * 链接：https://leetcode.cn/problems/print-binary-tree/
 * 描述：
 * 给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，用以表示树的 格式化布局 。构造此格式化布局矩阵需要遵循以下规则：
 * <p>
 * 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
 * 矩阵的列数 n 应该等于 2height+1 - 1 。
 * 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
 * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2height-r-1] ，右子节点放置在 res[r+1][c+2height-r-1] 。
 * 继续这一过程，直到树中的所有节点都妥善放置。
 * 任意空单元格都应该包含空字符串 "" 。
 * 返回构造得到的矩阵 res 。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2]
 * 输出：
 * [["","1",""],
 *  ["2","",""]]
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4]
 * 输出：
 * [["","","","1","","",""],
 *  ["","2","","","","3",""],
 *  ["","","4","","","",""]]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数在范围 [1, 210] 内
 * -99 <= Node.val <= 99
 * 树的深度在范围 [1, 10] 内
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/print-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 每增加一层，其实就是每个点都在原来基础上2*n+1;
 * 所以我的思路时用一个结构体Pair2<TreeNode, Integer>来记录节点和其所应该所处的位置index。
 * 每增加一级，则index=index*2+1；
 * 最终遍历allList，生成结果即可。
 * <p>
 * <p>
 * state:done
 */
public class Solution655 {
    List<List<Pair2<TreeNode, Integer>>> allList = new ArrayList<>();
    int level = 0;
    public List<List<String>> printTree(TreeNode root) {
        List<Pair2<TreeNode, Integer>> nextList = new ArrayList<>();
        Pair2<TreeNode, Integer> rootPair = new Pair2<>();
        rootPair.value = 0;
        rootPair.key = root;
        nextList.add(rootPair);
        searchOneLevel(nextList);
        List<List<String>> lists = new ArrayList<>();
        int levelNum = (int) Math.pow(2, level) - 1;
        for (int i = 0; i < level; i++) {
            List<String> list = new ArrayList<>();
            lists.add(list);
            List<Pair2<TreeNode, Integer>> pair2s = allList.get(i);
            Map<Integer, Pair2<TreeNode, Integer>> map = list2Map(pair2s);
            for (int j = 0; j < levelNum; j++) {
                Pair2<TreeNode, Integer> pair2 = map.get(j);
                if (pair2 == null) {
                    list.add("");
                    continue;
                }
                list.add(String.valueOf(pair2.key.val));
            }
        }
        return lists;
    }

    private Map<Integer, Pair2<TreeNode, Integer>> list2Map(List<Pair2<TreeNode, Integer>> pair2s) {
        Map<Integer, Pair2<TreeNode, Integer>> map = new HashMap<>();
        for (Pair2<TreeNode, Integer> pair : pair2s) {
            map.put(pair.value, pair);
        }
        return map;

    }

    public void searchOneLevel(List<Pair2<TreeNode, Integer>> currentLevelList) {
        //key=对应的node.val,value=index
        if (currentLevelList.size() == 0) {
            return;
        }
        level++;
        allList.add(currentLevelList);
        //如果currentLevelList没有子节点，则跳出循环。
        boolean hasNext = false;
        for (Pair2<TreeNode, Integer> pair2 : currentLevelList) {
            if (pair2.key.right != null || pair2.key.left != null) {
                hasNext = true;
                break;
            }
        }
        if (!hasNext) {
            return;
        }

        List<Pair2<TreeNode, Integer>> nextList = new ArrayList<>();
        //下面计算的都是扩容之后的位置，错了。应该计算扩容之前的位置。
        for (int i = 0; i < allList.size(); i++) {
            List<Pair2<TreeNode, Integer>> levelList = allList.get(i);
            for (Pair2<TreeNode, Integer> pair : levelList) {
                TreeNode node = pair.key;
                pair.value = pair.value * 2 + 1;
                if (i < allList.size() - 1) {
                    continue;
                }
                if (node.left != null) {
                    Pair2<TreeNode, Integer> newPair = new Pair2<>();
                    newPair.key = node.left;
                    newPair.value = pair.value - 1;
                    nextList.add(newPair);
                }
                if (node.right != null) {
                    Pair2<TreeNode, Integer> newPair = new Pair2<>();
                    newPair.key = node.right;
                    newPair.value = pair.value + 1;
                    nextList.add(newPair);
                }
            }
        }
        searchOneLevel(nextList);
    }

    static class Pair2<K, V> {
        private K key;
        private V value;
    }
}