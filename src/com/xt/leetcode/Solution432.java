package com.xt.leetcode;

import java.util.*;

/**
 * 1688.比赛中的配对次数
 * 每日一题：2022.03.16
 * 完成日期：2022.03.16
 * 链接：https://leetcode-cn.com/problems/all-oone-data-structure/
 * 描述：
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 * <p>
 * 实现 AllOne 类：
 * <p>
 * AllOne() 初始化数据结构的对象。
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * 输出
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 * <p>
 * 解释
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "leet"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= key.length <= 10
 * key 由小写英文字母组成
 * 测试用例保证：在每次调用 dec 时，数据结构中总存在 key
 * 最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 104 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-oone-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 用两个集合进行数据的存储。
 * 第一个集合判断是否含有该元素，第二个集合是按照数量从小到大排列。
 * 添加一个字符时，首先判断是否存在该有字符，如果没有则创建Node添加到map当中，并且加入到list的最前面。此时次数为1。
 * 如果不为空，则使用需要修改Node的times次数，然后重新排序。
 * 删除时逻辑和添加类似。
 * 重新排序时，先使用search方法找到该Node节点在list中的位置，然后向后查找，找到其本应该所在的位置，然后交换。
 * 我这里的search使用的还是遍历，所以效率比较低。这里使用二叉树查找应该效率会更好一些。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution432 {

    public static class AllOne {

        List<Node> list = new ArrayList<>();
        Map<String, Node> indexMap = new HashMap<>();

        public AllOne() {

        }

        public void inc(String key) {
            Node node = indexMap.get(key);
            if (node == null) {
                node = new Node();
                node.key = key;
                node.times = 1;
                list.add(0, node);
                indexMap.put(node.key, node);
                return;
            }
            //二叉树搜索找到node的位置，调整node的位置
            node.times++;
            sort(true, node);
        }

        public void dec(String key) {
            Node node = indexMap.get(key);
            if (node == null) {
                return;
            }
            node.times--;
            if (node.times == 0) {
                indexMap.remove(node.key);
            }
            //重新排序
            sort(false, node);
        }

        public String getMaxKey() {
            if (list.size() == 0) {
                return "";
            }
            return list.get(list.size() - 1).key;
        }

        public String getMinKey() {
            if (list.size() == 0) {
                return "";
            }
            return list.get(0).key;
        }


        private void sort(boolean isForward, Node node) {
            int index = search(node);
            if (node.times == 0) {
                list.remove(index);
                return;
            }
            if (isForward) {
                int i = index + 1;
                for (; i < list.size(); i++) {
                    Node node1 = list.get(i);
                    if (node.times > node1.times) {
                        continue;
                    }
                    break;
                }
                change(node, list.get(i - 1));
                return;
            }
            int i = index - 1;
            for (; i >= 0; i--) {
                Node node1 = list.get(i);
                if (node.times < node1.times) {
                    continue;
                }
                break;
            }
            change(node, list.get(i + 1));
        }

        private int search(Node node) {
            int i = list.indexOf(node);
            return i;
        }

        private void change(Node node, Node node1) {
            if (node.key.equals(node1.key)) {
                return;
            }
            int times = node.times;
            String key = node.key;

            node.times = node1.times;
            node.key = node1.key;

            node1.times = times;
            node1.key = key;
            indexMap.put(node.key, node);
            indexMap.put(node1.key, node1);
        }

        static class Node {
            String key;
            int times;
        }
    }


/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
}