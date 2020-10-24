package normal.part7_array_matrix;

public class Code04_MaxSubArrLenForAimSum {

    public static int getMaxLen(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int maxLen = 0;
        while (right < arr.length) {
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
                sum -= arr[left++];
            } else if (sum < k) {
                right ++;
                if (right >= arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(getMaxLen(new int[]{8,9,7,4,2,6,8}, 6));
    }
}
