package top_k_elements;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/maximum-frequency-stack/
 *
 * Author:   softtwilight
 * Date:     2020/07/07 21:32
 */
public class _895_H_Maximum_Frequency_Stack {
    private static final _895_H_Maximum_Frequency_Stack instance = new _895_H_Maximum_Frequency_Stack();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 	48 ms  +  84.3 MB
     *
     * 	freq 保存各个数对应的频率
     * 	freqStack 需要保存在各个频率（也就是相同频率）的数之前插入的顺序
     */

    class FreqStack {

        private Map<Integer, Integer> freq = new HashMap<>();
        private Map<Integer, Stack<Integer>> freqStack = new HashMap<>();
        private int maxFreq;
        public FreqStack() {
            maxFreq = 0;
        }

        public void push(int x) {
            int xFreq = freq.getOrDefault(x, 0) + 1;
            freq.put(x, xFreq);
            maxFreq = Math.max(xFreq, maxFreq);
            freqStack.computeIfAbsent(xFreq, i -> new Stack<>()).push(x);
        }

        public int pop() {
            int result = freqStack.get(maxFreq).pop();
            freq.put(result, freq.get(result) - 1);
            if (freqStack.get(maxFreq).isEmpty()) {
                // 因为存在freq 为 7的freqStack， 那么在这之前6的 freStack 一定数存在的
                maxFreq--;
            }
            return result;
        }
    }
}
