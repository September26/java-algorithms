package com.xt.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 798. 得分最高的最小轮调
 * 每日一题：2022.03.09
 * 完成日期：2022.03.09
 * 链接：https://leetcode-cn.com/problems/smallest-rotation-with-highest-score/
 * 描述：
 * 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为 [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。
 * <p>
 * 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,4,0]
 * 输出：3
 * 解释：
 * 下面列出了每个 k 的得分：
 * k = 0,  nums = [2,3,1,4,0],    score 2
 * k = 1,  nums = [3,1,4,0,2],    score 3
 * k = 2,  nums = [1,4,0,2,3],    score 3
 * k = 3,  nums = [4,0,2,3,1],    score 4
 * k = 4,  nums = [0,2,3,1,4],    score 3
 * 所以我们应当选择 k = 3，得分最高。
 * 示例 2：
 * <p>
 * 输入：nums = [1,3,0,2,4]
 * 输出：0
 * 解释：
 * nums 无论怎么变化总是有 3 分。
 * 所以我们将选择最小的 k，即 0。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] < nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-rotation-with-highest-score
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题长度为10^5，则时间复杂度一定小于为O(n*lgN)，所以首先去尝试O(n)级别的解法。
 * 我们发现变换其实是有规律的，我把对应的值氛围3种类型：
 * 1.value值为0任意移动都满足的。
 * 2.不移动已满足条件的。
 * 3.移动后才会满足的。
 * 对于第一种，我们不用管。
 * 第二种，则移动后会导致不满足条件，继续移动会导致又满足条件。比如value=2在index=3的位置，移动2次后不满足条件。移动4次后导致排列到对位，又满足条件了。
 * 第三种，移动若干后满足条件，继续移动导致又不满足条件了。
 * 我们记录其满足和不满足的位置。
 * 我们用data来记录，data[1]的数组，代表移动1位时，导致的变化。data[1][0]表示导致满足条件的数量，data[1][1]表示导致不满足的数量。
 * 则我们计算出data后，便利一边data，根据初始值加减，则可以求出最大值。
 * <p>
 * <p>
 * state:done
 */
public class Solution798 {

    public int bestRotation(int[] nums) {
        int[][] data = new int[nums.length][2];
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            //加入
            int addIndex;
            //移除
            int removeIndex;

            if (value <= i) {
                currentSum++;
                addIndex = i + 1;
                removeIndex = i - value + 1;
            } else {
                addIndex = i + 1;
                removeIndex = nums.length - value + i + 1;
            }
            if (addIndex >= nums.length) {
                addIndex = addIndex - nums.length;
            }
            if (removeIndex >= nums.length) {
                removeIndex = removeIndex - nums.length;
            }
            if (value == 0) {
                continue;
            }
            data[addIndex][0]++;
            data[removeIndex][1]++;
        }
        int maxSum = currentSum;
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            currentSum += data[i][0];
            currentSum -= data[i][1];
            if (currentSum > maxSum) {
                result = i;
                maxSum = currentSum;
            }
        }
        return result;
    }


}