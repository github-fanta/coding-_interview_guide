package easy.part9_other;

/**
 * 给定两个不等于0的整数M和N，求M和N的最大公约数
 * 欧几里得在《几何原本》中提出的欧几里得算法，又称辗转相除法。
 * 具体做法：如果q和r分别是m/n的商及余数，即m = nq+r,那么m和n的最大公约数等于n和r的最大公约数。详细证明略。
 * 即 等于 “商和余数的最大公约数”。
 */
public class Code01_GCD {
    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, (m % n));
    }

    public static void main(String[] args) {
        System.out.println(gcd(48,13));
    }
}
