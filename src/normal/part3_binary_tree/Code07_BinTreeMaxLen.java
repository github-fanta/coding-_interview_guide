package normal.part3_binary_tree;

/**
 * @Author liq
 * @Date 2020/5/28
 */
public class Code07_BinTreeMaxLen {

    static class Node {
        int val;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
        }
    }

    public static int maxLen(Node head, int[] curDepth) {
        if (head == null) {
            curDepth[0] = 0;
            return 0;
        }
        // 左子树存在的最长链
        int leftMax = maxLen(head.left, curDepth);
        // 左子树上距左节点最远的举例
        int leftDepth = curDepth[0];
        // 右子树存在的最长链
        int rightMax = maxLen(head.right, curDepth);
        // 右子树上距右节点最远的举例
        int rightDepth = curDepth[0];

        // 包含当前节点的最长链  更新
        curDepth[0] = Math.max(leftDepth, rightDepth) + 1;

        // 看 加上当前节点构成的链 是否能和 左右已存在的最长链 一战
        int curMax = leftDepth + rightDepth + 1;
        return Math.max(Math.max(leftMax, rightMax), curMax);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        int maxLen = maxLen(head, new int[1]);
        System.out.println(maxLen);
    }
}
