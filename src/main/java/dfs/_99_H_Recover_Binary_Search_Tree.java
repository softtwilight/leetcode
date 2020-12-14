package dfs;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:   softtwilight
 * Date:     2020/12/14 21:39
 *
 * https://leetcode.com/problems/recover-binary-search-tree/
 */
public class _99_H_Recover_Binary_Search_Tree {
    private static final _99_H_Recover_Binary_Search_Tree instance = new _99_H_Recover_Binary_Search_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * inOrder 遍历是排序的，所以相当与在排序的数组中找到交换位置的两个数。
     * 怎么判断没有深想，直接排序的方法先解出来。
     */
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inOrderTrans(root, list);
        List<Integer> sorted = list.stream().map(treeNode -> treeNode.val)
                .sorted()
                .collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).val = sorted.get(i);
        }
    }

    private void inOrderTrans(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        inOrderTrans(root.left, list);
        list.add(root);
        inOrderTrans(root.right, list);
    }


}
