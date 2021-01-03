package normal.part1_stack_queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 滑动窗口最大值数组
 */
public class Code03_SlidingWindowMaxArray {
    public static ArrayList<Integer> getMaxArr(int[] arr, int winSize) {
        ArrayList<Integer> resList = new ArrayList<>();
        LinkedList<Integer> qMax = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            // qMax构成一个自成系统的"后宫"，作用是能够残酷竞争出当前阶段的最大值(在first端)
            while (!qMax.isEmpty() && arr[i] >= arr[qMax.peekLast()]) {
                qMax.pollLast();  //从last端开始，所有没有自己实力强的，都踢出去
            }
            qMax.addLast(i); //然后自己上位

            if (qMax.peekFirst() <= i - winSize) qMax.pollFirst(); // 虽然你是最大，但是可能过期了(不是这个窗口内的值了)，所以在添加到结果前排除掉
            if (i >= winSize - 1) resList.add(arr[qMax.peekFirst()]); // 从构成窗口后，添加这个"后宫"系统中的最大的(就是First)
        }
        return resList;
    }
    public static void main(String[] args) {
        int[] data = {2,3,4,2,6,2,5,1};
        List<Integer> list1 = getMaxArr(data, 3);
        for (int i : list1) {
            System.out.print(i+" ");
        }
    }
}

