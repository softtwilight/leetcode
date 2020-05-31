package dfs;

import datastructure.TreeNode;

import javax.xml.stream.events.EntityReference;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Author:   softtwilight
 * Date:     2020/05/31 21:33
 */
public class _98_M_Validate_Binary_Search_Tree {
    private static final _98_M_Validate_Binary_Search_Tree instance = new _98_M_Validate_Binary_Search_Tree();

    public static void main(String[] args) {
        TreeNode input = TreeNode.createByArray(new Integer[]{10,5,15,null,null,6,20});
        System.out.println(instance.isValidBST(input));
    }

    /**
     * 0 ms	39.2 MB
     *
     * 实际上 isValidBST里的判断可以不用， 直接用root去调用dfs会更简单一点。
     * 要记录一个root的取值范围， 判断是否是valid的，
     * 比如在10 的所有右边结点， min = 10，左边结点max = 10。
     * 然后递归更改范围。
     *
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, Long.MIN_VALUE, root.val) && dfs(root.right, root.val, Long.MAX_VALUE);
    }

    public boolean dfs(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }


}
