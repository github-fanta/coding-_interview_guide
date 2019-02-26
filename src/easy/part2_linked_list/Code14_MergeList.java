package easy.part2_linked_list;

public class Code14_MergeList {
    static class Node {
        int val;
        Node next;
        Node(int data) {
            this.val = data;
        }
    }
    public static Node merge(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        Node dummyNode = new Node(-1);
        Node cur = dummyNode;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        if (cur1 != null) {
           cur.next = cur1;
        }
        if (cur2 != null) {
            cur.next = cur2;
        }
        Node res = dummyNode.next;
        dummyNode = null;
        return res;
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(3);

        Node head2 = new Node(1);
        head2.next = new Node(1);
        head2.next.next = new Node(2);
        head2.next.next.next = new Node(2);
        head2.next.next.next.next = new Node(2);
        head2.next.next.next.next.next = new Node(3);
        Node temp = merge(head, head2);

        while (temp != null){
            System.out.println(temp.val+" ");
            temp = temp.next;
        }
    }
}
