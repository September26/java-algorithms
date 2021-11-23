package com.xt.lintcode;

public class OceanDistance {


    public int test() {
        int[][] ints = new int[5][5];
        ints[0] = new int[]{0, 0, 0, 0, 0};
        ints[1] = new int[]{0, 1, 0, 0, 0};
        ints[2] = new int[]{0, 1, 1, 0, 0};
        ints[3] = new int[]{0, 0, 0, 0, 0};
        ints[4] = new int[]{0, 0, 0, 0, 0};
        int maxDistance = maxDistance(ints);
        return maxDistance;
    }

    public int maxDistance(int[][] ints) {
        int n = ints.length;
        int maxDistance = -1;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                if (ints[i][j] == 1) {
                    continue;
                }
                int distance = getDistance(ints, n, j, i);
                maxDistance = Math.max(maxDistance, distance);
            }
        }
        return maxDistance;
    }


    private int getDistance(int[][] ints, int n, int x, int y) {
        int maxSize = Math.max(x + y, n - x + y);
        maxSize = Math.max(maxSize, x + n - y);
        maxSize = Math.max(maxSize, n - x + n - y);
        for (int i = 1; i <= maxSize; i++) {
            if (isHave(ints, n, x, y, i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isHave(int[][] ints, int n, int x, int y, int sum) {
        for (int yDiff = 0; yDiff <= sum; yDiff++) {
            int xDiff = sum - yDiff;
            if ((y + yDiff) < n && (x + xDiff) < n && ints[y + yDiff][x + xDiff] == 1) {
                return true;
            }
            if ((y - yDiff) >= 0 && (x + xDiff) < n && ints[y - yDiff][x + xDiff] == 1) {
                return true;
            }
            if ((y + yDiff) < n && (x - xDiff) >= 0 && ints[y + yDiff][x - xDiff] == 1) {
                return true;
            }
            if ((y - yDiff) >= 0 && (x - xDiff) >= 0 && ints[y - yDiff][x - xDiff] == 1) {
                return true;
            }
        }
        return false;
    }


}
