package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * @author softtwilight 2020/01/27
 *
 */
public class _57_Insert_Interval {
    /**
     * the key is to find the insert point. N * 2 range array can have N * 2 + 2
     * insert points. the odd number in between the interval. the even number is
     * out of interval.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> list = new ArrayList<>();
        int left = newInterval[0], right = newInterval[1];
        int l = intervals.length;
        int lip = -1, rip = -1;
        for (int i = 0; i < l; i++) {
            int[] range = intervals[i];
            if (left < range[0] && lip == -1) lip = 2 * i;
            if (left <= range[1] && lip == -1) lip = 2 * i + 1;
            if (right >= range[0]) rip = 2 * i + 1;
            if (right > range[1]) rip = 2 * i + 2;
        }

        lip = lip == -1 ? 2 * l : lip;
        rip = rip == -1 ? 0 : rip;

        // append the intervals which is small than newInterval
        for (int i = 0; i < lip / 2; i++) {
            list.add(intervals[i]);
        }

        // merge the overlap part. compute the overlap inteval left and right.
        int overl, overR;
        // if left insert point is even, so it's out of range, use lip
        // otherwise, use old interval's left value.
        overl = lip % 2 == 0 ? left : intervals[lip / 2][0];

        // same as overL
        overR = rip % 2 == 0 ? right : intervals[rip / 2][1];
        list.add(new int[] {overl, overR});

        // append the intervals bigger than new interval
        for (int i = (rip + 1) / 2; i < l; i++) {
            list.add(intervals[i]);
        }
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }



    /**
     * code from leetcode. really clever!
     */
    public int[][] insert2(int[][] intervals, int[] newInterval) {

        ArrayList<int[]> aList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[1] < newInterval[0]) {
                aList.add(interval);

            } else if (newInterval[1] < interval[0]) {
                aList.add(newInterval);

                // this is the most brilliant code.
                // 用画图来表示的时候，很容易将newInterval 理解成一个不可变的interval，一个区间
                // 将一个区间插入另外一个区间。
                // 但是当这个区间是一个活动的区间的时候，一些就都不一样了。
                // newInterval 变成一个当前最大的区间。
                // merge overlap的过程就很简单了。
                newInterval = interval;

            } else {
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        aList.add(newInterval);
        int[][] result = new int[aList.size()][2];
        for (int j = 0; j < result.length; j++) {
            result[j] = aList.get(j);
        }
        return result;
    }
}
