package easy.part2_linked_list;

import java.util.Stack;

/**
 * 两个单链表生成相加链表： 假设链表中每一个节点的值都在0~9之间，那么链表整体就可以代表一个整数。例如：9->3->7，可以代表整数937.
 * 给定两个这种链表的头节点head1和head2，请生成代表两个整数相加值的结果链表。
 * 例如：链表1为9->3->7,链表2为6->3,最后生成新的结果链表为1->0->0->0;
 */
public class Code08_Add2List {

    static class Node {
        int val;
        Node next;

        Node(int data) {
            this.val = data;
        }
    }
    // 方法一：用两个栈
    public static Node addList1(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        while (head1 != null) {
            stack1.push(head1);
            head1 = head1.next;
        }
        while (head2 != null) {
            stack2.push(head2);
            head2 = head2.next;
        }
        Node pre = null;
        Node newHead = null;
        int carry = 0;
        int n1 = 0;
        int n2 = 0;
        int tempSum = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            // 1.取值
            n1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            n2 = stack2.isEmpty() ? 0 : stack2.pop().val;
            tempSum = n1 + n2 + carry;
            // 2.处理进位
            if (tempSum >= 10) {
                carry = 1;
                tempSum -= 10;
            } else {
                carry = 0;
            }
            // 3.连接新链
            newHead = new Node(tempSum);
            newHead.next = pre;
            pre = newHead;
        }
//        if (carry == 1) {
//            newHead = new Node(1);
//            newHead.next = pre;
//        }
        return newHead;
    }


    //方法二：
    public static Node addList2(Node head1, Node head2) {
        if(head1 == null || head2 == null) {
            return null;
        }
        Node list1 = reverseList(head1);
        Node list2 = reverseList(head2);
        int num1 = 0;
        int num2 = 0;
        int sum = 0;
        int carry = 0;
        Node pre = null;
        Node newHead = null;
        while (list1 != null || list2 != null || carry != 0) {
            // 获取数字
            num1 = list1 == null ? 0 : list1.val;
            num2 = list2 == null ? 0 : list2.val;
            sum = num1 + num2 + carry;
            // 处理进位
            if (sum >= 10) {
                sum -= 10;
                carry = 1;
            }else {
                carry = 0;
            }
            newHead = new Node(sum);
            // 连接新链
            newHead.next = pre; // 头插
            pre = newHead;
            // 向后继续
            if (list1 != null) list1 = list1.next;
            if (list2 != null) list2 = list2.next;
        }
        reverseList(head1);
        reverseList(head2);
        return newHead;
    }
    public static Node reverseList(Node head) {
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
        Node head1 = new Node(9);
        head1.next = new Node(3);
        head1.next.next = new Node(7);

        Node head2 = new Node(6);
        head2.next = new Node(3);

        Node node = addList2(head1, head2);
        while (node != null) {
            System.out.println(node.val + " ");
            node = node.next;
        }

    }
}
