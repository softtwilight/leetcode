package fastslowpointers;

/**
 * https://leetcode.com/problems/sort-list/
 *
 * Author:   softtwilight
 * Date:     2020/05/20 20:35
 */
public class _148_M_Sort_List {
    private static final _148_M_Sort_List instance = new _148_M_Sort_List();

    public static void main(String[] args) {
        ListNode n1 = ListNode.createByArray(new int[]{-1, 4, 2, 3, 1});
        n1.print();
        ListNode sorted = instance.sortList(n1);
        sorted.print();
    }

    /**
     * Sort a linked list in O(n log n) time using constant space complexity.
     *
     * 用merge sort的方法， 跟双指针好像也没什么关系。
     * 4 ms	+ 48.1 MB
     */
    public ListNode sortList(ListNode head) {

        ListNode p = head;
        int size = 0;
        while (p != null) {
            size++;
            p = p.next;
        }
        if (size <= 1) return head;
        return mergeSort(head, size);
    }

    public ListNode mergeSort(ListNode head, int size) {
        if (size == 1) {
            return head;
        }
        ListNode mid = head;
        int leftSize = (size + 1) / 2;
        int rightSize = size - leftSize;
        int count = leftSize;
        while (count > 0) {
            mid = mid.next;
            count--;
        }
        ListNode sortedLeft = mergeSort(head, leftSize);
        ListNode sortedRight = mergeSort(mid, rightSize);
        ListNode mergedNode;
        if (sortedLeft.val > sortedRight.val) {
            mergedNode = sortedRight;
            sortedRight = sortedRight.next;
            rightSize--;
        } else {
            mergedNode = sortedLeft;
            sortedLeft = sortedLeft.next;
            leftSize--;
        }
        ListNode temp = mergedNode;
        while (leftSize > 0 || rightSize > 0) {
            if (leftSize <= 0) {
                temp.next = sortedRight;
                sortedRight = sortedRight.next;
                temp = temp.next;
                rightSize--;
            } else if (rightSize <= 0) {
                temp.next = sortedLeft;
                temp = temp.next;
                sortedLeft = sortedLeft.next;
                leftSize--;
            } else {
                if (sortedLeft.val < sortedRight.val) {
                    temp.next = sortedLeft;
                    temp = temp.next;
                    sortedLeft = sortedLeft.next;
                    leftSize--;
                } else {
                    temp.next = sortedRight;
                    temp = temp.next;
                    sortedRight = sortedRight.next;
                    rightSize--;
                }
            }
        }
        temp.next = null;
        return mergedNode;
    }

}
