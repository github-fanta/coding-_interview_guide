package normal.part7_bit_problem;

public class Code03_OddTimesNum {

    /**
     * 给定一个整形数组arr, 其中只有一个数出现了奇数次，其他的数都出现了偶数次，打印这个数。
     * @param arr
     */
    public static void printOddTimesNum(int[] arr) {
        int e = 0;
        for (int cur : arr) {
            e ^= cur;
        }
        System.out.println(e);
    }

    /**
     * 有两个数出现了奇数次，其它的数都出现了偶数次，打印这两个数。
     * @param arr
     */
    public static void printOddTimeNums(int[] arr) {
        int e = 0;
        int eOne = 0;
        for (int cur : arr) {
            e ^= cur;
        }

        int rightOne = e & (~e + 1);
        for (int cur : arr) {
            if ((cur & rightOne) != 0) {
                eOne ^= cur;
            }
        }
        System.out.println(eOne + " " + (e^eOne));
    }

    public static void main(String[] args) {
        printOddTimesNum(new int[]{2,3,4,2,4,5,5});
        printOddTimeNums(new int[]{2,1,3,2,3,5});
    }
}
