package top_k_elements;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/course-schedule-iii/
 *
 * Author:   softtwilight
 * Date:     2020/06/18 22:23
 */
public class _630_H_Course_Schedule_III {
    private static final _630_H_Course_Schedule_III instance = new _630_H_Course_Schedule_III();

    public static void main(String[] args) {
        int[][] input = {{10,12},{6,15},{1,12},{3,20},{10,19}};
        System.out.println(instance.scheduleCourse(input));
    }

    /**
     * 没有做出来，参考的solution
     *
     * 这个题最关键的一个观察是， 优先排早结束的课程。
     * 然后用最大堆记录已排列的课程。
     * 当time + c[0] <= c[1]， 也就是说当前课程可以排进去的时候， 放入堆， 然后累加时间
     * 当time + c[0] > c[1], 无法直接将当前课程排入， 我们比较当前课程和堆里（也就是以前排好的课程）
     * ，如果queue.peek() > c[0]， 那么我们用当前课程替代该最耗时的课程
     * （因为耗时少， 结束又晚，当前课程就是完全优于被替代的课程的）
     *
     * 最后解答就是queue的size
     */
    public int scheduleCourse1(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue <Integer> queue = new PriorityQueue < > ((a, b) -> b - a);
        int time = 0;
        for (int[] c: courses) {
            if (time + c[0] <= c[1]) {
                queue.offer(c[0]);
                time += c[0];
            } else if (!queue.isEmpty() && queue.peek() > c[0]) {
                time += c[0] - queue.poll();
                queue.offer(c[0]);
            }
        }
        return queue.size();
    }

    /**
     *
     */
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) :
                Integer.compare(b[1], a[1]));

        int result = 0;
        int spent = 0;
        int left = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int i = 0; i < courses.length; i++) {
            int[] cur = courses[i];

            if (cur[1] - cur[0] >= left) {
                heap.offer(cur);
                spent += cur[0];
                result++;
            }
            while (!heap.isEmpty() && spent >= heap.peek()[1]) {
                left += heap.poll()[0];
            }
        }
        return result;
    }
}
