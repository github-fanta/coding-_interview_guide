package normal.part5_string;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串str, 返回str的最长无重复字符子串的长度
 * 如果str的长度是N, 请实现时间复杂度为O(N)的方法
 */
public class Code05_MaxLenOfNoRepeatStr {
    public static int maxLen(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        Map<Character, Integer> charIdxMap = new HashMap<>();
        for (char ch : chars) {
            charIdxMap.put(ch, -1);
        }
        int resMaxLen = 0;
        int wholePreIdx = -1;
        for (int i = 0; i < chars.length; i++) {
            // 该字符之前最近出现的位置
            Integer preIdx = charIdxMap.get(chars[i]);
            // wholePreIdx一旦增大  就不能减小了。如 串： a b(pre指针在这) b a(i指针在这)  如果直接用map('a') (而不是用max比较wholePreIdx)指向第一个a
            // 那么这两个a的len就肯定包含了两个重复b之间的那一段了。
            // pre尽量保持-1 那i-pre是最长的  但若有再次遇到某字符，pre就得移动到此字符上次出现的位置，准备做统计。
            if (preIdx > wholePreIdx) {
                wholePreIdx = preIdx;
            }
            // 计算
            int distance = i - wholePreIdx;
            // 更新
            resMaxLen = Math.max(resMaxLen, distance);
            // 未来铺路
            charIdxMap.put(chars[i], i);
        }
        return resMaxLen;
    }

    public static void main(String[] args) {
        // acdbfg
        System.out.println(maxLen("acdbfgcda"));
        // abcd
        System.out.println(maxLen("abcd"));
        // abc
        System.out.println(maxLen("aabcb"));
    }
}
