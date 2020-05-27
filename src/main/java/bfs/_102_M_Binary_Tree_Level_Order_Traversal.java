package bfs;

import datastructure.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * Author:   softtwilight
 * Date:     2020/05/27 21:25
 */
public class _102_M_Binary_Tree_Level_Order_Traversal {
    private static final _102_M_Binary_Tree_Level_Order_Traversal instance = new _102_M_Binary_Tree_Level_Order_Traversal();

    public static void main(String[] args) {
        Integer[] array = new Integer[] {3,9,20,null,null,15,7};
        TreeNode input = TreeNode.createByArray(array);
        input.print();
        List<List<Integer>> result = instance.levelOrder3(input);
        System.out.println(result);
    }

    /**
     * 递归版本
     * 也就是DFS版本
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> re = new ArrayList<>();
        helper(root, 0, re);
        return re;
    }


    private void helper(TreeNode root, int layer, List<List<Integer>> re) {
        if (root == null) return;
        if (re.size() <= layer) {
            re.add(new ArrayList<>());
        }
        re.get(layer).add(root.val);
        helper(root.left, layer + 1, re);
        helper(root.right, layer + 1, re);
    }


    /**
     * 1 ms	39.5 MB
     * 思路是一层tree， 用queue来表示。
     * 从queue poll之后， 将node的子node放入queue。
     *
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {

            // 可以通过size来决定queue的循环次数，减少创建queue的内存消耗
            Queue<TreeNode> layerQueue = new ArrayDeque<>();
            List<Integer> layerList = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                layerList.add(node.val);
                if (node.left != null) {
                    layerQueue.offer(node.left);
                }
                if (node.right != null) {
                    layerQueue.offer(node.right);
                }
            }
            queue = layerQueue;
            result.add(layerList);
        }
        return result;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> layerList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                layerList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(layerList);
        }
        return result;
    }
}
