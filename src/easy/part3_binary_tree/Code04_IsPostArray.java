package easy.part3_binary_tree;

/**
 * 给定一个整形数组arr, 已知其中没有重复值，判断arr是否可能是节点值类型为整型的搜索二叉树后序遍历的结果。
 * 进阶：如果整型数组arr中没有重复值，且已知是一棵搜索二叉树的后序遍历结果，通过数组arr重构二叉树。
 */
public class Code04_IsPostArray {
    static class Node{
		int value;
		Node left;
		Node right;
		Node(int value){
			this.value = value;
		}
	}
    // 第一题：判断是否是后续
	public static boolean isPostArray(int[] arr) {
    	if (arr == null || arr.length == 0) {
    		return false;
		}
    	return check(arr, 0, arr.length - 1);
	}

	private static boolean check(int[] arr, int start, int end) {
        if (start <= end) {
        	return true;
		}
        int moreStart = start;
        while (arr[moreStart] < arr[end]) {
			moreStart++;
		}
        for (int i = moreStart; i < end; i++) {
        	if (arr[i] < arr[end]) {
				return false;
			}
		}
        return check(arr, start, moreStart - 1) && check(arr, moreStart, end - 1);
	}

	// 第二题：后续遍历数组重构二叉树
	public static Node postArrToBST(int[] arr) {
    	if (arr == null || arr.length == 0) {
    		return null;
		}
    	return posToBST(arr, 0, arr.length - 1);
	}

	private static Node posToBST(int[] arr, int start, int end) {
        if (start >= end) {
        	return null;
		}
        Node head = new Node(arr[end]);
        int moreStart = start;
        while (arr[moreStart] < arr[end]) {
        	moreStart ++;
		}
        head.left = posToBST(arr, start, moreStart - 1);
		head.right = posToBST(arr, moreStart, end - 1);
	    return head;
	}

	public static void main(String[] args) {
		System.out.println(isPostArray(new int[]{1,2,3,4,5,6}));
		Node head = postArrToBST(new int[]{1,2,3,4,5,6});

	}
}
