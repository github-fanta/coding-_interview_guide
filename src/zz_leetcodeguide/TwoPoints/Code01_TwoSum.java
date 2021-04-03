package zz_leetcodeguide.TwoPoints;

public class Code01_TwoSum {

    public static void main(String[] args) {
        int[] nums = find2NumInSortedArray(new int[]{2, 7, 11, 15}, 9);
        for (int num : nums) {
            System.out.print(num + ", ");
        }
    }

    public static int[] find2NumInSortedArray(int[] arr, int target) {
        if (arr == null|| arr.length <= 1) {
            return new int[2];
        }
        int lIdx = 0;
        int rIdx = arr.length - 1;
        while (lIdx < rIdx) {
            if (arr[lIdx] + arr[rIdx] == target) {
                return new int[]{lIdx, rIdx};
            }
            if (arr[lIdx] + arr[rIdx] < target) {
                lIdx ++;
            } else if (arr[lIdx] + arr[rIdx] > target) {
                rIdx --;
            }
        }
        return new int[2];
    }
}
