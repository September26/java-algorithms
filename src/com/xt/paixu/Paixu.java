package com.xt.paixu;

import com.xt.Solution;

public class Paixu {
    //从小到打排序
    static int[] ints = {7, 8, 9, 5, 7, 1, 6, 3, 55, 7, 2, 5, 4, 6, 2, 8, 1, 9, 4};


    public static void main(String[] args) {

        new DualPivotQuickSort().sort(ints);
//        show(ints);

    }


    public static void show(int[] ints) {
        StringBuilder builder = new StringBuilder();
        for (int anInt : ints) {
            builder.append(anInt).append(",");
        }
        System.out.println(builder.toString());
    }

    /**
     * 冒泡排序
     */
    static class Maopao extends Sort {
        public void sort(int[] ints) {
            for (int i = ints.length - 1; i >= 0; i--) {
                for (int k = ints.length - 1; k >= ints.length - i; k--) {
                    if (ints[k] < ints[k - 1]) {
                        exchangeIndex(ints, k - 1, k);
                    }
                }
                print(ints);
            }
        }
    }

    //简单选择排序
    static class Xuanze extends Sort {
        public void sort(int[] ints) {
            for (int i = 0; i < ints.length; i++) {
                int minIndex = i;
                for (int j = i + 1; j < ints.length; j++) {
                    if (ints[j] < ints[minIndex]) {
                        minIndex = j;
                    }
                }
                int local = ints[i];
                ints[i] = ints[minIndex];
                ints[minIndex] = local;
            }
        }
    }


    /**
     * 快速排序
     */
    static class Kuaisu extends Sort {

        public void sort(int[] ints) {
            sort(ints, 0, ints.length - 1);
        }

        void sort(int[] ints, int startIndex, int endIndex) {
            int compareIndex = startIndex;
            int leftIndex = startIndex;
            int rightIndex = endIndex;
            boolean isRight = true;

            while (rightIndex >= leftIndex) {
                if (isRight) {
                    if (ints[compareIndex] > ints[rightIndex]) {
                        int local = ints[rightIndex];
                        ints[rightIndex] = ints[compareIndex];
                        ints[compareIndex] = local;
                        compareIndex = rightIndex;
                        leftIndex++;
                        isRight = false;
                        continue;
                    }
                    rightIndex--;
                    continue;
                }
                if (ints[compareIndex] < ints[leftIndex]) {
                    int local = ints[leftIndex];
                    ints[leftIndex] = ints[compareIndex];
                    ints[compareIndex] = local;
                    compareIndex = leftIndex;
                    rightIndex--;
                    isRight = true;
                    continue;
                }
                leftIndex++;
            }
            for (int i = startIndex; i < endIndex + 1; i++) {
                System.out.print(ints[i] + ",");
            }
            System.out.println();
            if ((compareIndex - startIndex) > 1) {
                sort(ints, startIndex, compareIndex - 1);
            }
            if ((endIndex - compareIndex) > 1) {
                sort(ints, compareIndex + 1, endIndex);
            }
        }
    }


    //双轴快排
    static class DualPivotQuickSort extends Sort {

        @Override
        public void sort(int[] a) {
            sort(a, 0, a.length - 1);
        }


        public void sort(int[] a, int left, int right) {


            int leftIndex = left;
            int rightIndex = right;

            int littleValue = a[leftIndex];
            int bigValue = a[right];
            int index = leftIndex + 1;

            //先比较两者，如果left大则交换
            if (littleValue > bigValue) {
                exchange(a, leftIndex, rightIndex);
                littleValue = a[leftIndex];
                bigValue = a[rightIndex];
            }

            //比较
            while (index < rightIndex) {
                Solution.print(a);
                int value = a[index];
                if (value > bigValue) {
                    exchange(a, index, rightIndex);
                    rightIndex--;
                    continue;
                }
                if (value < littleValue) {
                    exchange(a, index, leftIndex);
                    leftIndex++;
                    continue;
                }
                index++;
            }

        }


        private void exchange(int[] a, int index1, int index2) {
            int local = a[index1];
            a[index1] = a[index2];
            a[index2] = local;
        }
    }

    /**
     * 归并排序
     */
    static class Guibind extends Sort {
        /**
         * @param ints
         */
        public void sort(int[] ints) {

            recursion(ints, 1);
            print(ints);
            //1 拆成若干个小数组


            //2 相邻的两个数组合并


            //3 递归合并

        }

        /**
         * @param ints
         * @param num  2
         */
        void recursion(int[] ints, int num) {
            int index = 0;
            while (index < ints.length) {
                merge(ints, index, index + num - 1, index + num, index + num + num - 1);
                index = index + num * 2;
            }
            if (num >= ints.length) {
                return;
            }
            print(ints);
            recursion(ints, num * 2);
        }


        //num=1,2,4,8,16
        void merge(int[] ints, int start, int end, int start2, int end2) {
            System.out.println("start:" + start + ",end:" + end + ",start2:" + start2 + ",end2:" + end2);
            while (start <= end && start2 <= end2) {
                if (start2 >= ints.length) {
                    break;
                }
                System.out.println(ints[start] + "," + ints[start2]);
                if (ints[start] > ints[start2]) {
                    exchangeIndex(ints, start, start2);
                    start2++;
                } else {
                    start++;
                }


            }
        }
    }


    //简单插入排序


    //希尔排序


    //计数排序


    //橘排序


    //基数排序


    private static void exchangeIndex(int[] ints, int index1, int index2) {
        int local = ints[index1];
        ints[index1] = ints[index2];
        ints[index2] = local;
    }

    private static void print(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + ",");
        }
        System.out.println();
    }


    static abstract class Sort {
        abstract public void sort(int[] ints);
    }

}
