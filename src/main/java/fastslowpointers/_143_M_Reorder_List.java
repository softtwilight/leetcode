package fastslowpointers;

import java.util.Stack;

/**
 * Author:   softtwilight
 * Date:     2020/05/19 21:25
 */
public class _143_M_Reorder_List {
    private static final _143_M_Reorder_List instance = new _143_M_Reorder_List();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 8 ms	50.8 MB
     * 最开始考虑用递归的，有点复杂， 写了两版都没过测试。
     * 后来想这个思路stack也是可以的，所以直接改成了stack，将node 遍历一次放入stack。
     * 然后交织顺序
     * 对奇数偶数的处理稍微有点复杂，要画图才能写清楚。
     *
     * 因为实际上只会用到一半容量的stack，用双指针只add后半段节省空间也是可以的。
     *
     * 当然这就不是fast-slow pointers的解法了。
     */
    public void reorderList(ListNode head) {
        if (head == null) return;
        Stack<ListNode> nodes = new Stack<>();
        ListNode fast = head;
        while (fast != null) {
            nodes.add(fast);
            fast = fast.next;
        }
        fast = head;
        int n = (nodes.size() - 1) / 2;
        while (n >= 0) {
            ListNode temp = fast.next;
            ListNode top = nodes.pop();
            if (fast == top) break;
            fast.next = top;
            top.next = temp;
            fast = temp;
            n--;
        }
        fast.next = null;
    }
}
