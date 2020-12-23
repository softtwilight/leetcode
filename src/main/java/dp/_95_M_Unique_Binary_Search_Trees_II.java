package dp;

import datastructure.ListNode;
import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/12/23 21:49
 *
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 */
public class _95_M_Unique_Binary_Search_Trees_II {
    private static final _95_M_Unique_Binary_Search_Trees_II instance = new _95_M_Unique_Binary_Search_Trees_II();

    public static void main(String[] args) {
        instance.generateTrees(9).forEach(treeNode -> treeNode.print());
    }

    /**
     * Same as substring DP, the sub tree in range from low to high
     */
    public List<TreeNode> generateTrees(int n) {
        // the corner case.
        // the n = 0 doesn't make sense, but in the interview, I should ask how to handle this error input.
        if (n == 0) return new ArrayList<>();
        List<TreeNode>[][] memo = new List[n][n];
        return dp(1, n, memo);
    }

    private List<TreeNode> dp(int low, int high, List<TreeNode>[][] memo) {
        List<TreeNode> result = new ArrayList<>();
        if (low > high) {
            result.add(null);
            return result;
        }

        if (low == high) {
            result.add(new TreeNode(low));
            return result;
        }

        if (memo[low - 1][high - 1] != null) {
            return memo[low - 1][high - 1];
        }

        for (int i = low; i <= high; i++) {
            List<TreeNode> lefts = dp(low, i - 1, memo);
            List<TreeNode> rights = dp(i + 1, high, memo);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        memo[low - 1][high - 1] = result;
        return result;
    }
}
