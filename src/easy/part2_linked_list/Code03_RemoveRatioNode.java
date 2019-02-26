package easy.part2_linked_list;

public class Code03_RemoveRatioNode {

    static class Node {
        int val;
        Node next;
        public Node(int data) {
            this.val = data;
        }
    }

    /**
     * 第一题： 给定链表的头节点head 删除链表的中间节点
     */
    public static Node removeMidNode(Node head) {
        // 只有0或1个节点
        if(head == null || head.next == null) {
            return head;
        }
        // 只有2个节点 删除头
        if (head.next.next == null) {
            return head.next;
        }
        Node slow = head;
        Node fast = head.next.next;
        while(fast.next != null && fast.next.next != null) {
           slow = slow.next;
           fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 第二题(进阶) 给定链表的头节点head,整数a 和 b ,删除位于a/b处的节点
     */
    public static Node removeByRatio(Node head, int a, int b) {
        if (a < 1 || a > b) {
            return head;
        }
        int nodeCount = 0;
        Node cur = head;
        while(cur != null) {
            cur = cur. next;
            nodeCount++;
        }
        // 要使得ceil括号中的数字为小数——小数除以小数才是小数 整数除以整数是整数。所以加上double
        int removeNodeIdx = (int) Math.ceil(((double) nodeCount*a)/(double)b);
        if (removeNodeIdx == 1) {
            return head.next;
        }
        if (removeNodeIdx > 1) {
            cur = head;
            while(--removeNodeIdx > 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }
    public static void main(String[] args) {
        /*
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        Node cur = removeMidNode(head);
        while(cur != null) {
           System.out.println(cur.val+"");
           cur = cur.next;
       }
       */
        // 测试第二题
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);

        Node cur = removeByRatio(head, 1, 5);

        while(cur != null) {
           System.out.println(cur.val+"");
           cur = cur.next;
        }
    }

}
