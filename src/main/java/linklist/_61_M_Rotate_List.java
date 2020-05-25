package linklist;

import datastructure.ListNode;

/**
 * Author:   softtwilight
 * Date:     2020/05/25 23:11
 */
public class _61_M_Rotate_List {
    private static final _61_M_Rotate_List instance = new _61_M_Rotate_List();

    public static void main(String[] args) {
        ListNode input = ListNode.createByArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        input.print();
        ListNode result = instance.rotateRight(input, 7);
        result.print();
    }

    /**
     * 0 ms	+ 38.9 MB
     * 这道理没有旋转，简单很多
     * 优化点是k以length为周期，所以去mod就行
     * 需要改变的指针是两个点， 最后的Node， end， 和 Node n， n之后有k个node
     * 然后改变他们的指针就行了
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ListNode endNode = head;
        int length = 1;
        while (endNode.next != null) {
            length++;
            endNode = endNode.next;
        }
        k = k % length;
        if (k == 0) return head;
        ListNode headOfLastKNode = head;
        for (int i = 0; i < length - k - 1; i++) {
            headOfLastKNode = headOfLastKNode.next;
        }

        ListNode result = headOfLastKNode.next;
        headOfLastKNode.next = null;
        endNode.next = head;
        return result;
    }
}
