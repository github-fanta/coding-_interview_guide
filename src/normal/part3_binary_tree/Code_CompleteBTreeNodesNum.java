package normal.part3_binary_tree;

public class Code_CompleteBTreeNodesNum {

    static class Node{
        int value;
        Node left;
        Node right;
        Node(int value){
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);

        System.out.println(nodeNum(head));
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head, 1, mostLeftLevel(head, 1));
    }

    private static int process(Node head, int curLevel, int HIGH) {
        if (curLevel == HIGH) {
            return 1;
        }

        if(mostLeftLevel(head.right, curLevel + 1) == HIGH) {
            //右子树最左能触到底部，说明左子树是满的 左子树 + 当前head + 右子树 注意 2^n =  1 << n`
            return ((1 << (HIGH - curLevel)) - 1) + 1 + process(head.right, curLevel + 1, HIGH);
        } else {
            // 右子树不能触到底部，说明右子树差最后一层 且是满二叉树。 左子树 + 当前head + 右子树
            return process(head.left, curLevel + 1, HIGH) + 1 + (1 << (HIGH - curLevel - 1) - 1);
        }
    }

    private static int mostLeftLevel(Node head, int curLevel) {
        while (head != null) {
            head = head.left;
            curLevel ++;
        }
        return curLevel - 1;
    }

}
