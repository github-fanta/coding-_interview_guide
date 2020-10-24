package easy.part2_linked_list;

import java.util.Stack;

/**
 * 给定一个链表的头节点head 和一个正上诉num,请实现函数将值为num的节点全部删除。
 * 例如，链表为1->2->3->4->null, num=3, 链表调整后为: 1->2->4->null
 */
public class Code10_RemoveValue {
    static class Node {
        int val;
        Node next;
        Node(int data) {
            this.val = data;
        }
    }
    // 方法一： 利用栈  时间：O(N) 空间：O(N)
    public static Node removeVal1(Node head, int num) {
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            if (head.val != num) {
                stack.push(head);
            }
            head = head.next;
        }
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }
        return head;
    }
    // 不用栈 时间：O(N)  空间: O(1)
    public static Node removeVal2(Node head, int num) {
        // 找到第一个不为num
        while (head != null) {
            if (head.val != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        // cur 遍历链表
        while(cur != null) {
            // cur遍历到的是num 就删除
            if (cur.val == num) {
                pre.next = cur.next;
            }else {
                // 遍历到的不是num pre就向前
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static Node removeNotUseStack(Node head, int aim) {
        while (head != null) {
            if (head.val != aim) {
                break;
            }
            head = head.next;
        }
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            if (cur.val == aim) {
                pre.next = cur.next;
            } else {
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
//        head = removeVal1(head,2);
        head = removeNotUseStack(head, 2);
//        head = removeValUseStack(head, 2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
