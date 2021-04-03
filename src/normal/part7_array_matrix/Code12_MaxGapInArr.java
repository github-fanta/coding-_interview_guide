package normal.part7_array_matrix;

public class Code12_MaxGapInArr {

    public static int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int len  = nums.length;
        for (int i = 0; i < len; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (max == min){
            return 0;
        }

        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        boolean[] hasNum = new boolean[len + 1];

        for (int i = 0; i < len; i++) {
            int idx = bucketIdx(len, max, min, nums[i]);
            maxs[idx] = hasNum[i] ? Math.max(maxs[idx], nums[i]) : nums[i];
            mins[idx] = hasNum[i] ? Math.min(mins[idx], nums[i]) : nums[i];
            hasNum[i] = true;
        }

        int lastNum = 0;
        int i = 0;
        // 找到第一个不为空的桶
        for (i = 0; i < len + 1; i++) {
            if (hasNum[i]) {
                lastNum = maxs[i];
                break;
            }
        }

        int resMaxGap = 0;
        for (; i < len + 1; i++) {
            if (hasNum[i]) {
                resMaxGap = Math.max(resMaxGap, mins[i] - lastNum);
                lastNum = maxs[i];
            }
        }
        return resMaxGap;
    }

    private static int bucketIdx(long len, long max, long min, long cur) {
        return (int)((cur - min) * len / (max - min));
    }

    public static void main(String[] args) {
        System.out.println(maxGap(new int[]{9,3,1,10}));
        System.out.println(maxGap(new int[]{5,5,5,5}));
    }
}
