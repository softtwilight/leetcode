package dfs;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * Author:   softtwilight
 * Date:     2020/06/03 21:41
 */
public class _124_H_Binary_Tree_Maximum_Path_Sum {
    private static final _124_H_Binary_Tree_Maximum_Path_Sum instance = new _124_H_Binary_Tree_Maximum_Path_Sum();

    public static void main(String[] args) {
        TreeNode input = TreeNode.createByArray(new Integer[] {-10,9,20,null,null,15,7});
        input.print();
        System.out.println(instance.maxPathSum(input));
    }



    /**
     * 0 ms	41.4 MB
     * 只有一个节点能拥有两个branch，其子节点都只能有一个branch
     * 所以我们记录max的时候，用left + right + val
     * 但是作为返回值，作为子branch时，我们只能选择更大的一个分支。
     *
     */
    public int maxPathSum(TreeNode root) {
        helper(root);
        return result;
    }

    int result = Integer.MIN_VALUE;

    int helper(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);
        result = Math.max(left + right + root.val, result);
        return root.val + Math.max(left, right);
    }


}
