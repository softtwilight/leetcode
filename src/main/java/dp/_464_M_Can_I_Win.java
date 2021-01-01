package dp;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:   softtwilight
 * Date:     2021/01/01 20:56
 *
 * https://leetcode.com/problems/can-i-win/
 */
public class _464_M_Can_I_Win {
    private static final _464_M_Can_I_Win instance = new _464_M_Can_I_Win();

    public static void main(String[] args) {
        System.out.println(instance.canIWin(5, 50));
    }

    /**
     * 将Array string 化来保存状态
     * desiredTotal array的状态推断出来， 所以只有一个变量， 一维数组就可以了
     *
     * 因为maxChoosableInteger <= 20, 所以也可以用个integer来作为key， 用bit位来表示是否选了
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

        if (desiredTotal <= maxChoosableInteger)
            return true;
        if (((1 + maxChoosableInteger) / 2 * maxChoosableInteger) < desiredTotal) {
            return false;
        }
        int[] choosable = new int[maxChoosableInteger + 1];
        memo = new HashMap<>();
        return dp(choosable, desiredTotal);
    }

    private Map<String, Boolean> memo;
    private boolean dp(int[] choosable, int desiredTotal) {

        String chosenSerialization = Arrays.toString(choosable);
        if (memo.containsKey(chosenSerialization)) return memo.get(chosenSerialization);
        for (int i = choosable.length - 1; i > 0; i--) {
            if (choosable[i] == 0) {
                if (i >= desiredTotal) {
                    return true;
                }
                choosable[i] = 1;
                boolean sub = dp(choosable, desiredTotal - i);
                choosable[i] = 0;
                if (!sub) {
                    memo.put(chosenSerialization, true);
                    return true;
                }

            }
        }
        memo.put(chosenSerialization, false);
        return false;
    }
}
