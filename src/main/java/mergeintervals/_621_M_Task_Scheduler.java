package mergeintervals;

import java.util.*;

/**
 * https://leetcode.com/problems/task-scheduler/
 * Author:   softtwilight
 * Date:     2020/05/22 22:49
 */
public class _621_M_Task_Scheduler {
    private static final _621_M_Task_Scheduler instance = new _621_M_Task_Scheduler();

    public static void main(String[] args) {

        System.out.println(instance.leastInterval(new char[]{'A', 'B', 'A', 'B'}, 2));
    }


    /**
     * 消耗的时间 = 任务数 + idle的数目
     * solution里的两张图，可以很清晰的明白怎么计算空闲的数目。
     * 我们找到需要执行最多的m个任务，需要执行k次。
     * 要执行k轮， （k - 1) ( n - (m - 1)) 个空格
     * 还剩下tasks.length - m * k 个任务
     * 所以可以计算处idle，最小为0
     *
     *
     *
     */
    public int leastInterval3(char[] tasks, int n) {
        int[] counter = new int[26];
        int max = 0;
        int maxCount = 0;
        for(char task : tasks) {
            counter[task - 'A']++;
            if(max == counter[task - 'A']) {
                maxCount++;
            }
            else if(max < counter[task - 'A']) {
                max = counter[task - 'A'];
                maxCount = 1;
            }
        }

        int partCount = max - 1;
        int partLength = n - (maxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - max * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + idles;
    }
    /**
     * 36 ms  +	40.6 MB
     * 利用Priority Queue
     * 也就是说优先安排最多的任务，在一轮之中，只安排一次任务，所以安排过的任务用temp保存起来
     * 结束一个周期（n + 1 个任务后）将temp之后重新入queue。
     * 直到队列任务消费结束。（值为0标志消耗结束了，poll from queue）
     */
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        PriorityQueue< Integer > queue = new PriorityQueue <> (26, Collections.reverseOrder());
        for (int f: map) {
            if (f > 0)
                queue.add(f);
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List<Integer> temp = new ArrayList<>();
            while (i <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1)
                        temp.add(queue.poll() - 1);
                    else
                        queue.poll();
                }
                time++;

                // 队列中空了 同时temp里消费过的也为空，证明任务执行完了
                if (queue.isEmpty() && temp.size() == 0)
                    break;
                i++;
            }
            for (int l: temp)
                queue.add(l);
        }
        return time;
    }


    /**
     * 思想同priority queue， 用数组实现。
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }

}
