package com.xt.paixu;

public class Paixu {
    //从小到打排序
    static int[] ints = {15, 61, 25, 68, 49, 2, 456, 246, 56, 76, 53, 4, 56, 7, 8, 9, 5, 7, 1, 6, 3, 55, 7, 2, 5, 4, 6, 2, 8, 1, 9, 4};


    public static void main(String[] args) {

//        int[] sort = new Maopao().sort(ints);
//        show(sort);

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


    //todo 自己写
    static class DualPivotQuickSort extends Sort {

        @Override
        public void sort(int[] a) {
            sort(a, 0, a.length - 1, true);
        }


        public void sort(int[] a, int left, int right, boolean leftmost) {
            int length = right - left + 1;

            // Use insertion sort on tiny arrays

            // Inexpensive approximation of length / 7
            int seventh = (length >> 3) + (length >> 6) + 1;

            /*
             * Sort five evenly spaced elements around (and including) the
             * center element in the range. These elements will be used for
             * pivot selection as described below. The choice for spacing
             * these elements was empirically determined to work well on
             * a wide variety of inputs.
             */
            int e3 = (left + right) >>> 1; // The midpoint
            int e2 = e3 - seventh;
            int e1 = e2 - seventh;
            int e4 = e3 + seventh;
            int e5 = e4 + seventh;

            // Sort these elements using insertion sort
            if (a[e2] < a[e1]) {
                int t = a[e2];
                a[e2] = a[e1];
                a[e1] = t;
            }

            if (a[e3] < a[e2]) {
                int t = a[e3];
                a[e3] = a[e2];
                a[e2] = t;
                if (t < a[e1]) {
                    a[e2] = a[e1];
                    a[e1] = t;
                }
            }
            if (a[e4] < a[e3]) {
                int t = a[e4];
                a[e4] = a[e3];
                a[e3] = t;
                if (t < a[e2]) {
                    a[e3] = a[e2];
                    a[e2] = t;
                    if (t < a[e1]) {
                        a[e2] = a[e1];
                        a[e1] = t;
                    }
                }
            }
            if (a[e5] < a[e4]) {
                int t = a[e5];
                a[e5] = a[e4];
                a[e4] = t;
                if (t < a[e3]) {
                    a[e4] = a[e3];
                    a[e3] = t;
                    if (t < a[e2]) {
                        a[e3] = a[e2];
                        a[e2] = t;
                        if (t < a[e1]) {
                            a[e2] = a[e1];
                            a[e1] = t;
                        }
                    }
                }
            }

            // Pointers
            int less = left;  // The index of the first element of center part
            int great = right; // The index before the first element of right part

            if (a[e1] != a[e2] && a[e2] != a[e3] && a[e3] != a[e4] && a[e4] != a[e5]) {
                /*
                 * Use the second and fourth of the five sorted elements as pivots.
                 * These values are inexpensive approximations of the first and
                 * second terciles of the array. Note that pivot1 <= pivot2.
                 */
                int pivot1 = a[e2];
                int pivot2 = a[e4];

                /*
                 * The first and the last elements to be sorted are moved to the
                 * locations formerly occupied by the pivots. When partitioning
                 * is complete, the pivots are swapped back into their final
                 * positions, and excluded from subsequent sorting.
                 */
                a[e2] = a[left];
                a[e4] = a[right];

                /*
                 * Skip elements, which are less or greater than pivot values.
                 */
                while (a[++less] < pivot1) ;
                while (a[--great] > pivot2) ;

                /*
                 * Partitioning:
                 *
                 *   left part           center part                   right part
                 * +--------------------------------------------------------------+
                 * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
                 * +--------------------------------------------------------------+
                 *               ^                          ^       ^
                 *               |                          |       |
                 *              less                        k     great
                 *
                 * Invariants:
                 *
                 *              all in (left, less)   < pivot1
                 *    pivot1 <= all in [less, k)     <= pivot2
                 *              all in (great, right) > pivot2
                 *
                 * Pointer k is the first index of ?-part.
                 */
                outer:
                for (int k = less - 1; ++k <= great; ) {
                    int ak = a[k];
                    if (ak < pivot1) { // Move a[k] to left part
                        a[k] = a[less];
                        /*
                         * Here and below we use "a[i] = b; i++;" instead
                         * of "a[i++] = b;" due to performance issue.
                         */
                        a[less] = ak;
                        ++less;
                    } else if (ak > pivot2) { // Move a[k] to right part
                        while (a[great] > pivot2) {
                            if (great-- == k) {
                                break outer;
                            }
                        }
                        if (a[great] < pivot1) { // a[great] <= pivot2
                            a[k] = a[less];
                            a[less] = a[great];
                            ++less;
                        } else { // pivot1 <= a[great] <= pivot2
                            a[k] = a[great];
                        }
                        /*
                         * Here and below we use "a[i] = b; i--;" instead
                         * of "a[i--] = b;" due to performance issue.
                         */
                        a[great] = ak;
                        --great;
                    }
                }

                // Swap pivots into their final positions
                a[left] = a[less - 1];
                a[less - 1] = pivot1;
                a[right] = a[great + 1];
                a[great + 1] = pivot2;

                // Sort left and right parts recursively, excluding known pivots
                sort(a, left, less - 2, leftmost);
                sort(a, great + 2, right, false);

                /*
                 * If center part is too large (comprises > 4/7 of the array),
                 * swap internal pivot values to ends.
                 */
                if (less < e1 && e5 < great) {
                    /*
                     * Skip elements, which are equal to pivot values.
                     */
                    while (a[less] == pivot1) {
                        ++less;
                    }

                    while (a[great] == pivot2) {
                        --great;
                    }

                    /*
                     * Partitioning:
                     *
                     *   left part         center part                  right part
                     * +----------------------------------------------------------+
                     * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                     * +----------------------------------------------------------+
                     *              ^                        ^       ^
                     *              |                        |       |
                     *             less                      k     great
                     *
                     * Invariants:
                     *
                     *              all in (*,  less) == pivot1
                     *     pivot1 < all in [less,  k)  < pivot2
                     *              all in (great, *) == pivot2
                     *
                     * Pointer k is the first index of ?-part.
                     */
                    outer:
                    for (int k = less - 1; ++k <= great; ) {
                        int ak = a[k];
                        if (ak == pivot1) { // Move a[k] to left part
                            a[k] = a[less];
                            a[less] = ak;
                            ++less;
                        } else if (ak == pivot2) { // Move a[k] to right part
                            while (a[great] == pivot2) {
                                if (great-- == k) {
                                    break outer;
                                }
                            }
                            if (a[great] == pivot1) { // a[great] < pivot2
                                a[k] = a[less];
                                /*
                                 * Even though a[great] equals to pivot1, the
                                 * assignment a[less] = pivot1 may be incorrect,
                                 * if a[great] and pivot1 are floating-point zeros
                                 * of different signs. Therefore in float and
                                 * double sorting methods we have to use more
                                 * accurate assignment a[less] = a[great].
                                 */
                                a[less] = pivot1;
                                ++less;
                            } else { // pivot1 < a[great] < pivot2
                                a[k] = a[great];
                            }
                            a[great] = ak;
                            --great;
                        }
                    }
                }

                // Sort center part recursively
                sort(a, less, great, false);

            } else { // Partitioning with one pivot
                /*
                 * Use the third of the five sorted elements as pivot.
                 * This value is inexpensive approximation of the median.
                 */
                int pivot = a[e3];

                /*
                 * Partitioning degenerates to the traditional 3-way
                 * (or "Dutch National Flag") schema:
                 *
                 *   left part    center part              right part
                 * +-------------------------------------------------+
                 * |  < pivot  |   == pivot   |     ?    |  > pivot  |
                 * +-------------------------------------------------+
                 *              ^              ^        ^
                 *              |              |        |
                 *             less            k      great
                 *
                 * Invariants:
                 *
                 *   all in (left, less)   < pivot
                 *   all in [less, k)     == pivot
                 *   all in (great, right) > pivot
                 *
                 * Pointer k is the first index of ?-part.
                 */
                for (int k = less; k <= great; ++k) {
                    if (a[k] == pivot) {
                        continue;
                    }
                    int ak = a[k];
                    if (ak < pivot) { // Move a[k] to left part
                        a[k] = a[less];
                        a[less] = ak;
                        ++less;
                    } else { // a[k] > pivot - Move a[k] to right part
                        while (a[great] > pivot) {
                            --great;
                        }
                        if (a[great] < pivot) { // a[great] <= pivot
                            a[k] = a[less];
                            a[less] = a[great];
                            ++less;
                        } else { // a[great] == pivot
                            /*
                             * Even though a[great] equals to pivot, the
                             * assignment a[k] = pivot may be incorrect,
                             * if a[great] and pivot are floating-point
                             * zeros of different signs. Therefore in float
                             * and double sorting methods we have to use
                             * more accurate assignment a[k] = a[great].
                             */
                            a[k] = pivot;
                        }
                        a[great] = ak;
                        --great;
                    }
                }

                /*
                 * Sort left and right parts recursively.
                 * All elements from center part are equal
                 * and, therefore, already sorted.
                 */
                sort(a, left, less - 1, leftmost);
                sort(a, great + 1, right, false);
            }
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
