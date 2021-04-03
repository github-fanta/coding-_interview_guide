package normal.part4_recursion_dynamic;

/**
 * 换钱的最少货币数
 * 给定数组arr,其中所有的值都是正数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，
 * 再给定一个整数aim代表要找的钱数，求组成aim的最少货币数
 * 如 arr=[5,2,3], aim=20   4张5元可以组成20元，其他的找钱方案都要使用更多张的货币，所以返回4
 * arr =[5,2,3], aim=0  不用任何货币就可以组成0元，返回0
 * arr=[3,5], aim= 2   无法组成2元，钱不能找开的情况下默认返回-1
 *
 *
 * 【进阶问题】
 * 每个面值只能使用一次呢？ 求组成aim的最小货币数
 */
public class Code02__ChangeMoneyLeastCount {


    public static int minCount(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        // 凑成0块钱 的 任何面值的最少张数都是0。
        for (int i = 0; i < arr.length; i ++) {
            dp[i][0] = 0;
        }
        // 第一行  前0个面值 想要凑成j块钱  需要的张数  （凑不成的 就都设为MAX）
        for (int j = 1; j <= aim; j ++) {
            dp[0][j] = Integer.MAX_VALUE;
            if (j - arr[0] >= 0 && dp[0][j - arr[0]] != Integer.MAX_VALUE) {
                dp[0][j] = dp[0][j - arr[0]] + 1;
            }
        }
        // 开始填表
        int left = 0;
        for (int i = 1; i < arr.length; i ++) {
            for (int j = 1; j <= aim; j ++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i][j - arr[i]] + 1;
                }
                // 还得对比前i- 1张面值凑成的 最小张数
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
            }
        }
        return dp[arr.length - 1][aim] != Integer.MAX_VALUE ? dp[arr.length - 1][aim] : -1;
    }

    public static void main(String[] args) {
        System.out.println(minCount(new int[]{5, 2, 3}, 20));
        System.out.println(minCount(new int[]{5, 2, 3}, 0));
        System.out.println(minCount(new int[]{5, 3}, 2));

        System.out.println("只用一次：-----------------------------");
        System.out.println(minCountUseOnce(new int[]{2,5,3}, 20));
        System.out.println(minCountUseOnce(new int[]{5,2,5,3}, 10));
        System.out.println(minCountUseOnce(new int[]{5,2,5,3}, 15));
        System.out.println(minCountUseOnce(new int[]{5,2,5,3}, 0));
    }

    // dp[i][j]: 在可以任意使用arr[0..i]货币的情况下(每个值仅代表一张货币)，组成j所需的最小的总张数。
    public static int minCountUseOnce (int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i ++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j <= aim; j ++) {
            if (j == arr[0]) {
                // 为什么这里是<=  不是== ？ 没想通
                if (arr[0] <= aim) {
                    dp[0][j] = 1;
                }
                continue;
            }
            dp[0][j] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < arr.length; i ++) {
            for (int j = 1; j <= aim; j ++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (j - arr[i] >= 0 && dp[i - 1][j - arr[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j - arr[i]] + 1;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
            }
        }

        return dp[arr.length - 1][aim] != Integer.MAX_VALUE ? dp[arr.length - 1][aim] : -1;
    }



}
