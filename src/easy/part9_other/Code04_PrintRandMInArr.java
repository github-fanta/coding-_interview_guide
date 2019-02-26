package easy.part9_other;

/**
 * 给定一个长度为N且没有重复元素的数组arr和一个整数m，实现函数等概论随机打印arr中的M个数。
 */
public class Code04_PrintRandMInArr {
    public static void printRandMInArray(int[] arr, int m) {
        if (m < 1 || m > arr.length - 1) return;
        int count = 0;
        while (++count <= m) {
            int i = (int) (Math.random()*(arr.length - count));
            System.out.println(arr[i]);
            swap(arr, i, arr.length - count);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void main(String[] args) {
        printRandMInArray(new int[]{2,1,4,6,9}, 3);
    }
}
