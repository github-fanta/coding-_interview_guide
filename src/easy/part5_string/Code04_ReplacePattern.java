package easy.part5_string;
/**
 * 给定三个字符串str, from和to，已知from字符串中无重复字符，把str中所有from的子串全部替换成to字符串，
 * 对连续出现from的部分要求只替换成一个to字符串，
 * 返回最终的结果字符串
 *
 * 注意分析：由于from中没有重复的字符串，代表着没有这种可能：匹配到str的x位置时不同(已经匹配到了x位置，说明0位置的字符相等)，str还有0~x的某个位置开始能匹配的上from。
 * (因为如果相等，那么str[0~x的某个位置] == from[0] == str[0]，显然str[0~x某个位置]和str[0]相同了，而题目说不重复)，
 *
 */
public class Code04_ReplacePattern {
    static String str = "hello";
    public static String replacePattern(String str, String from, String to) {
        if (str == null || str.length() == 0 || str.length() < from.length()) {
            return str;
        }

        // from字符串match到的位置
        int fromMatch = 0;
        char[] charStr = str.toCharArray();
        char[] charFrom = from.toCharArray();

        // 步骤一： 标记from的pattern字符串置为0
        for (int i = 0; i < charStr.length; i++) {
            // 匹配
            if (charStr[i] == charFrom[fromMatch]) {
                if (fromMatch == charFrom.length-1) {   // 若匹配到from的最后一个字符，说明匹配到一个from这样的pattern
                    clear(charStr, i, charFrom.length); // 从i开始向后清理from个字符的0
                    fromMatch = 0;
                }
                // from匹配字符加一
                fromMatch++;
            } else {
             // 不匹配
                // 但匹配上了charFrom的开头，保持i不动 (for循环结束i要加一，所以此处减个一)
                if (charStr[i] == charFrom[0]) {
                    i--;
                }
                // 不匹配 from从0开始
                fromMatch = 0;
            }
        }

        String curStr = ""; // 一段连续的不为0的字符串
        String result = ""; // 总的不为0的字符串

        // 步骤二： 取出不为0的字符
        for (int i = 0; i < charStr.length; i++) {

            if (charStr[i] != 0) {
                if (i > 0 && charStr[i - 1] == 0) {  // 上台阶型(非零的前一个是0) 结算
                    result += curStr + to;
                    curStr = "";  // 小簸箕清0
                }
                curStr += charStr[i]; //平台型 收揽
            }
            // 遇到的是零 无动于衷 继续往前走
        }

        // 最后揽收的字符们还没有添加到结果（小心还要结算一次）
        if (!curStr.equals("")) {
            result += charStr[charStr.length - 1] == 0 ? curStr + to : curStr;
//            if (charStr[charStr.length - 1] == 0) {  //str最后一个是0，结算成一个to
//                result += curStr + to;
//            } else { // 否则只加上最后的小簸箕残余
//                result += curStr;
//            }
        }

        return result;
    }

    private static void clear(char[] charStr, int endIdx, int backLen) {
        if (endIdx < 0 || endIdx > charStr.length) return;
        while (backLen-- != 0) {
            charStr[endIdx--] = 0;
        }
    }



    /**
     * 给定三个字符串str, from和to，已知from字符串中无重复字符，把str中所有from的子串全部替换成to字符串，
     * 对连续出现from的部分要求只替换成一个to字符串，
     * 返回最终的结果字符串
     *
     * 注意分析：由于from中没有重复的字符串，代表着没有这种可能：匹配到str的x位置时不同(已经匹配到了x位置，说明0位置的字符相等)，str还有0~x的某个位置开始能匹配的上from。
     * (因为如果相等，那么str[0~x的某个位置] == from[0] == str[0]，显然str[0~x某个位置]和str[0]相同了，而题目说不重复)，
     *
     */
    public static String replacePattern2(String str, String from, String to) {
        if (str == null || str.length() == 0 || from == null || to == null) {
            return str;
        }
        char[] strChars = str.toCharArray();
        char[] fromChars = from.toCharArray();
        int matchIdx = 0;
        for (int i = 0; i < strChars.length; i++) {
            if (strChars[i] == fromChars[matchIdx]) {
                if (matchIdx == fromChars.length - 1) {
                    clear2(strChars, i, fromChars.length);
                    matchIdx = 0;
                    continue;
                }
                matchIdx ++;
            } else {
                // 未匹配上
                matchIdx = 0;
            }
        }

        String curStr = "";
        for (int i = 0; i < strChars.length; i++) {
            if (strChars[i] != 0) {
                if (i >= 1 && strChars[i - 1] == 0) {
                    // 前一个是0
                    curStr += to;
                }
                curStr += strChars[i];
            }
        }
        if (strChars[strChars.length - 1] == 0) {
            // 最后校验最后的
            curStr += to;
        }
        return curStr;
    }

    private static void clear2(char[] strChars, int idx, int backLen) {
        if (idx < 0 || idx > strChars.length - 1) {
            return;
        }
        while (backLen -- > 0) {
            strChars[idx --] = 0;
        }
    }


    public static void main(String[] args) {
//        System.out.println(replacePattern("abc123abc123abcabc", "abc", "X"));
//        System.out.println(replacePattern2("abc123abc123abcabc", "abc", "X"));
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j ++;
        }
        Code04_ReplacePattern bean = new Code04_ReplacePattern();
        bean  = null;
        bean.toString();
    }
}
