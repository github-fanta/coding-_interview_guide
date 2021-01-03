package normal.part2_linked_list;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制含有随机指针节点的链表
 */
public class Code03_CopyListWithRandom {
    public static class Node{
        int value;
        Node next;
        Node rand;
        Node(int value){
            this.value = value;
        }
    }
    //方法1：用hash表实现
    public static Node CopyListWithRandom1(Node head) {
        if (head == null) {
            return head;
        }
        //复制所有节点放入hash表中
        Map<Node, Node> map = new HashMap<Node, Node>();
        Node temp = head;
        while(temp != null) {
            map.put(temp, new Node(temp.value));
            temp = temp.next;
        }

        //连接hash表中的新创建的结点之间的关系
        temp = head;
        while(temp != null) {
            map.get(temp).next = map.get(temp.next);
            map.get(temp).rand = map.get(temp.rand);
            temp = temp.next;
        }
        //返回原始head对应的新链表的头结点
        return map.get(head);
    }

    //方法2：不用hash表实现
    public static Node CopyListWithRandom2(Node head) {
        if (head == null) {
            return head;
        }
        //复制当前结点放入紧跟的后面
        Node cur = head;
        Node nextHolder = null;
        while (cur != null) {
            nextHolder = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = nextHolder;
            cur = cur.next.next;
        }

        // 复制random
        cur = head;
        while (cur != null) {
            cur.next.rand = cur.rand != null ? cur.rand.next : null;// 你(新节点)的rand是我的(老链表)rand的下一个。我的rand有可能是null
            cur = cur.next.next;
        }
        //分离新旧链表
        cur = head; //原始链表的遍历变量
        Node copyHead = head.next; //记录拷贝链表的表头
        Node copyCur = null; //拷贝链表的遍历变量
        while (cur != null) {
            copyCur = cur.next; //temp.next一定存在
            cur.next = copyCur.next;
            copyCur.next = cur.next != null ? cur.next.next : null;// 你的下一个是我的下一个的下一个，当然我的下一个可能是null
            cur = cur.next;
        }
        return copyHead;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        Node copyHead = CopyListWithRandom1(head);
        Node copyHead2 = CopyListWithRandom2(head);
        printRandLinkedList(copyHead);
        printRandLinkedList(copyHead2);
    }

}
