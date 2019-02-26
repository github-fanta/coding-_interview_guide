package easy.part8_array_matrix;

/**
 * 需要排序的最短子数组长度
 * 给定一个无序数组arr, 求出需要排序(不妨要求从小到大)的最短子数组长度
 * 例如： arr=[1,5,3,4,2,6,7]返回4，因为只有[5,3,4,2]需要排序。
 */
public class Code04_UnsortedLength {
    public static int getUnsortedLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = arr[arr.length - 1];  // 把min试着卡在一个地方
        int leftIdx = -1;  // 用leftIdx不断探索左边界
        for (int i=arr.length-2; i > -1; i--) {
            if (arr[i] > min) { // 看不符合左边界的最远到哪里
                leftIdx = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (leftIdx == -1) return 0; // 数组有序

        int max= arr[0];
        int rightIdx = arr.length;
        for (int i=1; i < arr.length; i++) {
            if (arr[i] < max) {
                rightIdx = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return rightIdx - leftIdx + 1;
    }

    public static void main(String[] args) {
        System.out.println(getUnsortedLength(new int[]{1,5,3,4,6,2,7}));
    }
}
