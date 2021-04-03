package zz_leetcodeguide.TwoPoints;

public class Code02_TwoArrayMerge {
    public static void main(String[] args) {
        int[] merge = merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
        for (int i : merge) {
            System.out.print(i + " ");
        }
    }

    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int idx1 = m - 1;
        int idx2 = n - 1;
        int p = m + n - 1;

        while (idx1 >= 0 && idx2 >= 0) {
            nums1[p--] = (nums1[idx1] > nums2[idx2]) ? nums1[idx1--] : nums2[idx2--];
        }
        if (idx2 >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, idx2 + 1);
        }
        return nums1;
    }
}
