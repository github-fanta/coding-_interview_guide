package easy.part2_linked_list;

/**
 * 分别实现两个函数，一个可以删除单链表中倒数第K个节点，另一个可以删除双链表中倒数第K个节点
 * @author liqiang
 *
 */
public class Code02_RemoveLastKthNode {

	/*
	 * 单链表
	 */
	public static class Node {
		int val;
		Node next;
		public Node(int data) {
		    val = data;	
		}
		
	}
	
	public static Node removeLastKthNode(Node head, int k) {
	    if (head == null || k < 1) {
	    	return head;
	    }
	    Node cur = head;
	    // 直到最后的null 每个node都减了个1 有一个算一个 把k减成负的(正常思维)
	    while(cur != null) {
	    	k --;
	    	cur = cur.next;
	    }
	   // 如果k减少为0了，说明k和node的个数相等，即倒数第k个就是第一个
	    if (k == 0) {
	    	head = head.next;
	    }
	    // 如果k 减成负的了。再遍历一遍 加回来。
	    if (k < 0) {
	    	cur = head;
	    	// 遍历到删除节点的前一个停下里
	    	while (++k != 0) {
	    	   cur = cur.next;
	    	}
	    	// 手术割掉
	    	cur.next = cur.next.next;
	    	
	    }
	    
	    // 减了半天k还是大于0 说明k大于节点数  返回head即可
	    return head;
	}
	
	/*
	 * 双链表
	 */
	static class DoubleNode {
		int val;
		DoubleNode pre;
		DoubleNode next;
		DoubleNode(int data) {
			val = data;
		}
	}
	
	public static DoubleNode removeLastKthDoubleNode(DoubleNode head, int k) {
		if (head == null || k < 1) {
			return head;
		}
		DoubleNode cur = head;
		while(cur != null) {
			k --;
			cur = cur.next;
		}
		
		if (k == 0) {
			head = head.next;
			head.pre = null;
		}
		
		if (k < 0) {
			cur = head;
			while (k+1 != 0) {
				cur = cur.next;
				k ++;
			}
			// nextNode有可能是null
			DoubleNode nextNode = cur.next.next;
			cur.next = nextNode;
			if (nextNode != null) {
				nextNode.pre = cur;
			}
			
		}
		return head;
	}
	public static void main(String[] args) {
		Node head1 = new Node(0);
		head1.next = new Node(-1);
		head1.next.next = new Node(-2);
		head1.next.next.next = new Node(3);
		// 删除倒数第2个
		head1 = removeLastKthNode(head1, 2);
		while(head1 != null) {
			System.out.println(head1.val);
			head1 = head1.next;
		}
//		DoubleNode head = new DoubleNode(0);
//		head.next = new DoubleNode(-1); head.next.pre = head;
//		head.next.next = new DoubleNode(-2);head.next.next.pre = head.next;
//		head.next.next.next = new DoubleNode(3);head.next.next.next.pre = head.next.next;
//		// 删除掉倒数第4个(即表头)
//		head = removeLastKthDoubleNode(head, 4);
//		while(head != null) {
//			System.out.println(head.val);
//			head = head.next;
//		}
	}
}