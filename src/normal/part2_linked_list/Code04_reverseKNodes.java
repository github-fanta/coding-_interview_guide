package normal.part2_linked_list;

import java.util.Stack;

public class Code04_reverseKNodes {

    public static class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }

    // 方法一：借助栈 时间复杂度O(N) 空间复杂度O(K)
    public static Node reverseKNode1(Node head, int K) {
        if (K < 2) return head;
        Stack<Node> stack = new Stack<>();
        Node newHead = head;
        Node cur = head;
        Node lastEnd = null;   // 上次处理的结尾节点
        Node next = null; // 下批次要处理的开头
        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == K) {
                lastEnd = reverse(stack, lastEnd, next);// 反转lastEnd到nextStart之间的K个节点
                if (newHead == head) newHead = cur; // 如果是第一次 cur就是新头
            }
            cur = next;
        }
        return newHead;
    }

    // 反转stack中的K个节点
    private static Node reverse(Stack<Node> stack, Node lastEnd, Node nextStart) {
        Node cur = stack.pop(); // 栈中弹出第一个
        if (lastEnd != null) lastEnd.next = cur; // 接上前面的部分
        while (!stack.isEmpty()) {  // 接上栈中本次要反转的所有节点
            cur.next = stack.pop();
            cur = cur.next;
        }
        cur.next = nextStart;  // 接上剩下未反转的右边部分
        return cur; // 返回本次反转后的最后一个节点
    }

    // 方法2：链表区间反转 时间复杂度O(N) 空间复杂度O(1)
    public static Node reverseKNode2(Node head, int K) {
        if (K < 2) return head;
        Node lastEnd = null; //上一组最后一个节点
        Node thisStart = null;// 本组第一个节点
        Node cur = head;
        Node next = null;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (count == K) {
                if (lastEnd == null) { //lastEnd为null，说明本组是第一组K个节点 本组的开始就是head。
                    thisStart = head;
                    head = cur; // 如果是第一次反转 本组最后一个(cur)的就是反转后新链表的头
                } else { // 不是第一组
                    thisStart = lastEnd.next; //本组不是第一组K个节点，那么本次start就定为上次lastEnd之后的第一个
                }
                reverseList(lastEnd, thisStart, cur, next);// 上组结尾，本组开始，本组结尾，下组开始

                // 更新
                lastEnd = thisStart;
                count = 0;
            }
            count ++;
            cur = next;
        }
        return head;
    }

    private static void reverseList(Node preEnd, Node thisStart, Node thisEnd, Node nextStart) { // 上组结尾，本组开始，本组结尾，下组开始
        // 三个指针为了反转链表的标准模式
        Node pre = thisStart; // 本组开始将变成已反转的节点的结尾
        Node cur = thisStart.next; //从第二个节点开始反转方向
        Node next = null;
        while (cur != nextStart) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (preEnd != null) preEnd.next = thisEnd; //连接左边
        thisStart.next = nextStart; //连接右边
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        //Node node = reverseKNode1(head, 3);
        Node node = reverseKNode2(head, 3);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
