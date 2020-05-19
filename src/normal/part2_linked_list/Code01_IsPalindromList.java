package normal.part2_linked_list;

import java.util.Stack;

public class Code01_IsPalindromList {

    public static class Node{
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }
    // 方法2：后半部分入栈
    public static boolean isPalindrom2(Node head){
        if (head == null || head.next == null || head.next.next == null) return false;
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (head.val != stack.pop().val) return false;
            head = head.next;
        }
        return true;
    }

    // 方法3 ：后半部分链表反转
    public static boolean isPalindrom3(Node head) {
        if (head == null || head.next == null || head.next.next == null) return false;
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node rightHead = reverseList(slow);
        Node endNode = rightHead;
        boolean res = true;
        // check the left part and right part
        while (head != null && rightHead != null) {
            if (head.val != rightHead.val) {
                res = false;
                break;
            }
            head = head.next;
            rightHead = rightHead.next;
        }
        reverseList(endNode);
        return res;
    }

    private static Node reverseList(Node head) {
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


    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);

        //判断之前输出链表
        Node cur = head;
        System.out.println("链表判定前：");
        while(cur != null) {
            System.out.print(cur.val+" ");
            cur = cur.next;
        }
        System.out.println("\n方法二："+isPalindrom2(head));
        System.out.println("\n方法二："+isPalindrom3(head));
        //判断之后输出链表
        cur = head;
        System.out.println("\n链表判定后：");
        while(cur != null) {
            System.out.print(cur.val+" ");
            cur = cur.next;
        }
        System.out.println();
    }

}
