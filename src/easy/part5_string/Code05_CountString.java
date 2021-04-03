package easy.part5_string;

/**
 * 题目一：给定一个字符串str, 返回str的统计字符串。例如，"aaabbadddffc"的统计字符串为"a_3_b_2_a_1_d_3_f_2_c_1"
 * 补充题目，
 * 题目二：给定一个字符串的统计字符串cstr,再给定一个整数index,返回cstr所代表的原始字符串上的第index个字符。例如，
 * "a_1_b_100"所代表的原始字符串上第0个字符是'a'，第50个字符是'b'。
 */
public class Code05_CountString {
    public static String getCountString(String str) {
        if (str == null || str.length() == 0) return str;
        char[] chs = str.toCharArray();
        int count = 1;
        String res = chs[0]+"";
        for (int i = 1; i < chs.length; i++) {
            if (chs[i] == chs[i - 1]) {
                count ++;
            } else {
                // 不相等时要结算
                res += "_" + count + "_" + chs[i];
                count = 1;
            }
        }
        return res + "_" + count;
    }

    /*
     * 题目二：给定一个字符串的统计字符串cstr,再给定一个整数index,返回cstr所代表的原始字符串上的第index个字符。例如，
     * "a_1_b_100"所代表的原始字符串上第0个字符是'a'，第50个字符是'b'。
     */
    public static char getCharAt(String cstr, int index) {
        if (cstr == null || cstr.length() == 0 || index < 0 || index > cstr.length() - 1) return 0;
        char[] chs = cstr.toCharArray();
        char stageChar = 0; // 上个阶段的字符
        int stageNum = 0;     // 上阶段字符的个数
        boolean charStage = true; // 字符阶段/数字字符阶段
        int curIdx = 0;  //用来判断当前是否到达给定index
        for (int i = 0; i < chs.length; i++) {
            // 1. 遇到'_'
            if (chs[i] == '_') {
                charStage = !charStage;
            } else if (charStage) {
                // 2. 遇到新字符 且是字符阶段 说明当前要读的又是一个新的字符了，刚好结算上个阶段的
                curIdx += stageNum;
                if (curIdx > index) return stageChar;
                // 否则 就恢复原位 从这个字符开始 又是一个新阶段
                stageNum = 0;
                stageChar = chs[i];
            } else if (!charStage) {
                // 3. 在数字阶段遇到字符，这个字符要读的就是一个数字
                stageNum = stageNum*10 +(chs[i] - '0');
            }
        }
        // 最后一个没有字符来结算。要再手动判断一次
        return curIdx + stageNum > index ? stageChar : 0;


    }

    public static void main(String[] args) {
//        System.out.println(getCountString("aaabbadddffc")); //a_3_b_2_a_1_d_3_f_2_c_1
        System.out.println(getCharAt("a_3_b_2_a_1_d_3_f_2_c_1", 5));
    }
}
