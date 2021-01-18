package kwaymerge;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Author:   softtwilight
 * Date:     2021/01/18 21:41
 */
public class _378_M_Kth_Smallest_Element_in_a_Sorted_Matrix {
    private static final _378_M_Kth_Smallest_Element_in_a_Sorted_Matrix instance = new _378_M_Kth_Smallest_Element_in_a_Sorted_Matrix();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * use k way merge to solve.
     * but pretty slow.
     *
     * there has a property is not used. the row is also sorted.
     * the intuitive thought is maybe we can use binary search to sorted array.
     *
     * 40 ms, faster than 5.18%
     */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < matrix.length; i++) {
            minHeap.add(new int[]{matrix[i][0], i, 0});
        }
        int result = 0;
        while (k > 0) {
            int[] min = minHeap.poll();
            result = min[0];
            if (min[2] + 1 < matrix[min[1]].length) {
                int newValue = matrix[min[1]][min[2] + 1];
                minHeap.offer(new int[] {newValue, min[1], min[2] + 1});
            }

            k--;
        }
        return result;

    }
}
