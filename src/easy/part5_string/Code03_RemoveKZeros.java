package easy.part5_string;

/**
 * 给定一个字符串str和一个整数k,如果str中正好又连续的k个'0'字符出现时，把k个连续的'0'字符去除，返回处理后的字符串。
 * 如str="A0000B000", k=3,返回"A0000B"
 * 时间：O（N）  空间：O（1）
 */
public class Code03_RemoveKZeros {

    public static String removeKZeros(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int eachPatternStart = -1;
        int eachPatternCount = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                // 碰到的是0 就继续统计
                eachPatternCount++;
                if (eachPatternStart == -1) eachPatternStart = i;
            } else {
                // 碰到的不是0 就结算
                if (eachPatternCount == k) {
                    while (eachPatternCount-- != 0) {
                        chars[eachPatternStart++] = 0; // 填坑
                    }
                }
                eachPatternStart = -1; // 从头开始准备
                eachPatternCount = 0;  // 如果0的数目不是k个的话，得清零
            }
        }
        // 结尾再检查
        if (eachPatternCount == k) {
            while (eachPatternCount-- != 0) {
                chars[eachPatternStart++] = 0;
            }
        }
        return String.valueOf(chars);
    }



    public static void main(String[] args) {
        System.out.println(removeKZeros("A0000B000",3));
    }
}
