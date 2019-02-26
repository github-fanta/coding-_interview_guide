package easy.part2_linked_list;

public class Code15_MergeLR {
    static class Node {
        int val;
        Node next;
        Node(int data) {
            this.val = data;
        }
    }
    public static Node mergeLR(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head;
        // 快慢指针都从head开始说明
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        slow.next = null;
//        merge (head, fast);
        meregeLR(head, fast);
        return head;
    }

    // 两个链表合并方法一： dummyNode
    private static void merge(Node L, Node R) {
        Node dummyNode = L;
        L = L.next;
        // 从右边第一个节点开始
        boolean isLeftSide = false;
        while (L != null) {
            if (isLeftSide) {
                dummyNode.next = L;
                L = L.next;
                isLeftSide = false;
            }else {
                dummyNode.next = R;
                R = R.next;
                isLeftSide = true;
            }
            dummyNode = dummyNode.next;
        }
        if (R != null) {
            dummyNode.next = R;
        }
    }
    // 链表合并方法二：将右边链表的节点逐个插入左边链表中。
    private static void meregeLR(Node L, Node R) {
        Node next = null;
        while (L.next != null) {
            next = R.next;
            R.next = L.next;
            L.next = R;
            L = R.next;
            R = next;
        }
        L.next = R;
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        mergeLR(head);
        while (head != null) {
            System.out.println(head.val + " ");
            head = head.next;
        }
    }
}
