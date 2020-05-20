package fastslowpointers;

/**
 * https://leetcode.com/problems/add-two-numbers/submissions/
 *
 * Author:   softtwilight
 * Date:     2020/05/18 22:16
 */
public class _2_M_Add_Two_Numbers {
    private static final _2_M_Add_Two_Numbers instance = new _2_M_Add_Two_Numbers();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode prv = dummy;

        int carry = 0;
        while (l1 != null || l2 != null){
            int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;

            int rem = val % 10;
            carry = val / 10;

            ListNode nn = new ListNode(rem);
            prv.next = nn;
            prv = nn;

            if (l1 != null)l1 = l1.next;
            if (l2 != null)l2 = l2.next;
        }

        if (carry != 0){
            ListNode nn = new ListNode(carry);
            prv.next = nn;
        }
        return dummy.next;
    }


    ListNode result;
    int carry = 0;
    /**
     * 额， 这个题我是理解错了题意了， 原题要简单一半吧至少。。。
     * 真的是尴尬，我以为最后输出的bit的数位也是反的
     * 主要是它的列子是， 342 + 465 = 807.正反都是这个数。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return new ListNode(0);
        }
        ListNode node = helper(l1, l2);
        if (carry == 1) {
            node.next = new ListNode(1);
        }
        return result;
    }

    ListNode helper(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode nl1 = l1 == null ? null : l1.next;
        int vl1 = l1 == null ? 0 : l1.val;
        ListNode nl2 = l2 == null ? null : l2.next;
        int vl2 = l2 == null ? 0 : l2.val;
        ListNode node = helper(nl1, nl2);
        int v = vl1 + vl2 + carry;
        carry = v / 10;
        v %= 10;
        ListNode ln = new ListNode(v);
        if (node == null) {
            result = ln;
        } else {
            node.next = ln;
        }
        return ln;
    }



}
