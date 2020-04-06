package normal.part3_binary_tree;

import java.util.HashMap;
import java.util.Map;

public class Code02_SumMaxLenInBinTree {
    static class Node{
        int value;
        Node left;
        Node right;
        public Node(int val) {
            this.value = val;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(-3);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(0);
        head.left.right.left = new Node(1);
        head.left.right.right = new Node(6);

        head.right = new Node(-9);
        head.right.left = new Node(2);
        head.right.right = new Node(1);
        Map<Integer, Integer> sumLevelMap = new HashMap<>();
        // 要让最早的0在root，否则如果之后有条链路到第n层的和是0 那么就会记录第n层是0， 遍历到第n+m层时，若n~m的sum是targetSum
        // 那么记录的最长链路长度就是 n~m了  而本应该是root~m更长。
        // 如果提前放了(0,0) ,那之前有这个值，说明距离当前遍历节点更远就已经有了，就不用记录在map中，因为这样，下面的节点找出来链路会更长。
        sumLevelMap.put(0, 0);
        int maxLen = preOrder(head, 6, 0, 1, sumLevelMap, 0);
        System.out.println(maxLen);
    }

    public static int preOrder(Node head, int targetSum, int curSum, int curLevel, Map<Integer, Integer> sumLevelMap, int maxLen) {
        if (head == null) {
            return maxLen;
        }

        curSum += head.value;
        if (!sumLevelMap.containsKey(curSum)) {
             sumLevelMap.put(curSum, curLevel);
        }
        if (sumLevelMap.containsKey(curSum - targetSum)) {
            maxLen = Math.max(maxLen, curLevel - sumLevelMap.get(curSum - targetSum));
        }
        maxLen = preOrder(head.left, targetSum, curSum, curLevel + 1, sumLevelMap, maxLen);
        maxLen = preOrder(head.right, targetSum, curSum, curLevel + 1, sumLevelMap, maxLen);
        if (curLevel == sumLevelMap.get(curSum)) {
            sumLevelMap.remove(curSum);
        }
        return maxLen;
    }
}
