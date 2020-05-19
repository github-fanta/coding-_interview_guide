package normal.part3_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

public class Code04_BinTreeLevelAndZigZagPrint {

    static class Node {
        int value;
        Node left;
        Node right;
        public Node(int val) {
            this.value = val;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);

        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

//        printByLevel(head);
        printByZigZag(head);
    }

    public static void printByLevel(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        int level = 1;
        Node last = head;
        Node nextLast = null;
        queue.offer(head);
        System.out.print("Level " + (level ++) + " : ");
        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();
            System.out.print(pollNode.value + " ");
            if (pollNode.left != null) {
                queue.offer(pollNode.left);
                nextLast = pollNode.left;
            }
            if (pollNode.right != null) {
                queue.offer(pollNode.right);
                nextLast = pollNode.right;
            }
            // 弹出的是队列的最后一个节点，就不要打印下一层，更换下一层的最后一个了。
            if (pollNode == last && !queue.isEmpty()) {
                System.out.print("\nLevel " + (level ++) + " : ");
                last = nextLast;
            }
        }
        System.out.println();
    }

    public static void printByZigZag(Node head) {
        if (head == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        int level = 1;
        Node nextChildrenLast = null;
        Node last = head;
        queue.offerFirst(head);
        boolean leftToRight = true;
        printLevelAndDirection(level, leftToRight);
        while (!queue.isEmpty()) {
            if (leftToRight) {
                head = queue.pollFirst();
                if (head.left != null) {
                    if (nextChildrenLast == null) {
                        nextChildrenLast = head.left;
                    }
                    queue.offerLast(head.left);
                }
                if (head.right != null) {
                    if (nextChildrenLast == null) {
                        nextChildrenLast = head.right;
                    }
                    queue.offerLast(head.right);
                }
            } else {
                head = queue.pollLast();
                if (head.right != null) {
                    if (nextChildrenLast == null) {
                        nextChildrenLast = head.right;
                    }
                    queue.offerFirst(head.right);
                }
                if (head.left != null) {
                    if (nextChildrenLast == null) {
                        nextChildrenLast = head.left;
                    }
                    queue.offerFirst(head.left);
                }
            }
            System.out.print(head.value + " ");

            // 用last来标记每层最后一个节点 如果当前打印节点是最后一个节点 切换方向
            if (!queue.isEmpty() && (head == last)) {
                leftToRight = !leftToRight;
                last = nextChildrenLast;
                nextChildrenLast = null;
                // 一层打印完 换行
                System.out.println();
                // 打印当前行数和方向
                printLevelAndDirection(level ++, leftToRight);
            }
        }
        System.out.println();
    }

    private static void printLevelAndDirection(int level, boolean leftToRight) {
        System.out.println("Level: " + level);
        System.out.println("Direction: " + (leftToRight ? "left to right" : "right to left"));
    }
}
