package binarysearch;

/**
 * https://leetcode.com/problems/count-of-range-sum/
 *
 * Author:   softtwilight
 * Date:     2020/06/15 21:38
 */
public class _327_H_Count_of_Range_Sum {
    private static final _327_H_Count_of_Range_Sum instance = new _327_H_Count_of_Range_Sum();

    public static void main(String[] args) {
        int[] nums = {-2, 5, -1};
        int lower = -2;
        int upper = 2;
        System.out.println(instance.countRangeSum(nums, lower, upper));
    }

    /**
     *
     */
    public int countRangeSum(int[] nums, int lower, int upper) {

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = 0;
            for (int j = i; j < nums.length; j++) {
                cur += nums[j];
                if (cur >= lower && cur <= upper) {
                    result++;
                }
            }
        }

        return result;
    }



    /**
     * 这个解法就太棒了，和315中利用merge sort计算右边更小的数的方法是类似的。
     * 1. 求nums的累加和数组，sums
     *  - 对315题来说  count[i] = count of nums[j] - nums[i] < 0 with j > i
     *  - 对于这题来说 count[i] = count of lo <= sums[j] - sums[i] <= hi with j > i
     *
     * 这一步至关重要，因为是累加和sum， 我们在merge之后， sum[j] - sum[i] 代表的才是一个连续range的sum，
     * 这样merge改变的原数组指针，不会影响的是否连续，otherwise 我们可以想象到对原数组排序， range的性质就完全变了。
     *
     * 2. 我们对sums进行mergeSort
     *  -mergeSort 1是对原数组排序
     *  -      2是返回番位start ～ end 内 Sum 在[lower, upper]的数
     *
     * 3. 具体的mergeSort，对范围二分，然后对两个子范围mergeSort
     * 4. 相加两个left 和 right的count和， 对整个范围， 还缺少跨越中点
     * 5. 所以我们在merge的时候， 1是merge了原来sort的数组
     *      2是计算在 start - mid 的范围内 开始， 结束在mid - end 范围的range， 值在lower 和 upper之间
     *      因为子数组是排序的， 我们只需要找到第一个>= lower 和 最后一个 <= upper的值，然后+ 这两个数的差
     * 6. 将merge的新数组， copy回原数组。
     */
    public int countRangeSum2(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {

        // 这里为什么 = 1的时候也返回0呢，因为单个元素也可能是在范围内
        // 这里要注意sums是从0开始的， 而sums[0] = 0, 没有sum。这里实在有点明白，其实我像如下这么改也是正确的
        //  如下写的时候，其实是自己计算从开始到现在的sum 满足的range的情况。 而这正好可以通过引入一个值
        // 为0的sum 来计算sum[i] - sum[0] 实现。
        // so
        /**
         * return countWhileMergeSort(sums, 1, n + 1, lower, upper); //忽视第一个
         * ...
            if (end - start < 1) return 0;
            if (end - start == 1)  {
                if (sums[start] >= lower && sums[start] <= upper) return 1;
                return 0;
            }

         */


        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper)
                + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            while (k < end && sums[k] - sums[i] < lower) k++;
            while (j < end && sums[j] - sums[i] <= upper) j++;
            while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }
}
