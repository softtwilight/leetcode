package dfs;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/same-tree/
 *
 * Author:   softtwilight
 * Date:     2020/05/31 22:33
 */
public class _100_E_Same_Tree {
    private static final _100_E_Same_Tree instance = new _100_E_Same_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 	0 ms	37.1 MB
     * 	很简单的递归
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) return q == null;
        if (q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
