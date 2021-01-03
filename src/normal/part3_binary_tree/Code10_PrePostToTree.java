package normal.part3_binary_tree;

import java.util.HashMap;

public class Code10_PrePostToTree {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static Node prePosToTree(int[] pre, int[] pos) {
        if (pre == null || pos == null) {
            return null;
        }
        // value - posIdx
        HashMap<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < pos.length; i++) {
            idxMap.put(pos[i], i);
        }
        return prePos(pre, 0, pre.length - 1, pos, 0, pos.length - 1, idxMap);
    }

    private static Node prePos(int[] pre, int pi, int pj, int[] pos, int si, int sj, HashMap<Integer, Integer> idxMap) {
        Node head = new Node(pos[sj]);
        sj --;
        if (pi == pj) {
            return head;
        }
        pi ++;
        Integer posIdx = idxMap.get(pre[pi]);
        head.left = prePos(pre, pi, pi + (posIdx - si), pos, si, posIdx, idxMap);
        head.right = prePos(pre, pi + (posIdx - si) + 1, pj, pos, posIdx + 1, sj, idxMap);
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.left.left.left = new Node(8);
        head.left.left.right = new Node(9);

        head.right.left = new Node(6);
        head.right.right = new Node(7);
        int[] pre = new int[]{1,2,4,8,9,5,3,6,7};
        int[] pos = new int[]{8,9,4,5,2,6,7,3,1};
        Node node = prePosToTree(pre, pos);
    }
}
