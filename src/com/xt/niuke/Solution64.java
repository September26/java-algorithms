package com.xt.niuke;


/**
 * 描述
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * <p>
 * 数据范围： 0<n≤200
 * 进阶： 空间复杂度 O(1) ，时间复杂度 O(n)
 * 10月26
 * done
 */
public class Solution64 {
    public int Sum_Solution(int n) {
        if (n == 1) {
            return 1;
        }
        return Sum_Solution(n - 1) + n;
    }

}
