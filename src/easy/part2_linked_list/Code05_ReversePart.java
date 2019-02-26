package easy.part2_linked_list;

/**
 * 给定一个单向链表的头节点head， 以及两个整数from和to,在单向链表上把第from个节点到第to个节点这一部分进行反转。
 * 例如 1->2->3->4->5->null, from=2, to=4
 * 调整结果为： 1->4->3->2->5->null;
 * 再如 1->2->3->null , from = , to=3
 * 调整结果为: 3->2->1->null
 */
public class Code05_ReversePart {
    static class Node {
        int val;
        Node next;
        public Node(int data) {
            this.val = data;
        }
    }
    public static Node reversePart(Node head, int from, int to) {
        int len = 0;
        Node cur = head;
        Node fPre = null;
        Node tNext = null;
        while (cur !=  null) {
            len ++;
            // 当走到from的前一个时 下桩fPre
            if (len == from - 1) {
                fPre = cur;
            }
            // 当走到to的后一个时 下桩tNext
            if (len == to + 1) {
                tNext = cur;
            }
            cur = cur.next;
        }
        if (from < 1 || from > to || to > len) {
            return head;
        }
        // 开始反转from到to
        // 固定好 pre 和 cur
        Node pre = fPre == null ? head : fPre.next;
        cur = pre.next;
        // 先将pre指向tNext 连接好
        pre.next = tNext;
        Node next = null;
        while (cur != tNext) {
           next = cur.next;
           cur.next = pre;
           pre = cur;
           cur = next;
        }
        // pre之前还有就连接上
        if (fPre != null) {
            fPre.next = pre;
            return head;
        }
        // pre之前没有了就直接返回pre
        return pre;
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head  = reversePart(head, 3, 5);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }
}
