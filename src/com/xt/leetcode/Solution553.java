package com.xt.leetcode;


/**
 * 553.最优除法
 * 日期：2022.2.27
 * 链接：https://leetcode-cn.com/problems/optimal-division/
 * 描述：
 * 给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。
 * <p>
 * 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应该含有冗余的括号。
 * <p>
 * 示例：
 * <p>
 * 输入: [1000,100,10,2]
 * 输出: "1000/(100/10/2)"
 * 解释:
 * 1000/(100/10/2) = 1000/((100/10)/2) = 200
 * 但是，以下加粗的括号 "1000/((100/10)/2)" 是冗余的，
 * 因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。
 * <p>
 * 其他用例:
 * 1000/(100/10)/2 = 50
 * 1000/(100/(10/2)) = 50
 * 1000/100/10/2 = 0.5
 * 1000/100/(10/2) = 2
 * 说明:
 * <p>
 * 输入数组的长度在 [1, 10] 之间。
 * 数组中每个元素的大小都在 [2, 1000] 之间。
 * 每个测试用例只有一个最优除法解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/optimal-division
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 这题其实很简单，我看是中等级，就想复杂了。
 * 求最优除法，那么只要保证除了第一位之后的数字组合起来最小就好，所以用n1除以后面所有的数即可。
 * 就是n0/(n1/n2/n3/...)这种形式。
 * state:done
 */
public class Solution553 {
    public String optimalDivision(int[] nums) {
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        if (nums.length == 2) {
            return nums[0] + "/" + nums[1];
        }
        StringBuilder builder = new StringBuilder();
        builder.append(nums[0]);
        builder.append("/(");
        for (int i = 1; i < nums.length; i++) {
            builder.append(nums[i]);
            if (i != nums.length - 1) {
                builder.append("/");
            }
        }
        builder.append(")");
        return builder.toString();
    }

}