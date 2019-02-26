package easy.part3_binary_tree;

public class Code07_LowestAncestor {
    public static class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }
    public static Node lowestAncestor(Node head, Node node1, Node node2) {
        // 情况一： head == null 或者head == node1 或者head == node2 直接返回head
        if (head == null || head == node1 || head == node2) {
            return head;
        }
        Node left = lowestAncestor(head.left, node1, node2);
        Node right = lowestAncestor(head.right, node1, node2);
        // 情况二：左右都不为null, 说明左右子树分别找到node1和node2了 返回当前head节点即可
        if (left != null && right != null) {
            return head;
        }
        // 情况三：left和right有一个不空，另一个空，
        // 返回不空的那个(node1或者node2中的一个， 要么node已经是node1和node2的最近公共祖先)
        // 情况四： 左右返回都为空 说明整棵树上没有发现node1或node2
        return left != null ? left : right;

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right = new Node(3);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.right.left = new Node(8);
        // 2和8
        System.out.println(lowestAncestor(head, head.left, head.right.right.left ).value);
    }
}
