package bfs;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 *
 * Author:   softtwilight
 * Date:     2020/05/30 21:52
 */
public class _863_M_All_Nodes_Distance_K_in_Binary_Tree {
    private static final _863_M_All_Nodes_Distance_K_in_Binary_Tree instance = new _863_M_All_Nodes_Distance_K_in_Binary_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 	15 ms  + 40.2 MB
     *
     * 	DFS
     *  求target的子距离
     *  然后所有target的parent的节点另一个分支上的子距离为（ k - 2）的点。
     *  每上移动一个parent点， k - 1
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        findChild(target, K, result);
        findParent(root, target, K, result);
        return result;
    }

    private int  findParent(TreeNode root, TreeNode target, int k, List<Integer> result) {

        if (root == null) return -1;
        if (root.val == target.val) {
            return k - 1;
        }

        int re = findParent(root.left, target, k, result);
        if (re >= 0) {
            if (k == 0) {
                result.add(root.val);
            } else {
                findChild(root.right, re - 1, result);
            }
            return re - 1;
        }

        re = findParent(root.right, target, k, result);
        if (re >= 0) {
            if (k == 0) {
                result.add(root.val);
            } else {
                findChild(root.left, re - 1, result);
            }
            return re - 1;
        }
        return -1;
    }


    private void findChild(TreeNode target, int k, List<Integer> result) {
        if (target == null) return;
        if (k == 0) result.add(target.val);
        findChild(target.left, k - 1, result);
        findChild(target.right, k - 1, result);
    }
}
