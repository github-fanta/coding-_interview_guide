package normal.part7_bit_problem;

public class Code02_CountBitOne {

    public static int count1(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            // 无符号右移。无论是正数还是负数，高位通通补0
            n >>>= 1;
        }
        return res;
    }

    // 每次消除最后一个1   模型  xxxxxx100   xxxxxx011   相与后   消除掉最后一个1
    public static int count2(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);
            res ++;
        }
        return res;
    }

    // n& (~n + 1)   模型 xxxxxx100 取反 ^x^x^x^x^x^x011  n& ~n肯定都是0   但如果加了1  后面就又回去了 变成^x^x^x^x^x^x100 和 xxxxxx100  相与后
    // 剩下100 然后 n把它减掉  相当于减掉了最后一个1。
    public static int count3(int n) {
        int res = 0;
        while (n != 0) {
            n -= n & (~n + 1);
            res ++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(count1(7));
        System.out.println(count2(7));
        System.out.println(count3(7));
    }
}
