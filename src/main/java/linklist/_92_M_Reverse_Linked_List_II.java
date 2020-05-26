package linklist;

import datastructure.ListNode;

/**
 * Author:   softtwilight
 * Date:     2020/05/26 22:22
 */
public class _92_M_Reverse_Linked_List_II {
    private static final _92_M_Reverse_Linked_List_II instance = new _92_M_Reverse_Linked_List_II();

    public static void main(String[] args) {

        ListNode input = ListNode.createByArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        input.print();
        ListNode result = instance.reverseBetween(input, 1, 5);
        result.print();
    }

    /**
     * 0 ms	37.1 MB
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        int i = 1;
        while (true) {
            if (i >= m) {
                ListNode he = cur.next;
                while (i++ < n) {
                    ListNode tmp = he.next.next;
                    he.next.next = cur.next;
                    cur.next = he.next;
                    he.next = tmp;
                }
                return dummy.next;
            }
            i++;
            cur = cur.next;
        }
    }
}
