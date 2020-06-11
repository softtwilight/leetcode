package twoheap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 *
 * Author:   softtwilight
 * Date:     2020/06/11 21:11
 */
public class _295_H_Find_Median_from_Data_Stream {
    private static final _295_H_Find_Median_from_Data_Stream instance = new _295_H_Find_Median_from_Data_Stream();

    public static void main(String[] args) {


        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        medianFinder.addNum(-2);
        medianFinder.addNum(-3);
        medianFinder.addNum(-4);
        medianFinder.addNum(-5);
        System.out.println(medianFinder.findMedian());

    }

    /**
     * 65 ms	61.5 MB
     *
     * 用两个PriorityQueue（heap）来装数据
     * 左边是更小一半（nearly balanced），右边是更大的一半
     * 左边是升序排列，右边是降序排列。
     * 这样求median的时候，我们只需要peek，就可以求出median了
     *
     */
    static class MedianFinder {

        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        /** initialize your data structure here. */
        public MedianFinder() {
            left = new PriorityQueue<>(Comparator.reverseOrder());
            right = new PriorityQueue<>();
        }

        public void addNum(int num) {
            Integer leftMax = left.peek();
            if (leftMax == null || num <= leftMax) {
                left.offer(num);
            } else {
                right.offer(num);
            }
            rebalance();
        }

        private void rebalance() {
            if (left.size() - right.size() == 2) {
                right.offer(left.poll());
            }
            if (right.size() - left.size() == 2) {
                left.offer(right.poll());
            }
        };

        public double findMedian() {
            Integer leftMax = left.peek();
            Integer rightMin = right.peek();
            if (leftMax == null) return 0;
            if (left.size() == right.size()) {
                return (leftMax + rightMin) / 2.0;
            }
            return left.size() > right.size() ? leftMax : rightMin;
        }
    }
}
