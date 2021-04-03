package normal.part4_recursion_dynamic;

/**
 * @Author liq
 * @Date 2021/4/2
 *
 * 排成一条线的纸牌博弈问题
 *  给定一个整数数组arr,代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿，
 *  但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B都决定聪明。请返回最后获胜者的分数。
 *
 *  arr=[1,2,100,4] 开始时玩家A只能拿走1或4.如果玩家A拿走1，则排列变为[2,100,4],
 *  接下来玩家B可以拿走1或者100，然后继续轮到玩家A。玩家A作为决定聪明的人不会先拿4，因为拿4之后，玩家B将拿走100.所以玩家A会先拿1，
 *  让排列变为[2， 100， 4]， 接下来玩家B不管怎么选，100都会被玩家A拿走。玩家A会获胜 分数为101. 所以返回101。
 *  arr=[1,100,2]。
 *  开始时玩家A不管拿1还是2，玩家B作为绝顶聪明的人，都会把100拿走。玩家B会获胜，分数为100.所以返回100.
 *
 */
public class Code06_CardLineTakeContest {

    /**
     * 方法一： 递归实现：
     * f(i, j) : [i..j] 范围的纸牌，我先拿，我最终获得的分数
     * s(i, j) : [i..j] 范围的纸牌，对手先拿，我最终获得分数
     *
     * 分析f(i, j)
     *      1）如果i==j   只剩一张，我拿了就返回 arr[i];
     *      2）如果i!=j   我要么拿arr[i]， 要么拿arr[j]。
     *                                  若拿i,排列剩下[i+1..j] 对剩下的序列 我成了后拿的，后续获得分数为：s(i+1, j)
     *                                  若拿j, 剩下[i, j-1].后续获得分数为：s(i, j-1)
     *                   我最终获得：max{arr[i]+s(i+1, j), arr[j]+s(i, j-1)};
     *
     * 分析s(i, j)
     *      1) i==j,剩1张，对手先拿，我啥也拿不到 返回0
     *      2) i!=j， 对手要么拿i,要么拿j.
     *          对手拿i, 剩下[i+1..j] 我拿f(i+1, j)
     *          对手拿j,剩下[i..j-1] 我拿f(i, j-1)
     *      对手是决定聪明，肯定会把分数少的留给我。min(f(i+1, j), f(i, j-1));
     *
     * 递归函数一共有N层，并且是f和s交替出现的。f(i, j)会有s(i+1, j) 和 s(i, j-1)两个递归分支，s(i, j)也会有f(i+1, j)和f(i, j-1)两个
     * 递归分支。最终为O(2^N)。空间复杂度O(N)。
     */
    public static int takeCardRecursion(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    private static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    private static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        // 对手先拿，他拿了，我本次收益：0分，就只看后续的f过程就是我最终拿到的分数了。
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }

    public static void main(String[] args) {
        System.out.println(takeCardRecursion(new int[]{1, 100, 2}));
        System.out.println(takeCard(new int[]{1, 100, 2}));
    }


    /**
     * 方法2：动态规划
     *
     *
     */
    public static int takeCard(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // f函数表
        int[][] f = new int[arr.length][arr.length];
        // s函数表
        int[][] s = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            f[i][i] = arr[i];
        }

        for(int j = 0; j < arr.length; j ++) {
            for (int i = j - 1; i >= 0; i --) {
                // 1.我取i,和{i+1,j}这段上我的收益； 2.我取j加上{i, j - 1}这段上我的收益
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                // 对手先取时，我的收益。
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }


}
