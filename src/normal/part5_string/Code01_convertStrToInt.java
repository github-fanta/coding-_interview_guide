package normal.part5_string;

/**
 * 给定一个字符串str,如果str符合日常书写的整数形式，并且属于32位整数的范围，返回str所代表的整数值，否则返回0
 */
public class Code01_convertStrToInt {
    public static int convert(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        char[] chars = str.toCharArray();
        if (!isValid(chars)) {
            return 0;
        }
        boolean sign = chars[0] != '-';
        // 商
        int minq = Integer.MIN_VALUE / 10;
        // 余数
        int minr = Integer.MIN_VALUE % 10;

        int res = 0;
        int cur = 0;
        for (int i = sign ? 0 : 1; i < chars.length; i++) {
            cur = '0' - chars[i];
            if (res < minq || (res == minq) && cur < minr) {
                // 不能转
                return 0;
            }
            res = res * 10 + cur;
        }
        if (res == Integer.MIN_VALUE && sign) {
            // 是最负数时， 符号还是正号  那就刚好溢出了。
            return 0;
        }
        return sign ? -res : res;

    }

    private static boolean isValid(char[] chars) {
        if (chars[0] != '-') {
            if (chars[0] < '0' || chars[0] > '9') {
                return false;
            }
        }
        if (chars[0] == '-') {
            if (chars.length == 1 || chars[1] == '0') {
                return false;
            }
        }
        if (chars[0] == '0' && chars.length > 1) {
            return false;
        }
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int convert = convert("123");
        int convert2 = convert("023");
        int convert3 = convert("A13");
        int convert4 = convert("0");
        int convert5 = convert("2147483647");
        int convert6 = convert("2147483648");
        int convert7 = convert("-123");

    }
}
