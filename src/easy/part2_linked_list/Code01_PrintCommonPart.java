package easy.part2_linked_list;

import javax.xml.soap.Node;

/**
 * 打印两个有序链表的公共部分： 给定两个有序链表的头指针 head1和head2,打印两个链表的公共部分
 * @author liqiang
 *
 */
public class Code01_PrintCommonPart {
    
    public static class Node {
        int value;
        Node next;
        public Node(int data) {
	        value = data;	
        }
        
    }

    public static void printCommonPart(Node head1, Node head2) {
        while(head1 != null && head2 != null) {
        	if(head1.value < head2.value) {
        		head1 = head1.next;
        	}else if(head2.value < head1.value) {
        		head2 = head2.next;
        	}else {
        		System.out.println(head1.value + " ");
        		head1 = head1.next;
        		head2 = head2.next;
        		
        	}
        	
        }
        
    }
    
    public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
	    	
		Node head2 = new Node(0);
		head2.next = new Node(2);
		head2.next.next = new Node(3);
		printCommonPart(head1, head2);
	}
}
