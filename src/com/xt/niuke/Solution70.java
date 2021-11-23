package com.xt.niuke;


/**
 * 矩形覆盖
 * 描述
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，从同一个方向看总共有多少种不同的方法？
 * <p>
 * 比如n=3时，2*3的矩形块有3种不同的覆盖方法(从同一个方向看)：
 * <p>
 * 输入描述：
 * 2*1的小矩形的总个数n
 * 返回值描述：
 * 覆盖一个2*n的大矩形总共有多少种不同的方法(从同一个方向看)
 * 示例1
 * 输入：
 * 0
 * 返回值：
 * 0
 * 示例2
 * 输入：
 * 1
 * 返回值：
 * 1
 * 示例3
 * 输入：
 * 4
 * 返回值：
 * 5
 * 11月1
 */
public class Solution70 {
    public int rectCover(int target) {
        if (target <= 2) {
            return target;
        }
        return rectCover(target - 1) + rectCover(target - 2);
    }
}
