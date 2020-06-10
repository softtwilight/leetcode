package dfs;

import datastructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 *
 * Author:   softtwilight
 * Date:     2020/06/10 22:28
 */
public class _662_M_Maximum_Width_of_Binary_Tree {
    private static final _662_M_Maximum_Width_of_Binary_Tree instance = new _662_M_Maximum_Width_of_Binary_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 2 ms	39.7 MB
     *
     * 这里也可以不用map， 用list， 然后通过size 和 depth来判断是否是第一个元素。
     *
     * result的初始值为1。
     */
    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 0, 0);
        return result;
    }

    Map<Integer, Integer> layerFirst = new HashMap<>();
    int d = -1;
    int result = 1;
    private void dfs(TreeNode root, int i, int depth) {
        if (root == null) return;
        if (depth > d) {
            d = depth;
            layerFirst.put(depth, i);
        } else {
            result = Math.max(result, i - layerFirst.get(depth) + 1);
        }
        dfs(root.left, i << 1, depth + 1);
        dfs(root.right, (i << 1) + 1, depth + 1);
    }


}
