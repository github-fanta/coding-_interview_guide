package easy.part5_string;

/**
 * 1.新类型字符是长度为1或者2的字符串
 * 2.表现形式：仅 一个小写字母 例如“e”；可以是 一个大写+一个小写 字母(如Ab) 可以是 一个大写+一个大写(如DC)
 * 现在给定一个字符串str,str一定是若干新类型字符正确组合的结果。比如"eaCCBi",由新类型字符"e","a","CC"和"Bi"拼成。
 * 再给定一个整数k,代表str中的位置。请返回被k位置指中的新类型字符。
 */
public class Code10_PointNewChar {
    public static String pointNewChar(String str, int k) {
        if (str == null || str.equals("") || k < 0 || k >= str.length()) {
            return "";
        }
        char[] chs = str.toCharArray();
        int successiveUpperCharNum = 0;
        // 统计k位置之前的 连续大写字符的个数  小写字母绝对是某个新类型字符的结尾。
        for (int i = k - 1; i >= 0; i--){
            if (!isUpper(chs[i])) break;
            successiveUpperCharNum++;
        }
        // 统计完成后开始分情况
        // 1.是奇数个  只有当前是一大一小才会出现奇数个连续大写的字符
        if ((successiveUpperCharNum & 1) == 1) {
            return str.substring(k-1, k+1); // [k-1~k]两个字符 k-1肯定是大写字符
        }
        // 2.是偶数  说明k前面的字符已经都是组合成的完美结果，一个新的新类型字符从k处开始
        return isUpper(chs[k]) ? str.substring(k, k+2) : String.valueOf(chs[k]);
    }

    private static boolean isUpper(char ch) {
        return ch >= 65 && ch <= 90;
    }

    public static void main(String[] args) {
        System.out.println(pointNewChar("aaABCDEcBCg", 10));
    }
}
