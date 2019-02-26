package easy.part3_binary_tree;

public class Code02_IsTreeContains {
    static class Node{
		int value;
		Node left;
		Node right;
		Node(int value){
			this.value = value;
		}
	}

	public static boolean contains(Node t1, Node t2) {
		return check(t1, t2) || check(t1.left, t2) || check(t1.right, t2);
	}

	private static boolean check(Node t, Node t2) {
        if (t2 == null) {
        	return true;
		}
        if (t == null || t.value != t2.value) {
        	return false;
		}
        return check(t.left, t2.left) && check(t.right, t2.right);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        Node head2 = new Node(3);
        head2.right = new Node(5);
		boolean isContains = contains(head, head2);
		System.out.println(isContains);
	}
}
