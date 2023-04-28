package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1656. 设计有序流
 * 每日一题：2022.08.16
 * 完成日期：2022.08.16
 * 链接：https://leetcode.cn/problems/design-an-ordered-stream/
 * 描述：
 * 有 n 个 (id, value) 对，其中 id 是 1 到 n 之间的一个整数，value 是一个字符串。不存在 id 相同的两个 (id, value) 对。
 * <p>
 * 设计一个流，以 任意 顺序获取 n 个 (id, value) 对，并在多次调用时 按 id 递增的顺序 返回一些值。
 * <p>
 * 实现 OrderedStream 类：
 * <p>
 * OrderedStream(int n) 构造一个能接收 n 个值的流，并将当前指针 ptr 设为 1 。
 * String[] insert(int id, String value) 向流中存储新的 (id, value) 对。存储后：
 * 如果流存储有 id = ptr 的 (id, value) 对，则找出从 id = ptr 开始的 最长 id 连续递增序列 ，并 按顺序 返回与这些 id 关联的值的列表。然后，将 ptr 更新为最后那个  id + 1 。
 * 否则，返回一个空列表。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入
 * ["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
 * [[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
 * 输出
 * [null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]
 * <p>
 * 解释
 * OrderedStream os= new OrderedStream(5);
 * os.insert(3, "ccccc"); // 插入 (3, "ccccc")，返回 []
 * os.insert(1, "aaaaa"); // 插入 (1, "aaaaa")，返回 ["aaaaa"]
 * os.insert(2, "bbbbb"); // 插入 (2, "bbbbb")，返回 ["bbbbb", "ccccc"]
 * os.insert(5, "eeeee"); // 插入 (5, "eeeee")，返回 []
 * os.insert(4, "ddddd"); // 插入 (4, "ddddd")，返回 ["ddddd", "eeeee"]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 1000
 * 1 <= id <= n
 * value.length == 5
 * value 仅由小写字母组成
 * 每次调用 insert 都会使用一个唯一的 id
 * 恰好调用 n 次 insert
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-an-ordered-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 设置一个数组用来存放(id,value)，以及一个ptr来记录位置。
 * 每次插入的时候，先设置对应位置的数据，然后从ptr开始遍历，如果为空就跳出循环，ptr=i；
 * 否则加入到集合中返回。
 * 由于本题中是从1开始的，所以数组长度设置为n+1,0的位置不使用。
 * <p>
 * <p>
 * state:done
 */
public class Solution1656 {

    public static class OrderedStream {
        int ptr = 1;
        String[] strings;

        public OrderedStream(int n) {
            strings = new String[n + 1];
        }

        public List<String> insert(int idKey, String value) {
            strings[idKey] = value;

            ArrayList<String> list = new ArrayList<>();
            if (this.strings[ptr] == null) {
                return list;
            }
            for (int i = ptr; i < strings.length; i++) {
                if (strings[i] == null) {
                    ptr = i;
                    break;
                }
                list.add(strings[i]);
            }
            return list;
        }
    }
}