package normal.part3_binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计和生成不同的二叉树
 * 给定一个整数N， 如果N<1 代表空树结构，否则代表中序遍历的结果为{1,2,3,...,N}。请返回可能的二叉树结构有多少。
 *
 * @Author liq
 * @Date 2020/6/12
 */
public class Code08_GenDiffTrees {



    public static int treeNums(int n) {
        if (n < 2) {
            return 1;
        }
        int[] nums = new int[n + 1];
        nums[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                nums[i] += nums[j - 1] * nums[i - j];
            }
        }
        return nums[n];
    }

    // 进阶问题：N的含义不变，假设可能的二叉树结构有M种， 返回构造的二叉树的头结点 每一棵二叉树代表一种可能的结构
    public static List<Node> genTrees(int n) {
        return generate(1, n);
    }

    public static List<Node> generate(int start, int end) {
        List<Node> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
        }

        Node head = null;
        for (int i = start; i < end + 1; i++) {
            head = new Node(i);
            // 在同一个head下有多个左右不同的树来排列组合 但是这个head只有一个，跟不同的左右子树组合生成一个树;所以需要每组合一次 就要克隆一次。
            List<Node> lNodes = generate(start, i - 1);
            List<Node> rNodes = generate(i + 1, end);
            for (Node l : lNodes) {
                for (Node r : rNodes) {
                    head.left = l;
                    head.right = r;
                    res.add(cloneTree(head));
                }
            }
        }
        return res;
    }

    private static Node cloneTree(Node head) {
        if (head == null) {
            return null;
        }
        Node res = new Node(head.value);
        res.left = cloneTree(res.left);
        res.right = cloneTree(res.right);
        return res;
    }

    public static void main(String[] args) {
        //System.out.println(treeNums(8));
        List<Node> nodes = genTrees(8);
        for (Node n : nodes) {
            System.out.println(n.value + " ");
        }
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
