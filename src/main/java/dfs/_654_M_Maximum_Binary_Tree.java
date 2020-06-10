package dfs;

import datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/maximum-binary-tree/
 *
 * Author:   softtwilight
 * Date:     2020/06/10 21:29
 */
public class _654_M_Maximum_Binary_Tree {
    private static final _654_M_Maximum_Binary_Tree instance = new _654_M_Maximum_Binary_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 4 ms	52 MB
     *
     * 最坏的时间复杂度为O（n^2)
     * 有一个寻找max的过程
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        int max = lo;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        TreeNode root = new TreeNode(nums[max]);
        root.left = dfs(nums, lo, max - 1);
        root.right = dfs(nums, max + 1, hi);
        return root;
    }


    // 基于queue的O（N）的复杂读，参考leetcode
    // 实际上并不喜欢这个方法， 太tricky了
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++) {
            TreeNode curr = new TreeNode(nums[i]);
            while(!stack.isEmpty() && stack.peek().val < nums[i]) {
                curr.left = stack.pop();
            }
            if(!stack.isEmpty()) {
                stack.peek().right = curr;
            }
            stack.push(curr);
        }

        return stack.isEmpty() ? null : stack.removeLast();
    }

}
