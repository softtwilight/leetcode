package kwaymerge;

import datastructure.ListNode;

/**
 * Author:   softtwilight
 * Date:     2020/07/09 23:46
 */
public class _21_E_Merge_Two_Sorted_Lists {
    private static final _21_E_Merge_Two_Sorted_Lists instance = new _21_E_Merge_Two_Sorted_Lists();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 0 ms	39.2 MB
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                cur.next = l2;
                break;
            }
            if (l2 == null) {
                cur.next = l1;
                break;
            }
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
