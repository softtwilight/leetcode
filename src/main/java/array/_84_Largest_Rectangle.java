package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Given n non-negative integers representing the histogram's bar height
 * where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10
 *
 * Author:   softtwilight
 * Date:     2020/04/20 21:25
 */
public class _84_Largest_Rectangle {

    public static void main(String[] args) {
        _84_Largest_Rectangle instance = new _84_Largest_Rectangle();
        int[] input = {2,1,5,6,2,3};
        int[] input2 = {2,1,2};
        System.out.println(instance.largestRectangleArea_v4(input));
        System.out.println(instance.largestRectangleArea_v4(input2));
    }


    /**
     * 怎么记录需要计算的值呢，如果要循环一次，看起来需要保存的值是无限多的，怎么把这个值降下来。
     * 先用最简单的方法来实现吧，n * n的复杂度。分别向前遍历和向后遍历。 766 ms + 40.5 MB
     * 这里有很多遍历时候多余的，比如说很多点都是1，那么在1的连续区域内，只需要遍历一个1就可以了。
     * 我们先试着优化这一部分。用map保存当前height（k）和 右边第一个比他小的坐标（v）
     * 然后我们在小于v的坐标下，都不再遍历height。
     *
     * 这样我们有了version2，有点一点改观，190 ms + 41.2 MB，还是不够快。
     * 在相同的区间内比height更小的值也是不用遍历的，可是实现会很麻烦，而且这个会有必要吗。
     * 验证的结果是 187 ms  +	40.6 MB，确实没有什么必要。
     *
     * 所以我们还是回到最开始的思路，能不能减少循环数呢？
     * 这里有个很棘手的问题，需要回头计算，比如6 下降到 2的时候，我们要回头去计算2的值
     * 感觉用栈好像可行，在出栈的时候来计算值，遇到递增的数就入栈，变小的数就出栈到栈顶小于当前的数，= 不操作。
     * 可是还不够，同时还需要记录下标。
     *
     * 然后我们有了version4， 结果还算可以， 5 ms  +  40.5 MB， 两个指标都超过了 90%的提交。
     * 具体的思路看v4的注释。
     *
     * 两个小时就这么过去了。。。
     *
     */
    public int largestRectangleArea(int[] heights) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < heights.length; i++) {
            int temp = heights[i];

            for (int j = i + 1; j < heights.length; j++) {
                if (heights[j] >= heights[i]) {
                    temp += heights[i];
                } else {
                    break;
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] >= heights[i]) {
                    temp += heights[i];
                } else {
                    break;
                }
            }
            result = Math.max(temp, result);
        }
        return result;
    }

    /**
     * 更简洁的stack 版本
     */
    public int largestRectangleArea_v5(int[] height) {
        if(height == null || height.length == 0) return 0;
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                // 长方形的面积在于，找到两个index，1是左边第一个比tp下小的index，2是右边第一个比tp小的index
                // width = （rightIndex - leftIndex + 1);
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }

    /**
     * 这个实现的思路大致是这样的，因为我们观察到，只有下降的时候，至少一个范围结束了。
     * 如果上升，这个范围就没结束，所以我们不再一边遍历，一边计算，而是放入stack。
     * 我们保证栈里的数都是小于等于当前height的。
     * 有一个关键点是，出栈后，要把新的height的index，设置为最后一个出栈的index。
     * 这样可以把新的height的起始值推到了第一个比他大的点。
     * 这就是我们之前分析的往前计算，通过设置index的方法，很轻易而不增加复杂度的实现了。
     *
     * ps，抛了一个空stack的异常，注意check栈是否是empty
     * （因为把相等的情况也出栈了，如果不出栈，那么在pop的时候添加一个 ！= 的判断条件也可以）
     *
     * 每一个元素都被入栈，出栈，然后遍历了一次， 时间复杂度是2 * N， 空间复杂度最坏是N（递增的数组）
     *
     *
     */
    public int largestRectangleArea_v4(int[] heights) {
        Stack<int[]> stack = new Stack<>(); // 数组的第一个元素是高度，第二个元素是计算该高度的起始下标。
        stack.push(new int[]{0, 0}); // 添加一个sentiment， 然for循环内简洁一点。
        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] > stack.peek()[0]) { // 升序，入栈。
                stack.push(new int[]{heights[i], i});
            } else if (heights[i] == stack.peek()[0]) {// pass

            } else {
                int[] head = new int[2];
                while (!stack.isEmpty() && heights[i] <= stack.peek()[0]) {
                    head = stack.pop();
                    result = Math.max(result, head[0] * (i - head[1]));
                }
                stack.push(new int[] {heights[i], head[1]}); // 取head[1] 非常关键！
            }
        }
        while (stack.size() > 1) {
            int[] head = stack.pop();
            result = Math.max(result, head[0] * (heights.length - head[1]));
        }
        return result;
    }

    public int largestRectangleArea_v2(int[] heights) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
//        int lastBreak = 0;  //v3
        for (int i = 0; i < heights.length; i++) {
            int temp = heights[i];
            if (map.containsKey(temp) && map.get(temp) > i) continue;
//            if (temp < lastBreak) continue;
            int j;
            for (j = i + 1; j < heights.length; j++) {
                if (heights[j] >= heights[i])  temp += heights[i];
                else break;
            }
//            if (j != heights.length) lastBreak = heights[j];
            map.put(heights[i], j);
            for (j = i - 1; j >= 0; j--) {
                if (heights[j] >= heights[i]) temp += heights[i];
                else break;
            }
            result = Math.max(temp, result);
        }
        return result;
    }
}
