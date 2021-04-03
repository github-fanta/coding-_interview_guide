package normal.part3_binary_tree;

import java.util.Stack;

/**
 * @Author liq
 * @Date 2020/5/27
 */
public class Code05_TwoUnOrderNodes {

    public static Node[] getTwoUnOrderNodes(Node head) {
        Node[] result = new Node[2];
        if (head == null) {
            return result;
        }
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && pre.value > head.value) {
                    result[0] = result[0] == null ? pre : result[0];
                    result[1] = head;
                }
                pre = head;
                head = head.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(5);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right = new Node(2);
        Node[] twoUnOrderNodes = getTwoUnOrderNodes(head);
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            this.value = val;
        }
    }
}
