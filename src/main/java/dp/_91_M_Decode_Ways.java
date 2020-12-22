package dp;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2020/12/22 21:30
 *
 * https://leetcode.com/problems/decode-ways/
 */
public class _91_M_Decode_Ways {
    private static final _91_M_Decode_Ways instance = new _91_M_Decode_Ways();

    public static void main(String[] args) {
        String input = "206";
        System.out.println(instance.numDecodings(input));
    }

    /**
     * bottom-up solution
     */
    public int numDecodings(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dp(s, 0, memo);
    }

    private int dp(String s, int idx, int[] dp){
        if(s.length()<= idx) return 1;

        char c = s.charAt(idx);
        if(c=='0') return 0;
        if(dp[idx]!= -1) return dp[idx];
        if(c == '1'){
            if(idx + 1 <s.length()){
                dp[idx] = dp(s,idx+1, dp) + dp(s,idx+2, dp);
            } else {
                dp[idx] = dp(s,idx+1, dp);
            }
        } else if (c == '2') {
            if(idx + 1 < s.length() && s.charAt(idx + 1) >= '0' && s.charAt(idx + 1) <= '6'){
                dp[idx] = dp(s,idx+1, dp) + dp(s,idx+2, dp);
            } else {
                dp[idx] = dp(s,idx+1, dp);
            }
        } else {
            dp[idx] = dp(s,idx+1, dp);
        }
        return dp[idx];
    }
}
