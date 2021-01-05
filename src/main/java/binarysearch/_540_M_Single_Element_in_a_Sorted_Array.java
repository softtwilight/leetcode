package binarysearch;

/**
 * Author:   softtwilight
 * Date:     2021/01/05 21:49
 */
public class _540_M_Single_Element_in_a_Sorted_Array {
    private static final _540_M_Single_Element_in_a_Sorted_Array instance = new _540_M_Single_Element_in_a_Sorted_Array();

    public static void main(String[] args) {
        int[] nums = {0,0, 1,1,2,2,3,3,4,4,6,8,8,9, 9};
        System.out.println(instance.singleNonDuplicate(nums));
    }

    /**
     * binary search
     * the key point is create the Predict
     *
     * There is the only 1 unique element k, so the element i < k /2, nums[i * 2] == nums[i * 2 + 1]
     * and from the k, for j, j >= k / 2, nums[j * 2] < nums[j * 2 + 1]
     *
     * use this we can reduce the range. the core case of the last element is unique.
     * (the unique element's index k, k % 2 == 0)
     */
    public int singleNonDuplicate(int[] nums) {
        int hi = nums.length / 2;
        int lo = 0;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid * 2] == nums[mid * 2 + 1]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        if (lo * 2 < nums.length) {
            return nums[lo * 2];
        }
        return nums[nums.length - 1];
    }
}
