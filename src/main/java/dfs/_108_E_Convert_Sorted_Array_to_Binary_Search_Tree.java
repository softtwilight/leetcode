package dfs;

import datastructure.TreeNode;

/**
 * Author:   softtwilight
 * Date:     2020/12/16 22:01
 *
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class _108_E_Convert_Sorted_Array_to_Binary_Search_Tree {
    private static final _108_E_Convert_Sorted_Array_to_Binary_Search_Tree instance = new _108_E_Convert_Sorted_Array_to_Binary_Search_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     *
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    TreeNode dfs(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        int mid = (hi - lo) / 2 + lo;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, lo, mid - 1);
        root.right = dfs(nums, mid + 1, hi);
        return root;
    }
}
