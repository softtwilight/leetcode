package datastructure;

import javax.management.loading.ClassLoaderRepository;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Author:   softtwilight
 * Date:     2020/05/27 21:22
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode createByArray(Integer[] input) {
        TreeNode result = new TreeNode(input[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(result);
        int k = 1;
        for (int i = 2; i < input.length; i <<= 1) {
            Queue<TreeNode> layer = new ArrayDeque<>();
            for (int j = 0; j < i; j++) {
                TreeNode child = null;
                if (input[j + k] != null) {
                    child = new TreeNode(input[j + k]);
                }
                if (j % 2 == 0) {
                    TreeNode parent = queue.peek();
                    parent.left = child;
                } else {
                    TreeNode parent = queue.poll();
                    parent.right = child;
                }
                if (child != null)
                    layer.offer(child);
            }
            k += i;
            queue = layer;
        }
        return result;
    }

    public void print() {
        printWithLayer(1);
        System.out.println();
    }

    private void printWithLayer(int i) {
        if (left != null) {
            left.printWithLayer(i + 1);
        }
        System.out.print(i + "--" + val + " ");
        if (right != null) {
            right.printWithLayer(i + 1);
        }
    }

}

