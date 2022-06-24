package com.xt.leetcode;

import java.util.*;

/**
 * 954. 二倍数对数组
 * 每日一题：2022.04.01
 * 完成日期：2022.04.01
 * 链接：https://leetcode-cn.com/problems/array-of-doubled-pairs/
 * 描述：
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,1,3,6]
 * 输出：false
 * 示例 2：
 * <p>
 * 输入：arr = [2,1,2,6]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：arr = [4,-2,2,-4]
 * 输出：true
 * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= arr.length <= 3 * 10^4
 * arr.length 是偶数
 * -10^5 <= arr[i] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/array-of-doubled-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题其实很简单，但是题目描述的不清楚导致理解起来有些困难。
 * 首先i是在数组中的index值。
 * 我们一一比较arr[2 * i + 1] 和2 * arr[2 * i]，发现其实就是1和0比较，3和2比较，5和4比较，依次下去。
 * 只要依次判断arr[1]=arr[0]*2,arr[3]=arr[2]*2,arr[5]=arr[4]*2。
 * 因此发现了规律，我们只要判断数组中的数和其双倍的数是否是成对出现即可。比如0和0,1和2,-1和-2。
 * 分成3部分的数组，分别小于0，大于0，等于0。
 * 大于0的数组我们一定要从小向大遍历，小于0的则从大向小遍历。分别统计是否成对即可。
 * 统计是否成对，我们可以读到某个数则从map中对应数量-1，并且找其双倍大小的数，同时-1。如果不存在，则返回false。
 * <p>
 * <p>
 * state:done
 */
public class Solution954 {

    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> numMap = new HashMap<>();
        List<Integer> biggerZero = new ArrayList<>();
        List<Integer> smallZero = new ArrayList<>();
        int zeroNum = 0;
        for (int i : arr) {
            Integer num = numMap.getOrDefault(i, 0);
            numMap.put(i, num + 1);
            if (i == 0) {
                zeroNum++;
                continue;
            }
            if (i > 0) {
                biggerZero.add(i);
                continue;
            }
            smallZero.add(i);
        }
        if (zeroNum % 2 != 0) {
            return false;
        }
        Collections.sort(biggerZero);
        smallZero.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        return traversesList(biggerZero, numMap) && traversesList(smallZero, numMap);
    }

    public boolean traversesList(List<Integer> list, Map<Integer, Integer> numMap) {
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            Integer num = numMap.get(value);
            if (num == null || num == 0) {
                continue;
            }
            numMap.put(value, num - 1);
            num = numMap.get(value * 2);
            if (num == null || num == 0) {
                return false;
            }
            numMap.put(value * 2, num - 1);
        }
        return true;
    }

}