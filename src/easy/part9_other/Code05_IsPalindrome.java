package easy.part9_other;

/**
 * 定义回文数的概念如下：
 * 如果一个非负数左右完全对应，则该数是回文数，例如：1221,22等。
 * 如果一个负数的绝对值左右完全对应，也是回文数，例如：-121，-22等。
 * 给定一个32位整数num，判断num是否是回文数。
 */
public class Code05_IsPalindrome {
    public static boolean isPalindrome(int n){
        if (n == Integer.MIN_VALUE) return false;
        if (n < 0) n = -n;
        int help = 1;
        while (n/help >= 10) {
            help *= 10;
        }
        while (n != 0) {
            if (n / help != n % 10) return false;
            n = (n % help) / 10; // “掐头去尾”
            help /= 100; // 上面"掐头去尾" 收缩了两位，所有help与n位数保持一致。
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(-13231));
    }
}
