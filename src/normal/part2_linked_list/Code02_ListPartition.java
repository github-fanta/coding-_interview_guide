package normal.part2_linked_list;

public class Code02_ListPartition {
    public static class Node{
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int count = 0;
        while (cur != null) {
            count ++;
            cur = cur.next;
        }
        Node[] arr = new Node[count];
        cur = head;
        for (int i = 0; i < count; i++) {
            arr[i] = cur;
            cur = cur.next;
        }
        arrPartition(arr, pivot);
        for (int i = 0; i < count-1; i++) {
            arr[i].next = arr[i+1];
        }
        arr[count - 1].next = null;
        return arr[0];
    }

    private static void arrPartition(Node[] arr, int pivot) {
        int small = -1;
        int big = arr.length;
        int curIdx = 0;
        while (curIdx != big) {
            if (arr[curIdx].val < pivot) {
                swap(arr, ++small, curIdx++);  // curIdx敢直接向前移是因为 从small端来的都是检查过的比pivot小的
            } else if (arr[curIdx].val > pivot) {
                swap(arr, --big, curIdx);
            } else {
                curIdx++;
            }
        }
    }
    private static void swap(Node[] arr, int a, int b) {
        Node temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //方法2：将链表装入三个筒之后重连
    public static Node listPartition2(Node head, int pivot) {
        //small筒之中的链表头和尾
        Node sHead = null;
        Node sTail = null;
        //equal筒之中的链表头和尾
        Node eHead = null;
        Node eTail = null;
        //larger筒之中的链表头和尾
        Node lHead = null;
        Node lTail = null;
        Node next = null; //保存下一个结点
        while(head != null) {
            next = head.next; // 保存next作为剩下的链头
            head.next = null; // 断连，取出head
            if (head.val < pivot) {  //放入small筒中
                if (sHead == null) {   //若是放入筒中的第一个元素
                    sHead = head;
                    sTail = head;
                }else {
                    sTail.next = head;
                    sTail = sTail.next;
                }
            }else if(head.val == pivot) {  //equal筒
                if (eHead == null) {		//若是放入筒中的第一个元素
                    eHead = head;
                    eTail = head;
                }else {
                    eTail.next = head;
                    eTail = eTail.next;
                }
            }else {						//larger筒
                if (lHead == null) {   //若是放入筒中的第一个元素
                    lHead = head;
                    lTail = head;
                }else {
                    lTail.next = head;
                    lTail = lTail.next;
                }
            }
            head = next;
        }
        // 三个桶之间的两次相连
//        if (sTail != null) {
//            sTail.next = eHead;
//            if (eTail == null) eTail = sTail;
//        }
//        if (eTail != null) {
//            eTail.next = lHead;
//        }
//        return sHead != null ? sHead : eHead != null ? eHead : lHead;

        // 连接方法2：
        // 连接1、2两个桶
        if (sTail != null) {
            sTail.next = eHead;
        }
        // 连接2、3两个桶  连接第三个桶要注意， 可以从第二个桶连  也可以从第一个桶连过来
        if (eTail != null) {
            eTail.next = lHead;
        } else {
            if (sTail != null) {
                sTail.next = lHead;
            }
        }
        return sHead != null ? sHead : eHead != null ? eHead : lHead;
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);

        Node temp = head1;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
//        temp = listPartition1(head1, 5);
        temp = listPartition2(head1, 3);
        System.out.println();
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }
}
