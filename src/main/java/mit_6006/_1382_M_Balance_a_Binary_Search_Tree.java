package mit_6006;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/10/27 23:51
 *
 * https://leetcode.com/problems/balance-a-binary-search-tree/
 */
public class _1382_M_Balance_a_Binary_Search_Tree {
    private static final _1382_M_Balance_a_Binary_Search_Tree instance = new _1382_M_Balance_a_Binary_Search_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 感觉这很AVL也没什么关系。 O（n）的复杂度。
     */
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortedNode = new ArrayList<>();
        inOrder(root, sortedNode);
        return buildBalanceTree(sortedNode, 0, sortedNode.size() - 1);
    }

    private TreeNode buildBalanceTree(List<Integer> sortedNode, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        if (lo == hi) {
            return new TreeNode(sortedNode.get(lo));
        }
        int mid = (hi - lo) / 2 + lo;
        TreeNode re = new TreeNode(sortedNode.get(mid));
        re.left = buildBalanceTree(sortedNode, lo, mid - 1);
        re.right = buildBalanceTree(sortedNode, mid + 1, hi);
        return re;
    }

    private void inOrder(TreeNode root, List<Integer> sortedNode) {
        if (root == null) return;
        inOrder(root.left, sortedNode);
        sortedNode.add(root.val);
        inOrder(root.right, sortedNode);
    }


}
