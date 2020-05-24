package linklist;

import datastructure.ListNode;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * Author:   softtwilight
 * Date:     2020/05/24 23:23
 */
public class _24_M_Swap_Nodes_in_Pairs {
    private static final _24_M_Swap_Nodes_in_Pairs instance = new _24_M_Swap_Nodes_in_Pairs();

    public static void main(String[] args) {
        ListNode input = ListNode.createByArray(new int[]{1,2,3,4, 5});
        input.print();
        ListNode result = instance.swapPairs(input);
        result.print();
    }

    /**
     * node 的指针操作， 有一个dummy会让代码简单很多，
     * 让每一次循环的逻辑一样的。 画个图就清晰了，也没有太多要说的。
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode last = dummy;
        while (head != null && head.next != null) {
            ListNode temp = head.next.next;
            last.next = head.next;
            head.next.next = head;
            head.next = temp;
            last = head;
            head = temp;
        }
        return dummy.next;
    }
}
