package fastslowpointers;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Author:   softtwilight
 * Date:     2020/05/18 23:38
 */
public class _19_M_Remove_Nth_Last_Node {
    private static final _19_M_Remove_Nth_Last_Node instance = new _19_M_Remove_Nth_Last_Node();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 利用slow 和 fast的指针，可以在只遍历一次列表的情况下可以解
     * 边界条件还是要仔细考虑。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        // head被删除的情况， 这里导致提交错了好多次。。。
        if (fast == null) return head.next;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
