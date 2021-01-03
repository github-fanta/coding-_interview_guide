package easy.part2_linked_list;

/**
 * 给定一个无序单链表的头节点head，实现单链表的选择排序
 * 要求:额外空间复杂度为O(1)。
 */
public class Code11_SelectionSort {

    static class Node {
        int val;
        Node next;
        Node(int data) {
            this.val = data;
        }
    }
    public static Node selectionSort(Node head) {
        if (head == null) {
            return null;
        }

        // 未排序的头节点
        Node unsortedHead = head;
        // 一次遍历找到最小值的前一个
        Node smallPre = null;
        Node small = null;
        Node tail = null;
        while (unsortedHead != null) {
            small = unsortedHead;
            smallPre = getSmallestPreNode(unsortedHead);
            if (smallPre != null) {
                // 找到最小的
                small = smallPre.next;
                // 删除掉
                smallPre.next = small.next;
                // small 断连 其实不用，最后一个节点一定是unsortedHead == small 然后指向null
                //small.next = null;
            }
            // 如果是第一个最小的节点，将其标记为新头
            if (tail == null) {
                head = small;
            }else {
                tail.next = small;
            }
            tail = small;
            // 当前最小正是当前所指的节点,那就移向下一个 否则不变
            if(unsortedHead == small) {
                unsortedHead = unsortedHead.next;
            }
        }
        return head;
    }

    public static Node selectionSort2(Node head) {
        if (head == null) {
            return null;
        }
        Node unSortedPartHead = head;
        Node tail = null;
        // 将链表分成两个部分，一个是已经排序的，一个是未排序的部分，unSortedPartHead 就是未排序链表的表头。unSortedPartHead从左到右，一旦到右不会回到左边
        while (unSortedPartHead != null) {
            Node smallPre = getSmallestPre(unSortedPartHead);
            Node curSmall = null;
            // 当前就是最小
            if (smallPre == null) {
                curSmall = unSortedPartHead;
                unSortedPartHead = unSortedPartHead.next;
            } else {
                // 当前不是最小，在未排序链表里找到来最小， 脱链
                curSmall = smallPre.next;
                smallPre.next = curSmall.next;
            }
            // 将上面拿到的最小节点 连接到已排序的链表里。
            if (tail == null) {
                head = curSmall;
            } else {
                tail.next = curSmall;
            }
            tail = curSmall;
        }
        return head;
    }


    public static Node selectionSort3(Node head) {
        if (head == null) {
            return null;
        }
        Node unSortedPartHead = head;
        Node tail = null;
        while (unSortedPartHead != null) {
            Node smallPre = getSmallPre(unSortedPartHead);
            Node curSmall = null;
            if (smallPre == null) {
                curSmall = unSortedPartHead;
                unSortedPartHead = unSortedPartHead.next;
            } else {
                curSmall = smallPre.next;
                smallPre.next = curSmall.next;
            }
            if (tail == null) {
                head = curSmall;
            } else {
                tail.next = curSmall;
            }
            tail = curSmall;
        }
        return head;
    }

    private static Node getSmallPre(Node head) {
        Node cur = head;
        Node pre = null;
        Node small = head;
        Node smallPre = null;
        while (cur != null) {
            if (cur.val < small.val) {
                small = cur;
                smallPre = pre;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }


    private static Node getSmallestPre(Node head) {
        Node small = head;
        Node smallPre = null;
        Node cur = head;
        Node curPre = null;
        while (cur != null) {
            if (cur.val < small.val) {
                smallPre = curPre;
                small = cur;
            }
            curPre = cur;
            cur = cur.next;
        }
        return smallPre;
    }

    private static Node getSmallestPreNode(Node head) {
        Node smallPre = null;
        Node small = head;
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            if (cur.val < small.val) {
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(3);
        head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(4);
        head = selectionSort3(head);
        while (head != null) {
            System.out.println(head.val+" ");
            head = head.next;
        }
    }
}
