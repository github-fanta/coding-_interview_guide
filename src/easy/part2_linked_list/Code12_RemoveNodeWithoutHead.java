package easy.part2_linked_list;

/**
 * 链表节点值类型为int形，给定一个链表中的节点node，但不给定整个链表的头节点。如何在链表中删除node？分析这么会出现哪些问题？
 * 问题一：无法删除最后一个节点，因为没有头节点，回不到node的前一个节点
 * 问题二：node的下一个节点next 把值拷贝到node。实际工程中可能会很复杂。甚至不让删除。如外界对每个节点服务又很多依赖，删除节点2时
 * 其实影响了节点3对外提供的服务。
 */
public class Code12_RemoveNodeWithoutHead {
    static class Node {
        int val;
        Node next;
        public Node(int data) {
            this.val = data;
        }
    }
    public static void removeNodeWithoutHead(Node node) {
        if (node == null) {
            return;
        }
        Node next = node.next;
        if (next == null) {
            throw new RuntimeException("can not remove last node");
        }
        node.val = next.val;
        node.next = next.next;
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(2);

        removeNodeWithoutHead(head.next);
        while (head != null) {
            System.out.println(head.val+" ");
            head = head.next;
        }
//        removeNodeWithoutHead(head.next.next.next.next.next);
//        while (head != null) {
//            System.out.println(head.val+" ");
//            head = head.next;
//        }
    }
}
