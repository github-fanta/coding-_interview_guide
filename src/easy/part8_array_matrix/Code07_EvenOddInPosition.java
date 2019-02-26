package easy.part8_array_matrix;

/**
 * 【题目】
 * 给定一个长度不小于2的数组arr, 实现一个函数调整arr, 要么让所有的偶数下标都是偶数，要么让所有的奇数下标都是奇数
 */
public class Code07_EvenOddInPosition {

    public static void modify(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int evenIdx = 0; // 偶下标
        int oddIdx = 1; // 奇下标
        int end = arr.length - 1;
        while (evenIdx <= end && oddIdx <= end) {
            if ((arr[end] & 1) == 1) { // 奇数
                swap(arr, end, oddIdx);
                oddIdx += 2;
            } else {
                swap(arr, end, evenIdx);
                evenIdx += 2;
            }
        }

    }

    private static void swap(int[] arr, int end, int oddIdx) {
        int temp = arr[end];
        arr[end] = arr[oddIdx];
        arr[oddIdx] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,8,3,2,4,6};
        modify(arr);
        for (int e : arr) {
            System.out.println(e);
        }
    }
}
