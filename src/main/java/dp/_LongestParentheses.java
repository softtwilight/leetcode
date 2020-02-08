package dp;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * @author softtwilight 2019/11/28
 */
public class _LongestParentheses {

    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] cs = s.toCharArray();
        Stack<Integer> stack = new Stack<>();

        int maxSize = 0;
        stack.push(-1);
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxSize = Math.max(maxSize, i - stack.peek());
                }
            }
        }
        return maxSize;

    }

}
