package bfs;

import datastructure.Node;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 *
 * Author:   softtwilight
 * Date:     2020/05/28 23:37
 */
public class _117_M_Populating_Next_Right_Pointers2 {
    private static final _117_M_Populating_Next_Right_Pointers2 instance = new _117_M_Populating_Next_Right_Pointers2();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 题目是个普通二分树，有可能有残枝
     * 所以想用116中的levelFirst 的解法会有点麻烦，因为levelfirst不一定来自left节点。。
     * 提交了116的connnect2 直接过了 0 ms + 39.3 MB
     *
     * 思路是循环找到下层左边第一个点prev， 然后作为下一层的起始点。
     * 然后在沿着next ，循环找到下层下一个点。连接上prev点和新找的点。
     * 然后新找的点赋值给prev，接着找。新的点可能在同一个cur下，所以当cur.left != null
     * 的时候不能更新cur。
     *
     * 看到有解答用一个dummy来代替第一次寻找prev，代码会简单一些。
     * （当需要考虑初始值和最后一个值的时候就可以考虑使用dummy）
     *
     * 116 117 本质上都是通过next构建了一种访问一层node的机制， 类似放入queue中。
     * 而这个机制是parent构建的。每一层遍历构建一下层的next链。
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Node head = root;
        while (head != null) {
            Node cur = head;
            while (cur != null && cur.left == null && cur.right == null) {
                cur = cur.next;
            }
            if (cur == null) break;
            Node prev;
            head = cur.left != null ? cur.left : cur.right;
            prev = head;
            while (cur != null) {
                if (cur.left != null && cur.left != prev) {
                    prev.next = cur.left;
                    prev = cur.left;
                } else {
                    if (cur.right != null && cur.right != prev) {
                        prev.next = cur.right;
                        prev = cur.right;
                    }
                    cur = cur.next;
                }
            }
        }
        return root;
    }

    public void connect2(Node root) {
        if (root == null) return;
        Node dummy = new Node(0);
        Node p = dummy;
        Node head = root;
        while (head != null) { //if the head of the traverse layer is not null, then traverse that layer...
            Node node = head;
            while (node != null) {
                if (node.left != null) {
                    p.next = node.left;
                    p = p.next;
                }
                if (node.right != null) {
                    p.next = node.right;
                    p = p.next;
                }
                node = node.next;
            }
            //after traversed to the end of current layer, move to the next layer
            head = dummy.next;
            dummy.next = null;
            p = dummy;
        }
    }
}
