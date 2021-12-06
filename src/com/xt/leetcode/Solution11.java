package com.xt.leetcode;


/**
 * 11.盛最多的水
 * <p>
 * O(n)
 */
class Solution11 {

    public int maxArea(int[] height) {
        int maxValue = 0;
        int currentValue = 0;
        int endH;
        int maxHeight = 0;
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            //如果后读的高度还没有前面的高，那么就不用判断了。
            if (h < maxHeight) {
                continue;
            }
            maxHeight = h;
            for (int k = height.length - 1; k > i; k--) {
                endH = height[k];
                currentValue = (k - i) * Math.min(h, endH);
                maxValue = Math.max(maxValue, currentValue);
                //如果结束的高度大于start的高度，则也可以跳出循环
                if (endH >= h) {
                    break;
                }
            }
        }
        return maxValue;
    }
}
