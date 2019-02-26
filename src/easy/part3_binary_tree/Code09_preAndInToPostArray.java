package easy.part3_binary_tree;

import java.util.HashMap;
import java.util.Map;

public class Code09_preAndInToPostArray {
    public static class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }
    public static int[] preAndInToPostArr(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        int len = pre.length;
        int[] postArr = new int[len];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            map.put(in[i], i);
        }
        preAndInToPost(pre, 0, len - 1, in, 0, len - 1, postArr, len - 1, map);
        return postArr;
    }

    private static int preAndInToPost(int[] pre, int preStart, int preEnd ,int[] in, int inStart, int inEnd, int[] postArr, int postIdx, Map<Integer, Integer> map) {
        if (preStart > preEnd) {
            return postIdx;
        }
        postArr[postIdx --] = pre[preStart];
        int pos = map.get(pre[preStart]);
        postIdx = preAndInToPost(pre, preEnd - (inEnd - pos) + 1, preEnd, in, pos + 1, inEnd, postArr, postIdx, map);
        return preAndInToPost(pre, preStart + 1, preStart + (pos - inStart), in, inStart, pos - 1, postArr, postIdx, map);
    }

    public static void main(String[] args) {
        int[] postArr = preAndInToPostArr(new int[]{1, 2, 4, 5, 8, 9, 3, 6, 7}, new int[]{4, 2, 8, 5, 9, 1, 6, 3, 7});
        System.out.println(compArr(postArr, new int[]{4,8,9,5,2,6,7,3,1}));
    }

    private static boolean compArr(int[] arrA, int[] arrB) {
        if (arrA == null && arrB == null) {
            return true;
        }
        if (arrA == null || arrB == null) {
            return false;
        }
        if (arrA.length != arrB.length) {
            return false;
        }
        for (int i = 0; i < arrA.length; ++i) {
            if (arrA[i] != arrB[i]) {
                return false;
            }
        }
        return true;
    }
}
