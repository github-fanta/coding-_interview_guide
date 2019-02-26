package easy.part2_linked_list;

/**
 * 约瑟夫环(简单级别)
 * 输入：一个环形单向链表的头节点head和报数的值m(报数到m的人死)。
 * 返回：最后生存下来的节点，且这个节点自己组成环形单向链表，其他节点都删除掉。
 */
public class Code06_JosephusKill1 {
    static class Node {
        int val;
        Node next;
        Node(int data) {
            this.val = data;
        }
    }

    public static Node josephusKill1(Node head, int m) {
       if (head == null || head.next == null || m < 1) {
           return head;
       }
       Node last = head;
       while (last.next != head) {
           last = last.next;
       }
       int count = 0;
       while (head != last) {
           if ((++count % m) == 0) {
               System.out.println(last.next.val+" die!");
               last.next = last.next.next;
           } else {
                last = last.next;
           }
           head = last.next;
       }
       return head;
    }
    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = head;
        System.out.println("生还者:"+josephusKill1(head, 3).val);
    }
}
