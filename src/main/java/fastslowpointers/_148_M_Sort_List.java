package fastslowpointers;

import datastructure.ListNode;

/**
 * https://leetcode.com/problems/sort-list/
 * <p>
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
     * 以head为pivot的快排
     */
    public ListNode quickSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);
        ListNode equal = new ListNode(0);
        ListNode curSmall = small;
        ListNode curLarge = large;
        ListNode curEqual = equal;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < head.val) {
                curSmall.next = cur;
                curSmall = curSmall.next;
            } else if (cur.val == head.val) {
                curEqual.next = cur;
                curEqual = curEqual.next;
            } else {
                curLarge.next = cur;
                curLarge = curLarge.next;
            }
            cur = cur.next;
        }
        curSmall.next = null;
        curEqual.next = null;
        curLarge.next = null;
        return sort(quickSort(small.next), quickSort(large.next), equal.next);
    }

    private ListNode sort(ListNode one, ListNode two, ListNode pivot) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode curOne = one;
        while (curOne != null) {
            cur.next = curOne;
            curOne = curOne.next;
            cur = cur.next;
        }
        while (pivot != null) {
            cur.next = pivot;
            pivot = pivot.next;
            cur = cur.next;
        }
        cur.next = two;
        return dummy.next;
    }


    /**
     * buttom-up版的merge sort， constant space complexity.
     * https://youtu.be/WVl2QSe4R14?list=PLRdD1c6QbAqJn0606RlOR6T3yUqFWKwmX
     */
    public ListNode sortList3(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int n = 0;
        while (head != null) {
            head = head.next;
            n++;
        }

        for (int step = 1; step < n; step <<= 1) {
            ListNode prev = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, step);
                cur = split(right, step);
                prev = merge(left, right, prev);
            }
        }

        return dummy.next;
    }

    private ListNode split(ListNode head, int step) {
        if (head == null) return null;

        for (int i = 1; head.next != null && i < step; i++) {
            head = head.next;
        }

        ListNode right = head.next;
        head.next = null;
        return right;
    }

    private ListNode merge(ListNode left, ListNode right, ListNode prev) {
        ListNode cur = prev;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            }
            else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        if (left != null) cur.next = left;
        else if (right != null) cur.next = right;
        while (cur.next != null) cur = cur.next;
        return cur;
    }

    /**
     * 基于fast slow pointers的mergesort
     */
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 1.divide into smaller ones
        ListNode otherHalf = slow.next;

        // 切断左边和右边的link
        // 这样左边最后一个不会指向右边， ex当slow是最大的元素的时候，最后merge的结果是
        // 最后一个元素又指向前面（原来右边的）的某一个元素。
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(otherHalf);

        // dummy 可以减少是否为null的判断
        ListNode dummy = new ListNode(0);
        // 2.merge
        ListNode root = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                dummy.next = left;
                left = left.next;
            } else {
                dummy.next = right;
                right = right.next;
            }
            dummy = dummy.next;
        }

        if (left != null)
            dummy.next = left;
        else // right 为null 或者 不为null， 都一样
            dummy.next = right;
        return root.next;
    }

    /**
     * Sort a linked list in O(n log n) time using constant space complexity.
     * <p>
     * 用merge sort的方法， 跟双指针好像也没什么关系。
     * 事实上mergesort并不是constant space complexity, 递归利用函数调用的栈来储存数据了
     * 实际的复杂度是O（lgN）。
     * <p>
     * <p>
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
