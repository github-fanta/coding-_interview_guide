package easy.part5_string;

/**
 * 给定一个字符串，求其中所有数字串代表的数字之和
 * 时间：O（N）  空间：O（1）
 */
public class Code02_SumNumInString {
    public static int sumNum(String str){
        if (str == null) {
            return 0;
        }
        int sum = 0;
        int curNum = 0;
        int num = 0;
        // 默认是正
        int fuhao = 1;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 当前是数字
            if (isNum(chars[i])) {
                num = num * 10 + fuhao * (chars[i] - '0');
            } else {
                // 当前不是数字 若是符号进行保留
                // 当前是字符且前一个是数字才结算，否则只处理当前的符号(如果是符号的话)，连符号都不是就不用管了。
                if (i >= 1 && isNum(chars[i-1])){ // 前一个是数字 有可能是连续的字母或者-号
                    sum += num;
                    num = 0;
                    fuhao = 1;
                }

                // 处理符号
                if (chars[i] == '-') {
                    // 当前是第多个连续的-
                    //fuhao = (i >= 1 && chars[i - 1] == '-') ? -fuhao : -1;
//                    if (i >= 1 && chars[i - 1] == '-') {
//                        fuhao = -fuhao;
//                    } else {
//                        // 当前是第一个-
//                        fuhao = -1;
//                    }
                    fuhao = -fuhao;
                }
            }
        }
        // 末尾没有遇到字母结算，要多加一次
        sum += num;

        return sum;
    }

    private static boolean isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static void main(String[] args) {
        System.out.println(sumNum("a-1b--2c--d6"));
        System.out.println(sumNum("a--1h---12"));
    }
}
