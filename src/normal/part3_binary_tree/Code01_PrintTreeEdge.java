package normal.part3_binary_tree;

import normal.part2_linked_list.Code05_BinaryTreeToList;

/**
 * 【打印二叉树边界】
 *     标准1：打印每层最左和最右节点以及叶子节点
 *     标准2：在标准1基础上，不打印左右边界上有孩子的节点(不包含头结点)
 */
public class Code01_PrintTreeEdge {
    static class Node{
        int val;
        Node left;
        Node right;
        Node(int val) {
            this.val = val;
        }
    }

    // 按照标准一：
    public static void printEdge1(Node head) {
        if (head == null) {
            return;
        }
        int height = getHeight(head); // 树高
        Node[][] edgeArr = new Node[height][2]; // 记录每层的最左和最右节点
        setEdgeArr(head, 0, edgeArr); // 填充数组

        // 打印左边界 只打印每层最左节点
        for (int level = 0; level < height; level++) {
            System.out.print(edgeArr[level][0].val + " ");
        }
        // 打印既非左边界，又非右边界的叶子节点
        printLeafNotInArr(head, 0, edgeArr);
        // 打印右边界
        for (int level = height-1; level >= 0; level--) {
            if (edgeArr[level][1] != edgeArr[level][0]) { // 不是左边界(去除了root和一层只有一个节点的情况)
                System.out.print(edgeArr[level][1].val + " ");
            }
        }
    }

    private static int getHeight(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(getHeight(head.left), getHeight(head.right)) + 1;
    }

    private static void setEdgeArr(Node head, int level, Node[][] edgeArr) {
        if (head == null) {
            return;
        }
        if (edgeArr[level][0] == null) edgeArr[level][0] = head; // 第一次遇到的便是左边界，此后任何节点都不能改了
        edgeArr[level][1] = head; //往右边的每个节点都可以改这个值，直到最右的节点覆盖这个值。
        setEdgeArr(head.left, level+1, edgeArr); //从左边开始
        setEdgeArr(head.right, level+1, edgeArr); //再往右边跑
    }

    private static void printLeafNotInArr(Node head, int level, Node[][] edgeArr) {
        if (head == null) {
            return;
        }
        if (isLeaf(head) && isNotInArr(head, level, edgeArr)) {
            System.out.print(head.val + " ");
        }
        printLeafNotInArr(head.left, level+1, edgeArr);
        printLeafNotInArr(head.right, level+1, edgeArr);
    }

    private static boolean isNotInArr(Node head, int level, Node[][] edgeArr) {
        return edgeArr[level][0] != head && edgeArr[level][1] != head;
    }


    private static boolean isLeaf(Node head) {
        return head.left == null && head.right == null;
    }
    // 标准2：
    public static void printEdge2(Node head){
        if (head == null) {
            return;
        }
        System.out.print(head.val+" ");
        if (head.left == null || head.right == null) { // 将棒状走完
            // 递归打印非空的边界
            printEdge2(head.left == null ? head.right : head.left);
        } else { // 直到走到左右都有的节点
            printLeftEdge(head.left, true); // 打印左边界 isBorder表示head.left是边界节点
            printRightEdge(head.right, true); // 打印右边界 isBorder表示head.right是边界节点
        }
    }
    // 打印左边界

    private static void printLeftEdge(Node head, boolean isBorder) {
        if (head == null) {
            return;
        }
        if (isBorder || isLeaf(head)) {// head是边界节点或者是叶子节点 就打印
            System.out.print(head.val +" ");
        }
        printLeftEdge(head.left, true); // 打印左边界，由于这是打印左边界的函数 于是把左边节点认为是边界
        printLeftEdge(head.right, isBorder&&(head.left == null)); //当左边界不存在，右边节点就可以露头了
    }

    private static void printRightEdge(Node head, boolean isBorder) {
        if (head == null) {
            return;
        }
        printRightEdge(head.left, isBorder && (head.right == null));
        printRightEdge(head.right, true);
        if (isBorder || isLeaf(head)) {
            System.out.print(head.val + " ");
        }
    }


    public static void main(String[] args) {
//        Node head = new Node(6);
//        head.left = new Node(4);
//        head.right = new Node(7);
//        head.left.left = new Node(2);
//        head.left.right = new Node(5);
//        head.right.right = new Node(9);
//
//        head.left.left.left = new Node(1);
//        head.left.left.right = new Node(3);
//        head.right.right.left = new Node(8);

        Node head = new Node(1);
        head.left = new Node(2);
        head.left.right = new Node(4);
        head.left.right.left = new Node(7);
        head.left.right.right = new Node(8);
        head.left.right.right.right = new Node(11);
        head.left.right.right.right.left = new Node(13);
        head.left.right.right.right.right = new Node(14);

        head.right = new Node(3);
        head.right.left = new Node(5);
        head.right.left.left = new Node(9);
        head.right.left.left.left = new Node(12);
        head.right.left.left.left.left = new Node(15);
        head.right.left.left.left.right = new Node(16);
        head.right.left.right = new Node(10);
        head.right.right = new Node(6);

        printEdge1(head);
        System.out.println();
        printEdge2(head);
    }

}
