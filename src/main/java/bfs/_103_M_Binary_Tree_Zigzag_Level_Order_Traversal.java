package bfs;

import datastructure.TreeNode;
import jdk.swing.interop.DropTargetContextWrapper;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * Author:   softtwilight
 * Date:     2020/05/27 22:21
 */
public class _103_M_Binary_Tree_Zigzag_Level_Order_Traversal {
    private static final _103_M_Binary_Tree_Zigzag_Level_Order_Traversal instance = new _103_M_Binary_Tree_Zigzag_Level_Order_Traversal();

    public static void main(String[] args) {
        Integer[] array = new Integer[] {3,9,20,null,null,15,7};
        TreeNode input = TreeNode.createByArray(array);
        input.print();
        List<List<Integer>> result = instance.zigzagLevelOrder2(input);
        System.out.println(result);
    }

    /**
     * 0 ms	38.1 MB
     * 和102类似
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(root, 0, result);
        return result;
    }

    private void helper(TreeNode root, int layer, List<List<Integer>> result) {
        if (root == null) return;
        if (result.size() == layer) {
            result.add(new LinkedList<>());
        }
        LinkedList li = (LinkedList)result.get(layer);
        if (layer % 2 == 0) {
            li.addLast(root.val);
        } else {
            li.addFirst(root.val);
        }
        helper(root.left, layer + 1, result);
        helper(root.right, layer + 1, result);
    }


    /**
     * 1 ms	38.1 MB
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean odd = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> layer = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (odd) {
                    layer.addLast(node.val);
                } else {
                    layer.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(layer);
            odd = !odd;
        }
        return result;
    }



}
