package com.xt.leetcode;

import java.util.*;

/**
 * 1705. 吃苹果的最大数目
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
 * <p>
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 * <p>
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * 输出：7
 * 解释：你可以吃掉 7 个苹果：
 * - 第一天，你吃掉第一天长出来的苹果。
 * - 第二天，你吃掉一个第二天长出来的苹果。
 * - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
 * - 第四天到第七天，你吃的都是第四天长出来的苹果。
 * 示例 2：
 * <p>
 * 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * 输出：5
 * 解释：你可以吃掉 5 个苹果：
 * - 第一天到第三天，你吃的都是第一天长出来的苹果。
 * - 第四天和第五天不吃苹果。
 * - 第六天和第七天，你吃的都是第六天长出来的苹果。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-eaten-apples
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 这题涉及到的概念还是蛮多的。
 * 首先我们构建一个集合，存放所有未吃的苹果，包含当天的。
 * 这样每天我们去判断一下这个集合中是否存在符合条件的苹果就可以了。并且对过期的进行移除操作。
 * 简单的按照这个思路实验下来没问题，但是时间复杂度太高了，所以我们要优化。
 * 优化一：这个集合的对象本来我是以每个苹果为单位的，但是这样实践下来，list数量太大了，所以应该改成以每天的苹果为单位，构建了AppleNode对象，表示一天的苹果这个对象。这样遍历的对象就少很多了。
 * 优化二：之前集合对象是无序排列的，所以每次都要遍历一遍，这样成本很高。所以改成有序，苹果过期时间越早的，在集合中排列越往后。这样，我们每次只要取越早过期的就好了。
 *
 * 遍历数组长度为n，二叉树遍历log(length)，所以时间复杂度是n*log(length)，
 * <p>
 * state：done
 */
public class Solution1705 {

    //按照过期时间拍一个序，每次都先取过期时间早的。取完则取下一个，不满足就移除
    public int eatenApples(int[] apples, int[] days) {
        int num = 0;
        List<AppleNode> list = new ArrayList<>();//list有序，按照结束时间从大到小排列，每次remove最后一个
        for (int index = 0; ; index++) {
            if (index < apples.length) {
                //避免重复判断
                AppleNode appleNode = new AppleNode();
                appleNode.index = index;
                appleNode.day = days[index] - 1;//减去当前
                appleNode.num = apples[index];
                appleNode.overdue = index + appleNode.day;
                middel2Insert(list, appleNode);
            }
            if (removeLast(index, list)) {
                num++;
            }
            if (index >= apples.length && list.size() == 0) {
                break;
            }
        }
        return num;
    }

    private boolean removeLast(int day, List<AppleNode> list) {
        //遍历的时候找最快过期的
        while (true) {
            int i = list.size() - 1;
            if (i < 0) {
                return false;
            }
            AppleNode last = list.get(i);
            int surplusDay = last.overdue - day;
            if (surplusDay < 0) {
                list.remove(i);
                continue;
            }
            if (last.num > 0) {
                last.num--;
                if (last.num == 0) {
                    list.remove(i);
                }
                return true;
            }
            return false;
        }
    }


    /**
     * 二分查找插入
     * overdue小的在后面
     *
     * @param node
     */
    public void middel2Insert(List<AppleNode> list, AppleNode node) {
        if (node == null) {
            return;
        }
        if (list.size() == 0) {
            list.add(node);
            return;
        }
        int start = 0;
        int end = list.size() - 1;
        while (start <= end) {
            AppleNode startNode = list.get(start);
            AppleNode endNode = list.get(end);
            if (node.overdue > startNode.overdue) {
                list.add(start, node);
                return;
            }
            start++;
            if (node.overdue <= endNode.overdue) {
                list.add(end + 1, node);
                return;
            }
            end--;
        }
        list.add(start, node);
    }

    static class AppleNode {
        int index = 0;//生产日期
        int num = 0;//数量
        int day = 0;//保质期天数
        int overdue = 0;//过期日期
    }

}