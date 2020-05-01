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
     * 感觉动规的一般思路是，把求解的问题转换为子问题的解， 子问题的结构跟当前问题是同结构的（可以利用递归）
     * 这个转换过程一定要是收敛的（递归有break点），一直收敛到答案显而易见为止。这个就保证了问题可解。
     * 然后还要保证子问题是重复的，这样可以利用cache将子问题答案记录下来，避免重复计算。
     *
     * 对我来说用递归的方法思考更直观的，一般还要再优化的话需要反向来建立memo，用循环的方式，
     * 比如从右下角的公主点出发，然后慢慢回到右上角。这个过程少了递归方法调用的栈空间开支（如果没有尾递归优化）
     * 应该是可以节省一点内存空间的。
     *
     * 这样做之后，就可能会看到需要记住的子问题的解不是无数个，是最近几个的， 这个时候就可以优化
     * cache的空间复杂度了。
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
        int[][] memo = new int[m][n];
        if (dungeon[m - 1][n - 1] < 0) {
            memo[m - 1][n - 1] = -dungeon[m - 1][n - 1] + 1;
        }  else {
            memo[m - 1][n - 1] = 1;
        }
        return helper(dungeon, memo, 0, 0);
    }

    private int helper(int[][] dungeon, int[][] memo, int i, int j) {
        if (memo[i][j] != 0) {
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
