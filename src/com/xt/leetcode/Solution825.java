package com.xt.leetcode;

import com.xt.util.AlgorithmHelper;

import java.util.Arrays;

/**
 * 825. 适龄的朋友
 * 12.27
 * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
 * <p>
 * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
 * <p>
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * 否则，x 将会向 y 发送一条好友请求。
 * <p>
 * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
 * <p>
 * 返回在该社交媒体网站上产生的好友请求总数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：ages = [16,16]
 * 输出：2
 * 解释：2 人互发好友请求。
 * <p>
 * 示例 2：
 * <p>
 * 输入：ages = [16,17,18]
 * 输出：2
 * 解释：产生的好友请求为 17 -> 16 ，18 -> 17 。
 * 示例 3：
 * <p>
 * 输入：ages = [20,30,100,110,120]
 * 输出：3
 * 解释：产生的好友请求为 110 -> 100 ，120 -> 110 ，120 -> 100 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/friends-of-appropriate-ages
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 我们转化为满足三个条件的所有组合，则需要同时满足下面三个条件：
 * 1。ages[x]>=ages[y]
 * 2。ages[y]>0.5*ages[x]+7
 * 3。ages[x]>=100||y<=100
 * 首先第一个，条件，我们对数组从大到小排序，先取X后取Y。然后加上另外两个判断条件，则就可以依次统计了。
 * 基本的解法出来后，我们就得考虑如何优化了，因为现在的时间复杂度是O(n2)
 * 优化点就是第二个for循环我们其实都是在找一个临界值，只要找到这个临界值，比如67，那么大于67的都是符合的，就不用一个一个的去判断了。
 * state:done
 */
public class Solution825 {

    /**
     * 解法2，优化后二分查找的方案
     *
     * @param ages
     * @return
     */
    public int numFriendRequests2(int[] ages) {
        ages = Arrays.stream(ages).boxed().sorted((a, b) -> b - a).mapToInt(p -> p).toArray();
        int xIndex = 0;
        int x;
        int num = 0;
        int yCompare = 0;
        int xStart = 0;
        Boolean isMoreThan100 = null;
        for (int i = 0; i < ages.length; i++) {
            xIndex = i;
            x = ages[xIndex];
            yCompare = x / 2 + 7;
            if (isMoreThan100 == null || isMoreThan100) {
                isMoreThan100 = x >= 100;
            }
            if (ages[xStart] != x) {
                xStart = i;
            }

            //这里拆分成两个条件进行判断，二分查找找临界值
            if (isMoreThan100) {
                //找大于yCompare的数量
                int targetIndex = binarySearchForIndexByReverse(ages, yCompare, xStart, ages.length - 1, true);
                while (ages[targetIndex] == yCompare) {
                    targetIndex--;
                }
                num += (targetIndex - xStart);
                continue;
            }
            if (yCompare > 100) {
                continue;
            }
            //这里的判断y<=100，基本上不需要判断，因为yCompare一定是在100的右边
            int targetIndex1 = binarySearchForIndexByReverse(ages, yCompare, xStart, ages.length - 1, true);
            while (ages[targetIndex1] == yCompare) {
                targetIndex1--;
            }
            int i1 = targetIndex1 - xStart;
            if (i1 > 0) {
                num += i1;
            }
        }
        return num;
    }

    public static int binarySearchForIndexByReverse(int[] array, int target, int start, int end, boolean vague) {
        if (target > array[0]) {
            if (vague) {
                return 0;
            }
            return -1;
        }
        if (target < array[array.length - 1]) {
            if (vague) {
                return array.length - 1;
            }
            return -1;
        }

        int middle = 0;
        while (true) {
            middle = (start + end) / 2;
            if (target == array[middle]) {
                break;
            }
            if (start == end) {
                //找不到时的处理逻辑
                if (vague) {
                    return start;
                }
                return -1;
            }
            if ((end - start) == 1) {
//                直接判断
                if (array[start] == target) {
                    return start;
                } else if (array[end] == target) {
                    return end;
                } else {
                    if (vague) {
                        return start;
                    }
                    return -1;
                }
            }
            if (array[middle] < target) {
                end = middle;
            } else {
                start = middle;
            }
        }
        return middle;
    }

    /**
     * 解法1，最初方案，双重for循环
     */
    public int numFriendRequests(int[] ages) {
        ages = Arrays.stream(ages).boxed().sorted((a, b) -> b - a).mapToInt(p -> p).toArray();
        int xIndex = 0;
        int yIndex = 0;
        int x;
        int y;
        int num = 0;
        int yCompare = 0;
        int xStart = 0;
        Boolean isMoreThan100 = null;
        for (int i = 0; i < ages.length; i++) {
            xIndex = i;
            x = ages[xIndex];
            yCompare = x / 2 + 7;
            if (isMoreThan100 == null || isMoreThan100) {
                isMoreThan100 = x >= 100;
            }
            if (ages[xStart] != x) {
                xStart = i;
            }
            for (yIndex = xStart; yIndex < ages.length; yIndex++) {
                if (xIndex == yIndex) {
                    continue;
                }
                y = ages[yIndex];
                //这里可以根据具体的条件，进行二分法查找临界点
                if (y > yCompare && (isMoreThan100 || y <= 100)) {
                    num++;
                }
            }
        }
        return num;
    }
}