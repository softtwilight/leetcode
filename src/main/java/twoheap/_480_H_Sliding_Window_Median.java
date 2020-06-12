package twoheap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.function.Supplier;

/**
 * https://leetcode.com/problems/sliding-window-median/
 *
 * Author:   softtwilight
 * Date:     2020/06/12 22:10
 */
public class _480_H_Sliding_Window_Median {
    private static final _480_H_Sliding_Window_Median instance = new _480_H_Sliding_Window_Median();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 139 ms	41.8 MB
     *
     * pority queue 的 remove 是liner的。。。
     * 思路同295
     * 复杂度是O（n * k)
     */
    public double[] medianSlidingWindow(int[] nums, int k) {

        PriorityQueue<Integer> asc = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> desc = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            asc.offer(nums[i]);
        }
        for (int i = 0; i < k / 2 ; i++) {
            desc.offer(asc.poll());
        }
        int len = nums.length - k + 1;
        double[] result = new double[len];
        boolean isEven = k % 2 == 0;
        for (int i = 0; i < len; i++) {
            Integer leftMax = asc.peek();
            Integer rightMin = desc.peek();
            if (isEven) {
                result[i] = leftMax / 2.0 + rightMin / 2.0;
            } else {
                result[i] = leftMax;
            }

            if (k + i == nums.length) continue;
            if (asc.remove(nums[i])) {
                desc.offer(nums[k + i]);
                asc.offer(desc.poll());
            } else {
                desc.remove(nums[i]);
                asc.offer(nums[k + i]);
                desc.offer(asc.poll());
            }
        }
        return result;
    }


    /**
     * 29 ms	41 MB
     * 参考了讨论里的思路。
     *
     * 因为Heap删除是线性复杂度的，我们用Tree来排序
     * 用tree排序必须要解决的一个问题是不能有重复，不然后面的数据会覆盖前面的数据。
     * 这里就有一个非常巧妙的方法， 如果值相等，我们就比较坐标。
     * 这样全部的nums就是有序的了。
     *
     * 解决了这点后， 整体思路和Two Heap 是一致的。
     * 添加， balance， 然后求中位数。
     *
     */
    public double[] medianSlidingWindow2(int[] nums, int k) {
        // 比较坐标
        Comparator<Integer> comparator = (a, b) -> nums[a] == nums[b] ?
                Integer.compare(nums[a], nums[b]) : a - b;
        TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> right = new TreeSet<>(comparator);

        // 取中位数时，坐标转换为值
        Supplier<Double> median = () -> k % 2 == 0 ?
                nums[left.first()] / 2.0 + nums[right.first()] / 2.0
                : nums[right.first()] * 1.0;

        Runnable balance = () -> {
            while (left.size() > right.size()) {
                right.add(left.pollFirst());
            }
        };
        int len = nums.length - k + 1;
        double[] result = new double[len];
        for (int i = 0; i < k ; i++) left.add(i);
        balance.run();
        result[0] = median.get();

        for (int i = 1; i < len; i++) {
            boolean remove = left.remove(i) || right.remove(i);
            right.add(i + k - 1); // 先添加到右边，然后把右边最小的数 移到左边。这样保证两个tree还是有序的
            left.add(right.pollFirst()); // 保证左边元素大于等于右边
            balance.run();
            result[i] = median.get();
        }
        return result;
    }
}
