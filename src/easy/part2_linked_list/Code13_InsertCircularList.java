package easy.part2_linked_list;

/**
 * 环形单链表从头节点head开始不降序，同时由最后的节点指回头节点。
 * 给定这样一个环形单链表的头节点head和一个整形num,请生成节点值为num的新节点，
 * 并插入到这个环形链表中，保证调整后的链表依然有序。
 */
public class Code13_InsertCircularList {
    static class Node {
        int val;
        Node next;
        public Node(int data) {
            this.val = data;
        }
    }
    public static Node insertNode(Node head, int num) {
        Node newNode = new Node(num);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }
        Node pre = head;
        Node next = head.next;
        while (next != head) {
            if ( pre.val <= num && num <= next.val){
                break;
            }
            pre = pre.next;
            next = next.next;
        }
        pre.next = newNode;
        newNode.next = next;
        return num >= pre.val ? head : newNode;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(3);
        head.next.next.next.next.next.next = head;
        Node newHead = insertNode(head, -2);
        System.out.println(newHead.val+" ");
        newHead = newHead.next;
        for (int i = 0; i < 6; i++){
            System.out.println(newHead.val+" ");
            newHead = newHead.next;
        }
    }
}
