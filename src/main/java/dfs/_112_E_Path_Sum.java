package dfs;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/path-sum/
 *
 * Author:   softtwilight
 * Date:     2020/06/03 0:21
 */
public class _112_E_Path_Sum {
    private static final _112_E_Path_Sum instance = new _112_E_Path_Sum();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 0 ms	39.2 MB
     * 太轻视Easy难度了，错了好多次
     * [1, 2] [1] 这两个测试case依次错了
     *
     * a root-to-leaf path， 如果某一节点有leaf存在，另一leaf为null， 那么只有一条root-to-leaf的path
     * 只有node的left 和 right同时为null的时候为leaf
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

}
