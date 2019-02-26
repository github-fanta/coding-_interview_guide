package easy.part2_linked_list;

import java.util.Stack;

/**
 * 判断一个单链表是不是回文结构
 */
public class Code07_IsPalindrome1 {

    static class Node {
        int val;
        Node next;
        Node (int data) {
            this.val = data;
        }
    }
    // 用栈将其顺序反过来对比
    public static boolean isPalindrome1 (Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.next = new Node(2);
        System.out.println(isPalindrome1(head));
    }
}
