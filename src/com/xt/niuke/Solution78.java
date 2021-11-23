package com.xt.niuke;


import com.xt.model.TreeNode;

import java.util.ArrayList;

/**
 * 把二叉树打印成多行
 * 描述
 * 给定一个节点数为 n 二叉树，要求从上到下按层打印二叉树的 val 值，同一层结点从左至右输出，每一层输出一行，将输出的结果存放到一个二维数组中返回。
 * 例如：
 * 给定的二叉树是{1,2,3,#,#,4,5}
 */
public class Solution78 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list2 = new ArrayList<>();
        if (pRoot == null) {
            return list2;
        }
        ArrayList<TreeNode> inList = new ArrayList<>();
        inList.add(pRoot);
        ArrayList<TreeNode> outList = new ArrayList<>();
        while (inList.size() > 0) {
            outList.clear();
            ArrayList<Integer> integers = PrintLevel(inList, outList);
            inList.clear();
            inList.addAll(outList);
            list2.add(integers);
        }
        return list2;
    }

    public ArrayList<Integer> PrintLevel(ArrayList<TreeNode> inList, ArrayList<TreeNode> outList) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < inList.size(); i++) {
            TreeNode node = inList.get(i);
            list.add(node.val);
            if (node.left != null) {
                outList.add(node.left);
            }
            if (node.right != null) {
                outList.add(node.right);
            }
        }
        return list;
    }
}
