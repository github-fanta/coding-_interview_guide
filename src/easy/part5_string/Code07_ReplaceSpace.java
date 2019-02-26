package easy.part5_string;

/**
 * 给定一个字符数组chs[], 右半区全是空字符，左半区不含有空字符。现在想将左半区中所有的空格字符替换成"%20"
 * 假设chs右半区足够大，可以满足替换所需要的空间，请完成替换函数。
 * 【补充问题】
 * 给定字符数组chas[], 只含有数字字符和"*"字符。现在想把所有的"*"字符挪到chas的左边，数字字符挪到chas的右边。
 * 请完成调整函数。
 */
public class Code07_ReplaceSpace {
    public static String replace(char[] chs) {
        if (chs == null || chs.length == 0) {
            return "";
        }
        // 原字符串空格数目
        int spaceCount = 0;
        // 原字符长度
        int oldLen = 0;
        // 遍历算出原字符长度 和 空格个数
        while (oldLen < chs.length && chs[oldLen] != 0) {
            if (chs[oldLen] == ' ') {
                spaceCount ++;
            }
            oldLen ++;
        }

        // 新字符长度
        int newLen = oldLen + 2*spaceCount;
        // 新字符最后一个字符的下标
        int newEndIdx = newLen - 1;
        // 老字符最后一个字符的下标
        int oldEndIdx = oldLen - 1;
        // 从老字符串最后一个字符倒着遍历
        for(; oldEndIdx >= 0; oldEndIdx --) {
            // 碰到空格 就替换
            if (chs[oldEndIdx] == ' ') {
                chs[newEndIdx--] = '0';
                chs[newEndIdx--] = '2';
                chs[newEndIdx--] = '%';
            } else {
                chs[newEndIdx--] = chs[oldEndIdx];
            }
        }
        return String.valueOf(chs);
    }

    /**
     * 给定字符数组chas[], 只含有数字字符和"*"字符。现在想把所有的"*"字符挪到chas的左边，数字字符挪到chas的右边。
     * 请完成调整函数。
     * @param chas
     */
    public static String modify(char[] chas) {
        if (chas == null || chas.length == 0) {
            return "";
        }
        int chasEnd = chas.length - 1;
        int leftEnd = chas.length - 1;
        while (leftEnd >= 0) {
            // 数字字符挪动到右边
            if (chas[leftEnd] != '*') {
                chas[chasEnd--] = chas[leftEnd];
            }
            leftEnd --;
        }
        while(chasEnd >= 0) {
            chas[chasEnd--] = '*';
        }
        return String.valueOf(chas);
    }

    public static void main(String[] args) {
        char[] chs = new char[20];
          chs[0] = '*';
          chs[1] = '3';
          chs[2] = '*';
          chs[3] = '*';
          chs[4] = '8';
//        chs[0] = 'a';
//        chs[1] = ' ';
//        chs[2] = 'b';
//        chs[3] = ' ';
//        chs[4] = ' ';
//        chs[5] = 'c';
        System.out.println(replace(chs));
        System.out.println(modify(chs));
    }
}
