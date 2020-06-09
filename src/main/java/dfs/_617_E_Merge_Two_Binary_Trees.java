package dfs;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/
 *
 * Author:   softtwilight
 * Date:     2020/06/09 23:42
 */
public class _617_E_Merge_Two_Binary_Trees {
    private static final _617_E_Merge_Two_Binary_Trees instance = new _617_E_Merge_Two_Binary_Trees();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 0 ms	48.5 MB
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
