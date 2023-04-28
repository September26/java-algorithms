package com.xt;

import com.xt.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个类用来生成二叉树代码和链表代码
 * 1.二叉树代码生成:makeTreeNode：输入类似{1,#,2,#,3,#,4,#,5}格式的字符串，即可打印输出对应的二叉树结构代码
 * 2.二叉树代码生成:makeListNode：输入类似{1,2,3,4,5}格式的字符串，即可打印输出对应的链表结构代码
 * 3.二维数组转List<List<>>形式
 */
public class MakeCode {


    public static void main(String[] args) {

        MakeCode makeCode = new MakeCode();

        //生成二叉树代码
        String str = "[1,null,2,null,0,3]";
        makeCode.makeTreeNode(str);


        //生成链表代码
        makeCode = new MakeCode();
        String str2 = "{2,7,4,3,5}";
//        makeCode.makeListNode(str2);

        String str3 = "[[20,22],[71,38],[65,69],[63,69],[80,2],[67,31],[65,81],[4,58],[46,60],[32,20],[29,86],[74,73],[3,67],[26,0],[71,33],[76,84],[63,4],[36,12],[28,99],[27,85],[94,56],[32,78],[56,49],[63,27],[41,21],[91,96],[34,37],[9,24],[59,51],[82,6],[94,38],[70,87],[24,88],[42,18],[57,46],[69,47],[10,1],[34,67],[55,99],[81,23],[12,63],[24,75],[39,5],[41,42],[70,70],[7,86],[94,45],[28,81],[22,14],[80,87],[2,10],[26,88],[64,72],[92,69],[74,58],[44,38],[59,53],[10,67],[59,21],[17,54],[51,89],[8,37],[40,72],[71,31],[93,5],[57,88],[60,21],[47,40],[44,49],[16,14],[84,37],[38,1],[29,81],[79,38],[91,21],[4,42],[86,45],[62,81],[29,69],[22,71],[45,10],[28,80],[43,71],[25,87],[8,87],[89,42],[76,69],[97,9],[3,26],[81,19],[5,36],[31,100],[40,31],[23,12],[23,45]]";
//        makeCode.makeListList(str3);

    }

    /**
     * 根据二维数组，生成对应的List<List>
     *
     * @param str
     */
    public void makeListList(String str) {

//        List<List<Integer>> points = new ArrayList<>();
//        List<Integer> point0 = new ArrayList<>();
//        point0.add(1);
//        point0.add(1);

        String substring = str.substring(1, str.length() - 1);
        String[] split = substring.split("],");
        print("List<List<Integer>> points = new ArrayList<>();");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            String[] split1 = s.split(",");
            print("List<Integer> point" + i + " = new ArrayList<>();");
            print("point" + i + ".add(" + split1[0].replaceAll("\\[", "") + ");");
            print("point" + i + ".add(" + split1[1].replaceAll("\\]", "") + ");");
            print("points.add(point" + i + ");");
        }

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
        String replace = str.replaceAll("\\{|\\}", "").replaceAll("\\[|\\]", "");
        String[] split = replace.split(",");
        List<TreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String value = split[i];
            if (value.equals("#") || value.equals("null")) {
                treeNodes.add(null);
            } else {
                treeNodes.add(new TreeNode(Integer.parseInt(value.trim()), "treeNode" + i));
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
            if (childAllList.size() == 0) {
                break;
            }
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
