package easy.part5_string;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，求其中所有数字串代表的数字之和
 * 时间：O（N）  空间：O（1）
 */
public class Code02_SumNumInString {
    public static int sumNum(String str) {
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
                if (i >= 1 && isNum(chars[i - 1])) { // 前一个是数字 有可能是连续的字母或者-号
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
//        System.out.println(sumNum("a-1b--2c--d6"));
//        System.out.println(sumNum("a--1h---12"));
//        maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
//        groupAnagrams(new String[]{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"});
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        Map<Character, int[]> oriMap = new HashMap();
        for (char c : t.toCharArray()) {
            if (oriMap.get(c) == null) {
                oriMap.put(c, new int[2]);
            }
            oriMap.get(c)[0]++;
        }

        int left = 0;
        int resMinLen = Integer.MAX_VALUE;
        int minleftIdx = -1;
        int minRightIdx = -1;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (oriMap.containsKey(ch)) {
                oriMap.get(ch)[1]++;
            }
            // 收缩左
            while (containsAll(oriMap) && left <= right) {
                if (right - left + 1 < resMinLen) {
                    resMinLen = right - left + 1;
                    minleftIdx = left;
                    minRightIdx = right + 1;
                }
                // 先去掉当前的char的计数
                if (oriMap.containsKey(s.charAt(left))) {
                    oriMap.get(s.charAt(left))[1]--;
                }
                left++;
            }
        }
        return minleftIdx == -1 ? "" : s.substring(minleftIdx, minRightIdx);
    }

    private static boolean containsAll(Map<Character, int[]> map) {
        for (Character ch : map.keySet()) {
            int[] cntArr = map.get(ch);
            if (cntArr[0] > cntArr[1]) {
                return false;
            }
        }
        return true;
    }
}
