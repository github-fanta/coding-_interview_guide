package easy.part8_array_matrix;

public class Code08_MaxSumInArray {
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < arr.length; i++) {
            // curSum一路向前加，顺便记录最高水位，如果出现负数，那么就归零，重新加。因为不要累赘我也能加到最大
            // 为什么还要带上负数向前加呢? 之所以向前拖这么长就是因为前面还有价值，如果没有价值早扔了
            curSum += arr[i];
            max = Math.max(max, curSum); // max是最高水位记录者，只会高，不会低。
            // 一旦被减成负数就清零，因为之前的"高水位"已经被max记录下来了。curSum重新开始计算就行了
            // 因为之前的curSum这部分只能拉后腿。拉后腿肯定是后半部分出现负数。所以肯定不会有curSum中后半部分和
            // 以后的数加起来是最大值的情况。
            if (curSum < 0) {
                curSum = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{1,-2,3,5,-2,6,-1}));
    }
}
