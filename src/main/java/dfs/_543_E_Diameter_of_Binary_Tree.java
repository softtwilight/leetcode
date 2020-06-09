package dfs;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * Author:   softtwilight
 * Date:     2020/06/09 22:49
 */
public class _543_E_Diameter_of_Binary_Tree {
    private static final _543_E_Diameter_of_Binary_Tree instance = new _543_E_Diameter_of_Binary_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 0 ms	40.7 MB
     *
     * 主要是递归的方法， 求node到leaf的最长深度， 为Max（left， right） + 1
     * 如果node是leaf -> root -> leaf 的root点， 记录result值
     */
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return result;
    }
    int result = 0;

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        result = Math.max(result, left + right );
        return Math.max(left, right) + 1;
    }


}
