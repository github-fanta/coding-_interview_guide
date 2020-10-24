package normal.part7_array_matrix;

/**
 * 不重复打印排序数组中相加和为给定值的所有二元组和三元组
 * 例如,arr=[-8,-4,-3,0,1,2,4,5,8,9], k=10, 打印结果为
 * 1,9
 * 2,8
 * 补充题目:给定排序数组arr和整数k, 不重复打印arr中所有相加和为k的不降序三元组.
 */
public class Code03_FindSameSumAllUnit {

    /**
     * 寻找不降序的和为aim的二元组
     * @param arr
     * @param aim
     */
    public static void printUniquePair(int[] arr, int aim) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < aim) {
                left ++;
            } else if (arr[left] + arr[right] > aim) {
                right --;
            } else {
                if (left == 0 || arr[left] != arr[left - 1]) {
                    System.out.println("left:" + arr[left] + "right:" + arr[right]);
                }
                right --;
                left ++;
            }
        }
    }

    /**
     * 寻找不降序的和为aim的三元组
     */
    public static void printUniqueTriad(int[] arr, int aim) {
        if (arr == null || arr.length == 3) {
            return;
        }
        for (int i = 0; i < arr.length - 2; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                int left = i + 1;
                int right = arr.length - 1;
                printRest(arr, i, left, right, aim - arr[i]);
            }
        }
    }

    private static void printRest(int[] arr, int i, int left, int right, int aim) {
        while (left < right) {
            if (arr[left] + arr[right] < aim) {
                left ++;
            } else if (arr[left] + arr[right] > aim) {
                right --;
            } else {
                if (left == i + 1 || arr[left] != arr[left - 1]) {
                    System.out.println("arr[i]:" + arr[i] + " left:" + arr[left] + " right:" + arr[right]);
                }
                left ++;
                right --;
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = {-8, -4, -3, 0, 1, 2, 4, 5, 8, 9};
        System.out.println("Pair:");
        printUniquePair(arr, 10);
        System.out.println("Triad:");
        printUniqueTriad(arr, 10);
    }
}
