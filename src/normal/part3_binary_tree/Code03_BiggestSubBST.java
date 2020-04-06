package normal.part3_binary_tree;

public class Code03_BiggestSubBST {
    static class Node {
        int val;
        Node left;
        Node right;
        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] record = new int[3];
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
        Node bstHead = postOrder(head, record);
        System.out.println(bstHead.val);
    }

    private static Node postOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        Node lBST = postOrder(head.left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        Node rBST = postOrder(head.right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];

        // 记录head这一层
        record[1] = Math.min(head.val, lMin);
        record[2] = Math.max(head.val, rMax);
        if(lBST == head.left && rBST == head.right && lMax < head.val && head.val < rMin) {
            record[0] = Math.max(lSize, rSize) + 1;
            return head;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
    }


}
