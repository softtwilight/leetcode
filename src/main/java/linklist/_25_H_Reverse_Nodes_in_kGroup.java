package linklist;

import datastructure.ListNode;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * Author:   softtwilight
 * Date:     2020/05/25 22:33
 */
public class _25_H_Reverse_Nodes_in_kGroup {
    private static final _25_H_Reverse_Nodes_in_kGroup instance = new _25_H_Reverse_Nodes_in_kGroup();

    public static void main(String[] args) {
        ListNode input = ListNode.createByArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        input.print();
        ListNode result = instance.reverseKGroup(input, 3);
        result.print();
    }

    /**
     * 0 ms + 39.6 MB
     * 感觉这种题一定要画图，然后搞清楚指针变换和边界情况
     * 不画图的指针操作可能连代码也不好看懂。
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (hasNextK(head, k)) {
            int i = k;
            while (--i > 0) {
                ListNode tmp = head.next.next;
                head.next.next = cur.next;
                cur.next = head.next;
                head.next = tmp;
            }
            cur = head;
            head = head.next;
        }
        return dummy.next;
    }

    boolean hasNextK(ListNode node, int k) {
        while (k-- > 0) {
            if (node == null) return false;
            node = node.next;
        }
        return true;
    }
}
