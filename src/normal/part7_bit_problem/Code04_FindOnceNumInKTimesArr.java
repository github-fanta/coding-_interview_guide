package normal.part7_bit_problem;

public class Code04_FindOnceNumInKTimesArr {

    public static int getOnceNum (int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int[] bitArr = new int[32];
        // 1. 每个数字转成K 进制  无进位累加
        for (int num : arr) {
            int[] numArr = from10ToK(num, k);
            for (int i = 0; i < bitArr.length; i++) {
                bitArr[i] = (bitArr[i] + numArr[i]) % k;
            }
            // 2. 放进bitArr   (倒序的余数)

        }
        // 3. 转换回来就是10进制的结果

        int res = fromKTo10(bitArr, k);
        return res;
    }

    private static int fromKTo10(int[] bitArr, int k) {
        int res = 0;
        for (int  i = bitArr.length - 1; i != -1; i--) {
            res = res * k + bitArr[i];
        }
        return res;
    }

    private static int[] from10ToK(int num, int k) {
        int[] numArr = new int[32];
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = num % k;
            num /= k;
        }
        return numArr;
    }

    public static void main(String[] args) {
        System.out.println(getOnceNum(new int[]{4,3,1,2,4,1,3,3,1,4}, 3));
    }
}
