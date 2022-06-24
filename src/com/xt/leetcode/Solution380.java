package com.xt.leetcode;

import java.util.*;

/**
 * 380. O(1) 时间插入、删除和获取随机元素
 * 每日一题：2022.04.12
 * 完成日期：2022.04.12
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 * 描述：
 * 实现RandomizedSet 类：
 * <p>
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出
 * [null, true, false, true, 2, true, false, 2]
 * <p>
 * 解释
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
 * randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
 * randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
 * randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 *  
 * <p>
 * 提示：
 * <p>
 * -231 <= val <= 231 - 1
 * 最多调用 insert、remove 和 getRandom 函数 2 * 105 次
 * 在调用 getRandom 方法时，数据结构中 至少存在一个 元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 我的思路是用list装载所有的val值，用map来记录其在list中的位置。
 * 插入和随即返回时不需要多说，重点说一下删除时。
 * 删除的时候，我们通过map找到val在list中的对应位置，删除这个值之后，需要把list队尾的值挪到当前的对应位置，然后把队尾删掉。
 * 这样，就能保证list时连续的了。
 * <p>
 * <p>
 * state:done
 */
public class Solution380 {

    public static class RandomizedSet {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        public RandomizedSet() {

        }

        public boolean insert(int val) {
            Integer integer = map.get(val);
            if (integer != null) {
                return false;
            }
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            Integer index = map.get(val);
            if (index == null) {
                return false;
            }
            map.remove(val);
            if (index == list.size() - 1) {
                list.remove(list.size() - 1);
                return true;
            }
            //如果元素存在，则需要移除。移除时，把队尾的挪到当前位置
            Integer endValue = list.get(list.size() - 1);

            list.set(index, endValue);
            list.remove(list.size() - 1);
            map.put(endValue, index);

            return true;
        }

        public int getRandom() {
            return list.get((int) (Math.random() * list.size()));
        }
    }
}