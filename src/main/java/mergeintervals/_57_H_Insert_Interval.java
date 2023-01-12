package mergeintervals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/05/21 22:04
 */
public class _57_H_Insert_Interval {
    private static final _57_H_Insert_Interval instance = new _57_H_Insert_Interval();

    public static void main(String[] args) {

        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] ni = {4, 8};
        System.out.println(Arrays.deepToString(instance.insert(intervals, ni)));
    }

    /**
     * 	1 ms  +	41.7 MB
     * 	这道题做过了，关键是换newInterval，就可以将比较需要考虑的情况降下来。
     * 	掌握这个技巧就不难了.
     * The key is regard merge two intervals as a sub-problem, and left a (merged or not) interval to merge with remaining interval.
     *
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        for (int[] cur : intervals) {
            if (cur[1] < newInterval[0]) {
                result.add(cur);
            } else if (cur[0] > newInterval[1]) {
                result.add(newInterval);
                newInterval = cur;
            } else {
                newInterval[0] = Math.min(newInterval[0], cur[0]);
                newInterval[1] = Math.max(newInterval[1], cur[1]);
            }
        }
        result.add(newInterval);
        return result.toArray(new int[result.size()][2]);
    }
}
