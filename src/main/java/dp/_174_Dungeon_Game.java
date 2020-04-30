package dp;

import java.util.Arrays;

/**
 *
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 *
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 *
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 *
 *
 * Note:
 *
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 * Author:   softtwilight
 * Date:     2020/04/30 21:34
 */
public class _174_Dungeon_Game {



    /**
     * 0 ms	+ 39.3 MB
     * 用递归的方式求解的。
     *
     * 用二维数组memo来记录在每个点所需要的最小的体力数。准确是到达这个点之前所需要的体力数。
     * 因为负体力是不存在的，所以最小体力数为1。
     *
     * 我们可以观察到，在任意一点，可以选择往右和往左走，我们选择所需体力更小的那个。
     * 然后在这一点，有可能遇到魔鬼或者补给，那么如果补给能cover下一步所需的体力，那么这点就是1。
     * 如果不能，我们就需要在体力cover 下一步的体力和当前的消耗。
     *
     * 然后考虑边界条件，只能移动一个方向。
     *
     * 这样我们每一个点的计算都往右或左移动，直到公主的点，也就是右下角的点。
     * 我们可以容易的给出这点所需的体力。作为递归的break点。
     *
     * 因为两个路径的选择，➡⬇ 或 ⬇➡是会经过同一个点的，所以我们用memo保存这个点的结果，避免重复计算。
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        int m = dungeon.length, n = dungeon[0].length;
        Integer[][] memo = new Integer[m][n];
        if (dungeon[m - 1][n - 1] < 0) {
            memo[m - 1][n - 1] = -dungeon[m - 1][n - 1] + 1;
        }  else {
            memo[m - 1][n - 1] = 1;
        }
        return helper(dungeon, memo, 0, 0);
    }

    private int helper(int[][] dungeon, Integer[][] memo, int i, int j) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        int need;
        if (i == dungeon.length - 1) {
            need = helper(dungeon, memo, i, j + 1);
        } else if (j == dungeon[0].length - 1) {
            need = helper(dungeon, memo, i + 1, j);
        } else {
            need = Math.min(helper(dungeon, memo, i + 1, j),
                    helper(dungeon, memo, i, j + 1));
        }
        if (dungeon[i][j] >= need) {
            memo[i][j] = 1;
        } else {
            memo[i][j] = need - dungeon[i][j];
        }
        return memo[i][j];
    }
}
