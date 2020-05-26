package linklist;

import datastructure.ListNode;

import java.util.List;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 *
 * Author:   softtwilight
 * Date:     2020/05/26 23:37
 */
public class _328_M_Odd_Even_Linked_List {
    private static final _328_M_Odd_Even_Linked_List instance = new _328_M_Odd_Even_Linked_List();

    public static void main(String[] args) {
        ListNode input = ListNode.createByArray(new int[]{1, 2, 3, 4, 5, 6});
        input.print();
        ListNode result = instance.oddEvenList(input);
        result.print();
    }

    /**
     * 0 ms	39.1 MB
     * 用两个指针连接even 和 odd， 然后最后拼起来
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode evenHeader = head.next;
        ListNode oddHeader = head;
        ListNode odd = oddHeader;
        ListNode even = evenHeader;
        head = head.next.next;
        for (boolean isOdd = true; head != null; isOdd = !isOdd) {
            if (isOdd) {
                odd.next = head;
                odd = head;
            } else {
                even.next = head;
                even = head;
            }
            head = head.next;
        }
        even.next = null;
        odd.next = evenHeader;
        return oddHeader;
    }

    /**
     * 这个相等与同时移动两个指针， even的下一个是old， old的一下个是even
     * 代码更紧凑些
     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
