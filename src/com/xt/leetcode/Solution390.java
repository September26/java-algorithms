package com.xt.leetcode;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * 390. 消除游戏
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
 * <p>
 * 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 * 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 给你整数 n ，返回 arr 最后剩下的数字。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 9
 * 输出：6
 * 解释：
 * arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * arr = [2, 4, 6, 8]
 * arr = [2, 6]
 * arr = [6]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/elimination-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入值：10000000
 * <p>
 * 解题思路：
 * 我们可以发现这样一个规律，每一轮筛选过后，数量都会变为原来的一半，步长加倍。
 * 比如1-16，筛选一遍后，就会变为2,4,6..16，步长为2，
 * 在筛选一遍，就会变为2,6,10,14，步长为4，
 * 在筛选一遍，就会变为6,14，步长为8，
 * 在筛选一遍，就会变为6，则返回结果就是6。
 * 所以我们可以设置这样几个变量去记录，
 * 一个开始的数字start，
 * 一个步长step，
 * 一个遍历的次数k，
 * 一个统计当前总数量的count。
 * 这样每次筛选的时候，我们只要记录一个步长和一个start就好了。
 * 当count==1的时候，start就是我们想要的结果
 *
 * <p>
 * state:done
 */
public class Solution390 {

    public int lastRemaining(int n) {
        int start = 1;//记录从左侧开始的位置。
        int step = 1;//步长，1变为2，变为4，变为8等等
        int count = n;//当前的数量
        int times = 0;//统计次数
        while (count > 1) {
            if (times % 2 == 0) {
                //从左侧开始
                start += step;
            } else {
                //从右侧开始
                if (count % 2 != 0) {
                    start += step;
                }
            }
            count = count >> 1;//长度减少为原来的一半
            step = step << 1;//步长增加一倍
            times++;
        }
        return start;
    }


//    public int lastRemaining(int n) {
//        ArrayList<Integer> list = new ArrayList<>();
//        ArrayList<Integer> newList = new ArrayList<>();
//        for (int i = 1; i <= n; i++) {
//            list.add(i);
//        }
//        boolean fromLeft = true;
//        while (list.size() > 1) {
//            if (fromLeft) {
//                for (int i = 1; i < list.size(); i = i + 2) {
//                    newList.add(list.get(i));
//                }
//            } else {
//                for (int i = list.size() % 2 == 0 ? 0 : 1; i < list.size(); i = i + 2) {
//                    newList.add(list.get(i));
//                }
//            }
//            list.clear();
//            list.addAll(newList);
//            newList.clear();
//            fromLeft = !fromLeft;
//        }
//        return list.get(0);
//    }

}