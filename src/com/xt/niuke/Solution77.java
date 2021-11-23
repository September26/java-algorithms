package com.xt.niuke;


import com.xt.model.TreeNode;

import java.util.ArrayList;

/**
 *
 */
public class Solution77 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (pRoot == null) {
            return list;
        }
        ArrayList<TreeNode> inList = new ArrayList<>();
        inList.add(pRoot);
        ArrayList<TreeNode> outList = new ArrayList<>();
        boolean isFromLeft = true;
        while (inList.size() > 0) {
            outList.clear();
            ArrayList<Integer> integers = PrintLevel(isFromLeft, inList, outList);
            inList.clear();
            inList.addAll(outList);
            list.add(integers);
            isFromLeft = !isFromLeft;
        }
        return list;
    }

    public ArrayList<Integer> PrintLevel(boolean isFromLeft, ArrayList<TreeNode> inList, ArrayList<TreeNode> outList) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < inList.size(); i++) {
            TreeNode node = inList.get(i);
            if (isFromLeft) {
                list.add(node.val);
            } else {
                list.add(0, node.val);
            }
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
