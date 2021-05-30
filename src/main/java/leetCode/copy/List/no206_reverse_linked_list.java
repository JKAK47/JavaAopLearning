package leetCode.copy.List;


import leetCode.copy.common.ListNode;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * https://github.com/azl397985856/leetcode/blob/master/problems/206.reverse-linked-list.md
 */
public class no206_reverse_linked_list {

    // 方法1 递归
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;

        return reverseListDeep(null, head);
    }

    public ListNode reverseListDeep(ListNode pre, ListNode head) {
        if (head == null) return pre;

        ListNode next = head.next;
        head.next = pre;

        if (next == null)
            return head;

        return reverseListDeep(head, next);
    }

    // 方法2 迭代
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        if (head == null || head.next == null)
            return head;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;

            curr = temp;
        }
        return pre;
    }
}
