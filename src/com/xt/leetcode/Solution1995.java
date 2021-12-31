package com.xt.leetcode;

import java.util.*;

/**
 * 1995.统计特殊四元组
 * <p>
 * 给你一个 下标从 0 开始 的整数数组 nums ，返回满足下述条件的 不同 四元组 (a, b, c, d) 的 数目 ：
 * <p>
 * nums[a] + nums[b] + nums[c] == nums[d] ，且
 * a < b < c < d
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,6]
 * 输出：1
 * 解释：满足要求的唯一一个四元组是 (0, 1, 2, 3) 因为 1 + 2 + 3 == 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,3,6,4,5]
 * 输出：0
 * 解释：[3,3,6,4,5] 中不存在满足要求的四元组。
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,1,3,5]
 * 输出：4
 * 解释：满足要求的 4 个四元组如下：
 * - (0, 1, 2, 3): 1 + 1 + 1 == 3
 * - (0, 1, 3, 4): 1 + 1 + 3 == 5
 * - (0, 2, 3, 4): 1 + 1 + 3 == 5
 * - (1, 2, 3, 4): 1 + 1 + 3 == 5
 *  
 * <p>
 * 提示：
 * <p>
 * 4 <= nums.length <= 50
 * 1 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-special-quadruplets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 解题思路：
 * 思路1：
 * 这题我没有很好的思路，只能是四层for循环，然后把最后一层for循环改为map查找的形式。
 * 前三层for循环求初num[a]+num[b]+num[c]的和，然后从map中求出是否存在对应的数字的位置，如果存在，则判断位置是否大于c。
 * 时间复杂度是O(n3*字符串长度)
 * <p>
 * 思路2：
 * 运行map缓存的思想，求出num[d]-num[c]的值作为key，value为对应其出现的次数。
 * 然后依次遍历num[a]+num[b]的sum值，然后判断map中对应的sum的值的次数进行累加。
 * 这里map缓存的时候有一个优化点，b可以从后向前遍历，比如
 * 1，1，1，3，5
 * b=2时，c=3，d=4，这是sum=sum[4]-sum[3]=2，加入map。这时，a分别等于0和1，此时abSum都为2,则sum+=2；
 * b=1时，增加了两种场景c=3,d=5和c=3,d=4，这时候sum分别等于4和2，则map中的缓存为{2=2,4=1}，a只能等于1，这时候num[a]+num[b]=2,map中2对应的value为2，则sum=sum+2=4
 *
 * <p>
 * state:done
 */
public class Solution1995 {

    public int countQuadruplets2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int num = 0;
        int abSum = 0;
        for (int b = nums.length - 3; b > 0; b--) {
            int c = b + 1;
            for (int d = c + 1; d < nums.length; d++) {
                int i = nums[d] - nums[c];
                Integer sum = map.getOrDefault(i, 0);
                map.put(i, sum + 1);
            }
            abSum += nums[b];
            for (int a = 0; a < b; a++) {
                abSum += nums[a];
                Integer integer = map.get(abSum);
                if (integer != null) {
                    num += integer;
                }
                abSum -= nums[a];
            }
            abSum -= nums[b];
        }
        return num;
    }

    public int countQuadruplets(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> integers = map.computeIfAbsent(nums[i], f -> new ArrayList<>());
            integers.add(i);
        }
        int num = 0;
        int sum = 0;
        for (int a = 0; a < nums.length; a++) {
            sum += nums[a];
            for (int b = a + 1; b < nums.length; b++) {
                sum += nums[b];
                for (int c = b + 1; c < nums.length; c++) {
                    sum += nums[c];
                    List<Integer> dList = map.getOrDefault(sum, new ArrayList<>());
                    for (int i = 0; i < dList.size(); i++) {
                        Integer index = dList.get(i);
                        if (index <= c) {
                            continue;
                        }
                        num += (dList.size() - i);
                        break;
                    }
                    sum -= nums[c];
                }
                sum -= nums[b];
            }
            sum -= nums[a];
        }
        return num;
    }

}