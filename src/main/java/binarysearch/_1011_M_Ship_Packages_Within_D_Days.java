package binarysearch;

/**
 *
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 *
 * The i-th package on the conveyor belt has a weight of weights[i].
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages
 * on the conveyor belt being shipped within D days.
 *
 *
 *
 * Example 1:
 *
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * Output: 15
 * Explanation:
 * A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 *
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * Author:   softtwilight
 * Date:     2020/05/09 23:34
 */
public class _1011_M_Ship_Packages_Within_D_Days {
    private static final _1011_M_Ship_Packages_Within_D_Days instance = new _1011_M_Ship_Packages_Within_D_Days();

    public static void main(String[] args) {
        System.out.println(instance);
    }



    /**
     * 7 ms	+ 42.8 MB
     * 二分查找，上界是所有数组的sum， 下界是数组中的最大值（最小capacity一定是介于这两个之间的）
     * 然后我们再确定Predicate函数，（在D天之内能够运输完成）
     * （我们贪心的用capacity， 当超过之后进入下一天，然后如果天数小于等于D， 那么capacity是ok的）
     * 如果按照上述的方式来构建Predicate
     */
    public int shipWithinDays(int[] weights, int D) {

        int lo = 0, hi = 0;
        for (int w : weights) {
            lo = Math.max(lo, w);
            hi += w;
        }
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int dayLoad = 0;
            int dayCount = 1;
            for (int w : weights) {
                dayLoad += w;
                if (dayLoad > mid) {
                    dayLoad = w;
                    dayCount++;
                    if (dayCount > D) {
                        break;
                    }
                }
            }
            if (dayCount > D) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
