package dfs;

import datastructure.TreeNode;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Author:   softtwilight
 * Date:     2020/06/05 22:38
 */
public class _235_E_Lowest_Common_Ancestor_of_BST {
    private static final _235_E_Lowest_Common_Ancestor_of_BST instance = new _235_E_Lowest_Common_Ancestor_of_BST();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 4 ms	39.9 MB
     * 理解错题意了。。。这个是求一般tree的最接近的Ancestor
     *
     * 原题是BST， 可以利用二分查找的方式来搜索。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> path = new LinkedList<>();
        dfs(root, p, q, path);
        int r = Math.min(pParent.size(), qParent.size());
        TreeNode result = null;
        for (int i = 0; i < r; i++) {
            TreeNode n = pParent.pollFirst();
            TreeNode m = qParent.pollFirst();
            if (n == m) result = n;
            else break;
        }
        return result;
    }

    LinkedList<TreeNode> pParent;
    LinkedList<TreeNode> qParent;

    private void dfs(TreeNode root, TreeNode p, TreeNode q, LinkedList<TreeNode> path) {
        if (root == null || (pParent != null && qParent != null)) return;
        path.addLast(root);
        if (root == p) pParent = new LinkedList<>(path);
        if (root == q) qParent = new LinkedList<>(path);
        dfs(root.left, p, q, path);
        dfs(root.right, p, q, path);
        path.removeLast();
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        int rv = root.val, pv = p.val, qv = q.val;
        if (pv < rv && qv < rv) {
            return lowestCommonAncestor2(root.left, p, q);
        } else if (pv > rv && qv > rv) {
            return lowestCommonAncestor2(root.right, p, q);
        }
        return root;
    }

}
