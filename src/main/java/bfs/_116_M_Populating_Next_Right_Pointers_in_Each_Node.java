package bfs;

import datastructure.Node;

import java.util.*;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 *
 * Author:   softtwilight
 * Date:     2020/05/28 21:56
 */
public class _116_M_Populating_Next_Right_Pointers_in_Each_Node {
    private static final _116_M_Populating_Next_Right_Pointers_in_Each_Node instance = new _116_M_Populating_Next_Right_Pointers_in_Each_Node();

    public static void main(String[] args) {
        System.out.println(instance);
    }



    /**
     * parent node 可以直接让left -> right
     * 然后不共有一个parent的node， 其parent一定在一条next链上，
     * 我们可以通过next来建立关系。
     * 画个图就比较容易想到了。
     *
     * 常数空间，不同于普通的bfs， 所以需要我们利用不同的地方，也就是next指针。
     */
    public Node connect4(Node root) {
        Node levelStart = root;
        while (levelStart != null) {
            Node cur = levelStart;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                    if (cur.next != null) {
                        cur.right.next = cur.next.left;
                    }
                }
                cur = cur.next;
            }
            levelStart = levelStart.left;
        }
        return root;
    }


    /**
     * 2 ms	39.6 MB
     * 空间复杂度为 O(1)
     */
    public Node connect3(Node root) {
        connectChild(root.left, root.right);
        return root;
    }

    private void connectChild(Node left, Node right) {
        if (left == null) return;
        left.next = right;
        connectChild(left.left, left.right);
        if (right == null) return;
        connectChild(left.right, right.left);
        connectChild(right.left, right.right);
    }


    /**
     * 0 ms	39.6 MB
     * 空间复杂度为O（lgN），但是题目要求的是常数的复杂度。
     */
    public Node connect2(Node root) {
        List<Node> leftNode = new ArrayList<>();
        helper(root, 0, leftNode);
        return root;
    }

    private void helper(Node root, int layer, List<Node> leftNode) {
        if (root == null) return;
        if (leftNode.size() <= layer) {
            leftNode.add(root);
        } else {
            Node le = leftNode.get(layer);
            le.next = root;
            leftNode.set(layer, root);
        }
        helper(root.left, layer + 1, leftNode);
        helper(root.right, layer + 1, leftNode);
    }


    /**
     * 2 ms	39.8 MB
     * 空间复杂度为O（N）
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node last = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (last != null) {
                    last.next = node;
                }
                last = node;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }




}
