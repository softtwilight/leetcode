package linklist;

import datastructure.ListNode;

/**
 * Author:   softtwilight
 * Date:     2020/05/25 23:34
 */
public class _206_E_Reverse_Linked_List {
    private static final _206_E_Reverse_Linked_List instance = new _206_E_Reverse_Linked_List();

    public static void main(String[] args) {
        ListNode input = ListNode.createByArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        input.print();
        ListNode result = instance.reverseList3(input);
        result.print();
    }


    /**
     * 更简单的recursively version
     */
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode result = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }

    /**
     * iteratively 版本， 不用dummy的原因是， 让当前node指向前一个元素，
     * 这样其实更直观的理解reverse，而且跟递归的思路就很像了。
     */
    public ListNode reverseList4(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * recursively version
     * 	0 ms  +	39 MB
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode temp = head.next;
        head.next = null;
        return helper(temp, head);
    }

    /**
     * iteratively version
     * 	0 ms	39.1 MB
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (head.next != null) {
            ListNode tmp = head.next.next;
            head.next.next = dummy.next;
            dummy.next = head.next;
            head.next = tmp;
        }
        return dummy.next;
    }

    ListNode helper(ListNode after, ListNode before) {
        if (after.next == null) {
            after.next = before;
            return after;
        }
        ListNode temp = after.next;
        after.next = before;
        return helper(temp, after);
    }

}
