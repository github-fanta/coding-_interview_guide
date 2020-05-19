package normal.part2_linked_list;

import java.util.LinkedList;

/**
 * 将一搜索二叉树转化成为一有序的双向链表
 */
public class Code05_BinaryTreeToList {
    static class Node{
        int val;
        Node left;
        Node right;
        Node(int val) {
            this.val = val;
        }
    }

    // 方法一：队列等容器二叉树中序遍历结果的方法。时间复杂度为O(N) 额外空间复杂度为O(N)
    public static Node binTreeToList1(Node root){
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        LinkedList<Node> queue = new LinkedList<>();
        inOrderToQueue(queue, root);  //中序遍历全入队列

        Node head = queue.poll(); //标记链表头

        Node tail = head; // 从当前开始遍历
        Node temp = null; // 弹出的临时节点
        while (!queue.isEmpty()) {
            temp = queue.poll(); // 每次弹出一个节点
            tail.right = temp;  // 之前的链尾cur连接上temp。
            temp.left = tail;
            tail = temp;
        }
        tail.right = null;
        return head;
    }

    private static void inOrderToQueue(LinkedList<Node> queue, Node root) {
        if (root == null) return;
        inOrderToQueue(queue, root.left);
        queue.offer(root);
        inOrderToQueue(queue, root.right);
    }

    // 方法二：("杰克逊方法") 最右节点指向链头的模型，递归来做
    public static Node binTreeToList2(Node head) {
        if (head == null) {
            return null;
        }
        Node last = process(head);
        // 利用"杰克逊"姿势的链的尾巴拿到链头。
        head = last.right; // 拿到链头
        last.right = null; //"杰克逊"姿势恢复到正常链表的姿势。
        return head; //返回链头 大功告成。
    }

    // 划分左右链条，分别转化成双向链表
    private static Node process(Node head) {
        if (head == null) return head;
        Node lEnd = process(head.left); // 处理左子树的双向链表
        Node rEnd = process(head.right); // 处理右子树的双向链表
        Node lStart = lEnd == null ? null : lEnd.right; // 拿到左子树链表头
        Node rStart = rEnd == null ? null : rEnd.right; // 拿到右子树链表头
        if (lStart != null && rStart != null) { //左右子链都不为空
            // 连接左子树链条
            lEnd.right = head;
            head.left = lEnd;
            // 连接右子树链条
            head.right = rStart;
            rStart.left = head;
            // 右链结尾指向左链头
            rEnd.right = lStart;
            return rEnd; //返回右链尾
        } else if (lStart != null) { // 左链不为空，右链为空
            // 连接左链
            lEnd.right = head;
            head.left = lEnd;
            // 最右节点指向左链头
            head.right = lStart;
            return head; //返回右链尾
        } else if (rStart != null) { //左链为空，右链不为空
            // 连接右链
            head.right = rStart;
            rStart.left = head;
            // 摆好姿势
            rEnd.right = head;
            // 返回右链尾
            return rEnd;
        } else { //左右都为null
            head.right = head; //自己指向自己
            return head; // 返回自己
        }
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.left = new Node(4);
        head.right = new Node(7);
        head.left.left = new Node(2);
        head.left.right = new Node(5);
        head.right.right = new Node(9);

        head.left.left.left = new Node(1);
        head.left.left.right = new Node(3);
        head.right.right.left = new Node(8);

        //Node res = binTreeToList1(head);
        Node res = binTreeToList2(head);
        while (res != null) {
            System.out.print(res.val+" ");
            res = res.right;
        }
    }
}
