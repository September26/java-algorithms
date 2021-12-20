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
 * state:done
 */
public class Solution475 {
    //161834419
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