package easy.part5_string;

/**
 * 给定两个字符串，如果出现的字符种类一样且每一种字符出现的次数也一样，那么str1与str2互为变形词。
 * 实现函数判断两个字符串是否互为变形词
 */
public class Code01_IsDeformation {

    public static boolean isDeformation(String str1, String str2){
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[] buckets = new int[256];
        for (char c : chs1) {
            buckets[c]++;
        }
        for (char c : chs2) {
            if (--buckets[c] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isDeformation("a1b1c","ab11c"));
    }
}
