package normal.part7_array_matrix;

public class Code07_FindRangeMinIdxInArr {

    public static int findRangeMinIdxInArr (int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid - 1] < arr[mid]) {
                // 左边更小 往左边找
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                // 右边更小  往右边找
                left = mid + 1;
            }else  {
                // 既不比左边小  也不比右边小
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(findRangeMinIdxInArr(new int[] {1,2,3,4,5}));
        System.out.println(findRangeMinIdxInArr(new int[] {10,6,3,4,5}));
        System.out.println(findRangeMinIdxInArr(new int[] {10,7,4,1}));
        System.out.println(findRangeMinIdxInArr(new int[] {10}));
    }
}
