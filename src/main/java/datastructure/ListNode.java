package datastructure;

/**
 *
 * Author:   softtwilight
 * Date:     2020/05/18 23:39
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode createByArray(int[] vals) {
        ListNode first = new ListNode(vals[0]);
        ListNode temp = first;
        for (int i = 1; i < vals.length; i++) {
            temp.next = new ListNode(vals[i]);
            temp = temp.next;
        }
        return first;
    }

    public void print() {
        ListNode temp = this;
        while (temp != null) {
            if (temp.next != null) {
                System.out.print(temp.val + "->");
            } else {
                System.out.println(temp.val);
            }
            temp = temp.next;
        }

    }

}
