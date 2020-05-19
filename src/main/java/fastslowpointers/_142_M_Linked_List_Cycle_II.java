package fastslowpointers;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * Author:   softtwilight
 * Date:     2020/05/19 21:03
 */
public class _142_M_Linked_List_Cycle_II {
    private static final _142_M_Linked_List_Cycle_II instance = new _142_M_Linked_List_Cycle_II();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 这道题以前在别的地方遇见的时候，我还写过一个博客。
     * 快指针比慢指针快一步，如果没有循环，快指针会率先到达终点。放回null
     * 如果有循环，fast会追上slow， 刚好是fast 比 slow多跑一圈的时候。
     * 起点到循环点的距离为 b， 循环点到相遇点的距离是c, slow 移动的距离是a, fast为2a
     * 那么圆周长为a， 同时 b + c = a；b = a - c, 我们可以用这个等式来再次移动两个指针
     * 从c 出发， 走到循环点的距离 和 起到到循环点的距离是一样的， 也就是两个指针在这里的值相等
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == head) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }

        }
        return null;
    }
}
