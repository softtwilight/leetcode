package mergeintervals;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * Author:   softtwilight
 * Date:     2020/05/22 22:05
 */
public class _452_M_Minimum_Number_of_Arrows_to {
    private static final _452_M_Minimum_Number_of_Arrows_to instance = new _452_M_Minimum_Number_of_Arrows_to();

    public static void main(String[] args) {
        int[][] points = {{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}};
        System.out.println(instance.findMinArrowShots(points));
    }

    /**
     * 25 ms  +	47.2 MB
     *
     * 如果两个interval 不相交， 那么多需要一个arrow， 本质还是判断相交
     * 先排序。每次都是记录points， 发现只记录end点会更快一些。
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length < 1) return 0;
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int result = 1;
        int[] first = points[0];
        for (int i = 1; i < points.length; i++) {
            int[] cur = points[i];
            if (cur[0] > first[1]) {
                result++;
                first = cur;
            } else if (cur[1] < first[1]) {
                first = cur;
            }
        }
        return result;
    }


    /**
     * 15 ms  +	47.3 MB
     */
    public int findMinArrowShots2(int[][] points) {
        int count = 0;
        long end = Long.MIN_VALUE;
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        for(int[] p: points){
            if(p[0] > end){
                end = p[1];
                count++;
            }
        }

        return count;
    }

}
