package easy.part2_linked_list;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.HashSet;
import java.util.Set;

/**
 * 删除无序单链表中值重复出现的节点
 */
public class Code09_RemoveRep1 {
    static class Node {
        int val;
        Node next;
        Node (int data) {
           this.val = data;
        }
    }
    public static Node removeRep1(Node head) {
        if (head == null) {
            return null;
        }
        Node pre = head;
        Node cur = head.next;
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        while (cur != null) {
            if (set.contains(cur.val)) {
                pre.next = cur.next;
            }else {
                set.add(cur.val);
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(2);
        head = removeRep1(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
