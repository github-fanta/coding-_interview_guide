package normal.part4_recursion_dynamic;

/**
 * @Author liq
 * @Date 2021/4/2
 *
 * 龙与地下城游戏问题:
 *  给定一个二位数组map,含义是一张地图。
 *  游戏的规则如下： 骑士从左上角出发，每次只能向右或向下走，最后到达右下角见到公主
 *
 *  地图中每个位置的值代表骑士要遭遇的事情。如果是负数，说明此处有怪兽，要让骑士损失血量。如果是非负数，代表此处有血瓶，能让骑士回血。
 *
 *  骑士从左上角到右下角的过程中，走到任何一个位置时，血量都不能少于1。
 */
public class Code04_DragonAndBlood {

    /**
     * 还是一种倒推的方式。
     * dp[i][j]: 如果骑士要走上位置(i, j) 并且从该位置选出一条最优的路径，最后走到右下角，骑士起码应该具备的血量。
     * 根据dp的定义，我们最终需要的是dp[0][0]的结果。以题目的例子来说，map[2][2]的值为-5, 所以骑士若要走上这个位置，需要6点血才能让自己不死。
     * 同时位置(2,2)已经是最右下角的位置，即没有后续的路径，所以dp[2][2]==6
     * @param m
     * @return
     */
    public static int minHP(int [][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 1;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        // 由目标推原因   先定义到达右下角的目标位置时，不死状态的最小血量   这里同时有几个约束：
        // 1） 负数/正数的困惑：  需要举例去想。如果是正数，那么我来到这里之后只需要1就行，如果是负数 那么先把负数变正 再 +1就是我活着需要的血量
        // 2）1的困扰： 需要最少是1才能活着，而不是0，也是Math.max(v, 1)一直在保障的。
        dp[row - 1][col - 1] = m[row - 1][col - 1] < 0 ? -m[row - 1][col - 1] + 1 : 1;
        // 最后一行： 从右到左
        for (int j = col - 2; j >= 0; j --) {
            dp[row - 1][j] = Math.max (dp[row - 1][j + 1] - m[row - 1][j + 1], 1);
        }
        // 最后一列： 从下到上
        for (int i = row - 2; i >= 0; i --) {
            dp[i][col - 1] = Math.max(dp[i + 1][col - 1] - m[i][col - 1], 1);
        }

        // 自底向上
        for (int i = row - 2; i >= 0; i --) {
            for (int j = col - 2; j >= 0; j --) {
                int ifToRight = Math.max(dp[i][j + 1] - m[i][j], 1);
                int ifToDown = Math.max(dp[i + 1][j] - m[i][j], 1);
                dp[i][j] = Math.min(ifToDown, ifToRight);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(minHP(new int[][]{{-2, -3, 3},{-5, -10, 1},{0, 30, -5}}));
    }
}
