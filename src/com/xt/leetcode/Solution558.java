package com.xt.leetcode;

import com.xt.model.Node;
import com.xt.model.QuadtreeNodel;

import java.util.Vector;

/**
 * 558. 四叉树交集
 * 每日一题：2022.07.15
 * 完成日期：2022.07.15
 * 链接：https://leetcode.cn/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/
 * 描述：
 * 二进制矩阵中的所有元素不是 0 就是 1 。
 * <p>
 * 给你两个四叉树，quadTree1 和 quadTree2。其中 quadTree1 表示一个 n * n 二进制矩阵，而 quadTree2 表示另一个 n * n 二进制矩阵。
 * <p>
 * 请你返回一个表示 n * n 二进制矩阵的四叉树，它是 quadTree1 和 quadTree2 所表示的两个二进制矩阵进行 按位逻辑或运算 的结果。
 * <p>
 * 注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
 * <p>
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 * <p>
 * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
 * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
 * class Node {
 * public boolean val;
 *     public boolean isLeaf;
 *     public Node topLeft;
 *     public Node topRight;
 *     public Node bottomLeft;
 *     public Node bottomRight;
 * }
 * 我们可以按以下步骤为二维区域构建四叉树：
 * <p>
 * 如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
 * 如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
 * 使用适当的子网格递归每个子节点。
 * <p>
 * <p>
 * 如果你想了解更多关于四叉树的内容，可以参考 wiki 。
 * <p>
 * 四叉树格式：
 * <p>
 * 输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
 * <p>
 * 它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
 * <p>
 * 如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：quadTree1 = [[0,1],[1,1],[1,1],[1,0],[1,0]]
 * , quadTree2 = [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * 输出：[[0,0],[1,1],[1,1],[1,1],[1,0]]
 * 解释：quadTree1 和 quadTree2 如上所示。由四叉树所表示的二进制矩阵也已经给出。
 * 如果我们对这两个矩阵进行按位逻辑或运算，则可以得到下面的二进制矩阵，由一个作为结果的四叉树表示。
 * 注意，我们展示的二进制矩阵仅仅是为了更好地说明题意，你无需构造二进制矩阵来获得结果四叉树。
 * <p>
 * 示例 2：
 * <p>
 * 输入：quadTree1 = [[1,0]]
 * , quadTree2 = [[1,0]]
 * 输出：[[1,0]]
 * 解释：两个数所表示的矩阵大小都为 1*1，值全为 0
 * 结果矩阵大小为 1*1，值全为 0 。
 * 示例 3：
 * <p>
 * 输入：quadTree1 = [[0,0],[1,0],[1,0],[1,1],[1,1]]
 * , quadTree2 = [[0,0],[1,1],[1,1],[1,0],[1,1]]
 * 输出：[[1,1]]
 * 示例 4：
 * <p>
 * 输入：quadTree1 = [[0,0],[1,1],[1,0],[1,1],[1,1]]
 * , quadTree2 = [[0,0],[1,1],[0,1],[1,1],[1,1],null,null,null,null,[1,1],[1,0],[1,0],[1,1]]
 * 输出：[[0,0],[1,1],[0,1],[1,1],[1,1],null,null,null,null,[1,1],[1,0],[1,0],[1,1]]
 * 示例 5：
 * <p>
 * 输入：quadTree1 = [[0,1],[1,0],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * , quadTree2 = [[0,1],[0,1],[1,0],[1,1],[1,0],[1,0],[1,0],[1,1],[1,1]]
 * 输出：[[0,0],[0,1],[0,1],[1,1],[1,0],[1,0],[1,0],[1,1],[1,1],[1,0],[1,0],[1,1],[1,1]]
 *  
 * <p>
 * 提示：
 * <p>
 * quadTree1 和 quadTree2 都是符合题目要求的四叉树，每个都代表一个 n * n 的矩阵。
 * n == 2^x ，其中 0 <= x <= 9.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/logical-or-of-two-binary-grids-represented-as-quad-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 一道递归的题目，如果两个节点任意一个isLeaf为true，如果val=1使用其自身，否则使用另外一个。因为1|0或者1|1都为1。而0的话为输入值。
 * 如果两个节点的isLeaf都为false，则递归判断，得出结果后修改返回节点的isLeaf和val。
 * 这里要注意的是，判断四个值是否一致应该是a==b&b==c&c==d，而不是a==b==c==d。我就是这问题查了三个多小时
 * <p>
 * <p>
 * state:done
 */
public class Solution558 {

    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf) {
            if (quadTree1.val) {
                return quadTree1;
            }
            return quadTree2;
        }
        if (quadTree2.isLeaf) {
            if (quadTree2.val) {
                return quadTree2;
            }
            return quadTree1;
        }
        Node node = new Node();
        node.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        node.topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        node.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        node.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        //是否全一致
        node.isLeaf = (node.topLeft.isLeaf & node.topRight.isLeaf & node.bottomRight.isLeaf & node.bottomLeft.isLeaf) && (node.topLeft.val == node.topRight.val) &&  (node.topRight.val == node.bottomLeft.val)&&  (node.bottomLeft.val == node.bottomRight.val);
        node.val = node.topLeft.val | node.topRight.val | node.bottomRight.val | node.bottomLeft.val;
        if (node.isLeaf) {
            node.topLeft = null;
            node.topRight = null;
            node.bottomRight = null;
            node.bottomLeft = null;
        }
        return node;
    }

    public static class Node {
        public String name;
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(String name) {
            this.name = name;
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}