package easy.part2_linked_list;

/**
 * 反转单向和双向链表
 */
public class Code04_ReverseList {
    static class Node {
        int val;
        Node next;
        public Node(int data){
           this.val = data;
        }
    }
    // 1:反转单向链表
    public static Node reverseNode(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    static class DoubleNode {
        int val;
        DoubleNode pre;
        DoubleNode next;
        public DoubleNode(int data){
            this.val = data;
        }
    }
    // 2:反转双向链表
    public static DoubleNode reverseDoubleNode(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;

            pre = head;
            head = next;
        }
        return pre;
    }
    public static void main(String[] args) {

       /*
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head = reverseNode(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        */

        DoubleNode head = new DoubleNode(0);
        head.next = new DoubleNode(11);
        head.next.next = new DoubleNode(22);
        head.next.next.next = new DoubleNode(33);
        head.next.next.next.next = new DoubleNode(44);

        head = reverseDoubleNode(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
