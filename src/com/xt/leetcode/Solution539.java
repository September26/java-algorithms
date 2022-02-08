package com.xt.leetcode;

import java.util.*;

/**
 * 539. 最小时间差
 * 描述
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= timePoints <= 2 * 10^4
 * timePoints[i] 格式为 "HH:MM"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-time-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 全部转为int类型，统计从00:00开始的第几分钟。然后统计两两时间差即可
 * <p>
 * <p>
 * state:done
 */
public class Solution539 {

    public int findMinDifference(List<String> timePoints) {
        ArrayList<Integer> timeList = new ArrayList<>(timePoints.size());
        for (int i = 0; i < timePoints.size(); i++) {
            String s = timePoints.get(i);
            String[] split = s.split(":");
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            timeList.add(hour * 60 + minute);
        }
        Collections.sort(timeList);

        int minValue = timeList.get(0) + 24 * 60 - timeList.get(timeList.size() - 1);

        for (int i = 0; i < timeList.size() - 1; i++) {
            int currentValue = timeList.get(i + 1) - timeList.get(i);
            minValue = Math.min(minValue, currentValue);
        }
        return minValue;
    }
}