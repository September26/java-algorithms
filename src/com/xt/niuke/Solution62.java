package com.xt.niuke;


/**
 * 每年六一儿童节，牛客都会准备一些小礼物和小游戏去看望孤儿院的孩子们。
 * 其中，有个游戏是这样的：首先，让 n 个小朋友们围成一个大圈，小朋友们的编号是0~n-1。
 * 然后，随机指定一个数 m ，让编号为0的小朋友开始报数。每次喊到 m-1 的那个小朋友要出列唱首歌，然后可以在礼品箱中任意的挑选礼物，并且不再回到圈中，
 * 从他的下一个小朋友开始，继续0... m-1报数....这样下去....直到剩下最后一个小朋友，可以不用表演，
 * 并且拿到牛客礼品，请你试着想下，哪个小朋友会得到这份礼品呢？
 * <p>
 * 数据范围：1 \le n \le 50001≤n≤5000，1 \le m \le 100001≤m≤10000
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 * 10月25
 */
public class Solution62 {

    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0) return -1;
        return f(n, m);
    }

    int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);

        int i = (x + m) % n;

        return i;
    }

}
