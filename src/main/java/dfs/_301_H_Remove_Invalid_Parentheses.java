package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2021/01/10 20:21
 */
public class _301_H_Remove_Invalid_Parentheses {
    private static final _301_H_Remove_Invalid_Parentheses instance = new _301_H_Remove_Invalid_Parentheses();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 这道题解的点都想到了， 没写
     *
     * 这个答案写得太好了！赞叹
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }
}
