package dfs;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * Author:   softtwilight
 * Date:     2020/06/05 21:58
 */
public class _230_M_Kth_Smallest_Element_in_a_BST {
    private static final _230_M_Kth_Smallest_Element_in_a_BST instance = new _230_M_Kth_Smallest_Element_in_a_BST();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 00 ms	38.9 MB
     * 用类变量不太规范，用一个容器传入递归参数也可以，比如数组
     *
     * 按照inorder 搜索，正好是升序，记录经过多少node就可以了。
     * 如果找到后，index = k + 1， 提前return， 减少递归调用。
     */
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return result;
    }
    int index = 1;
    int result = 0;

    private void dfs(TreeNode root, int k) {
        if (root == null || index > k) return;
        dfs(root.left, k);
        if (index++ == k) {
            result = root.val;
            return;
        }
        dfs(root.right, k);
    }


}
