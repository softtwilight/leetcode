package mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 *
 * Author:   softtwilight
 * Date:     2020/05/23 21:57
 */
public class _986_M_Interval_List_Intersections {
    private static final _986_M_Interval_List_Intersections instance = new _986_M_Interval_List_Intersections();

    public static void main(String[] args) {
        int[][] A = {{0,2},{5,10},{13,23},{24,25}};
        int[][] B = {{1,5},{8,12},{15,24},{25,26}};
        System.out.println(Arrays.deepToString(instance.intervalIntersection(A, B)));
    }

    /**
     * 2 ms	39.8 MB
     * 考虑几种情况 合并interval就好了，画图就会很清晰
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {

        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int[] a = A[i];
            int[] b = B[j];

            if (a[0] > b[1]) {
                j++;
            } else if (a[1] < b[0]) {
                i++;
            } else {
                result.add(new int[] {Math.max(a[0], b[0]), Math.min(a[1], b[1])});
                if (a[1] > b[1]) {
                    j++;
                } else if (a[1] < b[1]) {
                    i++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
