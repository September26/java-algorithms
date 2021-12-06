package com.xt;

import com.xt.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个类用来生成二叉树代码和链表代码
 * 1.二叉树代码生成：输入类似{1,#,2,#,3,#,4,#,5}格式的字符串，即可打印输出对应的二叉树结构代码
 * 2.二叉树代码生成：输入类似{1,2,3,4,5}格式的字符串，即可打印输出对应的链表结构代码
 */
public class MakeCode {


    public static void main(String[] args) {

        MakeCode makeCode = new MakeCode();

        //生成二叉树代码
//        String str = "{1,#,2,#,3,#,4,#,5}";
//        makeCode.makeTreeNode(str);


        //生成链表代码
        makeCode = new MakeCode();
        String str2 = "{1,2,3,4,5,6,7}";
        makeCode.makeListNode(str2);
    }

    /**
     * 根据字符串构造二叉树
     * {1,2,3,4,5,6,7}
     */
    public void makeListNode(String str) {
        String replace = str.replaceAll("\\{|\\}", "");
        String[] split = replace.split(",");

        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            print("ListNode node" + i + " = new ListNode(" + s + ");");
        }

        for (int i = 0; i < split.length - 1; i++) {
            print("node" + i + ".next = node" + (i + 1) + ";");
        }
    }

    /**
     * 根据字符串构造二叉树
     * {-240,-500,-476,87,-783,-960,964,779,-317,532,190,-140,763,606,591,-202,-863,#,317,697,-943,811,-301,#,#,439,-56,-516,-586,670,-62,#,760,#,595,#,-929,808,#,#,862,-590,222,993,240,-895,-882,956,-301,909,#,742,537,#,#,273,228,#,#,-512,-357,#,#,-324,-797,-143,-784,324,#,#,-487,#,#,#,#,996,367,604,96,528,-127,-885,932,-51,464,#,#,608,-801,569,#,562,-412,-385,-451,579,368,#,#,#,#,-657,56,54,-506,#,425,#,#,933,76,-957,#,751,916,-988,182,247,817,-631,62,89,-297,937,32,#,#,#,#,#,888,#,#,-350,#,489,#,423,#,#,855,191,#,344,209,#,-521,-235,-148,-207,#,#,#,#,#,#,#,929,592,#,99,#,978,836,381,-664,-119,#,-84,#,#,#,#,#,-266,#,-603,303,-698,#,#,-94,#,#,#,#,#,#,#,#,#,#,-870,#,#,856,138,#,#,#,#,-456,#,#,#,#,#,#,4,#,#,#,#,#,884,-606,#,-139,905,#,174,#,302,-489,#,#,-208,-347,219,-457,#,#,#,-692,#,565,#,#,260,908,#,#,#,#,#,304,#,#,#,#,#,-237,529,-217,485,#,#,#,#,#,942,#,-248,#,#,-703,-872,818,-71,#,#,#,#,#,#,#,#,#,#,#,-160,#,#,#,#,#,#,-119,#,#,#,#,#,#,-762,#,249,#,-983,#,#,#,#,#,#,-842,#,567,#,#,#,#,#,#,493}
     */
    public void makeTreeNode(String str) {
//        TreeNode treeNode1 = new TreeNode(1);
        String replace = str.replaceAll("\\{|\\}", "");
        String[] split = replace.split(",");
        List<TreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String value = split[i];
            if (value.equals("#")) {
                treeNodes.add(null);
            } else {
                treeNodes.add(new TreeNode(Integer.parseInt(value), "treeNode" + i));
            }
        }
        //开始输出new的对象
        for (int i = 0; i < treeNodes.size(); i++) {
            if (treeNodes.get(i) != null) {
                print("TreeNode treeNode" + i + "= new TreeNode(" + treeNodes.get(i).val + ");");
            }
        }
        int base = 1;
        int sum = 1;
        ArrayList<TreeNode> headList = new ArrayList<>();
        ArrayList<TreeNode> childList = new ArrayList<>();
        ArrayList<TreeNode> childAllList = new ArrayList<>();
        headList.add(treeNodes.get(0));
        while (true) {
            childList.clear();
            childAllList.clear();
            for (int i = sum; i < sum + base * 2; i++) {
                if (i >= treeNodes.size()) {
                    break;
                }
                if (treeNodes.get(i) != null) {
                    childList.add(treeNodes.get(i));
                }
                childAllList.add(treeNodes.get(i));
            }
            printOneLevel(headList, childList, childAllList);
            headList.clear();
            headList.addAll(childList);
            base = childList.size();
            sum += childAllList.size();
            TreeNode lastNode = childAllList.get(childAllList.size() - 1);
            if (treeNodes.indexOf(lastNode) == (treeNodes.size() - 1)) {
                break;
            }
        }

    }

    public void printOneLevel(List<TreeNode> headList, List<TreeNode> childList, List<TreeNode> childAllList) {
        System.out.print("");
        for (int i = 0; i < headList.size(); i++) {
            TreeNode treeNode = headList.get(i);
            if ((i * 2) >= childAllList.size()) {
                break;
            }
            TreeNode leftNode = childAllList.get(i * 2);
            if (leftNode != null) {
                print(treeNode.name + ".left=" + leftNode.name + ";");
            }
            if ((i * 2 + 1) >= childAllList.size()) {
                break;
            }
            TreeNode rightNode = childAllList.get(i * 2 + 1);
            if (rightNode != null) {
                print(treeNode.name + ".right=" + rightNode.name + ";");
            }
        }
    }

    /**
     * 输出内容收口，可以改造生成到文件等等
     *
     * @param str
     */
    private void print(String str) {
        System.out.println(str);
    }
}
