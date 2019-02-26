package easy.part3_binary_tree;

/**
 * 给定无重复值的有序数组，生成一颗平衡搜索二叉树。要求其中序遍历结果与有序数组一致
 */
public class Code06_GenerateBST {
    static class Node {
        int value;
        Node left;
        Node right;
        Node(int data) {
            this.value = data;
        }
    }
    public static Node generateTree(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static Node process(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        // 有序数组最中间的数生成搜索二叉树的头节点
        int mid = (start + end) >> 1;
        Node head = new Node(arr[mid]);
        head.left = process(arr, start, mid - 1);
        head.right = process(arr, mid + 1, end);
        return head;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }
    public static void main(String[] args) {
        Node head = generateTree(new int[]{1,2,3,4,5,6,7,8});
        printTree(head);
        Node head1 = generateTree(new int[]{1,2,3,4,5,6,7,8,9});
        printTree(head1);
    }
}
