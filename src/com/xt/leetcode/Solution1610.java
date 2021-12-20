package com.xt.leetcode;

import java.util.*;

/**
 * 1610. 可见点的最大数目
 * 给你一个点数组 points 和一个表示角度的整数 angle ，你的位置是 location ，其中 location = [posx, posy] 且 points[i] = [xi, yi] 都表示 X-Y 平面上的整数坐标。
 * <p>
 * 最开始，你面向东方进行观测。你 不能 进行移动改变位置，但可以通过 自转 调整观测角度。换句话说，posx 和 posy 不能改变。你的视野范围的角度用 angle 表示， 这决定了你观测任意方向时可以多宽。设 d 为你逆时针自转旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
 * <p>
 * 对于每个点，如果由该点、你的位置以及从你的位置直接向东的方向形成的角度 位于你的视野中 ，那么你就可以看到它。
 * <p>
 * 同一个坐标上可以有多个点。你所在的位置也可能存在一些点，但不管你的怎么旋转，总是可以看到这些点。同时，点不会阻碍你看到其他点。
 * <p>
 * 返回你能看到的点的最大数目。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-visible-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * state:done
 */
public class Solution1610 {


    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        //统计每个点行对于location的角度
        List<Double> list = new ArrayList<>();
        Map<Double, List<List<Integer>>> distributionMap = new HashMap<>();

        int locationX = location.get(0);
        int locationY = location.get(1);
        int originNum = 0;
        for (List<Integer> point : points) {
            int x = point.get(0);
            int y = point.get(1);
            //计算角度；
            if (x == locationX && y == locationY) {
                originNum++;
                continue;
            }
            double currentAngle = Math.toDegrees(Math.atan2(x - locationX, y - locationY));
            List<List<Integer>> lists = distributionMap.get(currentAngle);
            if (lists == null) {
                list.add(currentAngle);
                lists = new ArrayList<>();
                distributionMap.put(currentAngle, lists);
            }
            lists.add(point);
        }

        list.sort(Double::compareTo);

        double startAngele;//区间开始的角度值
        double endAngeleKey;//区间结束的角度值的key值
        double endAngele;//区间结束的角度值
        int maxSum = 0;//最大数量
        int currentSum = 0;//当前区间的数量
        int startIndex = 0;//区间开始的角度值对应的位置
        int endIndex = 0;///区间结束的角度值对应的位置
        //动态区间
        while (startIndex < list.size() && endIndex < 2 * list.size()) {
            startAngele = list.get(startIndex);
            if (endIndex >= list.size()) {
                endAngeleKey = list.get(endIndex - list.size());
                endAngele = list.get(endIndex - list.size()) + 360;
            } else {
                endAngeleKey = list.get(endIndex);
                endAngele = list.get(endIndex);
            }
            double localAngle = endAngele - startAngele;

            //要判断角度
            while (localAngle > angle) {
                List<List<Integer>> startAngeleList = distributionMap.get(startAngele);
                currentSum -= startAngeleList.size();
                startIndex++;
                if (startIndex == list.size()) {
                    return maxSum + originNum;
                }
                startAngele = list.get(startIndex);
                localAngle = (endAngele < 0 && startAngele > 0) ? 360 + endAngele - startAngele : endAngele - startAngele;
            }
            List<List<Integer>> endList = distributionMap.get(endAngeleKey);
            currentSum += endList.size();
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            endIndex++;
        }

        return maxSum + originNum;
    }
}