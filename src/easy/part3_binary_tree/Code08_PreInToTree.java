package easy.part3_binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 先序、中序和后序数组两两结合重构二叉树
 */
public class Code08_PreInToTree {
    public static class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }
    // 题目一：先序+中序 恢复树
    public static Node preInToTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        Node head = new Node(pre[preStart]);
        int idx = inStart;
        // 这块可以用map预处理一下 放入节点值和中序数组下标的映射，以便更快找到idx
        while (in[idx] != pre[preStart]) {
            idx ++;
        }
        head.left = preInToTree(pre, preStart + 1, preStart + (idx - inStart), in, inStart, idx - 1);
        head.right = preInToTree(pre, preStart + (idx - inStart) + 1, preEnd, in, idx + 1, inEnd);
        return head;
    }



//    public static Node preInToTree(int[] pre, int preStart, int preEnd, int in, int inStart) {
//
//    }















































    // 题目二：中序+后序 恢复树
    public static Node inPos(int[] in, int inStart, int inEnd, int[] pos, int posStart, int posEnd) {
        if (in == null || pos == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; ++i) {
            map.put(in[i], i);
        }
        return inPosToTree(in, inStart, inEnd, pos, posStart, posEnd, map);
    }
    public static Node inPosToTree(int[] in, int inStart, int inEnd, int[] pos, int posStart, int posEnd, Map<Integer, Integer> map) {
        if (posStart > posEnd) {
            return null;
        }
        Node head = new Node(pos[posEnd]);
        int idx = map.get(head.value);
        head.left = inPosToTree(in, inStart, idx - 1, pos, posStart, posStart + (idx - inStart - 1), map);
        head.right = inPosToTree(in, idx + 1, inEnd, pos, posStart + (idx - inStart - 1) + 1, posEnd - 1, map);
        return head;
    }
    public static void main(String[] args) {
        //Node head = preInToTree(new int[]{1, 2, 4, 5, 8, 9, 3, 6, 7}, 0, 8, new int[]{4, 2, 8, 5, 9, 1, 6, 3, 7}, 0, 8);
//        Node head = inPos(new int[]{4, 2, 8, 5, 9, 1, 6, 3, 7}, 0, 8, new int[]{4,8,9,5,2,6,7,3,1}, 0, 8);
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer.toString());
    }
}
