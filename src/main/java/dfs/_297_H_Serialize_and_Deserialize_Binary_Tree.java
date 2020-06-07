package dfs;

import datastructure.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * Author:   softtwilight
 * Date:     2020/06/07 23:15
 */
public class _297_H_Serialize_and_Deserialize_Binary_Tree {
    private static final _297_H_Serialize_and_Deserialize_Binary_Tree instance = new _297_H_Serialize_and_Deserialize_Binary_Tree();

    public static void main(String[] args) {
        TreeNode input = TreeNode.createByArray(new Integer[] {1,2,3,null,null,4,5});

        String str = instance.serialize(input);
        System.out.println(str);
        TreeNode output = instance.deserialize(str);
        output.print();
    }

    /**
     * 14 ms  +	41 MB
     *
     * serialize的时候利用inorder
     */
    public String serialize(TreeNode root) {
        StringJoiner sj = new StringJoiner(",");
        serialize(root, sj);
        return sj.toString();
    }

    private void serialize(TreeNode root, StringJoiner joiner) {
        if (root == null) {
            joiner.add("#");
            return;
        }
        joiner.add(String.valueOf(root.val));
        serialize(root.left, joiner);
        serialize(root.right, joiner);
    }


    /**
     * deserialize inorder 序列化的node，我们用stack 来存储作为root的节点
     * 新节点，添加到stack顶的Node 的left 或 right。
     * 因为是inorder， 先遍历左边， 我们用一个Map来标记一个节点的左节点是否已经填充。
     * 如果在填充了right后，我们将parent 出栈。
     *
     * 然后新节点添加到parent的 子节点后，如果不为null， 就把新的节点也入栈。
     *
     * 这个方法纠结的地方在于ser 和 deser 的逻辑看起来不连贯
     */
    public TreeNode deserialize(String data) {
        if (!data.contains(",")) {
            return null;
        }
        String[] arr = data.split(",");
        TreeNode result = new TreeNode(Integer.valueOf(arr[0]));
        Stack<TreeNode> rootStack = new Stack<>();
        Set<TreeNode> accessed = new HashSet<>();
        rootStack.add(result);
        for (int i = 1; i < arr.length; i++) {
            String cur = arr[i];
            TreeNode parent = rootStack.peek();
            TreeNode child = "#".equals(cur) ? null : new TreeNode(Integer.valueOf(cur));
            if (accessed.contains(parent)) {
                parent.right = child;
                accessed.remove(parent);
                rootStack.pop();
            } else {
                parent.left = child;
                accessed.add(parent);
            }
            if (child != null) {
                rootStack.add(child);
            }
        }
        return result;
    }

    // 递归版本
    // 基于栈的实现感觉都可以转换为用递归实现，因为递归本身也是方法调用的栈
    // 简单就是beauty
    public TreeNode deserialize2(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split("，")));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals("#")) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}
