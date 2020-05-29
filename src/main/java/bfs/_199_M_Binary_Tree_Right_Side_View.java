package bfs;

import datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * Author:   softtwilight
 * Date:     2020/05/29 21:38
 */
public class _199_M_Binary_Tree_Right_Side_View {
    private static final _199_M_Binary_Tree_Right_Side_View instance = new _199_M_Binary_Tree_Right_Side_View();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * BFS
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) result.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }

    /**
     * 递归， 从右边开始递归， 刚好是从右边看到的数
     * DFS
     */
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        helper(root, result, 0);
        return result;
    }

    private void helper(TreeNode root, List<Integer> re, int h) {
        if (root == null) return;
        if (re.size() == h) re.add(root.val);
        helper(root.right, re, h + 1);
        helper(root.left, re, h + 1);
    }
}
