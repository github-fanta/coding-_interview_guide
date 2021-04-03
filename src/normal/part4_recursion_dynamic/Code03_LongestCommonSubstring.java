package normal.part4_recursion_dynamic;

/**
 * @Author liq
 * @Date 2021/4/1
 */
public class Code03_LongestCommonSubstring {

    /**
     * dp[i][j] : 是s1[0..i]  s2[0..j]的最长公共子序列的长度。从左到右，再从上到下计算矩阵dp。
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int[][] longestCommonSubstring(char[] s1, char[] s2) {
        int[][] dp = new int[s1.length][s2.length];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        // 第一列： s2[0]的字符是否和s1[0..i]（s1的前i个字符） 有相同（相同部分最多就是1个字符）
        for (int i = 1; i < s1.length; i ++) {
            dp[i][0] = Math.max(dp[i - 1][0], s1[i] == s2[0] ? 1 : 0);
        }
        // 第一行： s1[0]的字符是否和s2[0..i](s2的前i个字符)  有相同
        for (int j = 1; j < s1.length; j ++) {
            dp[0][j] = Math.max(dp[0][j - 1], s1[j] == s2[j] ? 1 : 0);
        }
        for (int i = 1; i < s1.length; i ++) {
            for (int j = 1; j < s2.length; j ++) {
                // dp[i]p[j]只可能来自三个地方：
                //  str1 长一个字符  和前一个状态一样  OR  str2长一个字符，和前一个状态一样  OR s1和s2前面都完全匹配了，新增了一个字符也相等
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (s1[i] == s2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }
}
