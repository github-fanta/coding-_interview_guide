package easy.part5_string;

/**
 * 题目一：给定字符数组chas，请在单词间做逆序调整。只要做到单词顺序逆序即可。
 *     chas:"dog loves pig" 调整为 "pig loves dog"。
 *     chas:"I'm a student." 调整为 "student. a I'm"。
 * 题目二：
 * 【补充题目】给定一个字符数组chas和一个整数size， 请把大小为size的左半区整体移动到右半区域
 */
public class Code08_RotateWord {
    // 题目1：单词整体逆序
    public static void rotateWord(char[] chs) {
        if (chs == null || chs.length == 0) {
            return;
        }

        // 1.先整体逆序
        reverse(chs, 0, chs.length - 1);
        // 2.局部逆序
        int left = -1;
        int right = -1;
        for (int i = 0; i < chs.length; i++) {
            // 遍历过程中标记
            if (chs[i] != ' ') {
                if (i == 0 || chs[i - 1] == ' ') left = i; // 标记每个单词的第一个字符
                if (i == chs.length - 1 || chs[i + 1] == ' ') right = i; // 标记每个单词的最后一个字符
            }
            // 检测是否锁定一个单词 若两个都已标记完成，说明锁定成功了一个单词
            if (left != -1 && right != -1) {
                reverse(chs, left, right);
                left = -1;
                right = -1;
            }
        }
    }

    private static void reverse(char[] chs, int left, int right) {
        char temp = 0;
        while (left < right) {
            temp = chs[left];
            chs[left] = chs[right];
            chs[right] = temp;
            left ++;
            right --;
        }
    }

    // 题目二：方法2.1：先把前半部分逆序，再后半部分逆序，再整体逆序
    public static void rotate1(char[] chs, int size) {
        if (chs == null || size <= 0 || size >= chs.length) {
            return;
        }
        reverse(chs, 0, size - 1);
        reverse(chs, size, chs.length - 1);
        reverse(chs, 0, chs.length - 1);
    }
    // 题目二：方法2.2：每次只交换左右部分少的一块
    public static void rotate2(char[] chs, int size) {
        if (chs == null || size <= 0 || size >= chs.length) {
            return;
        }
        int start = 0;
        int end = chs.length - 1;
        int leftCount = size;
        int rightCount = chs.length - size;

        int minPartCount = Math.min(leftCount, rightCount);
        int d = leftCount - rightCount;
        while (true) {
            exchange(chs, start, end, minPartCount);
            if (d == 0) {
                break;
            } else if (d < 0) { //左边少
                end -= minPartCount; // 少部分移动到右边后固定好，右界限往左边走
                rightCount = -d; // 新的右半部分刚好是左右部分差出来的个数(d是负值)
            } else {
                start += minPartCount; // 少部分移动到左边后固定好，左界限往右走
                leftCount = d; // 新的左半部分刚好是左右部分差出来的个数
            }
            minPartCount = Math.min(leftCount, rightCount);
            d = leftCount - rightCount;
        }
    }

    // 前后交换minCount个字符块大小
    private static void exchange(char[] chs, int start, int end, int size) {
        int rightBegin = end - size + 1;
        char temp = 0;
        while (size-- > 0) {
            // 交换
            temp = chs[start];
            chs[start] = chs[rightBegin];
            chs[rightBegin] = temp;
            start++;
            rightBegin++;
        }
    }

    public static void main(String[] args) {
        //char[] chs = "pig Loves dog".toCharArray();
        char[] chs = "I'm a student.".toCharArray();
        rotateWord(chs);
        System.out.println(String.valueOf(chs));
        rotate2(chs, 3);
        System.out.println(String.valueOf(chs));

    }
}
