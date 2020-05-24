package mergeintervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author:   softtwilight
 * Date:     2020/05/21 22:15
 */
public class _435_M_Non_Overlapping_Intervals {
    private static final _435_M_Non_Overlapping_Intervals instance = new _435_M_Non_Overlapping_Intervals();

    public static void main(String[] args) {
        int[][] input = {{1,2},{2,3}};
        System.out.println(instance.eraseOverlapIntervals(input));
    }

    /**
     * 3 ms	+ 41.7 MB
     *
     * 1. 对intervals排序， 优先以[0], 相等情况以[1]排序；
     * 2. 如果遇到overlap， 就需要移出一个interval， 移出end更大的，保留end更小的。
     * 3. 因为条件2 + intervals[0]没有overlap， by inductive，可以保证在k处，之前的interval里没有overlap的，又加上是排序的
     *      可以保证如果出现overlap 只可能是和当前的interval overlap，只有一个。
     *      那么这时候，对于左边移出哪一个都是可以的（都和之前的不overlap）
     *      对于右边，我们采用贪心的策略，移出end更大的intercal
     * 4. sort 的复杂度是O（N*logN),遍历为N，最终O（N*logN)
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) return 0;
        Arrays.sort(intervals, (a, b) -> {
            return a[0] >  b[0] ? 1 :
                    a[0] < b[0] ? - 1 : a[1] - b[1];
        });
        int count = 0;
        int[] small = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (small[0] == cur[0]) {
                count++;
            } else if (small[1] <= cur[0]) {
                small = cur;
            } else {
                if (small[1] > cur[1]) {
                   small= cur;
                }
                count++;
            }
        }
        return count;
    }

    /**
     * 这是1ms的解法
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // 按 start 升序排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        // 至少有一个区间不相交
        int count = 1;
        int len = intervals.length;
        int start = intervals[len - 1][0];
        for (int i = len - 1; i >= 0; i--) {
            int[] interval = intervals[i];
            int end = interval[1];
            if (end <= start) {
                // 找到下一个选择的区间了
                count++;
                start = interval[0];
            }
        }
        return intervals.length - count;
    }
}
