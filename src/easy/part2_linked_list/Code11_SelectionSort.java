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
        head.next.next = new Node(2);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.next = new Node(2);
        head = selectionSort(head);
        while (head != null) {
            System.out.println(head.val+" ");
            head = head.next;
        }
    }
}
