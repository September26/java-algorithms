package com.xt.leetcode;

import java.util.*;

/**
 * 2034. 股票价格波动
 * 描述
 * 给你一支股票价格的数据流。数据流中每一条记录包含一个 时间戳 和该时间点股票对应的 价格 。
 * <p>
 * 不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 更正 前一条错误的记录。
 * <p>
 * 请你设计一个算法，实现：
 * <p>
 * 更新 股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将 更正 之前的错误价格。
 * 找到当前记录里 最新股票价格 。最新股票价格 定义为时间戳最晚的股票价格。
 * 找到当前记录里股票的 最高价格 。
 * 找到当前记录里股票的 最低价格 。
 * 请你实现 StockPrice 类：
 * <p>
 * StockPrice() 初始化对象，当前无股票价格记录。
 * void update(int timestamp, int price) 在时间点 timestamp 更新股票价格为 price 。
 * int current() 返回股票 最新价格 。
 * int maximum() 返回股票 最高价格 。
 * int minimum() 返回股票 最低价格 。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
 * [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
 * 输出：
 * [null, null, null, 5, 10, null, 5, null, 2]
 * <p>
 * 解释：
 * StockPrice stockPrice = new StockPrice();
 * stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
 * stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
 * stockPrice.current();     // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
 * stockPrice.maximum();     // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
 * stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
 * // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
 * stockPrice.maximum();     // 返回 5 ，更正后最高价格为 5 。
 * stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
 * stockPrice.minimum();     // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= timestamp, price <= 10^9
 * update，current，maximum 和 minimum 总 调用次数不超过 10^5 。
 * current，maximum 和 minimum 被调用时，update 操作 至少 已经被调用过 一次 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stock-price-fluctuation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 1.构建一个Node节点来存储对应的信息，一个Node代表唯一的价格，一个价格可能有N多次，由num来记录。
 * 2.然后一个timesMap来记录这个时间点的价格是否出现过。
 * 3.如果没有出现过，则走流程5。
 * 4.如果出现过，则先减掉老price对应的节点的数量，然后走流程5
 * 5.根据当前价格查询查询priceMap是否存在，如果不存在则添加节点，并且num=1。如果存在则num++。
 *
 * <p>
 * <p>
 * state:done
 */
public class Solution2034 {


    public static class StockPrice {

        Map<Integer, Integer> timesMap = new HashMap<>();//价格是否存在
        Map<Integer, Node> priceMap = new HashMap<>();//key:价格，value:节点Node
        List<Node> priceList = new ArrayList<>();//按照金额排序。价格不会有重复
        int current = 0;
        int currentPirce = 0;


        public StockPrice() {

        }

        public void update(int timestamp, int price) {
            Integer oldPrice = timesMap.get(timestamp);
            timesMap.put(timestamp, price);
            if (timestamp >= current) {
                current = timestamp;
                currentPirce = price;
            }
            //添加
            if (oldPrice == null) {
                addNode(timestamp, price);
                return;
            }
            Node oldNode = priceMap.get(oldPrice);
            if (oldNode.num > 1) {
                oldNode.num--;
            } else {
                //删除老节点
                middelRemove(oldNode);
            }
            //添加新节点
            addNode(timestamp, price);
        }

        private void addNode(int timestamp, int price) {
            Node node = priceMap.get(price);
            if (node == null) {
                node = new Node();
                node.price = price;
                node.num++;
                node.indexList.add(timestamp);
                priceMap.put(price, node);
                middel2Insert(node);
            } else {
                node.num++;
                node.indexList.add(timestamp);
            }
        }

        public int current() {
            return currentPirce;
        }

        public int maximum() {
            return priceList.get(priceList.size() - 1).price;
        }

        public int minimum() {
            return priceList.get(0).price;
        }

        public void print() {
            System.out.println("min:" + priceList.get(0).price + ",max:" + priceList.get(priceList.size() - 1).price + ",currentPirce:" + currentPirce);
        }

        /**
         * 二分法插入一个节点
         * 从小到大排列
         *
         * @param node
         */
        public void middel2Insert(Node node) {
            if (priceList.size() == 0) {
                priceList.add(node);
                return;
            }
            int start = 0;
            int insert = 0;
            int end = priceList.size() - 1;
            while (start <= end) {
                int middle = (start + end) / 2;
                Node middleNode = priceList.get(middle);
                if (node.price > middleNode.price) {
                    start = middle + 1;
                    insert = start;
                    continue;
                }
                if (node.price < middleNode.price) {
                    end = middle - 1;
                    continue;
                }
            }
            priceList.add(insert, node);
        }

        /**
         * 移除节点
         *
         * @param node
         */
        public void middelRemove(Node node) {
            priceMap.remove(node.price);
            int i = Collections.binarySearch(priceList, node);
            priceList.remove(i);
        }

        /**
         * 记录价格，以及对应价格的数量
         * indexList无用，只做记录用
         */
        static class Node implements Comparable<Node> {
            int price = 0;//价格
            int num = 0;//对应的数量
            List<Integer> indexList = new ArrayList<>();

            @Override
            public int compareTo(Node o) {
                if (o.price == price) {
                    return 0;
                }
                return o.price < price ? 1 : -1;
            }
        }
    }


    /**
     * Your StockPrice object will be instantiated and called as such:
     * StockPrice obj = new StockPrice();
     * obj.update(timestamp,price);
     * int param_2 = obj.current();
     * int param_3 = obj.maximum();
     * int param_4 = obj.minimum();
     */
}