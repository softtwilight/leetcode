package twoheap;

import java.util.*;

/**
 * https://leetcode.com/problems/ipo/
 *
 * Author:   softtwilight
 * Date:     2020/06/13 22:33
 */
public class _502_H_IPO {
    private static final _502_H_IPO instance = new _502_H_IPO();

    public static void main(String[] args) {

        int k = 2;
        int W = 0;
        int[] profits = {1, 2, 3};
        int[] captials = {0, 1, 1};

        System.out.println(instance.findMaximizedCapital2(k, W, profits, captials));
    }

    /**
     * 67 ms  +	53.9 MB
     *
     * 思路来自discuss，这个方法虽然慢， 但是思路非常棒
     * （叫costQueue好像不太对， 其实是启动资金， 不是成本
     * 讲成本低于现有capital W的数组都放入到收益queue中，
     * 然后取最大的一个。
     * 直到k结束或者项目结束。
     *
     * 提交里最快的解法是在暴力解的基础上优化了一下的，
     *
     */

    public int findMaximizedCapital2(int k, int W, int[] Profits, int[] Capital) {

        PriorityQueue<int[]> costQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> profitQueue = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for (int i = 0; i < Profits.length; i++) {
            costQueue.add(new int[]{Capital[i], Profits[i]});
        }
        while (k-- > 0) {
            while (!costQueue.isEmpty() && costQueue.peek()[0] <= W) {
                profitQueue.offer(costQueue.poll());
            }
            if (profitQueue.isEmpty()) break;
            W += profitQueue.poll()[1];
        }
        return W;
    }

    /**
     * 找 i 符合条件Capital[i] <= W, Profits[i] max;
     * Profits[i] 必须大于 Capital[i]；
     * W = W - Capital[i] + Profits[i]；
     * 移除Capital[i] 和 Profits[i]；
     * k--;
     *
     * 81 ms  +	57.7 MB
     * 利用Tree排序， 按照收益从大到下查找
     * 最开始用stream 写， 删除的时候需要再次搜索一次。
     *
     * iterator 则不需要， 重写之后
     * 	20 ms  +  47.1 MB
     * 复杂度是O （n * log n + k * n）
     * 这个其实只是优化了一下暴力求解，如果是完全阶梯的input，就是暴力解了， 总要遍历到最后一个node
     * 像这样的输入
     * （1 1 1  ， 0 1 2  k = 3， W = 0）
     */
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {

        Comparator<int[]> comparator = (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return b[2] - a[2];
        };

        TreeSet<int[]> tree = new TreeSet<>(comparator);
        for (int i = 0; i < Profits.length ; i++) {
            int profit = Profits[i];
            int cost = Capital[i];
            tree.add(new int[] {profit, cost, i});
        }

        while (k-- > 0 && W >= 0) {
            Iterator<int[]> it = tree.iterator();
            while (it.hasNext()) {
                int[] max = it.next();
                if (max[1] <= W) {
                    W = W + max[0];
                    it.remove();
                    break;
                }
            }
        }
        return W;
    }


}
