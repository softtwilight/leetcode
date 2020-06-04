package dfs;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 *
 * Author:   softtwilight
 * Date:     2020/06/04 23:51
 */
public class _226_E_Invert_Binary_Tree {
    private static final _226_E_Invert_Binary_Tree instance = new _226_E_Invert_Binary_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 0 ms	36.4 MB
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = left;
        return root;

    }
}
