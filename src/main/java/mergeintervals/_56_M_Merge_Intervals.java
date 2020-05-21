package mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/05/21 21:18
 */
public class _56_M_Merge_Intervals {
    private static final _56_M_Merge_Intervals instance = new _56_M_Merge_Intervals();

    public static void main(String[] args) {
        int[][] input = {{1,3},{2,6},{6,10},{15,18}};
        System.out.println(Arrays.deepToString(instance.merge(input)));
    }

    /**
     * 10 ms  +	44.7 MB
     * (第一次忘判断空数组了，error了一次）
     *
     * 1.排序
     * 2.判断 cur的end 和 next的 begin，
     *  if end > begin, merge, cur[1] = max(cur[1], next[1])
     *  else cur = next, add cur to list.
     * 3.add the last one
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1 ) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();
        int[] first = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (first[1] < cur[0]) {
                result.add(first);
                first = cur;
            } else {
                first[1] = first[1] > cur[1] ? first[1] : cur[1];
            }
        }
        result.add(first);
        return result.toArray(new int[result.size()][2]);
    }
}
