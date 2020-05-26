package linklist;

import datastructure.ListNode;

import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/05/26 22:22
 */
public class _92_M_Reverse_Linked_List_II {
    private static final _92_M_Reverse_Linked_List_II instance = new _92_M_Reverse_Linked_List_II();

    public static void main(String[] args) {

        ListNode input = ListNode.createByArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        input.print();
        ListNode result = instance.reverseBetween2(input, 2, 5);
        result.print();
    }



    /**
     * 0 ms	37 MB
     * 递归版本
     * 很少会感觉递归比iteratively 更难的
     * 感觉reverse linklist就是这样的， 不过递归有意思很多。
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (n == 1) {
            return head;
        }
        if (m > 1) {
            ListNode result = reverseBetween2(head.next, m - 1, n - 1);
            head.next = result;
            return head;
        } else {
            ListNode next = head.next;
            ListNode result = reverseBetween2(head.next, m, n - 1);
            // 递归之后把successor 传递给next的next
            // 因为n == 1的时候， 返回head， head的next就是succesor
            // 那么我们讲successor赋值给head.next, 这样上一次递归就可以用同样的方式拿到succssor了
            ListNode successor = next.next;
            next.next = head;
            head.next = successor;
            return result;
        }
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
