package com.xt.leetcode;

import java.util.Arrays;
import java.util.Vector;

/**
 * 475.供暖器
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * <p>
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * <p>
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * <p>
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: houses = [1,2,3], heaters = [2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * 示例 2:
 * <p>
 * 输入: houses = [1,2,3,4], heaters = [1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 * 示例 3：
 * <p>
 * 输入：houses = [1,5], heaters = [2]
 * 输出：3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/heaters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 设置三个变量：
 * heaterIndex:记录当前对比的heater的位置
 * currentRadius:记录当前值的最小半径
 * maxRadius:记录所有值的最小半径
 * 先对两个数组进行排序，这样两个数组都是递归增加的了。
 * 然后遍历houses数组，我们分为三种场景：
 * 1.小于heaters[0]，那么距离最近的半径只能是house - heaters[0]。
 * 2.大于heaters[heaters.length - 1]或者小于heaters[heaters.length - 1]但是heaterIndex值已经读到了最后一个了。
 * 3.其余情况。这时候，我们要做一个循环，循环中两次对比，第一次是和headerIndex位置的对比，第二次是和headerIndex+1位置的对比。两者取小的那个。
 * 如果headerIndex+1位置的更大，那么就可以跳出循环了，因为继续headerIndex+1的话只会更大。
 * 举个例子
 * houses = {1,7}
 * heaters = {4,5,10}
 * houses读到7的时候，第一次循环，先和4比较结果=3，然后和5比较结果=2，则继续循环。直到headerIndex读到最后一个或者headerIndex+1的比较结果比headerIndex位置的更大
 * state:done
 */
public class Solution475 {

    public int findRadius(int[] houses, int[] heaters) {

        int heaterIndex = 0;
        int currentRadius = 0;
        int maxRadius = 0;

        Arrays.sort(houses);
        Arrays.sort(heaters);

        for (int house : houses) {
            if (house <= heaters[0]) {
                currentRadius = Math.abs(house - heaters[0]);
            } else if (house >= heaters[heaters.length - 1] || heaterIndex == heaters.length - 1) {
                currentRadius = Math.abs(house - heaters[heaters.length - 1]);
            } else {
                int abs = Math.abs(house - heaters[heaterIndex]);
                while (heaterIndex < heaters.length - 1) {
                    int newAbs = Math.abs(house - heaters[heaterIndex + 1]);
                    if (newAbs > abs) {
                        currentRadius = Math.abs(abs);
                        break;
                    } else {
                        abs = newAbs;
                        currentRadius = Math.abs(newAbs);
                        heaterIndex++;
                    }
                }
            }
            maxRadius = Math.max(currentRadius, maxRadius);
        }
        return maxRadius;
    }
}