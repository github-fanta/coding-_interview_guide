package normal.part7_array_matrix;

import java.util.Arrays;
import java.util.HashSet;

public class Code02_MaxLenIntegratedArr {

    public static void main(String[] args) {
        int[] arr = {5, 5, 3, 2, 6, 4, 3};
        System.out.println(maxLenIntegratedArr(arr));
        System.out.println(maxLenIntegratedArr2(arr));

        int[] arr2 = {5, 3, 4, 6, 2};
        System.out.println(maxLenIntegratedArr(arr2));
        System.out.println(maxLenIntegratedArr2(arr2));
    }

    /**
     * 方法1
     * @param arr
     * @return
     */
    public static int maxLenIntegratedArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int resLen = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (isIntegrated(arr, i, j)) {
                    resLen = Math.max(resLen, j - i + 1);
                }
            }
        }
        return resLen;
    }

    private static boolean isIntegrated(int[] arr, int start, int end) {
        int[] subArr = Arrays.copyOfRange(arr, start, end + 1);
        Arrays.sort(subArr);
        for (int i = 0; i < subArr.length - 1; i++) {
            if (subArr[i] + 1 != subArr[i+1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法2:一个数组中如果没有重复元素,并且如果最大值减去最小值,再加上1的结果等于元素个数  那么这个数组就是可整合数组.比如[3,2,5,6,4] max - min +1 = 6-2+1 = 5 == 元素个数
     */
    public static int maxLenIntegratedArr2(int[] arr) {
        if (arr == null || arr.length ==  0) {
            return 0;
        }

        int resLen = 0;
        int max = 0;
        int min = 0;

        HashSet<Integer> set= new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                if (set.contains(arr[j])) {
                    break;
                }
                set.add(arr[j]);
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                if (max - min == j - i) {
                    resLen = Math.max(resLen, j - i + 1);
                }
            }
            set.clear();
        }
        return resLen;
    }

}
