package mit_6006;

import datastructure.TreeNode;

import javax.swing.*;
import java.util.TreeSet;

/**
 * Author:   softtwilight
 * Date:     2020/10/26 22:13
 *
 * https://leetcode.com/problems/delete-node-in-a-bst/
 */
public class _450_M_Delete_Node_in_a_BST {
    private static final _450_M_Delete_Node_in_a_BST instance = new _450_M_Delete_Node_in_a_BST();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     *
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // 也可以找 right min
            // max left 可以确定至少有一个child是null， 删除它是上面if else的条件， 会比较简单。
            // 所以替换值， 然后删掉比较容易的。
            // 如果是真的删除节点， 感觉会移动很多节点。
            TreeNode maxLeft = getMax(root.left);
            root.val = maxLeft.val;

            root.left = deleteNode(root.left, root.val);

        } else if (root.val > key) {
            // 这是递归的关键，重新赋值left节点
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    private TreeNode getMax(TreeNode root) {
        TreeNode result = root;
        while (result.right != null) {
            result = result.right;
        }
        return result;
    }


}
