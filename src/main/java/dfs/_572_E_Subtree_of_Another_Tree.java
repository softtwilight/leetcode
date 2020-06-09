package dfs;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 *
 * Author:   softtwilight
 * Date:     2020/06/09 23:08
 */
public class _572_E_Subtree_of_Another_Tree {
    private static final _572_E_Subtree_of_Another_Tree instance = new _572_E_Subtree_of_Another_Tree();

    public static void main(String[] args) {
        TreeNode s = TreeNode.createByArray(new Integer[] {3,4,5,1,null,null, 2});
        TreeNode t = TreeNode.createByArray(new Integer[] {3, 1, 2});
        System.out.println(instance.isSubtree(s, t));
    }

    /**
     * 6 ms	44.5 MB
     * 感觉没什么好讲的，代码就是逻辑了
     *
     * main里测试case开始几次没通过，因为想用一个函数解决
     * 但是后来发现equal的逻辑和 isSubtree是不一样的。
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return t == null;
        if (t == null) return false;
        return equal(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }


    private boolean equal(TreeNode s, TreeNode t) {
        if (s == null) return t == null;
        if (t == null) return false;
        return s.val == t.val && equal(s.left, t.left) && equal(s.right, t.right);
    }
}
