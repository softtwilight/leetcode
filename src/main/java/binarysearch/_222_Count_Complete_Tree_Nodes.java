package binarysearch;

/**
 *
 Given a complete binary tree, count the number of nodes.

 Note:

 Definition of a complete binary tree from Wikipedia:
 In a complete binary tree every level, except possibly the last, is completely filled,
 and all nodes in the last level are as far left as possible.
 It can have between 1 and 2h nodes inclusive at the last level h.

 Example:

 Input:
 1
 / \
 2   3
 / \  /
 4  5 6

 Output: 6
 *
 * Author:   softtwilight
 * Date:     2020/05/04 22:00
 */
public class _222_Count_Complete_Tree_Nodes {


    /**
     * 0 ms	+ 41.9 MB
     * 一个非常简单的递归题，我也不是很懂为什么是中等难度。
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
